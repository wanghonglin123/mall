package com.whl.mall.core.configura.rabbitmq;
/**
 * @Title: MallRabbitMQTemplateConfigura
 * @Package: com.whl.mall.core.configura.rabbitMQ
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 5:35
 * @Version: V2.0.0
 */

import com.whl.mall.core.common.beans.MallBeans;
import com.whl.mall.core.configura.rabbitmq.callback.RabbitRecoveryCallback;
import com.whl.mall.core.configura.rabbitmq.callback.RabbitmqConfirmCallBack;
import com.whl.mall.core.configura.rabbitmq.callback.RabbitmqReturnCallBack;
import com.whl.mall.core.configura.rabbitmq.callback.RabbitmqRetryCallBack;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.PublisherCallbackChannelImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @ClassName: MallRabbitMQTemplateConfigura
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 5:35
 */
//@Configuration
//@AutoConfigureBefore(MallRabbitmqTemplateConfigura.class)
public class MallRabbitmqTemplateConfigura extends MallBeans{

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate(RetryTemplate retryTemplate) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // 设置重试模板
        rabbitTemplate.setRetryTemplate(retryTemplate);
        // 设置确认回调执行，可以重复发送消息，但是要注意消息重复消费问题
        rabbitTemplate.setConfirmCallback(new RabbitmqConfirmCallBack());
        // 对于需要返回的消息，必须设置为true, 才会执行ReturnCallback才会返回，ConnectionFactory publisherReturns属性也要设置为true
        rabbitTemplate.setMandatory(true);
        // 设置返回回调，比如Exchange不存在，会执行回调，可以执行一些自定义操作
        rabbitTemplate.setReturnCallback(new RabbitmqReturnCallBack());
        // 设置自动重连恢复回调类, 可以记录恢复前的最后一次异常等
        rabbitTemplate.setRecoveryCallback(new RabbitRecoveryCallback());

        // 以下为可能用到的api,难懂的
        // 通常，在使用模板时，Channel会从缓存（或创建的）中检出a ，用于该操作，并返回缓存以供重用。在多线程环境中，
        // 不能保证下一个操作将使用相同的通道。但是，有时您希望更多地控制通道的使用，并确保大量操作都在同一通道上执行。
        // 可以用于性能提升，减少Channel的切换
        // 使用场景：需要使用同一Channel 执行大量操作
        // 注意：如果模板操作已经在现有事务的范围内执行，上述讨论是没有意义的
        /*rabbitTemplate.invoke(rabbitOperations -> {
            rabbitOperations.convertAndSend("test", new C);
            rabbitOperations.waitForConfirms(5000);
            return null;
        });*/

        // 添加Channel监听器，目前版本只支持PublisherCallbackChannel，
        // 可以自定义实现PublisherCallbackChannel，用于模板回调操作，比如确认回调，返回回调
        //rabbitTemplate.addListener(new PublisherCallbackChannelImpl());

        // 当Channel执行操作后，实现ChannelCallback，可以执行一些自定义操作，需要实现该ChannelCallback
        //rabbitTemplate.execute()


        return rabbitTemplate;
    }

    @Bean
    public RetryTemplate retryTemplate() {
        // 重试模板
        RetryTemplate retryTemplate = new RetryTemplate();
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(500);
        backOffPolicy.setMultiplier(10.0);
        backOffPolicy.setMaxInterval(10000);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        return retryTemplate;
    }
}
