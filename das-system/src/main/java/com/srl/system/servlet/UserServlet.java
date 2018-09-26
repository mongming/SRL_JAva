package com.srl.system.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;


import com.srl.common.base.BaseServlet;
import com.srl.common.base.PageBean;
import com.srl.common.base.RespStatus;
import com.srl.common.utils.GsonUtils;
import com.srl.common.utils.MyUtil;
import com.srl.system.bean.User;
import com.srl.system.service.UserService;

/**
 * 
 * @author zwl
 *
 */
@WebServlet("/userServlet")
public class UserServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 189906650295152382L;

	private UserService userService = new UserService();

	public void queryUserPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String startStr = req.getParameter("start");
		String lengthStr = req.getParameter("length");
		String drawStr = req.getParameter("draw");
		
		String userName=req.getParameter("userName");
		String loginName=req.getParameter("loginName");
		
		StringBuffer sql = new StringBuffer("select * from s_user where 1=1 ");
		StringBuffer sql2 = new StringBuffer("select count(1) from s_user where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if(StringUtils.isNotEmpty(userName)){
			sql.append(" and user_name like ?");
			sql2.append(" and user_name like ?");
			param.add("%"+userName+"%");
		}
		if(StringUtils.isNotEmpty(loginName)){
			sql.append(" and login_name like ?");
			sql2.append(" and login_name like ?");
			param.add("%"+loginName+"%");
		}
		sql.append(" limit ?,?");
		
		PageBean<User> pageBean = new PageBean<>(startStr, lengthStr, drawStr);
		
		userService.queryUserPage(pageBean,sql.toString(),sql2.toString(),param);
		resp.getWriter().write(GsonUtils.toJson(pageBean));
	}
	
	public void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String id=req.getParameter("id");
		User user=new User();
		user.setId(Integer.parseInt(id));
		List<User> list=userService.queryUser(user);
		resp.getWriter().write(GsonUtils.toJson(list.get(0)));
	}
	
	public void saveUserInfo(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		User user=new User();
		String cmd=req.getParameter("cmd");
		MyUtil.getParam(req, user);
		
		//增加校验，判断新增的用户登录名是否重复
		String sql="select * from s_user where login_name=?";
		List<Object> parmList=new ArrayList<>();
		parmList.add(user.getLoginName());
		if("U".equals(cmd)){
			sql+=" and id!=?";
			parmList.add(user.getId());
		}
		//如果查询出来有数据，就不让新增，因为数据库已经存在同名登录名称
		List<User> checkUser = userService.queryUser(sql, parmList.toArray());
		if(checkUser!=null && checkUser.size()>0){
			//有重复数据
			resp.getWriter().write(RespStatus.error("403", "登录名存在重复数据!", null));
			return;
		}
		if("A".equals(cmd)){
			userService.insertUser(user);
		}else if("U".equals(cmd)){
			String id=req.getParameter("id");
			user.setId(Integer.parseInt(id));
			userService.updateUser(user);
		}
		resp.getWriter().write(RespStatus.ok());
	}
	
	public void removeUser(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String ids=req.getParameter("ids");
		String[] idarr=null;
		if(StringUtils.isNotEmpty(ids)){
			idarr =ids.split(",");
		}
		for(int i=0;i<idarr.length;i++){
			userService.deleteUserById(idarr[i]);
		}
		resp.getWriter().write(RespStatus.ok());
	}
	/**
	 * 根据角色id查询用户信息
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	public void getUserByRoleId(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String roleId=req.getParameter("roleId");
		if(!StringUtils.isNotEmpty(roleId)){
			resp.getWriter().write(RespStatus.error("403","id不能为空",null));
			return ;
		}
		List<User> userList=userService.queryUserByRoleId(roleId);
		resp.getWriter().write(RespStatus.ok(GsonUtils.toJson(userList)));
	}
	
}
