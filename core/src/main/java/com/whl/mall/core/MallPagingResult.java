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
package com.whl.mall.core;/**
 * @Title: MallPagingResult
 * @Package: com.whl.mall.core
 * @Description:
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: WangHongLin timo-wang@msyc.cc
 * @Date: 2018/4/19
 * @Version: V2.1.5
 * @Modify-by: WangHongLin timo-wang@msyc.cc
 * @Modify-date: 2018/4/19
 * @Modify-version: 2.0.10
 * @Modify-description: 新增：增，删，改，查方法
 */

import org.junit.Test;

/**
 * @ClassName: MallPagingResult
 * @Description: 自定义分页结果, 目前使用MallGridResult
 * @Author: WangHonglin timo-wang@msyc.cc
 * @Date: 2018/4/19
 */
public class MallPagingResult extends MallResult{
    /**
     * 行数
     */
    private Integer rows;
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 总数量
     */
    private Integer total;
    /**
     * 开始数
     */
    private Integer startNum;
    /**
     * 结束数
     */
    private Integer endNum;

    private MallPagingResult() {
        super();
    }

    private MallPagingResult(int status, String message, Object data) {
        super(status, message, data);
    }

    private MallPagingResult(int status, String message, Object data, Integer rows, Integer pageNum,
                            Integer totalPage, Integer total, Integer startNum, Integer endNum) {
        super(status, message, data);
        this.rows = rows;
        this.pageNum = pageNum;
        this.totalPage = totalPage;
        this.total = total;
        this.startNum = startNum;
        this.endNum = endNum;
    }

    /**
     * @return rows
     */
    public Integer getRows() {
        return rows;
    }

    /**
     * @param rows rows
     */
    public void setRows(Integer rows) {
        this.rows = rows;
    }

    /**
     * @return pageNum
     */
    public Integer getPageNum() {
        return pageNum;
    }

    /**
     * @param pageNum pageNum
     */
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * @return totalPage
     */
    public Integer getTotalPage() {
        return totalPage;
    }

    /**
     * @param totalPage totalPage
     */
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * @return total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return startNum
     */
    public Integer getStartNum() {
        return startNum;
    }

    /**
     * @param startNum startNum
     */
    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }

    /**
     * @return endNum
     */
    public Integer getEndNum() {
        return endNum;
    }

    /**
     * @param endNum endNum
     */
    public void setEndNum(Integer endNum) {
        this.endNum = endNum;
    }
}
