package com.whl.mall.core.configura.rabbitmq.listeners;
/**
 * @Title: RabbitMQChannelListenner
 * @Package: com.whl.mall.core.configura.rabbitMQ.listeners
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 4:19
 * @Version: V2.0.0
 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ShutdownSignalException;
import com.whl.mall.core.log.MallLog4jLog;
import org.springframework.amqp.rabbit.connection.ChannelListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: RabbitMQChannelListenner
 * @Description: 通道监听器
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 4:19
 */
public class RabbitMQChannelListenner implements ChannelListener{
    @Autowired
    private MallLog4jLog log4jLog;

    @Override
    public void onCreate(Channel channel, boolean b) {
        log4jLog.info("Rabbitmq Channel create success");
    }

    @Override
    public void onShutDown(ShutdownSignalException signal) {
        log4jLog.info("Rabbitmq Channel is a closed");
    }
}
