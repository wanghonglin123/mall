package com.whl.mall.pojo.member;

import com.whl.mall.core.base.pojo.AbstractMallBasePoJo;

import java.util.Date;

public class Menu extends AbstractMallBasePoJo {
    /**
     * 菜单idx
     */
    private Long idx;

    /**
     * 菜单idxCode
     */
    private Long idxCode;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 序号
     */
    private Short zindex;

    /**
     * 菜单级别
     */
    private Short level;

    /**
     * 父菜单Idx
     */
    private Long pidx;

    /**
     * 父菜单idxCode
     */
    private Long pidxCode;

    /**
     * 状态
     */
    private Short status;

    /**
     * 菜单编码
     */
    private String code;

    /**
     * 是否顶部显示 0 ：不显示 1：显示
     */
    private Short isTop;

    /**
     * 菜单图片
     */
    private String img;

    /**
     * 版本号
     */
    private Long version;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private Long createByMemberIdxCode;

    /**
     * 修改人
     */
    private Long updateByMemberIdxCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 扩展字段
     */
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
     * 这个pid是字符串的，和数据库中的pidx（Long)对应，是JQuery EasyUI组件使用了,
     * 其他地方不要随便使用，例如：不可以使用在DAO层进行业务处理或者插入数据到数据库，
     * 不是使用id，而是使用pidx（Long）
     * 唯一标识，pidx, JavaScript  对 18位数字pidx支持不好，导致最后一位丢失，所以使用字符串pid
     */
    private String pid;

    /**
     * 菜单路径
     */
    private String url;

    /**
     * 菜单idx
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 菜单idx
     */
    public void setIdx(Long idx) {
        this.idx = idx;
    }

    /**
     * 菜单idxCode
     */
    public Long getIdxCode() {
        return idxCode;
    }

    /**
     * @return id
     */
    public String getId() {
        return this.idx + "";
    }

    /**
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 菜单idxCode
     */
    public void setIdxCode(Long idxCode) {
        this.idxCode = idxCode;
    }

    /**
     * 菜单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 菜单名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 序号
     */
    public Short getZindex() {
        return zindex;
    }

    /**
     * 序号
     */
    public void setZindex(Short zindex) {
        this.zindex = zindex;
    }

    /**
     * 菜单级别
     */
    public Short getLevel() {
        return level;
    }

    /**
     * 菜单级别
     */
    public void setLevel(Short level) {
        this.level = level;
    }

    /**
     * 父菜单Idx
     */
    public Long getPidx() {
        return pidx;
    }

    /**
     * 父菜单Idx
     */
    public void setPidx(Long pidx) {
        this.pidx = pidx;
    }

    /**
     * 父菜单idxCode
     */
    public Long getPidxCode() {
        return pidxCode;
    }

    /**
     * 父菜单idxCode
     */
    public void setPidxCode(Long pidxCode) {
        this.pidxCode = pidxCode;
    }

    /**
     * 状态
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 状态
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 菜单编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 菜单编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 是否顶部显示 0 ：不显示 1：显示
     */
    public Short getIsTop() {
        return isTop;
    }

    /**
     * 是否顶部显示 0 ：不显示 1：显示
     */
    public void setIsTop(Short isTop) {
        this.isTop = isTop;
    }

    /**
     * 菜单图片
     */
    public String getImg() {
        return img;
    }

    /**
     * 菜单图片
     */
    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    /**
     * 版本号
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 版本号
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 创建人
     */
    public Long getCreateByMemberIdxCode() {
        return createByMemberIdxCode;
    }

    /**
     * 创建人
     */
    public void setCreateByMemberIdxCode(Long createByMemberIdxCode) {
        this.createByMemberIdxCode = createByMemberIdxCode;
    }

    /**
     * 修改人
     */
    public Long getUpdateByMemberIdxCode() {
        return updateByMemberIdxCode;
    }

    /**
     * 修改人
     */
    public void setUpdateByMemberIdxCode(Long updateByMemberIdxCode) {
        this.updateByMemberIdxCode = updateByMemberIdxCode;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 扩展字段
     */
    public String getExt() {
        return ext;
    }

    /**
     * 扩展字段
     */
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
        sb.append(", name=").append(name);
        sb.append(", zindex=").append(zindex);
        sb.append(", level=").append(level);
        sb.append(", pidx=").append(pidx);
        sb.append(", pidxCode=").append(pidxCode);
        sb.append(", status=").append(status);
        sb.append(", code=").append(code);
        sb.append(", isTop=").append(isTop);
        sb.append(", img=").append(img);
        sb.append(", version=").append(version);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createByMemberIdxCode=").append(createByMemberIdxCode);
        sb.append(", updateByMemberIdxCode=").append(updateByMemberIdxCode);
        sb.append(", remark=").append(remark);
        sb.append(", ext=").append(ext);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append(", pid=").append(pid);
        sb.append(", url=").append(url);
        sb.append("]");
        return sb.toString();
    }
}