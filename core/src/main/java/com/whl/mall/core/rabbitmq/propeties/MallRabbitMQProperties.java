package com.whl.mall.core.rabbitmq.propeties;
/**
 * @Title: MallRabbitMQProperties
 * @Package: com.whl.mall.core.configura.rabbitMQ
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-05 下午 11:09
 * @Version: V2.0.0
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MallRabbitMQProperties
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-05 下午 11:09
 */
@Component
@PropertySource("/${spring.profiles.active}/rabbitmq.properties")
public class MallRabbitMQProperties {
    @Value("${rabbitmq.addresses}")
    private String addresses;

    @Value("${rabbitmq.channelCacheSize}")
    private int channelCacheSize;

    @Value("${rabbitmq.channelCheckoutTimeout}")
    private long channelCheckoutTimeout;

    @Value("${rabbitmq.connectionCacheSize}")
    private int connectionCacheSize;

    @Value("${rabbitmq.connectionLimit}")
    private int connectionLimit;

    @Value("${rabbitmq.username}")
    private String username;

    @Value("${rabbitmq.host}")
    private String host;

    @Value("${rabbitmq.closeTimeout}")
    private int closeTimeout;

    @Value("${rabbitmq.url}")
    private String url;

    @Value("${rabbitmq.password}")
    private String password;

    @Value("${rabbitmq.port}")
    private int port;

    @Value("${rabbitmq.connectionTimeout}")
    private int connectionTimeout;

    @Value("${rabbitmq.virtualHost}")
    private String virtualHost;

    public String getAddresses() {
        return addresses;
    }

    public int getChannelCacheSize() {
        return channelCacheSize;
    }

    public long getChannelCheckoutTimeout() {
        return channelCheckoutTimeout;
    }

    public int getConnectionCacheSize() {
        return connectionCacheSize;
    }

    public int getConnectionLimit() {
        return connectionLimit;
    }

    public String getUsername() {
        return username;
    }

    public String getHost() {
        return host;
    }

    public int getCloseTimeout() {
        return closeTimeout;
    }

    public String getUrl() {
        return url;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public String getVirtualHost() {
        return virtualHost;
    }
}
