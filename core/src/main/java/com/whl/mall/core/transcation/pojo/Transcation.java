package com.whl.mall.core.transcation.pojo;

import com.whl.mall.core.annotations.MallMQ;
import com.whl.mall.core.rabbitmq.constants.RabbitConstants;
import com.whl.mall.core.transcation.base.MallAbstractTranscatonPojo;

import java.util.Date;

/**
 * 通用事务PO, 特殊事务，需要实现查询结果的扩展此类
 */
@MallMQ(module = "transcation", routingKey = RabbitConstants.TRANSCATION_ROUTINGKEY, tag="transcation")
public class Transcation extends MallAbstractTranscatonPojo {
    /**
     * 返回结果
     */
    private String resultMsg;

    private Long idx;

    private Long idxCode;

    /**
     * 事务体（多个表的封装数据）
     */
    private String transcationBody;

    /**
     * 异常信息
     */
    private String cause;

    /**
     * 事务标签，用于分类
     */
    private String tag;

    /**
     * 返回状态码（400， 500， 其它）
     */
    private Short resultStatu;

    /**
     * mq 发送状态 1：成功 0：失败
     */
    private Short mqSendStatus;

    /**
     * mq 消费状态（1：成功 2：失败 3：待消费）
     */
    private Short mqConsumeStatus;

    /**
     * 事务状态（0： 已删除 1：成功 2：失败 3: 待执行 ）
     */
    private Short transcationStatus;

    private Long version;

    private String ext;

    private Date createTime;

    private Date updateTime;

    private Long createMemberIdxCode;

    private Long updateMemberIdxCode;

    private static final long serialVersionUID = 1L;

    /**
     * 这个id是字符串的，和数据库中的idx（Long)对应，是JQuery EasyUI组件使用了,
     * 其他地方不要随便使用，例如：不可以使用在DAO层进行业务处理或者插入数据到数据库，
     * 不是使用id，而是使用idx（Long）
     * 唯一标识，idx, JavaScript  对 18位数字idx支持不好，导致最后一位丢失，所以使用字符串id
     */
    private String id;

    /**
     * 返回结果
     */
    public String getResultMsg() {
        return resultMsg;
    }

    /**
     * 返回结果
     */
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg == null ? null : resultMsg.trim();
    }

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public Long getIdxCode() {
        return idxCode;
    }

    public void setIdxCode(Long idxCode) {
        this.idxCode = idxCode;
    }

    /**
     * 事务体（多个表的封装数据）
     */
    public String getTranscationBody() {
        return transcationBody;
    }

    /**
     * 事务体（多个表的封装数据）
     */
    public void setTranscationBody(String transcationBody) {
        this.transcationBody = transcationBody == null ? null : transcationBody.trim();
    }

    /**
     * 异常信息
     */
    public String getCause() {
        return cause;
    }

    /**
     * 异常信息
     */
    public void setCause(String cause) {
        this.cause = cause == null ? null : cause.trim();
    }

    /**
     * 事务标签，用于分类
     */
    public String getTag() {
        return tag;
    }

    /**
     * 事务标签，用于分类
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * 返回状态码（400， 500， 其它）
     */
    public Short getResultStatu() {
        return resultStatu;
    }

    /**
     * 返回状态码（400， 500， 其它）
     */
    public void setResultStatu(Short resultStatu) {
        this.resultStatu = resultStatu;
    }

    /**
     * mq 发送状态 1：成功 0：失败
     */
    public Short getMqSendStatus() {
        return mqSendStatus;
    }

    /**
     * mq 发送状态 1：成功 0：失败
     */
    public void setMqSendStatus(Short mqSendStatus) {
        this.mqSendStatus = mqSendStatus;
    }

    /**
     * mq 消费状态（1：成功 2：失败）
     */
    public Short getMqConsumeStatus() {
        return mqConsumeStatus;
    }

    /**
     * mq 消费状态（1：成功 2：失败）
     */
    public void setMqConsumeStatus(Short mqConsumeStatus) {
        this.mqConsumeStatus = mqConsumeStatus;
    }

    /**
     * 事务状态（1：成功 2：失败）
     */
    public Short getTranscationStatust() {
        return transcationStatus;
    }

    /**
     * 事务状态（1：成功 2：失败）
     */
    public void setTranscationStatust(Short transcationStatust) {
        this.transcationStatus = transcationStatust;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateMemberIdxCode() {
        return createMemberIdxCode;
    }

    public void setCreateMemberIdxCode(Long createMemberIdxCode) {
        this.createMemberIdxCode = createMemberIdxCode;
    }

    public Long getUpdateMemberIdxCode() {
        return updateMemberIdxCode;
    }

    public void setUpdateMemberIdxCode(Long updateMemberIdxCode) {
        this.updateMemberIdxCode = updateMemberIdxCode;
    }
    /**
     * 这个id是字符串的，和数据库中的idx（Long)对应，是JQuery EasyUI组件使用了,
     * 其他地方不要随便使用，例如：不可以使用在DAO层进行业务处理或者插入数据到数据库，
     * 不是使用id，而是使用idx（Long）
     * 唯一标识，idx, JavaScript  对 18位数字idx支持不好，导致最后一位丢失，所以使用字符串id
     */
    /*@Override
    public String getId() {
        return this.idx + "";
    }*/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", resultMsg=").append(resultMsg);
        sb.append(", idx=").append(idx);
        sb.append(", idxCode=").append(idxCode);
        sb.append(", transcationBody=").append(transcationBody);
        sb.append(", cause=").append(cause);
        sb.append(", tag=").append(tag);
        sb.append(", resultStatu=").append(resultStatu);
        sb.append(", mqSendStatus=").append(mqSendStatus);
        sb.append(", mqConsumeStatus=").append(mqConsumeStatus);
        sb.append(", transcationStatust=").append(transcationStatus);
        sb.append(", version=").append(version);
        sb.append(", ext=").append(ext);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createMemberIdxCode=").append(createMemberIdxCode);
        sb.append(", updateMemberIdxCode=").append(updateMemberIdxCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append("]");
        return sb.toString();
    }
}