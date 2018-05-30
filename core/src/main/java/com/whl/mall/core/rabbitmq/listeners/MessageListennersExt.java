package com.whl.mall.core.rabbitmq.listeners;
/**
 * @Title: MessageListennersExt
 * @Package: com.whl.mall.core.rabbitmq.listeners
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-28 下午 10:37
 * @Version: V2.0.0
 */

import com.rabbitmq.client.Channel;
import com.whl.mall.core.base.pojo.MallBasePoJo;
import com.whl.mall.core.common.constants.MallConstants;
import com.whl.mall.core.common.utils.MallJsonUtils;
import com.whl.mall.core.log.MallLog4jLog;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * @ClassName: MessageListennersExt
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-28 下午 10:37
 */
public abstract class MessageListennersExt implements ChannelAwareMessageListener, MessageListener {
    @Autowired
    private MallLog4jLog log4jLog;

    /**
     * 消息确认处理：容器设置了setAcknowledgeMode（AcknowledgeMode.MANUAL）手动确认，必须执行channel.basicAck进行手动确认，否则消息将循环重复消费
     * 确认（可以多条一次ack）channel.basicAck(deliveryTag, multiple) multiple = false 单条ack true 批量ack 将一次性ack所有小于deliveryTag的消息
     * 不确认(可以多条一次nack) channel.basicNack（int deliveryTag，boolean multiple, boolean queue）未确认，queue=true 将未确认的消息重新发送到队列， false 丢弃
     * 拒绝消息 basicReject（p, p1），p1 = true 重发队列,消息在队列头部 p1 = false, 丢弃
     * @param message
     * @param channel
     * @throws Exception
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        byte[] body = message.getBody();
        MessageProperties properties = message.getMessageProperties();
        String contentEncoding = properties.getContentEncoding();
        String content = null;
        long deliveryTag = properties.getDeliveryTag();
        try {
            contentEncoding = Optional.ofNullable(contentEncoding).orElse(MallConstants.DEFAULT_ENCODING);
            content = new String(body, contentEncoding);
            Object msg = MallJsonUtils.jsonToObject(content, getJavaType());
            System.out.println(1 / 0);
            handleMessage(msg);
            channel.basicAck(deliveryTag, false);
        } catch (Throwable e) {
            log4jLog.error(e, String.format("消息确认失败，消息：%s", content));

            errorHandle(channel, properties, deliveryTag);
        }
    }

    @Override
    public void onMessage(Message message) {
        log4jLog.error("暂不开发");
    }

    /**
     * 消息业务处理
     * @param content 消息内容
     * @throws Exception
     */
    protected abstract void handleMessage(Object content) throws Exception;

    /**
     * 获取java类型
     * @return
     */
    protected abstract Class<? extends MallBasePoJo> getJavaType();

    /**
     * 监听器异常处理
     * @param channel
     * @param deliveryTag
     */
    private void errorHandle(Channel channel, MessageProperties messageProperties, long deliveryTag) {
        try {
            Boolean redelivered = messageProperties.getRedelivered();
            
            // 拒绝消息， false 消息将丢弃或者为死信 true 重复发送
            channel.basicReject(deliveryTag, false);
        } catch (Throwable e) {
            log4jLog.error(e, "拒绝消息失败");
        }
    }

    public MallLog4jLog getLog4jLog() {
        return log4jLog;
    }
}
