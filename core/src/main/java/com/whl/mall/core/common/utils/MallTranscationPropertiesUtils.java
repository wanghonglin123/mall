package com.whl.mall.core.common.utils;
/**
 * @Title: MallTranscationPropertiesUtils
 * @Package: com.whl.mall.core.common.utils
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-06-02 下午 10:47
 * @Version: V2.0.0
 */

import com.whl.mall.core.rabbitmq.constants.RabbitConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MallTranscationPropertiesUtils
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-06-02 下午 10:47
 */
public final class MallTranscationPropertiesUtils {
    private MallTranscationPropertiesUtils() {
    }
    public static final String ROLE_SERVICE_METHODS = RabbitConstants.ROLE_SERVICE_METHOD_SAVE;

    public static final String RESOURCE_SERVICE_METHODS = RabbitConstants.RESOURCE_SERVICE_METHOD_SAVE;

    public static final String TRANSCATION_SERVICE_METHODS = RabbitConstants.TRANSCATION_SERVICE_METHOD_SAVE;
}
