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
        rabbitTemplate.setRetryTemplate(retryTemplate);
        rabbitTemplate.setConfirmCallback(new RabbitmqConfirmCallBack());
        rabbitTemplate.setReturnCallback(new RabbitmqReturnCallBack());
        rabbitTemplate.setRecoveryCallback(new RabbitRecoveryCallback());
        // 设置为true, 会被回调处理
        rabbitTemplate.setMandatory(true);
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
        retryTemplate.execute(new RabbitmqRetryCallBack());
        return retryTemplate;
    }
}
