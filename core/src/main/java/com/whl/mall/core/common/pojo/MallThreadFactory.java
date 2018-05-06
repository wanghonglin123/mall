package com.whl.mall.core.common.pojo;
/**
 * @Title: MallThreadFactory
 * @Package: com.whl.mall.core.common.pojo
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 3:34
 * @Version: V2.0.0
 */

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: MallThreadFactory
 * @Description: 自定义线程工厂
 * @Author: WangHongLin
 * @Date: 2018-05-06 下午 3:34
 */
public class MallThreadFactory implements ThreadFactory{
    private static final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public MallThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix + "-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread( r,namePrefix + threadNumber.getAndIncrement());
        if (t.isDaemon())
            t.setDaemon(true);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}
