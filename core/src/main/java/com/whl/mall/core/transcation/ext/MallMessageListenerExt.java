package com.whl.mall.core.transcation.ext;
/**
 * @Title: MallMessageListenerExt
 * @Package: com.whl.mall.core.base.pojo
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-06-03 上午 12:18
 * @Version: V2.0.0
 */

import com.rabbitmq.client.Channel;
import com.whl.mall.core.MallTranscationException;
import com.whl.mall.core.common.constants.MallNumberConstants;
import com.whl.mall.core.common.constants.MallSymbolConstants;
import com.whl.mall.core.log.MallLog4jLog;
import com.whl.mall.core.transcation.base.MallMessageListener;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: MallMessageListenerExt
 * @Description: 消息监听扩展
 * @Author: WangHongLin
 * @Date: 2018-06-03 上午 12:18
 */
public abstract class MallMessageListenerExt implements MallMessageListener, ChannelAwareMessageListener, MessageListener {
    // 日志服务
    @Autowired
    private MallLog4jLog log4jLog;

    /**
     * 消息确认处理：容器设置了setAcknowledgeMode（AcknowledgeMode.MANUAL）手动确认，必须执行channel.basicAck进行手动确认，否则消息将循环重复消费
     * 确认（可以多条一次ack）channel.basicAck(deliveryTag, multiple) multiple = false 单条ack true 批量ack 将一次性ack所有小于deliveryTag的消息
     * 不确认(可以多条一次nack) channel.basicNack（int deliveryTag，boolean multiple, boolean queue）未确认，queue=true 将未确认的消息重新发送到队列， false 丢弃
     * 拒绝消息 basicReject（p, p1），p1 = true 重发队列,消息在队列头部 p1 = false, 丢弃
     *
     * @param message
     * @param channel
     * @throws Exception
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        MessageProperties properties = message.getMessageProperties();
        long deliveryTag = properties.getDeliveryTag();
        Object messageCntent = this.getMessageBody(message, properties);
        try {
            messageCntent = conventMessage(messageCntent, properties);
            handleMessage(messageCntent, properties);
            // 设置手动确认
            channel.basicAck(deliveryTag, false);
        } catch (Throwable e) {
            log4jLog.error(e, String.format("消息确认失败，消息：%s", messageCntent));
            errorHandle(channel, properties, deliveryTag, e);
        }
    }

    @Override
    public void onMessage(Message message) {
        log4jLog.error("暂不开发");
    }

    @Override
    public Object conventMessage(Object body, MessageProperties properties) throws Exception {
        //contentEncoding = Optional.ofNullable(contentEncoding).orElse(MallConstants.DEFAULT_ENCODING);
        String content = body.toString();
        String[] bodyContent = content.split(MallSymbolConstants.UNDERLINE);
        Long transcationId = Long.valueOf(bodyContent[MallNumberConstants.TWO]);
        return transcationId;
    }

    @Override
    public Object getMessageBody(Message message, MessageProperties properties) {
        return properties.getCorrelationId();
    }

    /**
     * 监听器异常处理
     *
     * @param channel
     * @param deliveryTag
     */
    protected void errorHandle(Channel channel, MessageProperties messageProperties, long deliveryTag, Throwable throwable) {
        try {
            // 是否支持消息重发
            boolean reject = messageProperties.getRedelivered();
            // do you need to reject message
            if (reject) {
                // discard message, the message will not recovery in zhe future
                channel.basicReject(deliveryTag, false);
            } else {
                // 重发到队列，并且会在队列头部，name可能会导致有些消息无法消费
                channel.basicReject(deliveryTag, true);
            }

        } catch (Throwable e) {
            log4jLog.error(e, String.format("message ack fail, the reason is that zhe message refuses to fail"));
        }
    }

    protected void transcationExceptionHand() throws Exception {

    }

    public MallLog4jLog getLog4jLog() {
        return log4jLog;
    }
}
