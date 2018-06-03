package com.whl.mall.mq.configura;
/**
 * @Title: RabbitmqConnectionConfigura
 * @Package: com.whl.mall.core.rabbitmq
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-16 下午 11:21
 * @Version: V2.0.0
 */

import com.whl.mall.core.common.pojo.MallThreadFactory;
import com.whl.mall.core.rabbitmq.listeners.RabbitMQChannelListenner;
import com.whl.mall.core.rabbitmq.listeners.RabbitMQCollectionListener;
import com.whl.mall.core.rabbitmq.listeners.RabbitRecoveryListener;
import com.whl.mall.core.rabbitmq.propeties.MallRabbitMQProperties;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ChannelListener;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @ClassName: RabbitmqConnectionConfigura
 * @Description: Rabbitmq 连接配置, 具体参考Manage模块配置，有API描述
 * @Author: WangHongLin
 * @Date: 2018-05-16 下午 11:21
 */
@Configuration
public class RabbitmqConnectionConfigura {
    @Autowired
    private MallRabbitMQProperties rabbitMQProperties;

    private static final String NAMEPREFIX = "ConnectionFactoryExecurotThread";

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = getConnectionFactory();
        connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
        return connectionFactory;
    }

    private CachingConnectionFactory getConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        setBasicInfo(connectionFactory);
        setCustomInfo(connectionFactory);
        return connectionFactory;
    }

    private void setBasicInfo(CachingConnectionFactory connectionFactory) {
        connectionFactory.setChannelCacheSize(rabbitMQProperties.getChannelCacheSize());
        connectionFactory.setChannelCheckoutTimeout(rabbitMQProperties.getChannelCheckoutTimeout());
        connectionFactory.setConnectionLimit(rabbitMQProperties.getConnectionLimit());
        connectionFactory.setPassword(rabbitMQProperties.getPassword());
        connectionFactory.setPort(rabbitMQProperties.getPort());
        connectionFactory.setHost(rabbitMQProperties.getHost());
        connectionFactory.setUsername(rabbitMQProperties.getUsername());
        connectionFactory.setConnectionTimeout(rabbitMQProperties.getConnectionTimeout());
        connectionFactory.setVirtualHost(rabbitMQProperties.getVirtualHost());
        connectionFactory.setCloseTimeout(rabbitMQProperties.getCloseTimeout());
    }

    private void setCustomInfo(CachingConnectionFactory connectionFactory) {
        connectionFactory.setExecutor(Executors.newCachedThreadPool(new MallThreadFactory(NAMEPREFIX)));
        connectionFactory.setConnectionThreadFactory(new MallThreadFactory(NAMEPREFIX));
        connectionFactory.setConnectionListeners(getConnectionListeners());
        connectionFactory.setChannelListeners(getChanneListeners());
        // connectionFactory.setCloseExceptionLogger(new RabbitmqCloseExceptionLogger());
        connectionFactory.setRecoveryListener(new RabbitRecoveryListener());
        connectionFactory.setConnectionNameStrategy(connectionFactory1 -> {
            return "consumeConnection";
        });
    }

    private List<ConnectionListener> getConnectionListeners() {
        List<ConnectionListener> connectionListenerList = new ArrayList<>();
        connectionListenerList.add(collectionListener());
        return connectionListenerList;
    }

    @Bean
    public RabbitMQCollectionListener collectionListener() {
        return new RabbitMQCollectionListener();
    }

    private List<ChannelListener> getChanneListeners() {
        List<ChannelListener> connectionListenerList = new ArrayList<>();
        connectionListenerList.add(channelListenner());
        return connectionListenerList;
    }

    @Bean
    public RabbitMQChannelListenner channelListenner() {
        return new RabbitMQChannelListenner();
    }
}
