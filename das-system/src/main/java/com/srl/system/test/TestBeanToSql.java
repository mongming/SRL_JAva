/**
 * 
 */
package com.srl.system.test;

import com.srl.common.utils.BeanToSqlUtils;
import com.srl.system.bean.User;
import org.junit.Test;



/** 
* @Description:
* @author: zwl
* @date: 2018年8月19日 下午4:30:58
*/
public class TestBeanToSql {

	@Test
	public void testInsertSql(){
		System.out.println("=============");
		String insertSql=BeanToSqlUtils.insertSql(User.class);
		System.out.println(insertSql);
		System.out.println("=============");
		
		User user=new User();
		String updateSql=BeanToSqlUtils.updateSql(user.getClass());
		System.out.println(updateSql);
		System.out.println("=============");
		
		System.out.println("=============");
		String querySql=BeanToSqlUtils.querySql(user.getClass());
		System.out.println(querySql);
		System.out.println("=============");
		
	}
}
