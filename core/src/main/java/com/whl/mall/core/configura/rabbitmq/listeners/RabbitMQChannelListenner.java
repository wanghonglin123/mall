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
import org.springframework.amqp.rabbit.connection.ChannelListener;

/**
 * @ClassName: RabbitMQChannelListenner
 * @Description: 通道监听器
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 4:19
 */
public class RabbitMQChannelListenner implements ChannelListener{
    @Override
    public void onCreate(Channel channel, boolean b) {
        System.out.println("创建通道");
    }

    @Override
    public void onShutDown(ShutdownSignalException signal) {
        System.out.println("销毁通道");
    }
}
