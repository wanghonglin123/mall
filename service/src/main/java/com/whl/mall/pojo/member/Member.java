package com.whl.mall.pojo.member;

import com.whl.mall.core.base.pojo.AbstractMallBasePoJo;

import java.util.Date;

public class Member extends AbstractMallBasePoJo {
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
    private String name;

    /**
     * 手机号
     */
    private String telphone;

    /**
     * 邮箱
     */
    private String email;

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

    /**
     * @return idx
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * @param idx idx
     */
    public void setIdx(Long idx) {
        this.idx = idx;
    }

    /**
     * @return idxCode
     */
    public Long getIdxCode() {
        return idxCode;
    }

    /**
     * @param idxCode idxCode
     */
    public void setIdxCode(Long idxCode) {
        this.idxCode = idxCode;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return telphone
     */
    public String getTelphone() {
        return telphone;
    }

    /**
     * @param telphone telphone
     */
    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd pwd
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return createByMemberIdxCode
     */
    public Long getCreateByMemberIdxCode() {
        return createByMemberIdxCode;
    }

    /**
     * @param createByMemberIdxCode createByMemberIdxCode
     */
    public void setCreateByMemberIdxCode(Long createByMemberIdxCode) {
        this.createByMemberIdxCode = createByMemberIdxCode;
    }

    /**
     * @return updateByMemberIdxCode
     */
    public Long getUpdateByMemberIdxCode() {
        return updateByMemberIdxCode;
    }

    /**
     * @param updateByMemberIdxCode updateByMemberIdxCode
     */
    public void setUpdateByMemberIdxCode(Long updateByMemberIdxCode) {
        this.updateByMemberIdxCode = updateByMemberIdxCode;
    }

    /**
     * @return version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * @param version version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * @return status
     */
    public Short getStatus() {
        return status;
    }

    /**
     * @param status status
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * @return ext
     */
    public String getExt() {
        return ext;
    }

    /**
     * @param ext ext
     */
    public void setExt(String ext) {
        this.ext = ext;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }
}