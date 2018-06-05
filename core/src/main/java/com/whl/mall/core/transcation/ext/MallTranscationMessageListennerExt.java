package com.whl.mall.core.transcation.ext;
/**
 * @Title: MallTranscationMessageListennerExt
 * @Package: com.whl.mall.core.base.pojo
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-06-03 上午 12:24
 * @Version: V2.0.0
 */

import com.whl.mall.core.MallException;
import com.whl.mall.core.MallTranscationException;
import com.whl.mall.core.common.constants.MallNumberConstants;
import com.whl.mall.core.common.constants.MallStatus;
import com.whl.mall.core.common.utils.MallThreadPollUtils;
import com.whl.mall.core.spring.SpringContentUtils;
import com.whl.mall.core.transcation.base.TranscationEnum;
import com.whl.mall.core.transcation.common.constants.TranscationContants;
import com.whl.mall.core.transcation.common.enums.RoleTranscationPropertiesEnum;
import com.whl.mall.core.transcation.pojo.Transcation;
import com.whl.mall.core.transcation.service.TranscationService;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MethodInvoker;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: MallTranscationMessageListennerExt
 * @Description: 事物消息处理，专门用于处理分布式多库，多服务事物，如果是db + es 服务，请扩展消息监听接口
 * @Author: WangHongLin
 * @Date: 2018-06-03 上午 12:24
 */
public abstract class MallTranscationMessageListennerExt extends MallMessageListenerExt {
    /**
     * 事物服务
     */
    @Autowired
    private TranscationService transcationService;

    @Override
    public void handleMessage(Object messageBody, MessageProperties properties) throws MallTranscationException {
        Object value = validationParameters(properties);
        try {
            Transcation transcation = getCurrentTranscation(messageBody);

            List<String> enumList = (List<String>) value;
            Class transcationClass = RoleTranscationPropertiesEnum.class;
            StringBuilder transcationBulder = enumList.parallelStream().collect(StringBuilder::new, (stringBuilder, enumName) -> {
                TranscationEnum targetEnum = (TranscationEnum) Enum.valueOf(transcationClass, enumName);
                String beanName = null;
                try {
                    beanName = targetEnum.getTranscationTargetBeanName();
                    String methodName = targetEnum.getTranscationTargetMethodName();
                    Object bean = SpringContentUtils.getBean(beanName);
                    MethodInvoker methodInvoker = new MethodInvoker();
                    methodInvoker.setTargetClass(bean.getClass());
                    methodInvoker.setArguments(messageBody);
                    methodInvoker.setTargetMethod(methodName);

                } catch (Exception e) {
                    getLog4jLog().error(e, String.format("子事物服务=%s, 执行失败", beanName));
                    stringBuilder.append(e.getMessage());
                }
            }, StringBuilder::append);

            // 修改主事物表
            if (transcationBulder.length() >= MallNumberConstants.ZERO) {
                transcation.setTranscationStatust(MallNumberConstants.ONE);
            } else { // 事物失败
                transcation.setTranscationStatust(MallNumberConstants.TWO);
            }
            transcationService.update(transcation);
        } catch (Exception e) {
            getLog4jLog().error(e, String.format("主事物修改状态失败"));
            throw new MallTranscationException(e);
        }
    }

    /**
     * 获取当前操作事务
     *
     * @param messageBody
     * @return
     * @throws MallException
     */
    private Transcation getCurrentTranscation(Object messageBody) throws MallException {
        Long transcationIdx = Long.valueOf(messageBody.toString());
        Transcation transcation = new Transcation();
        transcation.setIdx(transcationIdx);
        transcation = transcationService.queryOneAllInfoByCondition(transcation);
        if (transcation == null) {
            throw new MallTranscationException(MallStatus.HTTP_STATUS_500, "事物参数非法");
        }
        return transcation;
    }

    /**
     * 验证参数
     * @param properties 消息配置
     * @return Object 结果
     * @throws MallTranscationException
     */
    private Object validationParameters(MessageProperties properties) throws MallTranscationException {
        Map<String, Object> headers = properties.getHeaders();
        Object value = headers.get(TranscationContants.TRANSCATION_ENUMS_KEY);
        if (ObjectUtils.isEmpty(value)) {
            throw new MallTranscationException(MallStatus.HTTP_STATUS_500, "transcation properties is null");
        }
        if (!(value instanceof List)) {
            throw new MallTranscationException(MallStatus.HTTP_STATUS_500, "value must be a Collection");
        }
        return value;
    }
}
