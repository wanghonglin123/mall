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
 * @Description: 恢复回调
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 6:52
 */
public class RabbitRecoveryCallback implements RecoveryCallback{
    @Override
    public Object recover(RetryContext retryContext) throws Exception {
        return null;
    }
}
