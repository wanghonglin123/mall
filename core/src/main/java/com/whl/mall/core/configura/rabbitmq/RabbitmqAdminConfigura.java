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
import com.whl.mall.core.configura.rabbitmq.constants.RabbitConstants;
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

    /**
     * 获取rabbitAdmin
     *
     * @return
     */
    @Bean
    public RabbitAdmin rabbitAdmin(CachingConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareBinding(memberBinding());
        rabbitAdmin.declareBinding(roleBinding());
        rabbitAdmin.declareBinding(resourcesBinding());
        return rabbitAdmin;
    }
    /*===========================================Queue Begin ==============================================================*/
    /**
     * 队列名称最多可以有255个字节的UTF-8字符
     * 在可以使用队列之前，必须声明它。声明一个队列将导致它被创建，如果它不存在。如果队列已经存在并且其属性与声明中的相同，
     * 则声明不起作用。当现有的队列属性与声明中的不同时，将引发代码为406（PRECONDITION_FAILED）的通道级异常
     * 队列名称以“amq”开头。由经纪人保留供内部使用。尝试声明具有违反此规则的名称的队列将导致出现通道级别的异常，
     * 并显示回复代码403（ACCESS_REFUSED）
     *
     * 队列属性
     * 1：name 队列名称 必填 2：durable true 持久队列，重启之后队列还存在，消息不会丢失
     * 3：exclusive 默认false, true 排他性队列（临时队列），只有一个连接使用，当连接关闭时队列将被删除，断开连接后会自动删除，重启后需要重新创建，以前的队列消息会丢失。
     * 4: autoDelete 默认false, true 自动删除 当没有消费者时，是否自动删除队列，可能会造成内存或者磁盘增加，自动删除消息会丢失
     * 5: arguments 可选参数
     * @return
     */

    @Bean
    public Queue memberQueue() {
        Map<String, Object> arguments = new HashMap<>();
        return QueueBuilder.durable(RabbitConstants.MEMBER_QUEUE_NAME).withArguments(arguments).build();
    }

    @Bean
    public Queue roleQueue() {
        return QueueBuilder.durable(RabbitConstants.ROLE_QUEUE_NAME).build();
    }

    @Bean
    public Queue resourcesQueue() {
        return QueueBuilder.durable(RabbitConstants.RESOURCES_QUEUE_NAME).build();
    }

    /*===========================================Queue end ==============================================================*/

    /*===========================================Exchange Begin ==============================================================*/
    /**
     * 交换机作用：将消息路由到零个或者多个队列上，使用的路由算法取决于 交换类型和称为绑定的规则
     *
     * 交换机类型：
     * DirectExchange 匹配交换机 ：队列使用路由密钥K绑定到交换机，当具有路由密钥R的新消息到达直接交换时，如果K = R，则交换机将其路由到队列
     * 扇出交换：将消息路由到绑定到它的所有队列，并且路由密钥被忽略。如果N个队列绑定到扇出交换机，则当向该交换机发布新消息时，
     *           将该消息的副本传递到所有N个队列。扇出交换是消息广播路由的理想选择。
     * 主题交换： 通常用于实现各种发布/订阅模式变化，使用正则表达式匹配，比扇形交换更精确
     * 头部交换：匹配头部信息，这就是“x-match”绑定参数的用途。当“x-match”参数设置为“any”时，
     * 只有一个匹配的标题值就足够了。或者，将“x-match”设置为“all”则强制所有值必须匹配。
     *
     * 交换机属性 : 1 name 名称
     * 2：durable true 持久 false 非持久 rabbitMQ 服务器重启后仍然存在，false 不会，不存在的话消息发送出去将会返回，被ReturnCallBack接受到
     * 3：autoDelete 自动删除 当最后一个队列解除绑定时，将自动删除该交换机
     * 4: 自定义参数（不推荐，推荐使用rabbitmq 策略）
     * @return
     */

    @Bean
    public TopicExchange topicExchange() {
        TopicExchange topicExchange = (TopicExchange) ExchangeBuilder.topicExchange("com.mall.topExchange").build();
        return topicExchange;
    }

    /**
     * 发布订阅交换，默认名称（amq.fanout）
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        FanoutExchange fanoutExchange = (FanoutExchange) ExchangeBuilder.fanoutExchange("com.mall.fanoutExchange").build();
        return fanoutExchange;
    }

    /**
     * 直接匹配交换机，默认名称（空字符串和amq.dicect）
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        DirectExchange directExchange = (DirectExchange) ExchangeBuilder.directExchange("com.mall.directExchange").build();
        return directExchange;
    }

    /**
     * 头部匹配交换机，默认交换机名称(amq.match)
     * @return
     */
    @Bean
    public HeadersExchange headersExchange() {
        HeadersExchange headersExchange = (HeadersExchange) ExchangeBuilder.headersExchange("com.mall.headersExchange").build();
        return headersExchange;
    }
    /*===========================================Exchange Begin ==============================================================*/

    /*===========================================Bingding Begin ==============================================================*/
    @Bean
    public Binding memberBinding() {
        return BindingBuilder.bind(memberQueue()).to(topicExchange()).with("member.routingkey");
    }

    @Bean
    public Binding roleBinding() {
        return BindingBuilder.bind(roleQueue()).to(topicExchange()).with("role.routingkey");
    }

    @Bean
    public Binding resourcesBinding() {
        return BindingBuilder.bind(resourcesQueue()).to(topicExchange()).with("resources.routingkey");
    }
    /*===========================================Bingding end ==============================================================*/
}
