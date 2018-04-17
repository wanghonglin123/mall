package com.whl.mall.core.common.utils;
/**
 * @Title: MallMd5Utils
 * @Package: com.whl.mall.core.common.utils
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-16 下午 10:23
 * @Version: V2.0.0
 */


import com.whl.mall.core.MallException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @ClassName: MallMd5Utils
 * @Description: MD5 工具 32位
 * @Author: WangHongLin
 * @Date: 2018-04-16 下午 10:23
 */
public class MallMd5Utils extends DigestUtils{

    private static final String MESSAGE = "md5加密参数非法";

    /**
     * 不可逆加密
     * @param value
     * @return
     * @throws Exception
     */
    public static String md5ForData(String value) throws MallException{
        if (StringUtils.isEmpty(value)) {
            throw new MallException(MESSAGE);
        }
        return md5Hex(md5(value));
    }

    @Test
    public void test() throws Exception{
        System.out.println(MallMd5Utils.md5ForData(null));
    }
}
