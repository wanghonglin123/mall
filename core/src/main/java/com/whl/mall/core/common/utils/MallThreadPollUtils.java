package com.whl.mall.core.common.utils;
/**
 * @Title: MallThreadPollUtils
 * @Package: com.whl.mall.core.common.utils
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-06-03 上午 11:18
 * @Version: V2.0.0
 */

import com.whl.mall.core.common.pojo.MallThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: MallThreadPollUtils
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-06-03 上午 11:18
 */
public final class MallThreadPollUtils {
    private MallThreadPollUtils() {}

    public static final ExecutorService executorService = Executors.newCachedThreadPool(new MallThreadFactory("mall_common_thread"));
}
