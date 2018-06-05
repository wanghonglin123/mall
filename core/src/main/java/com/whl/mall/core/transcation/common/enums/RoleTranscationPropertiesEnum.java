package com.whl.mall.core.transcation.common.enums;
/**
 * @Title: TranscationPropertiesEnum
 * @Package: com.whl.mall.core.common
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-06-02 下午 11:17
 * @Version: V2.0.0
 */

import com.whl.mall.core.common.utils.MallTranscationPropertiesUtils;
import com.whl.mall.core.rabbitmq.constants.RabbitConstants;
import com.whl.mall.core.transcation.base.TranscationEnum;

/**
 * @ClassName: TranscationPropertiesEnum
 * @Description: 角色事物配置
 * @Author: WangHongLin
 * @Date: 2018-06-02 下午 11:17
 */
public enum RoleTranscationPropertiesEnum implements TranscationEnum{
    /**
     * 角色服务配置
     */
    ROLE(RabbitConstants.ROLE_SERVICE_BEAN_NAME, MallTranscationPropertiesUtils.ROLE_SERVICE_METHODS),

    /**
     * 资源服务配置
     */
    TRANSCATION(RabbitConstants.TRANSCATION_SERVICE_BEAN_NAME, MallTranscationPropertiesUtils.RESOURCE_SERVICE_METHODS),

    /**
     * 事物服务配置
     */
    RESOURCE(RabbitConstants.RESOURCE_SERVICE_BEAN_NAME, MallTranscationPropertiesUtils.TRANSCATION_SERVICE_METHODS);

    /**
     * 目标bean名称
     */
    private String targetBeanName;

    /**
     * 目标方法
     */
    private String[] targetMethods;

    RoleTranscationPropertiesEnum(String beanName, String[] targetMethods) {
        this.targetBeanName = beanName;
        this.targetMethods = targetMethods;
    }


    @Override
    public String getTargetBeanName() {
        return targetBeanName;
    }

    @Override
    public String[] getTargetMethods() {
        return targetMethods;
    }
}
