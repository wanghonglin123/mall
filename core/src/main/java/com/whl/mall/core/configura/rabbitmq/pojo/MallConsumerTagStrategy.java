package com.whl.mall.core.configura.rabbitmq.pojo;
/**
 * @Title: MallConsumerTagStrategy
 * @Package: com.whl.mall.core.configura.rabbitmq.pojo
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-22 下午 8:31
 * @Version: V2.0.0
 */

import org.springframework.amqp.support.ConsumerTagStrategy;

/**
 * @ClassName: MallConsumerTagStrategy
 * @Description: 自定义消费者标签，用于区分消费者
 * @Author: WangHongLin
 * @Date: 2018-05-22 下午 8:31
 */
public class MallConsumerTagStrategy implements ConsumerTagStrategy{
    @Override
    public String createConsumerTag(String queueName) {
        String consumerName = "Consumer_" + Thread.currentThread().getName();
        return consumerName + "_" + queueName;
    }
}
