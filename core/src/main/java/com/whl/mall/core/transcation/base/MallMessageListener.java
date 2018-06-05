package com.whl.mall.core.transcation.base;
/**
 * @Title: MallMessageListener
 * @Package: com.whl.mall.core.base.pojo
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-06-03 下午 3:39
 * @Version: V2.0.0
 */

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

/**
 * @ClassName: MallMessageListener
 * @Description: Mall 消息监听
 * @Author: WangHongLin
 * @Date: 2018-06-03 下午 3:39
 */
public interface MallMessageListener {
    /**
     * 消息转换
     * @param body 消息主体
     * @param properties 消息配置
     * @return Object 转换后的消息
     * @throws Exception 运行时异常
     */
    Object conventMessage(Object body, MessageProperties properties) throws Exception;

    /**
     * 获取消息体
     * @param message 消息
     * @param messageProperties 消息特性
     * @return
     */
    Object getMessageBody(Message message, MessageProperties messageProperties);

    /**
     * 处理消息
     */
    void handleMessage(Object messageBody, MessageProperties properties) throws Exception;
}
