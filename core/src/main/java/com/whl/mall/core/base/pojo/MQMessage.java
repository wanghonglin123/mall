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
package com.whl.mall.core.base.pojo;/**
 * @Title: MQMessage
 * @Package: com.whl.mall.core.base.pojo
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

import com.whl.mall.core.base.service.MallBaseMQService;
import com.whl.mall.core.base.service.MallBaseService;
import org.springframework.amqp.core.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MQMessage
 * @Description: MQ message
 * @Author: WangHonglin timo-wang@msyc.cc
 * @Date: 2018/5/14
 */
public abstract class MQMessage extends AbstractMallBasePoJo {
    /**
     * 消息id
     */
    private String messageId;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 目前服务，可以用于执行接口操作
     */
    private MallBaseService targetService;
    /**
     * 消息内容
     */
    private Integer messageCount;
    /**
     * 头部，可以动态添加属性
     */
    private final Map<String, Object> headers = new HashMap();
    /**
     * 优先级
     */
    private Integer priority;
    /**
     * 消息类型 1：新增 2：修改 3:删除
     */
    private Short type;
    /**
     * 是否支持重发
     */
    private Boolean redelivered;
    /**
     * 重发交换机
     */
    private String receivedExchange;
    /**
     * 重发路由key
     */
    private String receivedRoutingKey;
    /**
     * 重发虚拟机
     */
    private String receivedRoutingVM;
    /**
     * 重发userID
     */
    private String receivedUserId;
    /**
     * 延迟重发时间
     */
    private Integer receivedDelay;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MallBaseService getTargetService() {
        return targetService;
    }

    public void setTargetService(MallBaseService targetService) {
        this.targetService = targetService;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Boolean getRedelivered() {
        return redelivered;
    }

    public void setRedelivered(Boolean redelivered) {
        this.redelivered = redelivered;
    }

    public String getReceivedExchange() {
        return receivedExchange;
    }

    public void setReceivedExchange(String receivedExchange) {
        this.receivedExchange = receivedExchange;
    }

    public String getReceivedRoutingKey() {
        return receivedRoutingKey;
    }

    public void setReceivedRoutingKey(String receivedRoutingKey) {
        this.receivedRoutingKey = receivedRoutingKey;
    }

    public String getReceivedRoutingVM() {
        return receivedRoutingVM;
    }

    public void setReceivedRoutingVM(String receivedRoutingVM) {
        this.receivedRoutingVM = receivedRoutingVM;
    }

    public String getReceivedUserId() {
        return receivedUserId;
    }

    public void setReceivedUserId(String receivedUserId) {
        this.receivedUserId = receivedUserId;
    }

    public Integer getReceivedDelay() {
        return receivedDelay;
    }

    public void setReceivedDelay(Integer receivedDelay) {
        this.receivedDelay = receivedDelay;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }
}
