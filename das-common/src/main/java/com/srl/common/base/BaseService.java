package com.srl.common.base;

import java.sql.Connection;
import java.sql.SQLException;


/**
 *
 * @File name:  BaseService.java
 * @Description:   
 * @Create on:  2018年8月17日 下午8:27:22
 * @LinkPage :  
 * @ChangeList
 * ---------------------------------------------------
 * Date         Editor              ChangeReasons
 *
 *
 */
public abstract class BaseService<B,M extends BaseDao<B>> {
	
	public abstract String getBeanSql();
	
	public abstract M getDao();
   
	/**
	 * 根据getBeanSql方法查询单个实体
	 *  getBeanSql不是很通用
	 * @param conn
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public B getById(Connection conn,Object...params) throws SQLException{
		String sql=getBeanSql()+" where id=?";
		return getDao().getBy(conn, sql, params);
	}
	
	
	public Object getSigleCloumnVal(Connection conn,String sql,Object...params) throws SQLException{
		return getDao().querySignleCloumn(conn,sql, params);
	}
	
	public Object getSigleCloumnVal(String sql,Object...params) throws SQLException{
		return getDao().querySignleCloumn(sql, params);
	}
	
	
}
