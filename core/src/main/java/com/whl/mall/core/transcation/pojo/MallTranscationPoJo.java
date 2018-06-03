package com.whl.mall.core.transcation.pojo;
/**
 * @Title: MallTranscationPoJo
 * @Package: com.whl.mall.core.base.pojo
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-06-02 上午 10:53
 * @Version: V2.0.0
 */

import com.whl.mall.core.base.pojo.MallBasePoJo;

import java.util.Map;

/**
 * @ClassName: MallTranscationPoJo
 * @Description: 顶级事物Pojo， 多库事物， db Redis 事物
 * @Author: WangHongLin
 * @Date: 2018-06-02 上午 10:53
 */
public interface MallTranscationPoJo extends MallBasePoJo {
    Enum[] getEnum();
}
