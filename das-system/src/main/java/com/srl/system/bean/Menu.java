package com.srl.system.bean;

import java.util.Date;
import java.util.List;

import com.srl.common.annotation.NotColumn;

/**
 *
 * @File name:  Menu.java
 * @Description:   
 * @Create on:  2018年8月16日 下午7:54:01
 * @LinkPage :  
 * @ChangeList
 * ---------------------------------------------------
 * Date         Editor              ChangeReasons
 *
 *
 */
public class Menu {

	@NotColumn
	private String id;
	private String menuName;
	private String menuPath;
	private String level;
	private String parentId;
	private String is_del;
	private String is_enable;
	private Date createTime;
	private String createId;
	private Date updateTime;
	private String updateId;
	@NotColumn
	private List<Menu> childMenu;// 这个菜单下面有哪些子菜单
	@NotColumn
	private List<Role> roles;// 哪些角色有这些菜单


	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}


	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<Menu> getChildMenu() {
		return childMenu;
	}

	public void setChildMenu(List<Menu> childMenu) {
		this.childMenu = childMenu;
	}


	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the is_del
	 */
	public String getIs_del() {
		return is_del;
	}

	/**
	 * @param is_del the is_del to set
	 */
	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}

	/**
	 * @return the is_enable
	 */
	public String getIs_enable() {
		return is_enable;
	}

	/**
	 * @param is_enable the is_enable to set
	 */
	public void setIs_enable(String is_enable) {
		this.is_enable = is_enable;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the createId
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * @param createId the createId to set
	 */
	public void setCreateId(String createId) {
		this.createId = createId;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the updateId
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * @param updateId the updateId to set
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Menu [id=" + id + ", menuName=" + menuName + ", menuPath=" + menuPath + ", level="
				+ level + ", parentId=" + parentId + ", is_del=" + is_del + ", is_enable="
				+ is_enable + ", createTime=" + createTime + ", createId=" + createId
				+ ", updateTime=" + updateTime + ", updateId=" + updateId + ", childMenu="
				+ childMenu + ", roles=" + roles + "]";
	}

}
