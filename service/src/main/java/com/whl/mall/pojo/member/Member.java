package com.whl.mall.pojo.member;

import com.whl.mall.core.base.pojo.MallBasePoJo;

import java.util.Date;

public class Member implements MallBasePoJo {
    /**
     * 主键idx
     */
    private Long idx;

    /**
     * 外部关联idxCode
     */
    private Long idxCode;

    /**
     * 用户名
     */
    private String userName;

    private String pwd;

    private Date createTime;

    private Date updateTime;

    private Long createByMemberIdxCode;

    private Long updateByMemberIdxCode;

    private Long version;

    private Short status;

    private String ext;

    private static final long serialVersionUID = 1L;

    /**
     * 这个id是字符串的，和数据库中的idx（Long)对应，是JQuery EasyUI组件使用了,
     * 其他地方不要随便使用，例如：不可以使用在DAO层进行业务处理或者插入数据到数据库，
     * 不是使用id，而是使用idx（Long）
     * 唯一标识，idx, JavaScript  对 18位数字idx支持不好，导致最后一位丢失，所以使用字符串id
     */
    private String id;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    /**
     * 主键idx
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 主键idx
     */
    public void setIdx(Long idx) {
        this.idx = idx;
    }

    /**
     * 外部关联idxCode
     */
    public Long getIdxCode() {
        return idxCode;
    }

    /**
     * 外部关联idxCode
     */
    public void setIdxCode(Long idxCode) {
        this.idxCode = idxCode;
    }

    /**
     * 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
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

    public Long getCreateByMemberIdxCode() {
        return createByMemberIdxCode;
    }

    public void setCreateByMemberIdxCode(Long createByMemberIdxCode) {
        this.createByMemberIdxCode = createByMemberIdxCode;
    }

    public Long getUpdateByMemberIdxCode() {
        return updateByMemberIdxCode;
    }

    public void setUpdateByMemberIdxCode(Long updateByMemberIdxCode) {
        this.updateByMemberIdxCode = updateByMemberIdxCode;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", idx=").append(idx);
        sb.append(", idxCode=").append(idxCode);
        sb.append(", userName=").append(userName);
        sb.append(", pwd=").append(pwd);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createByMemberIdxCode=").append(createByMemberIdxCode);
        sb.append(", updateByMemberIdxCode=").append(updateByMemberIdxCode);
        sb.append(", version=").append(version);
        sb.append(", status=").append(status);
        sb.append(", ext=").append(ext);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append("]");
        return sb.toString();
    }
}