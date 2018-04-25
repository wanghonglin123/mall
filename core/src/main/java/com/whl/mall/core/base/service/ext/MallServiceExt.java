/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营品牌洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际品牌直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */
package com.whl.mall.core.base.service.ext;/**
 * @Title: MallServiceExt
 * @Package: com.whl.mall.common.base.service.ext
 * @Description:
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: WangHongLin timo-wang@msyc.cc
 * @Date: 2018/4/10
 * @Version: V2.1.5
 * @Modify-by: WangHongLin timo-wang@msyc.cc
 * @Modify-date: 2018/4/10
 * @Modify-version: 2.0.10
 * @Modify-description: 新增：增，删，改，查方法
 */

import com.whl.mall.core.MallException;
import com.whl.mall.core.MallGridResult;
import com.whl.mall.core.base.dao.MallBaseMapper;
import com.whl.mall.core.base.pojo.MallBasePoJo;
import com.whl.mall.core.base.service.MallBaseService;
import com.whl.mall.core.common.constants.MallJavaTypeConstants;
import com.whl.mall.core.common.constants.MallPojoFieldNameConstants;
import com.whl.mall.core.common.utils.MallPagingUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: MallServiceExt
 * @Description: 服务扩展类
 * @Author: WangHonglin timo-wang@msyc.cc
 * @Date: 2018/4/10
 */
public abstract class MallServiceExt<T extends MallBasePoJo> implements MallBaseService<T> {

    @Autowired
    private MallBaseMapper<T> baseMapper;

    @Override
    public T save(T po) throws MallException{
        // 设置po公共属性值,不采用反射方式，比较消耗性能
        inspectPojoFieldValue(po);
        baseMapper.save(po);
        return po;
    }

    @Override
    public int delete(T po) throws MallException {
        return baseMapper.delete(po);
    }

    @Override
    public int update(T po) throws MallException {
        return baseMapper.update(po);
    }

    @Override
    public List<T> queryDataByCondition(T po) throws MallException {
        return baseMapper.queryDataByCondition(po);
    }

    /**
     * 查询单个数据所有信息
     * @param po
     * @return
     */
    public T queryOneAllInfoByCondition(T po) throws MallException {
        return baseMapper.queryOneAllInfoByCondition(po);
    }

    /**
     * 查询单个数据部分信息，性能比所有信息更快
     * @param po
     * @return
     */
    public T queryOneSomeInfoByCondition(T po) throws MallException {
        return baseMapper.queryOneSomeInfoByCondition(po);
    }

    /**
     * 根据主键更新
     * @param idx
     * @return
     */
    public int updateByPrimaryKey(long idx) {
        return baseMapper.updateByPrimaryKey(idx);
    }

    /**
     * 分页查询结果
     * @param po po
     * @param pageNum 页码
     * @param rows 行数
     * @param orderBy 排序
     * @return
     * @throws MallException 运行时异常
     */
    public MallGridResult queryPageDataByCondition(T po, Integer pageNum, Integer rows, String orderBy) throws MallException{
        int[] beginAndEndRows = MallPagingUtils.getBeginAndEndRows(pageNum, rows);
        int total = baseMapper.queryTotal(po);
        List<T> data = baseMapper.queryPageDataByCondition(po, beginAndEndRows[0], beginAndEndRows[1], orderBy);
        return MallGridResult.ok(data, total);
    }

    /**
     * 使用Srping 反射工具类，设置pojo字段值
     * @param pojo 实体类
     * @throws MallException
     */
    protected void inspectPojoFieldValue(T pojo) throws MallException{
        // 获取类注解，到时候开发

        // 执行所有字段，设置新值
        ReflectionUtils.doWithFields(pojo.getClass(), new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                setFieldValue(field, pojo);
            }

            public String getNewStringValue(Object oldValue) {
                String strValue = String.valueOf(oldValue);
                String newValue = null;
                // 是null 或者 "" , " " ， "   "
                if (StringUtils.isEmpty(strValue)) {
                    // 设置默认空字符串, 不然数据库字段会为NULL，会影响性能
                    newValue = "";
                } else if (StringUtils.isNotEmpty(strValue)) {
                    // 去掉左右两边空格
                    newValue = strValue.trim();
                } else { // 预留特别情况
                    newValue = "";
                }
                return newValue;
            }

            public Long getNewLongValue() {
                // 目前使用这种时间戳，到时候改成分布式唯一id
                return System.nanoTime();
            }

            public void setFieldValue(Field field, T pojo) throws IllegalArgumentException, IllegalAccessException {
                // 允许访问,不然获取不到私有属性的信息
                field.setAccessible(true);
                // 获取属性值
                Object value = field.get(pojo);
                // 获取字段类型名称。比如java.lang.Interger
                String typeName = field.getType().getName();
                // 获取字段名称
                String fieldName = field.getName();
                Object newValue = null;
                if (MallPojoFieldNameConstants.FIELD_SERIALVERSIONUID.equals(fieldName)) {
                    return;
                }
                if (null != value) {
                    // 字符串区分，字符串可能存在非法字符等安全问题，做一下过滤
                    if (MallJavaTypeConstants.TYPE_REFERENCE_STRING_NAME.equals(typeName)
                            || MallJavaTypeConstants.TYPE_REFERENCE_CHARSEQUENCE_NAME.equals(typeName)) {  // 字符串类型 String
                        field.set(pojo, getNewStringValue(value));
                    }
                    return;
                }
                if (MallJavaTypeConstants.TYPE_REFERENCE_STRING_NAME.equals(typeName)
                        || MallJavaTypeConstants.TYPE_REFERENCE_CHARSEQUENCE_NAME.equals(typeName)) {  // 字符串类型 String
                    newValue = "";
                } else if (MallJavaTypeConstants.TYPE_REFERENCE_LONG_NAME.equals(typeName)) {  // Long类型
                    if (MallPojoFieldNameConstants.FIELD_IDX.equals(fieldName)
                            || MallPojoFieldNameConstants.FIELD_IDX_CODE.equals(fieldName)
                            || MallPojoFieldNameConstants.FIELD_VERSION.equals(fieldName)) {
                        newValue = getNewLongValue();
                    } else if (null == value) {
                        // 必须设置为0L, Long类型， 否则报错
                        newValue = 0L;
                    }
                } else if (MallJavaTypeConstants.TYPE_REFERENCE_DATE_NAME.equals(typeName)) {  // 日期类型 Date
                    if (null == value) {
                        newValue = new Date();
                    }
                } else if (MallJavaTypeConstants.TYPE_REFERENCE_INTEGER_NAME.equals(typeName)) {  // int类型
                    if (null == value) {
                        newValue = 0;
                    }
                } else if (MallJavaTypeConstants.TYPE_REFERENCE_SHORT_NAME.equals(typeName)) {  // Short类型
                    if (null == value) {
                        if (MallPojoFieldNameConstants.FIELD_STATUS.equals(fieldName)) {
                            newValue = (short) 1;
                        } else {
                            // Short 必须强制转换
                            newValue = (short) 0;
                        }
                    }
                }
                field.set(pojo, newValue);
            }
        });
    }
}
