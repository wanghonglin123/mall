package com.whl.mall.core.configura.rabbitmq.event;
/**
 * @Title: MallListenerContainerConsumerFailedEvent
 * @Package: com.whl.mall.core.configura.rabbitmq.event
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-22 下午 7:59
 * @Version: V2.0.0
 */

import org.springframework.amqp.rabbit.listener.ListenerContainerConsumerFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @ClassName: MallListenerContainerConsumerFailedEvent
 * @Description: 消息容器消费失败事件
 * @Author: WangHongLin
 * @Date: 2018-05-22 下午 7:59
 */

public class MallListenerContainerConsumerFailedEvent implements ApplicationListener<ListenerContainerConsumerFailedEvent> {
    @Override
    public void onApplicationEvent(ListenerContainerConsumerFailedEvent listenerContainerConsumerFailedEvent) {
        /*  container - 消费者经历问题的监听容器．
            reason - 失败的文本原因。
            fatal - 一个表示失败是否是致命的boolean值;对于非致命异常,容器会根据retryInterval值尝试重新启动消费者.
            throwable -捕捉到的Throwable.
            这些事件能通过实现ApplicationListener<ListenerContainerConsumerFailedEvent>来消费.

            当 concurrentConsumers 大于１时，系统级事件(如连接失败)将发布到所有消费者.

            如果消费者因队列是专有使用而失败了，默认情况下，在发布事件的时候，也会发出WARN 日志. 要改变日志行为,需要在SimpleMessageListenerContainer的exclusiveConsumerExceptionLogger属性中提供自定义的ConditionalExceptionLogger.
            也可参考the section called “Logging Channel Close Events”.

            致命错误都记录在ERROR级别中，这是不可修改的。*/

        /*其它事件：在容器生命周期的各个阶段发布了其他几个事件：

        AsyncConsumerStartedEvent （当消费者开始时）
        AsyncConsumerRestartedEvent（当消费者在故障后重新启动时 - SimpleMessageListenerContainer仅）
        AsyncConsumerTerminatedEvent （当消费者正常停止时）
        AsyncConsumerStoppedEvent（当消费者停止时 - SimpleMessageListenerContainer仅）
        ConsumeOkEvent（当consumeOk从代理收到a时，包含队列名称consumerTag）
        ListenerContainerIdleEvent（请参阅“检测空闲异步使用者”一节）*/
    }
}
