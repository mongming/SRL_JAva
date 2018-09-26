package com.srl.system.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @File name:  LoginFilter.java
 * @Description:   
 * @Create on:  2018年8月21日 下午7:07:52
 * @LinkPage :  
 * @ChangeList
 * ---------------------------------------------------
 * Date         Editor              ChangeReasons
 *
 *
 */
@WebFilter(filterName = "loginFilter", urlPatterns = { "*.jsp" })
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String url = request.getRequestURI();
		if (url.indexOf("login.jsp") != -1) {
			arg2.doFilter(arg0, arg1);
			return;
		} else {
			HttpSession hs=request.getSession();
			String loginName=(String) hs.getAttribute("login_name");
			if(null==loginName||"".equals(loginName)){
				response.sendRedirect("login.jsp");
				return;
			}else{
				arg2.doFilter(arg0, arg1);
			}
			
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
