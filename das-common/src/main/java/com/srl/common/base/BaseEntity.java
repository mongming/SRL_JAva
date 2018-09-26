/**
 *
 */
package com.srl.common.base;

import java.util.Date;

import com.srl.common.annotation.NotColumn;
import com.srl.common.annotation.TableCloumn;
import com.srl.common.annotation.TableId;

/**
 * 公有的字段类型
 *
 * @Description:
 * @author: zwl
 * @date: 2018年8月19日 下午3:28:22
 */
public class BaseEntity {

    @TableId("id")
    @NotColumn
    private Integer id;

    @TableCloumn("is_del")
    private Boolean isDel;

    @TableCloumn("is_enable")
    private Boolean isEnable;

    @TableCloumn("create_time")
    private Date createTime;

    @TableCloumn("create_id")
    private Integer createId;

    @TableCloumn("update_time")
    private Date updateTime;

    @TableCloumn("update_id")
    private Integer updateId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

}
