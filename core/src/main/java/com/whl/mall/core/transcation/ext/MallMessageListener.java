package com.whl.mall.core.transcation.ext;
/**
 * @Title: MallMessageListener
 * @Package: com.whl.mall.core.base.pojo
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-06-03 下午 3:39
 * @Version: V2.0.0
 */

import org.springframework.amqp.core.MessageProperties;

/**
 * @ClassName: MallMessageListener
 * @Description: Mall 消息监听
 * @Author: WangHongLin
 * @Date: 2018-06-03 下午 3:39
 */
public interface MallMessageListener {
    /**
     * 消息转换
     * @param contentEncoding 消息编码，由生产商配置
     * @param body 消息主体
     * @return Object 转换后的消息
     * @throws Exception 运行时异常
     */
    Object conventMessage(String contentEncoding, byte[] body) throws Exception;

    /**
     * 处理消息
     */
    void handleMessage(Object messageBody, MessageProperties properties) throws Exception;

}
