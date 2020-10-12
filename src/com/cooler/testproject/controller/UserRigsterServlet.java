package com.cooler.testproject.controller;

import com.cooler.testproject.dao.UserDao;
import com.cooler.testproject.entity.Users;
import com.cooler.testproject.jdbc.JDBCutil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

public class UserRigsterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("我老");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        /*
        * 在这里要处理请求：把用户输入的信息存储到数据库中
        * 1：获取信息。
        * 2：调用mysql工具类执行插入操作。
        * 3：返回处理结果
        * */

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String eamil = request.getParameter("email");
        Users user = new Users(null,userName,password,sex,eamil);  //这里要将UserID设置为null，系统会自动自增
        //计算注册一个用户耗时
        UserDao userDao = new UserDao();
        Date startDate = new Date();
        //使用了预创建的连接对象
        int result = userDao.add(user,request);
        Date endDate = new Date();
        PrintWriter out = response.getWriter();
        if (result>0){
            out.write("congradulation!");
        }else {
            out.write("register fail!");
        }
        System.out.println("注册一个用户耗时："+(endDate.getTime()-startDate.getTime())+"毫秒");
    }
}
