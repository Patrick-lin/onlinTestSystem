package com.cooler.testproject.controller;

import com.cooler.testproject.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.先设置浏览器的显示编码
        response.setContentType("text/html;utf-8");
        response.setCharacterEncoding("utf-8");
        //2.获取请求对象的参数信息
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        //3.将这些参数信息传递到sql查询语句当中
        UserDao userDao = new UserDao();
        boolean exist = userDao.loginUser(userName,password);
        //实现重定向
        if (exist){
            //发布令牌用于防止恶意登录
            HttpSession session = request.getSession();
            response.sendRedirect("/myweb/index.html");
        }else {
            response.sendRedirect("/myweb/user/loginError.html");
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
