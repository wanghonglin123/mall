package com.whl.mall.core.configura.rabbitmq.callback;
/**
 * @Title: RabbitRecoveryCallback
 * @Package: com.whl.mall.core.configura.rabbitmq.callback
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 6:52
 * @Version: V2.0.0
 */

import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryContext;

/**
 * @ClassName: RabbitRecoveryCallback
 * @Description: 重试回调，当rabbitMq rabbitTemplate.execute()，可以自定义重试回调的实现
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 6:52
 */
// 暂无使用地方，先废弃掉，以后需要在用
@Deprecated
public class RabbitRecoveryCallback implements RecoveryCallback{
    @Override
    public Object recover(RetryContext retryContext) throws Exception {
        return null;
    }
}
