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
package com.whl.mall.core.annotations;/**
 * @Title: MallMQ
 * @Package: com.whl.mall.core.annotations
 * @Description:
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: WangHongLin timo-wang@msyc.cc
 * @Date: 2018/5/14
 * @Version: V2.1.5
 * @Modify-by: WangHongLin timo-wang@msyc.cc
 * @Modify-date: 2018/5/14
 * @Modify-version: 2.0.10
 * @Modify-description: 新增：增，删，改，查方法
 */

import org.springframework.amqp.core.MessageDeliveryMode;

import java.lang.annotation.*;

/**
 * @ClassName: MallMQ
 * @Description: 自定义MQ 注解，用于判断是否需要发送MQ
 * @Author: WangHonglin timo-wang@msyc.cc
 * @Date: 2018/5/14
 */
// @Target 表示该注解用于什么地方。默认值为任何元素，表示该注解用于什么地方
@Target(ElementType.TYPE)
/*@Retention– 定义该注解的生命周期
RetentionPolicy.SOURCE : 在编译阶段丢弃。这些注解在编译结束之后就不再有任何意义，所以它们不会写入字节码。@Override, @SuppressWarnings都属于这类注解。
RetentionPolicy.CLASS : 在类加载的时候丢弃。在字节码文件的处理中有用。注解默认使用这种方式
RetentionPolicy.RUNTIME : 始终不会丢弃，运行期也保留该注解，因此可以使用反射机制读取该注解的信息。我们自定义的注解通常使用这种方式。*/
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MallMQ {
    /**
     * 模块
     * @return
     */
    String module();

    /**
     * ack 确认，true 手动确认， false 自动确认
     * @return
     */
    boolean autoAck() default true;

    /**
     * 模块别名， 默认为null
     * @return
     */
    String moduleAlias();

    /**
     * 交换机名称
     * @return
     */
    String exchangeName();

    /**
     * RoutKey
     * @return
     */
    String routingKey();

    /**
     * 消息是否持久
     * @return
     */
    MessageDeliveryMode persistent() default MessageDeliveryMode.PERSISTENT;
}
