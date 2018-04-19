package com.whl.mall.core.common.utils;
/**
 * @Title: MallJsonUtils
 * @Package: com.whl.mall.common.utils
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-09 下午 9:29
 * @Version: V2.0.0
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whl.mall.core.MallException;
import com.whl.mall.core.common.constants.MallStatus;

import java.util.Collection;

/**
 * @ClassName: MallJsonUtils
 * @Description: JSON 工具
 * @Author: WangHongLin
 * @Date: 2018-04-09 下午 9:29
 */
public final class MallJsonUtils {
    private MallJsonUtils() {}

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转json
     * @param data 对象
     * @return
     * @throws Exception
     */
    public static String objectToJson(Object data) throws MallException {
        if (data == null) {
            return null;
        }
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new MallException(MallStatus.HTTP_STATUS_500, e);
        }
    }

    /**
     * json转对象
     * @param json json字符串
     * @param beanType Object 类型
     * @param <T> 任意对象
     * @return
     * @throws Exception
     */
    public static <T> T jsonToObject(String json, Class<T> beanType) throws Exception{
        if (json == null || "".equals(json) || beanType == null) {
            return null;
        }
        return MAPPER.readValue(json, beanType);
    }

    /**
     * json转数组
     * @param json json字符串
     * @param beanType Object 类型
     * @param <T> 任意对象
     * @return
     * @throws Exception
     */
    public static <T> T[] jsonToArray(String json, Class<T> beanType) throws Exception{
        if (json == null || "".equals(json) || beanType == null) {
            return null;
        }
        JavaType javaType = MAPPER.getTypeFactory().constructArrayType(beanType);
        return MAPPER.readValue(json, javaType);
    }

    /**
     * json转集合
     * @param json json字符串
     * @param beanType Object 类型
     * @param <T> 任意对象
     * @return
     * @throws Exception
     */
    public static <T> Collection<T> jsonToCollection(String json, Class<? extends Collection> collectionType, Class<T> beanType) throws Exception{
        if (json == null || "".equals(json) || beanType == null) {
            return null;
        }
        JavaType javaType = MAPPER.getTypeFactory().constructCollectionType(collectionType, beanType);
        return MAPPER.readValue(json, javaType);
    }
}
