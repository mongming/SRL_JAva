/**
 * 
 */
package com.srl.system.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.srl.common.base.BaseServlet;
import com.srl.common.base.RespStatus;
import com.srl.system.bean.Menu;
import com.srl.system.service.MenuService;


/** 
* @Description:
* @author: zwl
* @date: 2018年8月22日 下午7:27:09
*/
@WebServlet(name="mainServlet", urlPatterns={"/mainServlet"})
public class MainServletServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4641354010726923847L;
	private MenuService menuService=new MenuService();
	
	protected void loadMenu(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Menu menu=new Menu();
		menu.setParentId("0");
		List<Menu> list=menuService.queryMenuAndChildMenu(menu);
		response.getWriter().write(RespStatus.ok(list));
	}
}
