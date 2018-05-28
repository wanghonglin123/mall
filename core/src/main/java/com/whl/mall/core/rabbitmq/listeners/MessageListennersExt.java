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
import com.whl.mall.core.common.utils.MallJsonUtils;
import com.whl.mall.core.log.MallLog4jLog;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: MessageListennersExt
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-28 下午 10:37
 */
public abstract class MessageListennersExt implements ChannelAwareMessageListener {
    @Autowired
    private MallLog4jLog log4jLog;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        byte[] body = message.getBody();
        MessageProperties properties = message.getMessageProperties();
        String content = null;
        Object msg = null;
        try {
            content = new String(body, properties.getContentEncoding());
            msg = MallJsonUtils.jsonToObject(content, getJavaType());
            handleMessage(msg);
            channel.basicAck();
        } catch (Exception e) {
            log4jLog.error("消息处理失败：");
        }
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

    public MallLog4jLog getLog4jLog() {
        return log4jLog;
    }
}
