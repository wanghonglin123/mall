package com.whl.mall.pojo.member;

import com.whl.mall.common.base.pojo.MallBasePoJo;

import java.util.Date;

public class ResourceGroup implements MallBasePoJo {
    /**
     * 主键idx，分布式架构，全局唯一递增
     */
    private Long idx;

    /**
     主键idx生成的code，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String idxCodeS;

    /**
     * 主键idx生成的code
     */
    private Long idxCode;

    /**
     * 备注，不能为Null
     */
    private String remark;

    /**
     * 状态，1：启用，2：停用，取值范围为0-9，不可以为null，必须手动设置为0或者1
     */
    private Short status;

    /**
     * 版本号，高并发，乐观锁的解决方案，不可以为null，必须赋值
     */
    private Long version;

    /**
     被哪位成员（member_idx）创建的，不可以为null创建成员idx，不可以为，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String createByMemberIdxDynamicS;

    /**
     * 被哪位成员（member_idx）创建的，不可以为null创建成员idx，不可以为
     */
    private Long createByMemberIdx;

    /**
     被哪位成员（member_idx）修改的，不可以为null创建成员idx，不可以为，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String updateByMemberIdxDynamicS;

    /**
     * 被哪位成员（member_idx）修改的，不可以为null创建成员idx，不可以为
     */
    private Long updateByMemberIdx;

    /**
    创建时间，后续不可以再更新时间，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    private String createTimeEnYyyyMMddHHmmss;

    /**
     * 创建时间，后续不可以再更新时间
     */
    private Date createTime;

    /**
    更新时间，每一次都要更新，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    private String updateTimeEnYyyyMMddHHmmss;

    /**
     * 更新时间，每一次都要更新
     */
    private Date updateTime;

    /**
     * 扩展字段，不可以为null，默认为空字符串
     */
    private String ext;

    /**
     * 资源组
     */
    private String name;

    /**
     * 资源组编码
     */
    private String code;

    /**
     * 排序号
     */
    private Short zindex;

    private static final long serialVersionUID = 1L;

    /**
     * 这个id是字符串的，和数据库中的idx（Long)对应，是JQuery EasyUI组件使用了,
     * 其他地方不要随便使用，例如：不可以使用在DAO层进行业务处理或者插入数据到数据库，
     * 不是使用id，而是使用idx（Long）
     * 唯一标识，idx, JavaScript  对 18位数字idx支持不好，导致最后一位丢失，所以使用字符串id
     */
    private String id;

    /**
     * 主键idx，分布式架构，全局唯一递增
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 主键idx，分布式架构，全局唯一递增
     */
    public void setIdx(Long idx) {
        this.idx = idx;
    }

    /**
     主键idx生成的code，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getIdxCodeS() {
        return String.valueOf(this.idxCode);
    }

    /**
     * 主键idx生成的code
     */
    public Long getIdxCode() {
        return idxCode;
    }

    /**
     * 主键idx生成的code
     */
    public void setIdxCode(Long idxCode) {
        this.idxCode = idxCode;
    }

    /**
     * 备注，不能为Null
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注，不能为Null
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 状态，1：启用，2：停用，取值范围为0-9，不可以为null，必须手动设置为0或者1
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 状态，1：启用，2：停用，取值范围为0-9，不可以为null，必须手动设置为0或者1
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 版本号，高并发，乐观锁的解决方案，不可以为null，必须赋值
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 版本号，高并发，乐观锁的解决方案，不可以为null，必须赋值
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     被哪位成员（member_idx）创建的，不可以为null创建成员idx，不可以为，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getCreateByMemberIdxDynamicS() {
        return this.createByMemberIdxDynamicS;
    }

    /**
     被哪位成员（member_idx）创建的，不可以为null创建成员idx，不可以为，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setCreateByMemberIdxDynamicS(String createByMemberIdxDynamicS) {
         this.createByMemberIdxDynamicS = createByMemberIdxDynamicS;
    }

    /**
     * 被哪位成员（member_idx）创建的，不可以为null创建成员idx，不可以为
     */
    public Long getCreateByMemberIdx() {
        return createByMemberIdx;
    }

    /**
     * 被哪位成员（member_idx）创建的，不可以为null创建成员idx，不可以为
     */
    public void setCreateByMemberIdx(Long createByMemberIdx) {
        this.createByMemberIdx = createByMemberIdx;
    }

    /**
     被哪位成员（member_idx）修改的，不可以为null创建成员idx，不可以为，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getUpdateByMemberIdxDynamicS() {
        return this.updateByMemberIdxDynamicS;
    }

    /**
     被哪位成员（member_idx）修改的，不可以为null创建成员idx，不可以为，
     MyBatis逆向工程，动态生成字符串类型
     */
    public void setUpdateByMemberIdxDynamicS(String updateByMemberIdxDynamicS) {
         this.updateByMemberIdxDynamicS = updateByMemberIdxDynamicS;
    }

    /**
     * 被哪位成员（member_idx）修改的，不可以为null创建成员idx，不可以为
     */
    public Long getUpdateByMemberIdx() {
        return updateByMemberIdx;
    }

    /**
     * 被哪位成员（member_idx）修改的，不可以为null创建成员idx，不可以为
     */
    public void setUpdateByMemberIdx(Long updateByMemberIdx) {
        this.updateByMemberIdx = updateByMemberIdx;
    }

    /**
     * 创建时间，后续不可以再更新时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间，后续不可以再更新时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间，每一次都要更新
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间，每一次都要更新
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 扩展字段，不可以为null，默认为空字符串
     */
    public String getExt() {
        return ext;
    }

    /**
     * 扩展字段，不可以为null，默认为空字符串
     */
    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

    /**
     * 资源组
     */
    public String getName() {
        return name;
    }

    /**
     * 资源组
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 资源组编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 资源组编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 排序号
     */
    public Short getZindex() {
        return zindex;
    }

    /**
     * 排序号
     */
    public void setZindex(Short zindex) {
        this.zindex = zindex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", idx=").append(idx);
        sb.append(", idxCodeS=").append(idxCodeS);
        sb.append(", idxCode=").append(idxCode);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", version=").append(version);
        sb.append(", createByMemberIdxDynamicS=").append(createByMemberIdxDynamicS);
        sb.append(", createByMemberIdx=").append(createByMemberIdx);
        sb.append(", updateByMemberIdxDynamicS=").append(updateByMemberIdxDynamicS);
        sb.append(", updateByMemberIdx=").append(updateByMemberIdx);
        sb.append(", createTimeEnYyyyMMddHHmmss=").append(createTimeEnYyyyMMddHHmmss);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTimeEnYyyyMMddHHmmss=").append(updateTimeEnYyyyMMddHHmmss);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", ext=").append(ext);
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", zindex=").append(zindex);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append("]");
        return sb.toString();
    }
}