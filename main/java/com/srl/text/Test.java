package com.srl.text;

import com.sun.net.httpserver.HttpServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        String userName=req.getParameter("userName");
        String userPwd=req.getParameter("userPwd");
        System.out.println(userName+" "+userPwd);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String userName=req.getParameter("userName");
        String userPwd=req.getParameter("userPwd");
        System.out.println(userName+" "+userPwd);
    }
}
