package com.srl.system.bean;

import java.util.Date;
import java.util.List;

import com.srl.common.annotation.NotColumn;
import com.srl.common.annotation.TableCloumn;
import com.srl.common.annotation.TableName;
import com.srl.common.base.BaseEntity;
/**
 *
 * @File name:  Role.java
 * @Description:   
 * @Create on:  2018年8月16日 下午7:59:29
 * @LinkPage :  
 * @ChangeList
 * ---------------------------------------------------
 * Date         Editor              ChangeReasons
 *
 *
 */
@TableName(name="s_role")
public class Role  extends BaseEntity {
	
	@TableCloumn("role_name")
	private String roleName;
	
	@TableCloumn("remark")
	private String remark;
	
	@NotColumn
	private List<User> users;
	@NotColumn
	private List<Menu> menus;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}


}
