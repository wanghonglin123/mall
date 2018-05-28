package com.whl.mall.core.rabbitmq.adapter;
/**
 * @Title: MallMessageListenerAdapter
 * @Package: com.whl.mall.core.rabbitmq.adapter
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-16 上午 12:14
 * @Version: V2.0.0
 */

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

/**
 * @ClassName: MallMessageListenerAdapter
 * @Description: 自定义消息监听适配器，重写getListenerMethodName, Rabbitmq 默认方法为handleMessage
 * @Author: WangHongLin
 * @Date: 2018-05-16 上午 12:14
 */
public class MallMessageListenerAdapter extends MessageListenerAdapter {
    private static final String DEFAULT_METHOD_NAME = "messageHandle";
    public MallMessageListenerAdapter(Object delegate) {
        super(delegate);
    }

    /**
     * 设置监听适配器目标方法，动态的
     * @param originalMessage
     * @param extractedMessage
     * @return
     * @throws Exception
     */
    @Override
    protected String getListenerMethodName(Message originalMessage, Object extractedMessage) throws Exception {
        // 暂时使用统一方法消费消息
        return DEFAULT_METHOD_NAME;
        // 到时候改成可配置多个方法用于区分
        //return originalMessage.getMessageProperties().getTargetMethod().getName();
    }
}
