package com.whl.mall.core.configura.rabbitmq.constants;
/**
 * @Title: RabbitConstants
 * @Package: com.whl.mall.core.configura.rabbitmq.constants
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-18 下午 11:29
 * @Version: V2.0.0
 */

/**
 * @ClassName: RabbitConstants
 * @Description: RabbitMQ 常量
 * @Author: WangHongLin
 * @Date: 2018-05-18 下午 11:29
 */
public final class RabbitConstants {
    private RabbitConstants() {

    }

    public static final String MEMBER_QUEUE_NAME = "member.queue";
    public static final String ROLE_QUEUE_NAME = "role.queue";
    public static final String RESOURCES_QUEUE_NAME = "resources.queue";
}
