package com.srl.system.dao;

import com.srl.common.base.BaseDaoImpl;
import com.srl.common.db.C3p0Utils;
import com.srl.common.utils.MyUtil;
import com.srl.system.bean.RoleUser;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @File name:  RoleUserUserDao.java
 * @Description:   
 * @Create on:  2018年8月18日 下午3:34:35
 * @LinkPage :  
 * @ChangeList
 * ---------------------------------------------------
 * Date         Editor              ChangeReasons
 *
 *
 */
public class RoleUserDao extends BaseDaoImpl<RoleUser> {

	public List<RoleUser> queryRoleUsers(RoleUser role) throws Exception{
		StringBuilder sql = new StringBuilder("select * from s_role_user where 1=1 ");
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
	
	public List<RoleUser> queryBySql(String sql,Object... o ) throws SQLException{
		Connection conn=C3p0Utils.getConnection();
		return queryBeanList(conn,sql,o);
	}
}
