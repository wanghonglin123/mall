package com.whl.mall.core.common.utils;
/**
 * @Title: MallBase64Utils
 * @Package: com.whl.mall.core.common.utils
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-15 下午 11:28
 * @Version: V2.0.0
 */

import com.whl.mall.core.MallAjaxException;

import java.util.Base64;

/**
 * @ClassName: MallBase64Utils
 * @Description: Base 64 编码，解码
 * @Author: WangHongLin
 * @Date: 2018-04-15 下午 11:28
 */
public final class MallBase64Utils {
    private MallBase64Utils() {}
    public static final String CHARSETNAME_UTF_8 = "utf-8";

    public static String encode(String value) throws Exception{
        if (value == null) {
            throw new MallAjaxException("参数非法");
        }
        return Base64.getEncoder().encodeToString(value.getBytes(CHARSETNAME_UTF_8));
    }

    public static String decode(String value) throws Exception{
        if (value == null) {
            throw new MallAjaxException("参数非法");
        }
        byte[] byteValue = Base64.getDecoder().decode(value.getBytes(CHARSETNAME_UTF_8));
        return new String(byteValue, CHARSETNAME_UTF_8);
    }
}
