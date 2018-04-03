package com.whl.mall.common.beans;

import com.whl.mall.common.log.adapter.MallLoggerAdapter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: XunbaoWebBeans
 * @Description: web 顶级父类，存放所有的bean
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
     * @return log4jLog
     */
    public MallLoggerAdapter getLog4jLog() {
        return log4jLog;
    }

}
