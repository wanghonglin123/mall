package com.whl.mall.core.rabbitmq.callback;
/**
 * @Title: RabbitMQReturnCallBack
 * @Package: com.whl.mall.core.configura.rabbitMQ
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 6:40
 * @Version: V2.0.0
 */

import com.whl.mall.core.log.MallLog4jLog;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: RabbitMQReturnCallBack
 * @Description: 发布商返回回调，当没有交换机，队列，绑定的时候，会触发该回掉函数
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 6:40
 */
public class RabbitmqReturnCallBack implements RabbitTemplate.ReturnCallback{
    @Autowired
    private MallLog4jLog log4jLog;

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText,
                                String exchange, String routingKey) {
        log4jLog.warn(String.format("消息发送失败，消息被返回，Meseage=%s, replyCode=%s, replyText=%s, exchange=%s, routingKey=%s",
                message, replyCode, replyText, exchange, routingKey));

        // // 这里消息被返回的时候需要更新事务表状态
    }
}
