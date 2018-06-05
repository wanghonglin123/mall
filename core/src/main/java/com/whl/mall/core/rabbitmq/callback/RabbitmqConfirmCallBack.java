package com.whl.mall.core.rabbitmq.callback;
/**
 * @Title: RabbitMQConfirmCallBack
 * @Package: com.whl.mall.core.configura.rabbitMQ
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 6:39
 * @Version: V2.0.0
 */

import com.whl.mall.core.log.MallLog4jLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

/**
 * @ClassName: RabbitMQConfirmCallBack
 * @Description: 发布商确认回调
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 6:39
 */
public class RabbitmqConfirmCallBack implements RabbitTemplate.ConfirmCallback{
    @Autowired
    private MallLog4jLog log4jLog;

    /**
     * 发布消息确认回调，如果发送Broker成功，那么Broker会返回true, 发送失败会返回false 和原因
     * @param correlationData 消息唯一标识
     * @param ack true 发送broker成功， false 发送broker失败
     * @param cause 异常
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // 因为在保存事物的时候，已经把数据全部包装了，这里不重试也不会丢失
        // 在这里不做重试操作，可能重试N次也被拒绝，所以消息失败处理全靠程序处理或者人工处理
        // 这里用于打印日志，排查原因
        if (!ack) {
            log4jLog.error(String.format("发布消息失败，correlationData=%s, ack=%s, cause=%s", correlationData, ack, cause));
        } else {
            log4jLog.info(String.format("发布消息成功，correlationData=%s", correlationData));
        }
    }
}
