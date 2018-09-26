package com.srl.system.bean;

import java.util.Date;

/**
 *
 * @File name:  LoginLog.java
 * @Description:   
 * @Create on:  2018年8月16日 下午8:09:01
 * @LinkPage :  
 * @ChangeList
 * ---------------------------------------------------
 * Date         Editor              ChangeReasons
 *
 *
 */
public class LoginLog {

	private String logId;
	private String username;
	private String account;
	private Date loginTime;
	/**
	 * @return the logId
	 */
	public String getLogId() {
		return logId;
	}
	/**
	 * @param logId the logId to set
	 */
	public void setLogId(String logId) {
		this.logId = logId;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * @return the loginTime
	 */
	public Date getLoginTime() {
		return loginTime;
	}
	/**
	 * @param loginTime the loginTime to set
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}
