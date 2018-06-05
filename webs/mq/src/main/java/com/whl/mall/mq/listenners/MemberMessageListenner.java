package com.whl.mall.mq.listenners;
/**
 * @Title: MemberMessageListenner
 * @Package: com.whl.mall.mq.listenners
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-28 下午 10:33
 * @Version: V2.0.0
 */

import com.whl.mall.core.transcation.ext.MallMessageListenerExt;
import org.springframework.amqp.core.MessageProperties;

/**
 * @ClassName: MemberMessageListenner
 * @Description: 成员消息监听器
 * @Author: WangHongLin
 * @Date: 2018-05-28 下午 10:33
 */
public class MemberMessageListenner extends MallMessageListenerExt {
    @Override
    public void handleMessage(Object messageBody, MessageProperties properties) throws Exception {
        System.out.println(1);
    }
}
