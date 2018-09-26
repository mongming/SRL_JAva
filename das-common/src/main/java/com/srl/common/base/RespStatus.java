/**
 * 
 */
package com.srl.common.base;

import com.google.gson.Gson;

/** 
 * 用于统一返回前端的对象信息
* @Description:
* @author: zwl
* @date: 2018年8月19日 上午10:41:44
*/
public class RespStatus {
	
	/**
	 * 状态码：200,404,500
	 */
	private String code;
	
	/**
	 * 是否执行成功：true成功，false失败
	 */
	private boolean success;
	
	public static String ok(){
		RespStatus respStatus=new RespStatus();
		respStatus.setCode("200");
		respStatus.setMessage("执行成功!");
		respStatus.setSuccess(true);
		Gson gson=new Gson();
		return gson.toJson(respStatus);
	}
	
	public static String ok(Object data){
		RespStatus respStatus=new RespStatus();
		respStatus.setCode("200");
		respStatus.setMessage("执行成功!");
		respStatus.setSuccess(true);
		respStatus.setData(data);
		Gson gson=new Gson();
		return gson.toJson(respStatus);
	}
	
	public static String ok(String code,String message,Object data){
		RespStatus respStatus=new RespStatus();
		respStatus.setCode(code);
		respStatus.setMessage(message);
		respStatus.setSuccess(true);
		respStatus.setData(data);
		Gson gson=new Gson();
		return gson.toJson(respStatus);
	}
	
	public static String error(){
		RespStatus respStatus=new RespStatus();
		respStatus.setCode("500");
		respStatus.setMessage("执行失败!");
		respStatus.setSuccess(false);
		Gson gson=new Gson();
		return gson.toJson(respStatus);
	}
	
	public static String error(Object data){
		RespStatus respStatus=new RespStatus();
		respStatus.setCode("500");
		respStatus.setMessage("执行失败!");
		respStatus.setSuccess(false);
		respStatus.setData(data);
		Gson gson=new Gson();
		return gson.toJson(respStatus);
	}
	
	public static String error(String code,String message,Object data){
		RespStatus respStatus=new RespStatus();
		respStatus.setCode(code);
		respStatus.setMessage(message);
		respStatus.setSuccess(false);
		respStatus.setData(data);
		Gson gson=new Gson();
		return gson.toJson(respStatus);
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 提示信息
	 */
	private String message;
	
	/**
	 * 传到前台的数据Bean
	 */
	private Object data;
	
	
}
