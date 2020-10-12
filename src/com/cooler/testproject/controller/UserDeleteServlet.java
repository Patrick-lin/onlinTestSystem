package com.cooler.testproject.controller;

import com.cooler.testproject.dao.UserDao;
import com.mysql.cj.conf.url.XDevApiDnsSrvConnectionUrl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置浏览器响应体的解析编码
        response.setContentType("text/html;charset=utf-8");
        //2.获取请求头中参数信息
        String userId = request.getParameter("UserId");
        UserDao userDao = new UserDao();
        int result = userDao.deleteUser(userId);
        PrintWriter out;
        out = response.getWriter();
        if (result>0){
            out.print("删除成功");
        }else {
            out.print("删除失败");
        }
    }
}
