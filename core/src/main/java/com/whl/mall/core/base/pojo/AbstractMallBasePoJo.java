package com.whl.mall.core.base.pojo;
/**
 * @Title: AbstractMallBasePoJo
 * @Package: com.whl.mall.core.base.pojo
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-17 下午 8:46
 * @Version: V2.0.0
 */

import com.whl.mall.core.rabbitmq.constants.RabbitConstants;

/**
 * @ClassName: AbstractMallBasePoJo
 * @Description: POJO扩展
 * @Author: WangHongLin
 * @Date: 2018-04-17 下午 8:46
 */
public abstract class AbstractMallBasePoJo implements MallBasePoJo {
    @Override
    public String getModule() {
        return null;
    }

    @Override
    public boolean autoAck() {
        return false;
    }

    @Override
    public String moduleAlias() {
        return null;
    }

    @Override
    public String exchange() {
        return null;
    }

    @Override
    public String routingKey() {
        return null;
    }
}
