package com.whl.mall.core.configura.rabbitmq.adapter;
/**
 * @Title: MallMessageListenerAdapter
 * @Package: com.whl.mall.core.configura.rabbitmq.adapter
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-16 上午 12:14
 * @Version: V2.0.0
 */

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

/**
 * @ClassName: MallMessageListenerAdapter
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-16 上午 12:14
 */
public class MallMessageListenerAdapter extends MessageListenerAdapter {
    @Override
    protected String getListenerMethodName(Message originalMessage, Object extractedMessage) throws Exception {
        return super.getListenerMethodName(originalMessage, extractedMessage);
    }
}
