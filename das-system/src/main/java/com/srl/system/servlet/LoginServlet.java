/**
 * 
 */
package com.srl.system.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.srl.common.base.BaseServlet;
import com.srl.common.base.RespStatus;
import com.srl.system.bean.User;
import com.srl.system.service.UserService;
import org.apache.commons.lang3.StringUtils;



/** 
* @Description:
* @author: zwl
* @date: 2018年8月19日 上午10:26:51
*/
@WebServlet("/loginServlet")
public class LoginServlet extends BaseServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -608225700779511834L;
	private UserService userService=new UserService();
	
	public void registUser(HttpServletRequest req, HttpServletResponse resp){
		
	}
	
	public void reSetPassword(HttpServletRequest req, HttpServletResponse resp){
		
	}
	
	/**
	 * 登录
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void loginServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置编码格式
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json;charSet=UTF-8");
		String respStatus=null;
		
		String loginName=req.getParameter("loginName");
		String password=req.getParameter("password");
		System.out.println(loginName+password);
		if(!StringUtils.isNotEmpty(loginName)){
			respStatus= RespStatus.error("403", "用户名不能为空", null);
			resp.getWriter().write(respStatus);
			return;
		}
		
		if(!StringUtils.isNotEmpty(password)){
			respStatus= RespStatus.error("403", "密码不能为空", null);
			resp.getWriter().write(respStatus);
			return;
		}
		
		//判断用户是否存在
		String sql="select * from s_user where login_name=?";
		List<User> userList=null;

		try {
			userList = userService.queryUser(sql,loginName);
			if(userList==null || userList.size()!=1){
				respStatus= RespStatus.error("403", "用户名不存在", null);
				resp.getWriter().write(respStatus);
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		User user=userList.get(0);
		//判断密码是否匹配
		if(!user.getPassword().equals(password)){
			respStatus= RespStatus.error("403", "请输入正确的密码!", null);
			resp.getWriter().write(respStatus);
			return;
		}
		
		//把用户信息存入到session中
		HttpSession hs=req.getSession();
		hs.setAttribute("login_name", user.getLoginName());
		//不把密码保存到session
		user.setPassword(null);
		hs.setAttribute("userInfo", user);
		
		
		
		//返回成功信息
		resp.getWriter().write(RespStatus.ok());
		
//		//非ajax请求时这么用 跳转到主页
//		String path=req.getContextPath();
//		String basePath=req.getScheme()+"://"
//				+req.getServerName()+":"+req.getServerPort()
//				+path+"/";
//		resp.sendRedirect(basePath+"index.jsp");
	}
}
