package com.srl.system.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.srl.common.base.BaseDaoImpl;
import com.srl.common.db.C3p0Utils;
import com.srl.common.utils.BeanToSqlUtils;
import com.srl.common.utils.MyUtil;
import com.srl.system.bean.Role;
import com.srl.system.bean.User;

/**
 *
 * @File name:  RoleDao.java
 * @Description:   
 * @Create on:  2018年8月18日 下午12:02:03
 * @LinkPage :  
 * @ChangeList
 * ---------------------------------------------------
 * Date         Editor              ChangeReasons
 *
 *
 */
public class RoleDao extends BaseDaoImpl<Role> {


	/**
	 * 新增用户
	 * @param role
	 * @return
	 * @author lhj
	 * @throws SQLException 
	 */
	public int insert(Role role) throws Exception{
		StringBuilder sql=new StringBuilder();
		sql.append(BeanToSqlUtils.insertSql(Role.class));
		System.out.println(sql.toString());
		List<Object> list = MyUtil.getSqlValue(role);
		return update(sql.toString(),list.toArray() );
	}
	
	/**
	 * 根据id更新所有字段
	 * @param role 要更新role
	 * @return 更新了多少个
	 * @throws Exception 
	 */
	public int updateRole(Role role) throws Exception{
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE s_role ");
		sql.append("SET role_name=?, login_name=?, password=?, sex=?, birthday=?, address=?, login_fail_count=?, is_del=?, is_enable=?, create_time=?, create_id=?, update_time=?, update_id=?");
		sql.append(" where id=?");
		List<Object> list=MyUtil.getSqlValue(role);
		list.add(role.getId());
		Connection conn=C3p0Utils.getConnection();
		return update(conn, sql.toString(), list.toArray());
	}
	
	/**
	 * 根据sql更新
	 * @param sql要运行的sql
	 * @param param 要传入的参数（包括ID）
	 * @return 更新的条数
	 * @throws Exception
	 */
	public int updateRoleBySql(String sql,Object[]... param) throws Exception{
		Connection conn=C3p0Utils.getConnection();
		return update(conn, sql.toString(), param);
	}
	
	/**
	 * 根据sql删除
	 * @param sql要运行的sql
	 * @param param传入的参数
	 * @return 删除了多少条
	 * @throws Exception
	 */
	public int deleteRoleBySql(String sql,Object[]... param) throws Exception{
		Connection conn=C3p0Utils.getConnection();
		return update(conn, sql.toString(), param);
	}
	
	/**
	 * 根据role中的属性删除相关的数据
	 * @param role 带有要删除的属性的role
	 * @return 删除了多少条
	 * @throws Exception
	 */
	public int deleteRole(Role role) throws Exception{
		if(null==role){
			return 0;
		}
		StringBuilder sql=new StringBuilder("delete from s_role where 1=1 ");
		List<Object> list = new ArrayList<Object>();
		Class c=role.getClass();
		Field[] fs=c.getDeclaredFields();
		for(int i=0;i<fs.length;i++){
			fs[i].setAccessible(true);
			Object o=fs[i].get(role);
			if(!(null==o||MyUtil.emptyCase(o).equals(""))){
				list.add(o);
				sql.append("and ").append(MyUtil.humpToUnderline(fs[i].getName())).append("=?");
			}
		}
		Connection conn=C3p0Utils.getConnection();
		return update(conn, sql.toString(), list.toArray());
	}
	
	public List<Role> queryBySql(String sql,Object... o ) throws SQLException{
		Connection conn=C3p0Utils.getConnection();
		List<Role> beanList = queryBeanList(conn,sql,o);
		C3p0Utils.closeConnection(conn);
		return beanList;
	}
	
	public List<Role> queryRoles(Role role) throws Exception{
		StringBuilder sql = new StringBuilder("select * from s_role where 1=1 ");
		List<Object> list = new ArrayList<Object>();
		Class c=role.getClass();
		Field[] fs=c.getDeclaredFields();
		for(int i=0;i<fs.length;i++){
			fs[i].setAccessible(true);
			Object o=fs[i].get(role);
			if(!(null==o||MyUtil.emptyCase(o).equals(""))){
				list.add(o);
				sql.append("and ").append(MyUtil.humpToUnderline(fs[i].getName())).append("=?");
			}
		}
		return queryBySql(sql.toString(), list.toArray());
	}
	
	
	public List<User> getUserRoles(Role role) throws Exception{
	
		return null;
	}

	public int deleteRoleBySql(Connection conn, String sql, Object[] roleId) throws SQLException {
		return update(conn, sql.toString(),roleId);
	}
	
}
