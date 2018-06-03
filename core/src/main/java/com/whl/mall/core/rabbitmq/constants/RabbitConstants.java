package com.whl.mall.core.rabbitmq.constants;
/**
 * @Title: RabbitConstants
 * @Package: com.whl.mall.core.rabbitmq.constants
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
    public static final String TRANSCATION_QUEUE_NAME = "transcation.queue";

    public static final String TOP_EXCHANGE_NAME = "com.mall.topExchange";
    public static final String HEADERS_EXCHANGE_NAME = "com.mall.headersExchange";
    public static final String DIRECT_EXCHANGE_NAME = "com.mall.directExchange";
    public static final String FANOUT_EXCHANGE_NAME = "com.mall.fanoutExchange";

    public static final String MEMBER_ROUTINGKEY = "member.routingkey";
    public static final String ROLE_ROUTINGKEY = "role.routingkey";
    public static final String RESOURCE_ROUTINGKEY = "resource.routingkey";
    public static final String TRANSCATION_ROUTINGKEY = "transcation.routingkey";

    public static final String TRANSCATION_SERVICE_BEAN_NAME = "transcationService";
    public static final String TRANSCATION_SERVICE_METHOD_SAVE = "save";

    public static final String ROLE_SERVICE_BEAN_NAME = "roleService";
    public static final String ROLE_SERVICE_METHOD_SAVE = "save";

    public static final String RESOURCE_SERVICE_BEAN_NAME = "resourceService";
    public static final String RESOURCE_SERVICE_METHOD_SAVE = "save";
}
