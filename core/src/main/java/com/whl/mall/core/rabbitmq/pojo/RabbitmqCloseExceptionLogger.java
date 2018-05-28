package com.whl.mall.core.rabbitmq.pojo;
/**
 * @Title: RabbitMQCloseExceptionLogger
 * @Package: com.whl.mall.core.configura.rabbitMQ
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 4:36
 * @Version: V2.0.0
 */

import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.logging.Log;
import org.springframework.amqp.rabbit.connection.RabbitUtils;
import org.springframework.amqp.support.ConditionalExceptionLogger;

/**
 * @ClassName: RabbitMQCloseExceptionLogger
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 4:36
 */
public class RabbitmqCloseExceptionLogger implements ConditionalExceptionLogger {
    @Override
    public void log(Log logger, String message, Throwable throwable) {
        if(throwable instanceof ShutdownSignalException) {
            ShutdownSignalException cause = (ShutdownSignalException)throwable;
            if(RabbitUtils.isPassiveDeclarationChannelClose(cause)) {
                if(logger.isDebugEnabled()) {
                    logger.debug(message + ": " + cause.getMessage());
                }
            } else if(RabbitUtils.isExclusiveUseChannelClose(cause)) {
                if(logger.isInfoEnabled()) {
                    logger.info(message + ": " + cause.getMessage());
                }
            } else if(!RabbitUtils.isNormalChannelClose(cause)) {
                logger.error(message + ": " + cause.getMessage());
            }
        } else {
            logger.error("Unexpected invocation of " + this.getClass() + ", with message: " + message, throwable);
        }
    }
}
