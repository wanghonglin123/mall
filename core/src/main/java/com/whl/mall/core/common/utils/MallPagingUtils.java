package com.whl.mall.core.common.utils;
/**
 * @Title: MallPagingUtils
 * @Package: com.whl.mall.core.common.utils
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-19 下午 10:53
 * @Version: V2.0.0
 */

import com.whl.mall.core.MallException;
import com.whl.mall.core.common.constants.MallMessage;
import com.whl.mall.core.common.constants.MallNumberConstants;
import com.whl.mall.core.common.constants.MallStatus;

/**
 * @ClassName: MallPagingUtils
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-19 下午 10:53
 */
public final class MallPagingUtils {
    private MallPagingUtils() {
    }

    /**
     * 最大行数
     */
    public static final int max_rows = 50;
    /**
     * 获取开始行和结束行
     * @param pageNum
     * @param rows
     * @return
     */
    public static int[] getBeginAndEndRows(Integer pageNum, Integer rows) throws MallException{
        if (pageNum == null || rows == null) {
            throw new MallException(MallStatus.HTTP_STATUS_400, MallMessage.INVALID_PARAMETER);
        }
        if (pageNum < MallNumberConstants.ONE || pageNum > Integer.MAX_VALUE
                || rows > max_rows || rows < MallNumberConstants.ONE) {
            throw new MallException(MallStatus.HTTP_STATUS_400, MallMessage.INVALID_PARAMETER);
        }
        int startRows = (pageNum - MallNumberConstants.ONE) * rows;
        int endRoes = pageNum * rows;
        return new int[]{startRows,endRoes};
    }

}
