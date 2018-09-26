package com.srl.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * @File name:  MyUtil.java
 * @Description:   
 * @Create on:  2018年8月18日 上午11:09:34
 * @LinkPage :  
 * @ChangeList
 * ---------------------------------------------------
 * Date         Editor              ChangeReasons
 *
 *
 */
public class MyUtil {

	public static List<Object> getSqlValue(Object o) throws Exception {
		List<Object> list = new ArrayList<Object>();
		Class c = o.getClass();
		Field[] fs = c.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			fs[i].setAccessible(true);
			if (!(fs[i].get(o)==null)) {
				list.add(fs[i].get(o));
			}
		}
		c=c.getSuperclass();
		fs=c.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			fs[i].setAccessible(true);
			if(fs[i].getName().equals("id")){
				continue;
			}
			if (!(fs[i].get(o)==null)) {
				list.add(fs[i].get(o));
			}
		}
		return list;
	}

	/**
	 * 用于将null转换为“”，避免空指针异常(Object版本)
	 * 
	 * @param string
	 * @return 经过处理的字符串
	 */
	public static String emptyCase(Object Str) {
		if (null == Str) {
			return "";
		} else {
			return Str + "";
		}
	}

	/***
	 * 下划线命名转为驼峰命名
	 * @param para
	 *        下划线命名的字符串
	 */

	public static String underlineToHump(String para) {
		StringBuilder result = new StringBuilder();
		String a[] = para.split("_");
		for (String s : a) {
			if (result.length() == 0) {
				result.append(s.toLowerCase());
			} else {
				result.append(s.substring(0, 1).toUpperCase());
				result.append(s.substring(1).toLowerCase());
			}
		}
		return result.toString();
	}

	/***
	* 驼峰命名转为下划线命名
	 * @param para
	 *        驼峰命名的字符串
	 */

	public static String humpToUnderline(String para) {
		StringBuilder sb = new StringBuilder(para);
		int temp = 0;// 定位
		for (int i = 0; i < para.length(); i++) {
			if (Character.isUpperCase(para.charAt(i))) {
				sb.insert(i + temp, "_");
				temp += 1;
			}
		}
		return sb.toString().toUpperCase();
	}

	
	public  static void getParam(HttpServletRequest req,Object obj){
		Class c=obj.getClass();
		
		//由于元素是私有的，所以要用getDeclaredFields才能获取到全部的元素
		Field[] fs=c.getDeclaredFields();
		for(int i=0;i<fs.length;i++){
			//由于元素是私有的，所以要获取一下权限
			fs[i].setAccessible(true);
			try {
				//设置name的值（User对象的，要设置的值是xxx）
				if(fs[i].getType().getName().equals("int")){
					fs[i].setInt(obj, Integer.parseInt(req.getParameter(fs[i].getName())));
				}else{
					fs[i].set(obj, req.getParameter(fs[i].getName()));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
