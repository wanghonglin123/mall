package com.whl.mall.core.base.pojo;
/**
 * @Title: AbstractMallBasePoJo
 * @Package: com.whl.mall.core.base.pojo
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-17 下午 8:46
 * @Version: V2.0.0
 */

import java.util.Date;

/**
 * @ClassName: AbstractMallBasePoJo
 * @Description: POJO扩展
 * @Author: WangHongLin
 * @Date: 2018-04-17 下午 8:46
 */
public abstract class AbstractMallBasePoJo implements MallBasePoJo{
    protected long idx;
    protected long idxCode;
    protected Date createTime;
    protected Date updateTime;
    protected long version;
    protected String remark;
    protected String ext;

    public long getIdx() {
        return idx;
    }

    public void setIdx() {
        this.idx = System.nanoTime();
    }

    public long getIdxCode() {
        return idxCode;
    }

    public void setIdxCode(long idxCode) {
        this.idxCode = idxCode;
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

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
