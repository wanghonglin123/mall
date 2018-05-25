package com.whl.mall.core.configura.rabbitmq.callback;
/**
 * @Title: RabbitMQConfirmCallBack
 * @Package: com.whl.mall.core.configura.rabbitMQ
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 6:39
 * @Version: V2.0.0
 */

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * @ClassName: RabbitMQConfirmCallBack
 * @Description: 发布商确认回调
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 6:39
 */
public class RabbitmqConfirmCallBack implements RabbitTemplate.ConfirmCallback{
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("执行确认");
    }
}
