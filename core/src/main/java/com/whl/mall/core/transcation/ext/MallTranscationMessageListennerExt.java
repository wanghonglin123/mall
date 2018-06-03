package com.whl.mall.core.transcation.ext;
/**
 * @Title: MallTranscationMessageListennerExt
 * @Package: com.whl.mall.core.base.pojo
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-06-03 上午 12:24
 * @Version: V2.0.0
 */

import com.whl.mall.core.MallTranscationException;
import com.whl.mall.core.common.constants.MallNumberConstants;
import com.whl.mall.core.common.constants.MallStatus;
import com.whl.mall.core.transcation.common.constants.TranscationContants;
import com.whl.mall.core.spring.SpringContentUtils;
import com.whl.mall.core.transcation.pojo.Transcation;
import com.whl.mall.core.transcation.service.TranscationService;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MethodInvoker;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

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

    private static final String ENEM_METHOD_NAME_GETTARGETMETHODS = "getTargetMethods";
    private static final String ENEM_METHOD_NAME_GETTARGETBEANNAME = "getTargetBeanName";
    private ConcurrentHashMap<String, Object> currentTranscationMap = new ConcurrentHashMap();

    @Override
    public void handleMessage(Object messageBody, MessageProperties properties) throws Exception{
        Map<String, Object> headers = properties.getHeaders();
        Object value = headers.get(TranscationContants.TRANSCATION_ENUMS_KEY);
        if (ObjectUtils.isEmpty(value)) {
            throw new MallTranscationException(MallStatus.HTTP_STATUS_500, "transcation properties is null");
        }
        if (! (value instanceof Enum[]) ) {
            throw new MallTranscationException(MallStatus.HTTP_STATUS_500, "value must is Enum[]");
        }

        Long transcationIdx = Long.valueOf(messageBody.toString());
        Transcation transcation = new Transcation();
        transcation.setIdx(transcationIdx);
        transcation = transcationService.queryOneAllInfoByCondition(transcation);
        if (transcation == null) {
            throw new MallTranscationException(MallStatus.HTTP_STATUS_500, "事物参数非法");
        }
        Enum[] enums = (Enum[]) value;
        final StringBuilder builder = new StringBuilder();
        for (Enum transcatioEnum : enums) {
            final Enum targetEnum = transcatioEnum;
            CompletableFuture.runAsync(() -> {
                Class classes = targetEnum.getClass();
                Method method = null;
                String beanName = null;
                try {
                    method = classes.getDeclaredMethod(ENEM_METHOD_NAME_GETTARGETBEANNAME);
                    beanName = (String) method.invoke(targetEnum);
                    method = classes.getDeclaredMethod(ENEM_METHOD_NAME_GETTARGETMETHODS);
                    String[] methodNames = (String[]) method.invoke(targetEnum);
                    Object bean = SpringContentUtils.getBean(beanName);
                    MethodInvoker methodInvoker = new MethodInvoker();
                    methodInvoker.setTargetClass(bean.getClass());
                    Object[] args = new Object[]{messageBody};
                    methodInvoker.setArguments(args);
                    for (String methodName : methodNames) {
                        methodInvoker.setTargetMethod(methodName);
                    }
                } catch (Exception e) {
                    getLog4jLog().error(e, String.format("子事物服务=%s, 执行失败", beanName));
                    builder.append(e.getMessage());
                }
            });
        }

        // 如果子事物多成功，那么更改主事物状态
        if (builder.toString().length() == 0) {
            transcation.setTranscationStatust(MallNumberConstants.ONE);
            transcationService.update(transcation);
        }
    }

}
