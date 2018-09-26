package com.srl.system.service;

import com.srl.common.base.BaseService;
import com.srl.common.base.PageBean;
import com.srl.common.db.C3p0Utils;
import com.srl.common.utils.BeanToSqlUtils;
import com.srl.system.bean.Role;
import com.srl.system.dao.RoleDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class RoleService extends BaseService<Role,RoleDao> {
	private RoleDao roleDao=new RoleDao();
	
	@Override
	public String getBeanSql() {
		return BeanToSqlUtils.querySql(Role.class);
	}

	@Override
	public RoleDao getDao() {
		return roleDao;
	}
	
	/**
	 * 分页查询
	 * @param pageBean
	 * @param sql
	 * @param sql2
	 * @param paramObj
	 * @throws SQLException
	 */
	public void queryRolePage(PageBean<Role> pageBean, String sql, String sql2, List<Object> paramObj) throws SQLException {
		//执行sql查询语句
		List<Role> roleList = roleDao.queryBySql(sql, paramObj.toArray());
		
		// 执行分页总记录数语句
		paramObj.add(pageBean.getStart());
		paramObj.add(pageBean.getLength());
		Object countVal = getSigleCloumnVal(sql2, paramObj.toArray());
		int total=0;
		if(countVal!=null){
			total= Integer.parseInt(String.valueOf(countVal));
		}
		
		pageBean.setAaData(roleList);
		pageBean.setiTotalDisplayRecords(total);
		pageBean.setiTotalRecords(total);
	}
	
	/**
	 * 添加角色
	 * @param role
	 * @throws Exception 
	 */
	public boolean insertRole(Role role) throws Exception {
		//增加校验，判断新增的角色名称是否重复
		int num=roleDao.insert(role);
		return num>0?true: false;
	}

	public boolean updateRole(Role role) throws Exception {
		String sql="update s_role set role_name=?,remark=?,update_time=?,update_id=? where id=?";
		System.out.println(sql);
		List<Object> sqlValue=new ArrayList<>();
		sqlValue.add(role.getRoleName());
		sqlValue.add(role.getRemark());
		sqlValue.add(role.getUpdateTime());
		sqlValue.add(role.getUpdateId());
		sqlValue.add(role.getId());
		//List<Object> sqlValue = MyUtil.getSqlValue(role);
		roleDao.update(sql, sqlValue.toArray());
		return true;
	}

	public void addUserToRole(String roleId, String[] userId) throws Exception {
		//必须要用事务
		Connection conn = C3p0Utils.getConnection();
		//先删除所有的数据
		roleDao.deleteRoleBySql(conn,"delete from s_role_user where role_id=?", new Object[]{roleId});
		
		//遍历insert 到s_role_user
		for (String uId : userId) {
			roleDao.update("insert into s_role_user(role_id,user_id,is_del,is_enable) values(?,?,?,?)", 
					new Object[]{roleId,uId,0,1});
		}
		C3p0Utils.closeConnection(conn);
	}



}
