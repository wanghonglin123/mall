package com.whl.mall.mq.configura;
/**
 * @Title: MallRabbitMQTemplateConfigura
 * @Package: com.whl.mall.core.configura.rabbitMQ
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 5:35
 * @Version: V2.0.0
 */

import com.whl.mall.core.common.beans.MallBeans;
import com.whl.mall.core.rabbitmq.callback.RabbitRecoveryCallback;
import com.whl.mall.core.rabbitmq.callback.RabbitmqConfirmCallBack;
import com.whl.mall.core.rabbitmq.callback.RabbitmqReturnCallBack;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @ClassName: MallRabbitMQTemplateConfigura
 * @Description: 消费者Rabbitmq 模板配置，跟生成者有点差异，请参考Manage 模块RabbitmqTemplateConfigura
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 5:35
 */
@Configuration
public class RabbitmqTemplateConfigura extends MallBeans{

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate(RetryTemplate retryTemplate) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setRetryTemplate(retryTemplate);
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
