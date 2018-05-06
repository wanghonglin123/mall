package com.whl.mall.core.configura.rabbitmq.listeners;
/**
 * @Title: RabbitMQCollectionListener
 * @Package: com.whl.mall.core.configura.rabbitMQ
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 4:02
 * @Version: V2.0.0
 */

import com.rabbitmq.client.ShutdownSignalException;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionListener;

/**
 * @ClassName: RabbitMQCollectionListener
 * @Description: 设置rabbitmq连接 监听器
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 4:02
 */
public class RabbitMQCollectionListener implements ConnectionListener{

    @Override
    public void onCreate(Connection connection) {
        connection.addBlockedListener(new RabbitMQBlockedListener());
        System.out.println("创建连接");
    }

    @Override
    public void onShutDown(ShutdownSignalException signal) {
        System.out.println("关闭连接");
    }
}
