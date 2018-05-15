package com.whl.mall.core.configura.rabbitmq.listeners;
/**
 * @Title: MallRoleMessageListener
 * @Package: com.whl.mall.core.configura.rabbitmq.listeners
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-16 上午 12:01
 * @Version: V2.0.0
 */

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

/**
 * @ClassName: MallRoleMessageListener
 * @Description: 角色消息监听器
 * @Author: WangHongLin
 * @Date: 2018-05-16 上午 12:01
 */
public class MallRoleMessageListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

    }
}
