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
package com.whl.mall;/**
 * @Title: MemberData
 * @Package: com.whl.mall.data.member
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

import com.whl.mall.core.base.service.ext.MallDbServiceExt;
import org.junit.Test;

import java.sql.Date;

/**
 * @ClassName: MemberData
 * @Description: 成员数据
 * @Author: WangHonglin timo-wang@msyc.cc
 * @Date: 2018/4/10
 */
public class MemberData {

    @Test
    public void addData() {
        final String sql = "insert into tb_member (idx, idx_code, name, telphone, email,\n" +
                "    pwd, create_time, update_time,\n" +
                "    create_by_member_idx_code, update_by_member_idx_code,\n" +
                "    version, status, ext\n" +
                "    )\n" +
                "    values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            final long times = System.nanoTime();
            final Date date = Date.valueOf("2018-08-10");
            MallDbServiceExt.execute(preparedStatement -> {
                    for (int i = 0; i < 10; i++) {
                        preparedStatement.setLong(1, times + i);
                        preparedStatement.setLong(2, times + i);
                        preparedStatement.setString(3, "user" + i);
                        preparedStatement.setString(4, "1807037753" + i);
                        preparedStatement.setString(5, "62581607" + i + "@qq.com");
                        preparedStatement.setString(6, "123456");
                        preparedStatement.setDate(7, date);
                        preparedStatement.setDate(8, date);
                        preparedStatement.setLong(9, times + i);
                        preparedStatement.setLong(10, times + i);
                        preparedStatement.setLong(11, times + i);
                        preparedStatement.setShort(12, (short) i);
                        preparedStatement.setString(13, "");
                        preparedStatement.addBatch();
                    }
                    preparedStatement.executeBatch();
            }, sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
