package com.whl.mall.mq.listenners;
/**
 * @Title: MemberMessageListenner
 * @Package: com.whl.mall.mq.listenners
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-28 下午 10:33
 * @Version: V2.0.0
 */

import com.whl.mall.core.base.pojo.MallBasePoJo;
import com.whl.mall.core.rabbitmq.listeners.MessageListennersExt;
import com.whl.mall.pojo.member.Member;
import com.whl.mall.pojo.member.Role;

/**
 * @ClassName: MemberMessageListenner
 * @Description: 成员消息监听器
 * @Author: WangHongLin
 * @Date: 2018-05-28 下午 10:33
 */
public class RoleMessageListenner extends MessageListennersExt{
    @Override
    protected void handleMessage(Object content) throws Exception {
        Role role = (Role) content;
        getLog4jLog().info("处理成员消息业务");
    }

    @Override
    protected Class<? extends MallBasePoJo> getJavaType() {
        return Role.class;
    }
}
