package com.srl.system.service;

import com.srl.system.bean.Menu;
import com.srl.system.dao.MenuDao;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @File name:  MenuService.java
 * @Description:   
 * @Create on:  2018年8月18日 下午2:21:36
 * @LinkPage :  
 * @ChangeList
 * ---------------------------------------------------
 * Date         Editor              ChangeReasons
 *
 *
 */
public class MenuService {
	
	private MenuDao dao=new MenuDao();
	
	public void insertMenu(Menu menu) throws Exception{
		dao.insert(menu);
	}
	public int deleteMenu(Menu menu) throws Exception{
		return dao.deleteMenu(menu);
	}
	public int deleteMenuById(String id) throws Exception{
		return dao.deleteMenuBySql("delete from s_menu where id=?",new Object[]{id});
	}
	public int deleteMenuBySql(String sql,Object... param) throws Exception{
		return dao.deleteMenuBySql(sql, param);
	}
	public int updateMenu(String sql,Object... param) throws Exception{
		return dao.updateMenuBySql(sql, param);
	}
	
	public List<Menu> queryMenu(String sql,Object... param) throws Exception{
		return dao.queryBySql(sql, param);
	}
	
	public List<Menu> queryMenu(Menu menu) throws Exception{
		return dao.queryMenus(menu);
	}
	
	public List<Menu> queryMenuAndChildMenu(String sql,Object... param) throws Exception{
		List<Menu> list= dao.queryBySql(sql, param);
		for(Menu m:list){
			List<Menu> childMenu = new ArrayList<Menu>();
			Menu menu=new Menu();
			menu.setParentId(m.getId());
			childMenu=queryMenuAndChildMenu("select * from s_menu where parent_id=?",new Object[] { m.getId() });
			m.setChildMenu(childMenu);
		}
		return list;
	}
	
	//查当前菜单以及它下属的子菜单
	public List<Menu> queryMenuAndChildMenu(Menu menu) throws Exception{
		List<Menu> list=dao.queryMenus(menu);//根据条件查这个菜单
		for(Menu m:list){//取出每个菜单，把子菜单拼上来
			List<Menu> childMenus = new ArrayList<Menu>();
			Menu childMenu=new Menu();
			childMenu.setParentId(m.getId());//把这个菜单的ID放到子菜单的parentId里面
			childMenus=queryMenuAndChildMenu(childMenu);//查当前菜单以及它下属的子菜单
			m.setChildMenu(childMenus);//把查的东西拼好
		}
		return list;
	}
	
	public List<Menu> queryMenuAndRole(Menu menu) throws Exception{
		List<Menu> list=dao.queryMenus(menu);//根据条件查这个菜单
		for(Menu m:list){
			m.setRoles(dao.queryMenuRoles(m));
		}
		return list;
	}
	

}
