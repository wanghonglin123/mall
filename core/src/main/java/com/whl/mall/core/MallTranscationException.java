package com.whl.mall.core;
/**
 * @Title: MallTranscationException
 * @Package: com.whl.mall.core
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-06-03 下午 1:08
 * @Version: V2.0.0
 */

/**
 * @ClassName: MallTranscationException
 * @Description: 事物异常
 * @Author: WangHongLin
 * @Date: 2018-06-03 下午 1:08
 */
public class MallTranscationException extends MallException{
    public MallTranscationException(int status) {
        super(status);
    }

    public MallTranscationException(Throwable cause) {
        super(cause);
    }

    public MallTranscationException(int status, String message) {
        super(status, message);
    }

    public MallTranscationException(int status, String message, Throwable cause) {
        super(status, message, cause);
    }

    public MallTranscationException(int status, Throwable cause) {
        super(status, cause);
    }

    public MallTranscationException(int status, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(status, message, cause, enableSuppression, writableStackTrace);
    }
}
