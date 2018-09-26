package com.srl.system.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.srl.common.base.BaseDaoImpl;
import com.srl.common.db.C3p0Utils;
import com.srl.common.utils.MyUtil;
import com.srl.system.bean.Menu;
import com.srl.system.bean.Role;


/**
 *
 * @File name:  MenuDao.java
 * @Description:   
 * @Create on:  2018年8月18日 上午11:59:42
 * @LinkPage :  
 * @ChangeList
 * ---------------------------------------------------
 * Date         Editor              ChangeReasons
 *
 *
 */
public class MenuDao extends BaseDaoImpl<Menu> {
	
	private RoleDao roleDao=new RoleDao();

	/**
	 * 新增菜单
	 * @param 要新增的菜单对象
	 */
	public void insert(Menu menu) throws Exception{
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO s_menu(menu_name, login_name, password, sex, birthday, address, login_fail_count, is_del, is_enable, create_time, create_id, update_time, update_id)");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		List<Object> list = MyUtil.getSqlValue(menu);
		/*list.add(menu.getMenuName());
		list.add(menu.getLoginName());
		list.add(menu.getPassword());
		list.add(menu.getSex());
		list.add(menu.getBirthday());
		list.add(menu.getAddress());
		list.add(menu.getLoginFailCount());
		list.add(menu.getIsDel());
		list.add(menu.getIsEnable());
		list.add(menu.getCreateTime());
		list.add(menu.getCreateId());
		list.add(menu.getUpdateTime());
		list.add(menu.getUpdateId());*/
		
		Connection conn=C3p0Utils.getConnection();
		update(conn, sql.toString(),list.toArray() );
		C3p0Utils.closeConnection(conn);
	}
	
	/**
	 * 根据id更新所有字段
	 * @param menu 要更新menu
	 * @return 更新了多少个
	 * @throws Exception 
	 */
	public int updateMenu(Menu menu) throws Exception{
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE s_menu ");
		sql.append("SET menu_name=?, login_name=?, password=?, sex=?, birthday=?, address=?, login_fail_count=?, is_del=?, is_enable=?, create_time=?, create_id=?, update_time=?, update_id=?");
		sql.append(" where id=?");
		List<Object> list=MyUtil.getSqlValue(menu);
		list.add(menu.getId());
		Connection conn=C3p0Utils.getConnection();
		int rNum=update(conn, sql.toString(), list.toArray());
		C3p0Utils.closeConnection(conn);
		return rNum;
	}
	
	/**
	 * 根据sql更新
	 * @param sql要运行的sql
	 * @param param 要传入的参数（包括ID）
	 * @return 更新的条数
	 * @throws Exception
	 */
	public int updateMenuBySql(String sql,Object[]... param) throws Exception{
		Connection conn=C3p0Utils.getConnection();
		int rNum=update(conn, sql.toString(), param);
		C3p0Utils.closeConnection(conn);
		return rNum;
	}
	
	/**
	 * 根据sql删除
	 * @param sql要运行的sql
	 * @param param传入的参数
	 * @return 删除了多少条
	 * @throws Exception
	 */
	public int deleteMenuBySql(String sql,Object[]... param) throws Exception{
		Connection conn=C3p0Utils.getConnection();
		int rNum=update(conn, sql.toString(), param);
		C3p0Utils.closeConnection(conn);
		return rNum;
	}
	
	/**
	 * 根据menu中的属性删除相关的数据
	 * @param menu 带有要删除的属性的menu
	 * @return 删除了多少条
	 * @throws Exception
	 */
	public int deleteMenu(Menu menu) throws Exception{
		if(null==menu){
			return 0;
		}
		StringBuilder sql=new StringBuilder("delete from s_menu where 1=1 ");
		List<Object> list = new ArrayList<Object>();
		Class c=menu.getClass();
		Field[] fs=c.getDeclaredFields();
		for(int i=0;i<fs.length;i++){
			fs[i].setAccessible(true);
			Object o=fs[i].get(menu);
			if(!(null==o||MyUtil.emptyCase(o).equals(""))){
				list.add(o);
				sql.append("and ").append(MyUtil.humpToUnderline(fs[i].getName())).append("=?");
			}
		}
		Connection conn=C3p0Utils.getConnection();
		return update(conn, sql.toString(), list.toArray());
	}
	
	public List<Menu> queryBySql(String sql,Object... o ) throws SQLException{
		Connection conn=C3p0Utils.getConnection();
		List<Menu> list=queryBeanList(conn,sql,o);
		C3p0Utils.closeConnection(conn);
		return list;
	}
	
	public List<Menu> queryMenus(Menu menu) throws Exception{
		StringBuilder sql = new StringBuilder("select * from s_menu where 1=1 ");
		List<Object> list = new ArrayList<Object>();
		Class c=menu.getClass();
		Field[] fs=c.getDeclaredFields();
		for(int i=0;i<fs.length;i++){
			fs[i].setAccessible(true);
			Object o=fs[i].get(menu);
			if(!(null==o||MyUtil.emptyCase(o).equals(""))){
				list.add(o);
				sql.append("and ").append(MyUtil.humpToUnderline(fs[i].getName())).append("=?");
			}
		}
		return queryBySql(sql.toString(), list.toArray());
	}
	
	public List<Role> queryMenuRoles(Menu menu) throws Exception{
		StringBuilder sql = new StringBuilder("select c.* from s_menu a,s_role_menu b ,s_role c ");
		sql.append(" where  a.id=b.menu_id and b.role_id=c.id ");
		List<Role> list = new ArrayList<Role>();
		if(!MyUtil.emptyCase(menu.getId()).equals("")){//如果菜单的ID不为空
			sql.append(" and a.id=?");
			list=roleDao.queryBySql(sql.toString(), new Object[] { menu.getId() });
		}else{
			Menu tempMenu=queryMenus(menu).get(0);
			sql.append(" and a.id=?");
			list=roleDao.queryBySql(sql.toString(), new Object[] { tempMenu.getId() });
		}
		return list;
	}
}
