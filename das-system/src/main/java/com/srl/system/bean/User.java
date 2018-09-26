package com.srl.system.bean;

import java.util.List;

import com.srl.common.annotation.NotColumn;
import com.srl.common.annotation.TableCloumn;
import com.srl.common.annotation.TableName;
import com.srl.common.base.BaseEntity;


@TableName(name="s_user",desc="表名为s_user")
public class User extends BaseEntity {
	
	
	
	
	@TableCloumn("user_name")
	private String userName;
	
	@TableCloumn(value="login_name")
	private String loginName;
	
	@TableCloumn("password")
	private String password;
	
	@TableCloumn("sex")
	private String sex;
	
	@TableCloumn("birthday")
	private String birthday;
	
	@TableCloumn("address")
	private String address;
	
	@TableCloumn("login_fail_count")
	private String loginFailCount;

	@NotColumn
	private List<Role> roles;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLoginFailCount() {
		return loginFailCount;
	}
	
	public void setLoginFailCount(String loginFailCount) {
		this.loginFailCount = loginFailCount;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


}
