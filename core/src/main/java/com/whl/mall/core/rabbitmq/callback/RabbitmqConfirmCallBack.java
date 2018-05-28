package com.whl.mall.core.rabbitmq.callback;
/**
 * @Title: RabbitMQConfirmCallBack
 * @Package: com.whl.mall.core.configura.rabbitMQ
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 6:39
 * @Version: V2.0.0
 */

import com.whl.mall.core.log.MallLog4jLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

/**
 * @ClassName: RabbitMQConfirmCallBack
 * @Description: 发布商确认回调
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 6:39
 */
public class RabbitmqConfirmCallBack implements RabbitTemplate.ConfirmCallback{
    @Autowired
    private MallLog4jLog log4jLog;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (StringUtils.isNotEmpty(cause)) {
            log4jLog.error(String.format("发布商发布消息异常, msg=%s", cause));
        } else {
            if (ack && ObjectUtils.isEmpty(correlationData)) {
                log4jLog.info(String.format("发布商发布消息成功, 自动确认模式 correlationData=%s", correlationData));
            } else if(ack && !ObjectUtils.isEmpty(correlationData)){
                log4jLog.info(String.format("发布商发布消息成功, 手动确认模式 correlationData=%s", correlationData));
            } else {
                log4jLog.warn(String.format("发布商发布消息失败, correlationData=%s", correlationData));
            }
        }
    }
}
