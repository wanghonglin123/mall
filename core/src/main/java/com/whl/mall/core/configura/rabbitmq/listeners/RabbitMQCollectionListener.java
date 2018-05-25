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
import com.whl.mall.core.log.MallLog4jLog;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: RabbitMQCollectionListener
 * @Description: 设置rabbitmq连接 监听器
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 4:02
 */
public class RabbitMQCollectionListener implements ConnectionListener{
    @Autowired
    private MallLog4jLog mallLog4jLog;

    @Override
    public void onCreate(Connection connection) {
        connection.addBlockedListener(new RabbitMQBlockedListener());
        mallLog4jLog.info("Rabbitmq connection create success");
    }

    @Override
    public void onShutDown(ShutdownSignalException signal) {
        mallLog4jLog.info("RabbitMQ connection is closed");
    }
}
