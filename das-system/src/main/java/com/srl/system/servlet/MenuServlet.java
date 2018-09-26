package com.srl.system.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.srl.common.base.BaseServlet;


/**
 *
 * @File name:  MenuServlet.java
 * @Description:   
 * @Create on:  2018年8月25日 上午9:14:28
 * @LinkPage :  
 * @ChangeList
 * ---------------------------------------------------
 * Date         Editor              ChangeReasons
 *
 *
 */
public class MenuServlet extends BaseServlet {
	
	public void menuList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/menu/menuList.jsp").forward(request, response);
	}

}
