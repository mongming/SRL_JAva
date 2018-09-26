package com.srl.system.test;

import java.util.List;

import com.srl.system.bean.Menu;
import com.srl.system.bean.Role;
import com.srl.system.service.MenuService;
import org.junit.Test;



/**
 *
 * @File name:  TestMenuService.java
 * @Description:   
 * @Create on:  2018年8月18日 下午2:48:16
 * @LinkPage :  
 * @ChangeList
 * ---------------------------------------------------
 * Date         Editor              ChangeReasons
 *
 *
 */
public class TestMenuService {

	@Test
	public void testQueryMenuAndChildMenu() throws Exception {
		MenuService ms=new MenuService();
		Menu m=new Menu();
		m.setMenuName("权限管理");
		List<Menu> list = ms.queryMenuAndChildMenu(m);
		print("主菜单 ",list);
	}
	
	private void print(String msg,List<Menu> list){
		for(Menu m:list){
			System.out.println(msg+m.getMenuName());
			if(null!=m.getChildMenu()&&m.getChildMenu().size()>0){
				print(msg+"的子菜单 ",m.getChildMenu());
			}
		}
	}
	
	
	@Test
	public void testQueryMenuAndRole() throws Exception {
		MenuService ms=new MenuService();
		Menu m=new Menu();
		m.setMenuName("权限管理");
		List<Menu> list = ms.queryMenuAndRole(m);
		for(Menu menu:list){
			System.out.println(menu.getMenuName());
			for(Role r:menu.getRoles()){
				System.out.println(r.getRoleName());
			}
		}
	}

}
