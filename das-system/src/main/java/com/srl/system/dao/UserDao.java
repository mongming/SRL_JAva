package com.srl.system.dao;

import com.srl.common.annotation.TableCloumn;
import com.srl.common.base.BaseDaoImpl;
import com.srl.common.db.C3p0Utils;
import com.srl.common.utils.MyUtil;
import com.srl.system.bean.Role;
import com.srl.system.bean.User;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UserDao extends BaseDaoImpl<User> {
	
	private RoleDao roleDao=new RoleDao();
   
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 * @author lhj
	 * @throws SQLException 
	 */
	public void insert(User user) throws Exception{
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO s_user(");
		StringBuilder sql2 = new StringBuilder(" VALUES( ");
		
		//循环user类里面所有的属性，生成 属性名=？,  这种格式的sql
				Class c=user.getClass();
				Field[] fs=c.getDeclaredFields();
				for(int i=0;i<fs.length;i++){
					fs[i].setAccessible(true);
					if(!(fs[i].get(user)==null)){
						TableCloumn t=fs[i].getAnnotation(TableCloumn.class);
						if(t!=null){//如果属性有注解tableCloumn,就根据注解名生成字段名
							sql.append(t.value()).append(",");
							sql2.append("?,");
						}else{//如果没有的话，根据类属性名，驼峰转下划线
							sql.append(MyUtil.humpToUnderline(fs[i].getName())).append(",");
							sql2.append("?,");
						}
					}
				}
				//由于有base在，循环base中的所有属性（ID除外）
				c=c.getSuperclass();
				fs=c.getDeclaredFields();
				for(int i=0;i<fs.length;i++){
					fs[i].setAccessible(true);
					if(fs[i].getName().equals("id")){
						continue;
					}
					if(!(fs[i].get(user)==null)){
						TableCloumn t=fs[i].getAnnotation(TableCloumn.class);
						if(t!=null){//如果属性有注解tableCloumn,就根据注解名生成字段名
							sql.append(t.value()).append(",");
							sql2.append("?,");
						}else{//如果没有的话，根据类属性名，驼峰转下划线
							sql.append(MyUtil.humpToUnderline(fs[i].getName())).append(",");
							sql2.append("?,");
						}
					}
				}
				sql.deleteCharAt(sql.length()-1).append(")");
				sql2.deleteCharAt(sql2.length()-1).append(")");
		
		List<Object> list = MyUtil.getSqlValue(user);
		Connection conn=C3p0Utils.getConnection();
		update(conn, sql.append(sql2).toString(),list.toArray() );
		C3p0Utils.closeConnection(conn);
		
	}
	
	/**
	 * 根据id更新所有字段
	 * @param user 要更新user
	 * @return 更新了多少个
	 * @throws Exception 
	 */
	public int updateUser(User user) throws Exception{
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE s_user ");
		sql.append("SET  ");
		
		//循环user类里面所有的属性，生成 属性名=？,  这种格式的sql
		Class c=user.getClass();
		Field[] fs=c.getDeclaredFields();
		for(int i=0;i<fs.length;i++){
			fs[i].setAccessible(true);
			if(!(fs[i].get(user)==null)){
				TableCloumn t=fs[i].getAnnotation(TableCloumn.class);
				if(t!=null){//如果属性有注解tableCloumn,就根据注解名生成字段名
					sql.append(t.value()).append("=?,");
				}else{//如果没有的话，根据类属性名，驼峰转下划线
					sql.append(MyUtil.humpToUnderline(fs[i].getName())).append("=?");
				}
			}
		}
		//由于有base在，循环base中的所有属性（ID除外）
		c=c.getSuperclass();
		fs=c.getDeclaredFields();
		for(int i=0;i<fs.length;i++){
			fs[i].setAccessible(true);
			if(fs[i].getName().equals("id")){
				continue;
			}
			if(!(fs[i].get(user)==null)){
				TableCloumn t=fs[i].getAnnotation(TableCloumn.class);
				if(t!=null){//如果属性有注解tableCloumn,就根据注解名生成字段名
					sql.append(t.value()).append("=?,");
				}else{//如果没有的话，根据类属性名，驼峰转下划线
					sql.append(MyUtil.humpToUnderline(fs[i].getName())).append("=?");
				}
			}
		}
		sql.deleteCharAt(sql.length()-1);
		
		sql.append(" where id=?");
		List<Object> list=MyUtil.getSqlValue(user);
		list.add(user.getId());
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
	public int updateUserBySql(String sql,Object[]... param) throws Exception{
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
	public int deleteUserBySql(String sql,Object... param) throws Exception{
		Connection conn=C3p0Utils.getConnection();
		int rNum=update(conn, sql.toString(), param);
		C3p0Utils.closeConnection(conn);
		return rNum;
	}
	
	/**
	 * 根据user中的属性删除相关的数据
	 * @param user 带有要删除的属性的user
	 * @return 删除了多少条
	 * @throws Exception
	 */
	public int deleteUser(User user) throws Exception{
		if(null==user){
			return 0;
		}
		StringBuilder sql=new StringBuilder("delete from s_user where 1=1 ");
		List<Object> list = new ArrayList<Object>();
		Class c=user.getClass();
		Field[] fs=c.getDeclaredFields();
		for(int i=0;i<fs.length;i++){
			fs[i].setAccessible(true);
			Object o=fs[i].get(user);
			if(!(null==o||MyUtil.emptyCase(o).equals(""))){
				list.add(o);
				sql.append("and ").append(MyUtil.humpToUnderline(fs[i].getName())).append("=?");
			}
		}
		Connection conn=C3p0Utils.getConnection();
		int rNum=update(conn, sql.toString(), list.toArray());
		C3p0Utils.closeConnection(conn);
		return rNum;
	}
	
	public List<User> queryBySql(String sql,Object... o ) throws SQLException{
		Connection conn=C3p0Utils.getConnection();
		List<User> list=queryBeanList(conn,sql,o);
		C3p0Utils.closeConnection(conn);
		return list;
	}
	
	public List<User> queryUsers(User user) throws Exception{
		StringBuilder sql = new StringBuilder("select * from s_user where 1=1 ");
		List<Object> list = new ArrayList<Object>();
		Class c=user.getClass();
		Field[] fs=c.getDeclaredFields();
		for(int i=0;i<fs.length;i++){
			fs[i].setAccessible(true);
			Object o=fs[i].get(user);
			if(!(null==o||MyUtil.emptyCase(o).equals(""))){
				list.add(o);
				sql.append("and ").append(MyUtil.humpToUnderline(fs[i].getName())).append("=?");
			}
		}
		c=c.getSuperclass();
		fs=c.getDeclaredFields();
		for(int i=0;i<fs.length;i++){
			fs[i].setAccessible(true);
			Object o=fs[i].get(user);
			if(!(null==o||MyUtil.emptyCase(o).equals(""))){
				list.add(o);
				sql.append("and ").append(MyUtil.humpToUnderline(fs[i].getName())).append("=?");
			}
		}
		return queryBySql(sql.toString(), list.toArray());
	}
	
	public List<Role> getUserRoles(User user) throws Exception{
		List<Role> list = new ArrayList<Role>();
		StringBuilder sql = new StringBuilder("select c.* from s_user a ,s_role_user b,s_role c ");
		sql.append(" where a.id=b.user_id and b.role_id=c.id ");
		
		if(!MyUtil.emptyCase(user.getId()).equals("")){
			sql.append(" and user_id=? ");
			list=roleDao.queryBySql(sql.toString(),user.getId() );
		}else{
			List<User> users=queryUsers(user);
			User u=users.get(0);
			list=roleDao.queryBySql(sql.toString(), u.getId());
		}
		return list;
	}

}
