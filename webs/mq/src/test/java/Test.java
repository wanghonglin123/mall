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
/**
 * @Title: EnumTest
 * @Package: PACKAGE_NAME
 * @Description:
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: WangHongLin timo-wang@msyc.cc
 * @Date: 2018/5/31
 * @Version: V2.1.5
 * @Modify-by: WangHongLin timo-wang@msyc.cc
 * @Modify-date: 2018/5/31
 * @Modify-version: 2.0.10
 * @Modify-description: 新增：增，删，改，查方法
 */

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName: EnumTest
 * @Description:
 * @Author: WangHonglin timo-wang@msyc.cc
 * @Date: 2018/5/31
 */
public class Test {
    @org.junit.Test
    public void test() {
        Map map = new TreeMap<>();
        map.put("7", 1);
        map.put("2", 2);
        map.put("3",3);
    }

    /**
     * 使用java 自带jdk 排序
     */
    @org.junit.Test
    public void test1(){
        int[] data = {10,3,2,9,7,5,21,16};
        Arrays.sort(data);
        for(int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }

    /**
     *
     */
    @org.junit.Test
    public void test2() {
        int[] data = {1,10,3,2,9,7,5,21,16,8,8};
        int k, f, g;
        int size = data.length;
        // 比较次数， 默认size - 1
        int compareCount = size - 1;
        int count = 0;
        for(int i = 0; i < size; i++) {
            count++;
            // 每循环一次比较将确定最大值或者最小值，所以比较次数可以减i，这样性能可以快一半，比较次数compareCount >>2 <<2
            for (int j = 0; j < compareCount - i; j++) {
                g = j + 1;
                k = data[j];
                f = data[g];
                if (k > f) {
                    data[j] = f;
                    data[g] = k;
                }
                count++;
            }
        }
        Arrays.stream(data).forEach(value -> {
            System.out.println(value);
        });
        System.out.println(count);
    }
}
