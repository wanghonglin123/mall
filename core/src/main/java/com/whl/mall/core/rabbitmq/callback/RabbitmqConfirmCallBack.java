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

    /**
     * 发布消息确认回调，如果发送Broker成功，那么Broker会返回true, 发送失败会返回false 和原因
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // 在发送消息前将消息存入数据库日志
        if (!ack) {
            log4jLog.error(String.format("发布消息失败，correlationData=%s, ack=%s, cause=%s", correlationData, ack, cause));
        } else {
            log4jLog.debug(String.format("发布消息成功，correlationData=%s", correlationData));
        }
    }
}
