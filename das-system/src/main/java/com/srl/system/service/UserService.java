package com.srl.system.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.srl.common.base.BaseService;
import com.srl.common.base.PageBean;
import com.srl.common.db.C3p0Utils;
import com.srl.common.utils.BeanToSqlUtils;
import com.srl.system.bean.User;
import com.srl.system.dao.RoleDao;
import com.srl.system.dao.RoleUserDao;
import com.srl.system.dao.UserDao;
import org.apache.commons.lang3.StringUtils;



/**
 *
 * @File name: UserServiceNew.java
 * @Description:
 * @Create on: 2018年8月18日 下午3:17:26
 * @LinkPage :
 * @ChangeList --------------------------------------------------- Date Editor
 *             ChangeReasons
 *
 *
 */
public class UserService extends BaseService<User, UserDao> {

	private UserDao dao = new UserDao();
	private RoleDao roleDao = new RoleDao();
	private RoleUserDao roleUserDao = new RoleUserDao();
	
	@Override
	public String getBeanSql() {
		return BeanToSqlUtils.querySql(User.class);
	}

	@Override
	public UserDao getDao() {
		return dao;
	}

	public void insertUser(User user) throws Exception {
		dao.insert(user);
	}

	public int deleteUser(User user) throws Exception {
		return dao.deleteUser(user);
	}

	public int deleteUserById(String id) throws Exception {
		return dao.deleteUserBySql("delete from s_user where id=?", new Object[] { id });
	}

	public int deleteUserBySql(String sql, Object... param) throws Exception {
		return dao.deleteUserBySql(sql, param);
	}

	public int updateUser(String sql, Object... param) throws Exception {
		return dao.updateUserBySql(sql, param);
	}
	
	public int updateUser(User user) throws Exception {
		if(user.getCreateTime()==null){
			user.setCreateTime(new Date());
		}
		if(user.getUpdateTime()==null){
			user.setUpdateTime(new Date());
		}
		return dao.updateUser(user);
	}

	public List<User> queryUser(String sql, Object... param) throws Exception {
		return dao.queryBySql(sql, param);
	}

	public List<User> queryUser(User user) throws Exception {
		return dao.queryUsers(user);
	}

	public List<User> queryUserAndRole(String sql, Object... param) throws Exception {
		List<User> list = dao.queryBySql(sql, param);
		for (User u : list) {
			u.setRoles(dao.getUserRoles(u));
		}
		return list;
	}

	// 查当前用户及所有的权限
	public List<User> queryUserAndRole(User user) throws Exception {
		List<User> list = dao.queryUsers(user);// 根据条件查这个用户
		for (User u : list) {// 取出用户中的权限，拼起来
			u.setRoles(dao.getUserRoles(u));
		}
		return list;
	}
	
	/**
	 * 分页查询用户列表
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean<User> queryUserPage(PageBean<User> pageBean, String sql, String countSql, List<Object> param) throws Exception {
		Connection conn = C3p0Utils.getConnection();

		Object cVal = getSigleCloumnVal(C3p0Utils.getConnection(), countSql,param.toArray());
		int total=0;
		if (cVal != null) {
			total=Integer.parseInt(String.valueOf(cVal).replace("null", "0"));
		}
		param.add(pageBean.getStart());
		param.add(pageBean.getLength());
		List<User> queryUser = queryUser(sql,param.toArray());

		pageBean.setAaData(queryUser);
		pageBean.setiTotalDisplayRecords(total);
		pageBean.setiTotalRecords(total);
		
		C3p0Utils.closeConnection(conn);
		return pageBean;
	}

	public List<User> queryUserByRoleId(String roleId) throws SQLException {
		String sql="select r.role_name,r.id as role_id,u.* from s_user u,s_role_user ru,s_role r where u.id=ru.user_id and ru.role_id=r.id ";
		sql+=" and r.id=?";
		List<User> userBeanList = dao.queryBeanList(sql, new Object[]{roleId});
		return userBeanList;
	}

}
