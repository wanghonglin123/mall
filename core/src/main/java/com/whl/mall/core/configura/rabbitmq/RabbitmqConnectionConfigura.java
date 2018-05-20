package com.whl.mall.core.configura.rabbitmq;
/**
 * @Title: RabbitmqConnectionConfigura
 * @Package: com.whl.mall.core.configura.rabbitmq
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-16 下午 11:21
 * @Version: V2.0.0
 */

import com.whl.mall.core.common.pojo.MallThreadFactory;
import com.whl.mall.core.configura.rabbitmq.listeners.RabbitMQChannelListenner;
import com.whl.mall.core.configura.rabbitmq.listeners.RabbitMQCollectionListener;
import com.whl.mall.core.configura.rabbitmq.listeners.RabbitRecoveryListener;
import com.whl.mall.core.configura.rabbitmq.propeties.MallRabbitMQProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ChannelListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @ClassName: RabbitmqConnectionConfigura
 * @Description: Rabbitmq 连接配置
 * @Author: WangHongLin
 * @Date: 2018-05-16 下午 11:21
 */
//@Configuration
public class RabbitmqConnectionConfigura {
    @Autowired
    private MallRabbitMQProperties rabbitMQProperties;

    private static final String NAMEPREFIX = "ConnectionFactoryExecurotThread";

    /**
     * 缓存连接工厂模式为：CONNECTION, 可以缓存连接和通道，实用于连接实例多的场景，集群场景
     *
     * @return
     */
    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = getConnectionFactory();
        // 缓存模式CONNECTION， 分布式模块多使用建议CONNECTION， 模块少使用CHANNEL 模式
        connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);
        connectionFactory.setConnectionCacheSize(rabbitMQProperties.getConnectionCacheSize());
        return connectionFactory;
    }

    /**
     * 获取rabbitmq 连接
     *
     * @return
     */
    private CachingConnectionFactory getConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        // 设置基本信息
        setBasicInfo(connectionFactory);
        // 设置自定义信息
        setCustomInfo(connectionFactory);
        return connectionFactory;
    }

    private void setBasicInfo(CachingConnectionFactory connectionFactory) {
        connectionFactory.setAddresses(rabbitMQProperties.getAddresses());
        connectionFactory.setChannelCacheSize(rabbitMQProperties.getChannelCacheSize());
        connectionFactory.setChannelCheckoutTimeout(rabbitMQProperties.getChannelCheckoutTimeout());
        connectionFactory.setConnectionCacheSize(rabbitMQProperties.getConnectionCacheSize());
        connectionFactory.setConnectionLimit(rabbitMQProperties.getConnectionLimit());
        connectionFactory.setPassword(rabbitMQProperties.getPassword());
        connectionFactory.setPort(rabbitMQProperties.getPort());
        connectionFactory.setUsername(rabbitMQProperties.getUsername());
        connectionFactory.setConnectionTimeout(rabbitMQProperties.getConnectionTimeout());
        connectionFactory.setVirtualHost(rabbitMQProperties.getVirtualHost());
        // 没测试过，rul 配置主机，端口，虚拟机，用户名和密码信息
        //connectionFactory.setUri(rabbitMQProperties.getUrl());
        connectionFactory.setCloseTimeout(rabbitMQProperties.getCloseTimeout());
        // 设置发布确认，可以防止mq消息丢失，但是消费端需要注意去重复，一般是消费者获取服务器出现故障返回
        connectionFactory.setPublisherConfirms(true);
        // 出现的概率一般是开发人员配置出错，服务器问题，建议收集信息
        connectionFactory.setPublisherReturns(true);
    }

    private void setCustomInfo(CachingConnectionFactory connectionFactory) {
        // rabbitmq-client默认为每个连接创建了固定大小5个线程的线程池。当使用大量的连接时，你要为CachingConnectionFactory指定executor。这时，
        // 同一个executor被所有的连接使用，他的线程也被共享。这个executor线程池的大小应该无界或者针对应用进行调解。
        // 如果多个channel被创建于每个连接上，这个池的大小将影响并发性，所以一个可变的线程池最适合。
        connectionFactory.setExecutor(Executors.newCachedThreadPool(new MallThreadFactory(NAMEPREFIX)));
        // 设置rabbitmq 线程工厂，可能等于connectionFactory.setExecutor(Executors.newCachedThreadPool(new MallThreadFactory(NAMEPREFIX)));
        connectionFactory.setConnectionThreadFactory(new MallThreadFactory(NAMEPREFIX));
        connectionFactory.setConnectionListeners(getConnectionListeners());
        connectionFactory.setChannelListeners(getChanneListeners());
        connectionFactory.setCloseExceptionLogger(new RabbitmqCloseExceptionLogger());
        connectionFactory.setRecoveryListener(new RabbitRecoveryListener());
        connectionFactory.setConnectionNameStrategy(connectionFactory1 -> {
            return "mallConnection";
        });
    }

    private List<ConnectionListener> getConnectionListeners() {
        List<ConnectionListener> connectionListenerList = new ArrayList<>();
        connectionListenerList.add(new RabbitMQCollectionListener());
        return connectionListenerList;
    }

    private List<ChannelListener> getChanneListeners() {
        List<ChannelListener> connectionListenerList = new ArrayList<>();
        connectionListenerList.add(new RabbitMQChannelListenner());
        return connectionListenerList;
    }
}
