package com.srl.system.servlet;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.srl.common.base.BaseServlet;
import com.srl.common.base.PageBean;
import com.srl.common.base.RespStatus;
import com.srl.common.db.C3p0Utils;
import com.srl.common.utils.BeanToSqlUtils;
import com.srl.common.utils.GsonUtils;
import com.srl.common.utils.MyUtil;
import com.srl.system.bean.Role;
import com.srl.system.bean.User;
import com.srl.system.service.RoleService;
import org.apache.commons.lang3.StringUtils;



/**
 * 
 * @author zwl
 *
 */
@WebServlet("/roleServlet")
public class RoleServlet extends BaseServlet {
	
	private RoleService roleService=new RoleService();

	/**
	 * 
	 */
	private static final long serialVersionUID = -4894168831806755392L;

	
	public void queryRolePage(HttpServletRequest request,HttpServletResponse respone) throws SQLException, IOException{
		//获取前端传递的分页起始记录数，及分页的条数
		String startStr=request.getParameter("start");
		String lengthStr=request.getParameter("length");
		String drawStr=request.getParameter("draw");
		
		//获取查询条件
		String roleName=request.getParameter("roleName");
		
		//拼接sql及分页总计录数sql语句
		StringBuffer sql=new StringBuffer(BeanToSqlUtils.queryAllSql(Role.class));
		StringBuffer sql2=new StringBuffer("select count(1) from s_role");
		sql.append(" where 1=1 ");
		sql2.append(" where 1=1 ");
		List<Object> paramObj=new ArrayList<>();
		if(StringUtils.isNotEmpty(roleName)){
			sql.append(" and role_name=?");
			sql2.append(" and role_name=?");
			paramObj.add("%"+roleName+"%");
		}
		sql2.append(" limit ?,?");
		
		//分页对象
		PageBean<Role> pageBean=new PageBean<>(startStr, lengthStr, drawStr);
		//调用service对象，执行分页查询
		System.out.println(sql.toString());
		System.out.println(sql2.toString());
		roleService.queryRolePage(pageBean,sql.toString(),sql2.toString(),paramObj);
		
		respone.getWriter().write(GsonUtils.toJson(pageBean));
	}
	
	/**
	 * 保存
	 * @param request
	 * @param respone
	 * @throws Exception
	 */
	public void saveRoleInfo(HttpServletRequest request,HttpServletResponse respone) throws Exception{
		//String roleName=request.getParameter("roleName");
		//String remark=request.getParameter("remark");
		String id=request.getParameter("id");
		String cmd=request.getParameter("cmd");
		
		Role role=new Role();
		//role.setRoleName(roleName);
		//role.setRemark(remark);
		MyUtil.getParam(request, role);
		
		//公共字段
		User user = (User)request.getSession().getAttribute("userInfo");
		if(user!=null && user.getId()!=null){
			role.setCreateId(user.getId());
			role.setUpdateId(user.getId());
		}
		role.setUpdateTime(new Date());
		
		boolean flag=false;
		if("A".equals(cmd)){
			role.setCreateTime(new Date());
			role.setIsDel(false);
			role.setIsEnable(true);
			flag = roleService.insertRole(role);
		}else if("U".equals(cmd)){
			if(!StringUtils.isNotEmpty(id)){
				respone.getWriter().write(RespStatus.error("403","id不能为空",null));
				return ;
			}
			role.setId(Integer.parseInt(id));
			role.setCreateId(null);
			flag = roleService.updateRole(role);
		}
		
		if(flag){
			respone.getWriter().write(RespStatus.ok());
		}else{
			respone.getWriter().write(RespStatus.error());
		}
	}
	
	/**
	 * 根据id获取角色信息
	 * @param request
	 * @param respone
	 * @throws Exception
	 */
	public void getRoleInfo(HttpServletRequest request,HttpServletResponse respone) throws Exception{
		String id=request.getParameter("id");
		Connection conn = C3p0Utils.getConnection();
		Role role = roleService.getById(conn, new Object[]{id});
		C3p0Utils.closeConnection(conn);
		respone.getWriter().write(GsonUtils.toJson(role));
	}
	
	public void addUserToRole(HttpServletRequest request,HttpServletResponse respone) throws Exception{
		String ids=request.getParameter("ids");
		String[] userId=ids.split(",");
		String roleId=request.getParameter("roleId");
		// todo  增加非空判断
		//最简单的做法是先删除一遍，再insert进去
		roleService.addUserToRole(roleId,userId);
		respone.getWriter().write(RespStatus.ok());
	}
	
}
