package com.whl.mall.core;
/**
 * @Title: MallMQException
 * @Package: com.whl.mall.core
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-06-01 下午 11:56
 * @Version: V2.0.0
 */

/**
 * @ClassName: MallMQException
 * @Description: MQ 异常
 * @Author: WangHongLin
 * @Date: 2018-06-01 下午 11:56
 */
public class MallMQException extends MallException{
    public MallMQException(int status) {
        super(status);
    }

    public MallMQException(Throwable cause) {
        super(cause);
    }

    public MallMQException(int status, String message) {
        super(status, message);
    }

    public MallMQException(int status, String message, Throwable cause) {
        super(status, message, cause);
    }

    public MallMQException(int status, Throwable cause) {
        super(status, cause);
    }

    public MallMQException(int status, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(status, message, cause, enableSuppression, writableStackTrace);
    }
}
