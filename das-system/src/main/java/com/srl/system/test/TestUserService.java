package com.srl.system.test;

import java.util.List;

import com.srl.system.bean.Role;
import com.srl.system.bean.User;
import com.srl.system.service.UserService;
import org.junit.Test;



/**
 *
 * @File name:  TestUserService.java
 * @Description:   
 * @Create on:  2018年8月18日 下午3:58:47
 * @LinkPage :  
 * @ChangeList
 * ---------------------------------------------------
 * Date         Editor              ChangeReasons
 *
 *
 */
public class TestUserService {

	@Test
	public void testQueryUserAndRole() throws Exception {
		User user=new User();
		user.setUserName("小明");
		UserService userSerive=new UserService();
		List<User> list=userSerive.queryUserAndRole(user);
		for(User u:list){
			System.out.println(u.getUserName());
			for(Role r:u.getRoles()){
				System.out.println("该用户的权限："+r.getRoleName());
			}
		}
	}
	
	

}
