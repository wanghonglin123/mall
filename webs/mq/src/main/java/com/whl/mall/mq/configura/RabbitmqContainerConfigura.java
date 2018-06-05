package com.whl.mall.mq.configura;
/**
 * @Title: RabbitmqMessageListenerContainerConfigura
 * @Package: com.whl.mall.core.rabbitmq
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-18 下午 11:23
 * @Version: V2.0.0
 */

import com.whl.mall.core.rabbitmq.adapter.MallMessageListenerAdapter;
import com.whl.mall.core.rabbitmq.constants.RabbitConstants;
import com.whl.mall.core.rabbitmq.pojo.MallConsumerTagStrategy;
import com.whl.mall.mq.handle.MessageHandle;
import com.whl.mall.mq.listenners.MemberMessageListenner;
import com.whl.mall.mq.listenners.TranscationMessageListenner;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: RabbitmqMessageListenerContainerConfigura
 * @Description: Rabbitmq 消息监听容器配置
 * 两种容器： SimpleMessageListenerContainer SMLC 和 DirectMessageListenerContainer DMLC
 * 区别：1： SMLC 支持TxSize() 和maxConcurrentConsumers（），DMLC 不支持
 * 2：SMLC ：为每个消费者使用内部队列和专用线程; 如果容器配置为侦听多个队列，则使用相同的使用者线程来处理所有队列
 * DMLC ：线程在消费者之间共享，而不是为SMLC中的每个消费者提供专用线程
 * 3：SMLC 支持消费者自动缩放，DMLC 不支持，但是DMLC 运行已编程的方式，更改consumersPerQueue属性，并且消费者将相应地进行调整
 * 4：DMLC 在运行时添加和删除队列，效率更高。SMLC，整个消费者线程重新启动（所有消费者都被取消并重新创建）; 对于DMLC，未受影响的消费者不会被取消
 * 5：DMLC 避免了RabbitMQ客户端线程和消费者线程之间的上下文切换
 * <p>
 * 定义消息监听器的方法 1：implements MessageListener 2: implements ChannelAwareMessageListener
 * 3: MessageListenerAdapter listener = new MessageListenerAdapter（somePojo）;
 * listener.setDefaultListenerMethod（“myMethod”）;
 * 4：使用@RabbitMQListener 注解，需要开启,
 * 具体实现参考官网spring-amqp
 * @Author: WangHongLin
 * @Date: 2018-05-18 下午 11:23
 */
@Configuration
//启用对@RabbitListener注释的支持，请将其添加@EnableRabbit到您的某个@Configuration类中
@EnableRabbit
public class RabbitmqContainerConfigura {
    @Autowired
    private CachingConnectionFactory connectionFactory;

    /**
     * 设置成员消息监听器，监听器类型为SimpleMessageListenerContainer
     * 配置参考apidDoc（）方法
     *  // 不推荐使用适配器方式，个人建议不要使用此方式
     * @return
     */
    /*@Bean
    public SimpleMessageListenerContainer transcationMessageListenerContainer() {
        SimpleMessageListenerContainer smlc = new SimpleMessageListenerContainer(connectionFactory);
        smlc.setQueueNames(RabbitConstants.TRANSCATION_QUEUE_NAME);
        MallMessageListenerAdapter adapter = new MallMessageListenerAdapter(new MessageHandle());
        smlc.setMessageListener(adapter);
        smlc.setConsumerTagStrategy(consumerTagStrategy());
        // 设置手动确认模式
        smlc.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // smlc.setMessageConverter(this.jackson2JsonMessageConverter());
        return smlc;
    }*/

    /**
     * 设置成员消息监听器，监听器类型为SimpleMessageListenerContainer
     * 配置参考apidDoc（）方法
     *
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer roleMessageListenerContainer() {
        SimpleMessageListenerContainer smlc = new SimpleMessageListenerContainer(connectionFactory);
        smlc.setQueueNames(RabbitConstants.ROLE_QUEUE_NAME);
        smlc.setMessageListener(this.memberMessageListenner());
        smlc.setConsumerTagStrategy(consumerTagStrategy());
        // 设置手动确认模式
        smlc.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return smlc;
    }

    /**
     * 设置事务消息监听器，监听器类型为SimpleMessageListenerContainer
     * 配置参考apidDoc（）方法
     *
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer transcationListenerContainer() {
        SimpleMessageListenerContainer smlc = new SimpleMessageListenerContainer(connectionFactory);
        smlc.setQueueNames(RabbitConstants.TRANSCATION_QUEUE_NAME);
        smlc.setMessageListener(transcationMessageListenner());
        smlc.setConsumerTagStrategy(consumerTagStrategy());
        // 设置手动确认模式
        smlc.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return smlc;
    }

    @Bean
    public DirectMessageListenerContainer directMessageListenerContainer() {
        DirectMessageListenerContainer dmlc = new DirectMessageListenerContainer(connectionFactory);
        dmlc.setQueueNames(RabbitConstants.MEMBER_QUEUE_NAME);
        dmlc.setMessageListener(new MallMessageListenerAdapter(new MessageHandle()));
        // 为每个创建的队列设置消费者数量，可以通过设置这个值调整消费者数量
        dmlc.setConsumersPerQueue(1);
        // 默认为1，每条消息多需要确认，设置确认的消息数量，可以减少发送给Broker确认的数量（会增加重复发送消息的可能性为代码），通常，在高容量监听器容器设置此属性
        // 如果prefetchCount小于messagesPerAck，它会增加以匹配messagesPerAck
        dmlc.setMessagesPerAck(1);
        // 设置ack 确认超时时间
        dmlc.setAckTimeout(5000);
        return dmlc;
    }

    @Bean
    public ConsumerTagStrategy consumerTagStrategy() {
        return new MallConsumerTagStrategy();
    }

    @Bean
    public MemberMessageListenner memberMessageListenner() {
        return new MemberMessageListenner();
    }

    @Bean
    public TranscationMessageListenner transcationMessageListenner() {
        return new TranscationMessageListenner();
    }

    /**
     * 监听器API doc
     * 官网：https://docs.spring.io/spring-amqp/reference/htmlsingle/#containerAttributes
     *
     * @return
     */
    @Deprecated
    public void apidDoc() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        // 设置消息监听容器监听的队列，一个或者多个
        simpleMessageListenerContainer.setQueueNames(RabbitConstants.MEMBER_QUEUE_NAME);
        // 设置消息监听容器的监听器，负责消费消息，里面执行消费消息操作和确认
        simpleMessageListenerContainer.setMessageListener(new MallMessageListenerAdapter(new MessageHandle()));

        /* 设置并发消费者参数 begin */
        // 设置并发消费者数量
        simpleMessageListenerContainer.setConcurrentConsumers(4);
        // 设置最大并发消费者数量
        simpleMessageListenerContainer.setMaxConcurrentConsumers(10);
        // 设置消费者数量和最大消费者数量，同时消费队列信息，一条消息只会被一个消费者消费，setConcurrency = setConcurrentConsumers + setMaxConcurrentConsumers;
        simpleMessageListenerContainer.setConcurrency("4-10");
        // 当设置了最大消费者时，如果启动新的消费者时，消费者接受到的连续消息的最小数量，没有发生接受超时, 这个配置依赖于setConcurrency(4-10);
        simpleMessageListenerContainer.setConsecutiveActiveTrigger(10);
        // 消费者在考虑停止消费者之前必须经历的接收超时的最小次数。也受到txSize的影响,这个配置依赖于setConcurrency(4-10);
        simpleMessageListenerContainer.setConsecutiveIdleTrigger(10);
        // 每个新消费者按需启动间隔时间（以毫秒为单位） 10000 = 10秒
        simpleMessageListenerContainer.setStartConsumerMinInterval(10000);
        // 消费者停止前必须经过的时间（以毫秒为单位），因为最后一位消费者被停止，此时检测到空闲消费者
        simpleMessageListenerContainer.setStopConsumerMinInterval(60000);
        /* 设置并发消费者参数 end */

        // 是否支持通道事物，事物会影响性能， 事物和确认不能兼容在一起使用
        simpleMessageListenerContainer.setChannelTransacted(false);
        // 以毫秒为单位等待消费者线程启动的时间。如果这段时间过去了，写入错误日志; 可能发生这种情况的一个示例是，如果a taskExecutor配置的线程数不足以支持容器concurrentConsumers
        simpleMessageListenerContainer.setConsumerStartTimeout(1000);

        /* 控制容器队列声明行为的属性 begin*/
        // 设置队列声明重试次数
        simpleMessageListenerContainer.setDeclarationRetries(3);
        // 设置队列声明失败重试间隔时间，默认5秒
        simpleMessageListenerContainer.setFailedDeclarationRetryInterval(5000);
        // 如果在消费者初始化期间配置的队列的子集可用，则消费者开始消费这些队列。消费者将尝试使用此间隔被动地声明丢失的队列。当这个间隔时，declarationRetries和failedDeclarationRetryInterval将再次被使用。
        // 如果仍然有队列丢失，则消费者会再次等待此间隔，然后重试。此过程将无限期地继续，直到所有队列都可用。默认值：60000（1分钟）
        simpleMessageListenerContainer.setRetryDeclarationInterval(60000);
        /* 控制容器队列声明行为的属性 end*/

        // 默认为false,, 如果为true, concurrency就必须设置为1，即只能单个消费者消费队列里的消息，适用于必须严格执行消息队列的消费顺序（先进先出）
        simpleMessageListenerContainer.setExclusive(false);
        // true, 队列丢失时，启动会失败，运行时队列删除，重试3次连接队列，不成功的话容器将停止
        // false, 如果重试3次失败，容器将进入恢复模式，重启声明队列，这个过程会无限恢复下去，直到成功为止
        simpleMessageListenerContainer.setMissingQueuesFatal(true);
        // 设置消息接收超时时间，它对于一个事务Channel具有最大的影响txSize > 1，因为它可能会导致已消耗的消息在超时过期之前不会被确认。
        // 慎用事物管道加setReceiveTimeout
        simpleMessageListenerContainer.setReceiveTimeout(5000);

        /*事物配置 begin*/
        // 事物大小值，当与acknowledgeMode 确认模式 AUTO一起使用时，容器将在发送ack之前尝试处理多达这些消息（等待每个消息达到接收超时设置）。
        // 这也是事务通道提交时的情况。如果prefetchCount小于txSize，它会增加以匹配txSize
        simpleMessageListenerContainer.setTxSize(4);
        // 确认模式：默认AUTO, 一般与channelTransacted 事物， txSize() 配合使用
        // 手动确认：MANUAL NONE ：监听者必须通过调用来确认所有消息Channel.basicAck()
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
        // 设置消费者预取消息，默认250，预取消息可能导致消息无序，这个值会用于匹配txSize() 和messagePerAck
        simpleMessageListenerContainer.setPrefetchCount(250);
        // 设置关闭超时，当一个容器关闭时（例如，如果它的封闭ApplicationContext处于关闭状态），它将等待处理中的消息被处理到这个限制。默认为5秒
        simpleMessageListenerContainer.setShutdownTimeout(5);
        // 是否强制关闭连接通道，默认true, true, 导致未消费的消息重新排序，false ，等待setShutdownTimeout后才会关闭连接通道
        simpleMessageListenerContainer.setForceCloseChannel(true);
    }

    /**
     * 消息处理器
     * @return
     *//*
    @Bean
    public MessageHandle messageHandle() {
        return new MessageHandle();
    }*/

    /**
     * 消息转换器，
     *
     * @return
     */
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        return jackson2JsonMessageConverter;
    }
}
