package com.whl.mall.core.configura.rabbitmq.listeners;
/**
 * @Title: RabbitMQBrokedListener
 * @Package: com.whl.mall.core.configura.rabbitMQ.listeners
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 4:25
 * @Version: V2.0.0
 */

import com.rabbitmq.client.BlockedListener;

import java.io.IOException;

/**
 * @ClassName: RabbitMQBrokedListener
 * @Description: 阻塞监听器
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 4:25
 */
public class RabbitMQBlockedListener implements BlockedListener{
    /**
     * 处理阻塞事件
     * @param s
     * @throws IOException
     */
    @Override
    public void handleBlocked(String s) throws IOException {

    }

    /**
     * 处理不阻塞事件
     * @throws IOException
     */
    @Override
    public void handleUnblocked() throws IOException {

    }
}
