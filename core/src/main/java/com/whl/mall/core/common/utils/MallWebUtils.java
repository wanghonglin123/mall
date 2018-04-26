package com.whl.mall.core.common.utils;
/**
 * @Title: MallWebUtils
 * @Package: com.whl.mall.core.common.utils
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-26 下午 10:16
 * @Version: V2.0.0
 */

import com.whl.mall.core.MallException;
import com.whl.mall.core.MallResult;
import com.whl.mall.core.log.adapter.MallLoggerAdapter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName: MallWebUtils
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-26 下午 10:16
 */
public final class MallWebUtils {
    private MallWebUtils() {}

    /**
     *   使用	response 输出JSON
     * @param response 响应对象
     * @param status 状态
     * @param msg 提示语
     */
    public static void out(ServletResponse response, MallLoggerAdapter loggerAdapter, int status, String msg) {
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.write(MallJsonUtils.objectToJson(MallResult.build(status, msg)));
            out.flush();
        } catch (Exception e) {
            loggerAdapter.error(e);
        }
    }

    /**
     * 是否是Ajax请求
     *
     * @param request 请求对象
     * @return 结果
     */
    public static boolean isAjax(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
