package com.whl.mall.core.configura.rabbitmq.listeners;
/**
 * @Title: RabbitRecoveryListener
 * @Package: com.whl.mall.core.configura.rabbitMQ.listeners
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 5:15
 * @Version: V2.0.0
 */

import com.rabbitmq.client.Recoverable;
import com.rabbitmq.client.RecoveryListener;

/**
 * @ClassName: RabbitRecoveryListener
 * @Description: 恢复监听器，比如服务器重连等
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 5:15
 */
public class RabbitRecoveryListener implements RecoveryListener{
    @Override
    public void handleRecovery(Recoverable recoverable) {
        System.out.println("处理恢复");
    }

    @Override
    public void handleRecoveryStarted(Recoverable recoverable) {
        System.out.println("处理恢复已启动");
    }
}
