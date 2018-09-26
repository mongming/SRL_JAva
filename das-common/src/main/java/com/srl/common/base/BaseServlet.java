/**
 *
 */
package com.srl.common.base;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description:
 * @author: zwl
 * @date: 2018年8月19日 下午2:39:23
 */
public class BaseServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 3841705529058256171L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charSet=UTF-8");
        //需要前端传递一个action参数值，这个是具体实现方法名称
        String methodName = request.getParameter("action");
        String respStatus = null;
        if (StringUtils.isNotBlank(methodName)) {
            try {
                /**
                 * getDeclaredMethod*()获取的是类自身声明的所有方法，包含public、protected和private方法。
                 getMethod*()获取的是类的所有共有方法，这就包括自身的所有public方法，和从基类继承的、从接口实现的所有public方法。
                 */
                Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                if (method != null) {
                    //通过method对象调用方法
                    //允许访问私有方法
                    method.setAccessible(true);
                    //第一个参数是调用该方法的对象,后面几个参数是该方法的参数值
                    method.invoke(this, request, response);
                } else {
                    respStatus = RespStatus.error("404", "请输入正确的action", null);
                    response.getWriter().write(respStatus);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                respStatus = RespStatus.error("500", "执行action出错", null);
                response.getWriter().write(respStatus);
                return;
            }
        } else {
            respStatus = RespStatus.error("404", "请输入正确的action", null);
            response.getWriter().write(respStatus);
            return;
        }
    }
}
