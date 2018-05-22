package com.whl.mall.core.configura.rabbitmq.listeners;
/**
 * @Title: ResourceMessageListener
 * @Package: com.whl.mall.core.configura.rabbitmq.listeners
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-22 下午 8:50
 * @Version: V2.0.0
 */

import com.whl.mall.core.configura.rabbitmq.constants.RabbitConstants;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ResourceMessageListener
 * @Description: 资源消息监听器，使用注解方式监听，最简单
 * @Author: WangHongLin
 * @Date: 2018-05-22 下午 8:50
 */
@Component
public class ResourceMessageListener {

    /**
     * 消费队列为resources.queue的消息
     * @param data
     */
    @RabbitListener(queues = RabbitConstants.RESOURCES_QUEUE_NAME)
    public void onResourceMessage(String data) {

    }

    /**
     * 使用属性占位符和SpEL来外化队列名称
     * @param data
     */
    @RabbitListener(queues = "#{'${property.with.comma.delimited.queue.names}'.split(',')}")
    public void onMessageTest1(String data) {

    }

    /**
     * 队列将与交易所一起自动（持续）声明，并使用路由键绑定到交易所,快捷申明
     * @param data
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "myQueue", durable = "true"),
            exchange = @Exchange(value = "auto.exch", ignoreDeclarationExceptions = "true", type = "topic"),
            key = "orderRoutingKey")
    )
    public void onMessageTest2(String data) {

    }

    /**
     * 在@QueueBinding批注，交换和绑定的注释中指定参数
     * @param data
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "myQueue", durable = "true"),
            exchange = @Exchange(value = "auto.exch", ignoreDeclarationExceptions = "true", type = "topic"),
            key = "orderRoutingKey")
    )
    public void onMessageTest3(String data) {

    }


}
