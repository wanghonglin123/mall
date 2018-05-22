package com.whl.mall.core.common.beans;

import com.whl.mall.core.component.MallDomain;
import com.whl.mall.core.log.adapter.MallLoggerAdapter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: XunbaoWebBeans
 * @Description: web 顶级父类，存放所有的bean
 * 注意：实际开发不要采用这种方法：1： 会导致继承的类过大 2：RPC调用一个服务，RPC 需要注入全部的接口信息，导致需要配置
 * @Author: WangHonglin timo-wang@msyc.cc
 * @Date: 2018/3/20
 */
public class MallBeans {
    /**
     * log4j 日志组件
     */
    @Autowired
    private MallLoggerAdapter log4jLog;

    /**
     * domain
     */
    @Autowired
    private MallDomain domain;

    /**
     * @return log4jLog
     */
    public MallLoggerAdapter getLog4jLog() {
        return log4jLog;
    }

    public MallDomain getDomain() {
        return domain;
    }
}
