package com.srl.system.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.srl.common.base.BaseService;
import com.srl.common.db.C3p0Utils;
import com.srl.system.bean.User;
import com.srl.system.dao.UserDao;

/**
 *
 * @File name:  UserService.java
 * @Description:   
 * @Create on:  2018年8月17日 下午7:50:34
 * @LinkPage :  
 * @ChangeList
 * ---------------------------------------------------
 * Date         Editor              ChangeReasons
 *
 *
 */
public class UserService_bak extends BaseService<User,UserDao> {
	private UserDao userDao=new UserDao();
	
	@Override
	public String getBeanSql() {
		String sql="select * from s_user where id=?";
		return sql;
	}

	@Override
	public UserDao getDao() {
		return userDao;
	}
	
	public User getUser(int id)throws SQLException{
		Connection conn=C3p0Utils.getConnection();
		return getById(conn, id);
	}
	

	/**
	 * 新增用户
	 * @param user
	 * @return
	 * @author lhj
	 * @throws SQLException 
	 */
	public boolean insert(User user) throws SQLException{
		
		String sql="insert into user(user_name,login_name) values(?,?)";
		Connection conn=C3p0Utils.getConnection();
		userDao.update(conn, sql, user.getUserName(),user.getLoginName());
		
		int i=10/0;
		
		userDao.update(conn, sql, user.getUserName(),user.getLoginName());
//		userDao.insert(user);
//		int i=10/0;
//		//添加角色
//		userDao.insert(user);
		return true;
	}

	
	
}
