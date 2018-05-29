package com.whl.mall.mq.handle;
/**
 * @Title: MallMemberMessageListener
 * @Package: com.whl.mall.core.rabbitmq.listeners
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-15 下午 11:58
 * @Version: V2.0.0
 */

/**
 * @ClassName: MallMemberMessageListener
 * @Description:  成员消息监听器，用于消费成员MQ消息
 * 定义消息监听器的方法 1：implements MessageListener 2: implements ChannelAwareMessageListener
 * 3: MessageListenerAdapter listener = new MessageListenerAdapter（somePojo）;
      listener.setDefaultListenerMethod（“myMethod”）;
   4：使用@RabbitMQListener 注解，需要开启
 * @Author: WangHongLin
 * @Date: 2018-05-15 下午 11:58
 */
public class MessageHandle {
    public void handleMessage(byte[] body){
        System.out.println(body);
    }
}
