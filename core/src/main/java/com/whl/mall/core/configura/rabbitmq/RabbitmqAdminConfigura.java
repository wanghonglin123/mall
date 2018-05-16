package com.whl.mall.core.configura.rabbitmq;
/**
 * @Title: MallRabbitMQConfigura
 * @Package: com.whl.mall.core.configura.rabbitMQ
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-05 下午 10:35
 * @Version: V2.0.0
 */

import com.rabbitmq.client.AMQP;
import com.whl.mall.core.common.beans.MallBeans;
import com.whl.mall.core.common.pojo.MallThreadFactory;
import com.whl.mall.core.configura.rabbitmq.propeties.MallRabbitMQProperties;
import com.whl.mall.core.configura.rabbitmq.listeners.RabbitMQChannelListenner;
import com.whl.mall.core.configura.rabbitmq.listeners.RabbitMQCollectionListener;
import com.whl.mall.core.configura.rabbitmq.listeners.RabbitRecoveryListener;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ChannelListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * @ClassName: MallRabbitMQConfigura
 * @Description: Rabbitmq admin 配置
 * @Author: WangHongLin
 * @Date: 2018-05-05 下午 10:35
 */
//@Configuration
public class RabbitmqAdminConfigura extends MallBeans {
    @Autowired
    private ConnectionFactory connectionFactory;
    /**
     * 获取rabbitAdmin
     *
     * @return
     */
    @Bean
    public RabbitAdmin rabbitAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        Queue queue = new Queue("test");
        TopicExchange topicExchange = new TopicExchange("test");
        Binding binding = BindingBuilder.bind(queue).to(topicExchange).with("test");
        rabbitAdmin.declareBinding(binding);

        queue = new Queue("test1");
        topicExchange = new TopicExchange("test1");
        binding = BindingBuilder.bind(queue).to(topicExchange).with("test1");
        rabbitAdmin.declareBinding(binding);

        queue = new Queue("test2");
        topicExchange = new TopicExchange("test2");
        binding = BindingBuilder.bind(queue).to(topicExchange).with("test2");
        rabbitAdmin.declareBinding(binding);

        queue = new Queue("test3");
        topicExchange = new TopicExchange("test3");
        binding = BindingBuilder.bind(queue).to(topicExchange).with("test3");
        rabbitAdmin.declareBinding(binding);
        return rabbitAdmin;
    }

    /**
     * 队列属性
     * 1：name 队列名称 必填 2：durable true 持久队列，重启之后队列还存在，消息不会丢失
     * 3：exclusive 默认false, true 排他性队列（临时队列），只有一个连接使用，当连接关闭时队列将被删除，断开连接后会自动删除，重启后需要重新创建，以前的队列消息会丢失。
     * 4: autoDelete 默认false, true 自动删除 当没有消费者时，是否自动删除队列，防止造成内存或者磁盘增加，但是消息会丢失
     * 5: arguments 可选参数
     * @return
     */
    @Bean
    public Queue memberQueue() {
        Map<String, Object> arguments = new HashMap<>();
        return QueueBuilder.durable("com.mall.member.queue").withArguments(arguments).build();
    }
}
