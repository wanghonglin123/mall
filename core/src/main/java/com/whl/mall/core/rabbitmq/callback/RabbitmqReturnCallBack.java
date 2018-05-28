package com.whl.mall.core.rabbitmq.callback;
/**
 * @Title: RabbitMQReturnCallBack
 * @Package: com.whl.mall.core.configura.rabbitMQ
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 6:40
 * @Version: V2.0.0
 */

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @ClassName: RabbitMQReturnCallBack
 * @Description: 发布商返回回调，当没有交换机，队列，绑定的时候，会触发该回掉函数
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 6:40
 */
public class RabbitmqReturnCallBack implements RabbitTemplate.ReturnCallback{
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText,
                                String exchange, String routingKey) {
        System.out.println("返回的消息");
    }
}
