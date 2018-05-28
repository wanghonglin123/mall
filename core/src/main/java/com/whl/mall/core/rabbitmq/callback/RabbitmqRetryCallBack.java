package com.whl.mall.core.rabbitmq.callback;
/**
 * @Title: RetryCallBack
 * @Package: com.whl.mall.core.configura.rabbitMQ.callback
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 6:44
 * @Version: V2.0.0
 */

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;

/**
 * @ClassName: RetryCallBack
 * @Description: 重试回调
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 6:44
 */
public class RabbitmqRetryCallBack implements RetryCallback{
    @Override
    public Object doWithRetry(RetryContext retryContext) throws Throwable {
        System.out.println("执行重试");
        return null;
    }
}
