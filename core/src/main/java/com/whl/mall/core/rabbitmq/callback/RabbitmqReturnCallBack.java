package com.whl.mall.core.rabbitmq.callback;
/**
 * @Title: RabbitMQReturnCallBack
 * @Package: com.whl.mall.core.configura.rabbitMQ
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 6:40
 * @Version: V2.0.0
 */

import com.whl.mall.core.log.MallLog4jLog;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: RabbitMQReturnCallBack
 * @Description: 发布商返回回调，当消息确认成功，当没有交换机，队列，路由的时候，会触发该回掉函数
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 6:40
 */
public class RabbitmqReturnCallBack implements RabbitTemplate.ReturnCallback{
    @Autowired
    private MallLog4jLog log4jLog;

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText,
                                String exchange, String routingKey) {
        // 因为在保存事物的时候，已经把数据全部包装了，这里不重试也不会丢失
        // 在这里不做重试操作，可能重试N次也被拒绝，所以消息失败处理全靠程序处理或者人工处理
        // 这里用于打印日志，排查原因
        log4jLog.error(String.format("消息入队失败，消息被返回，Meseage=%s, replyCode=%s, replyText=%s, exchange=%s, routingKey=%s",
                message, replyCode, replyText, exchange, routingKey));
    }
}
