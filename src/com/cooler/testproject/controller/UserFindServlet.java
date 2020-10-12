package com.cooler.testproject.controller;

import com.cooler.testproject.dao.UserDao;
import com.cooler.testproject.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserFindServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8"); //设置浏览器端的编码
        //1:调用dao将查询命令发到数据库服务器中，得到所有的用户信息
        UserDao userDao = new UserDao();
        List<Users> userlist = userDao.findAllUSers();
        //2：调用响应对象将用户的信息结合标签以二进制的形式写入到响应体当中
        PrintWriter out;
        out = response.getWriter();
        out.print("<table border='2' align='center'>");
        //标题行
        out.print("<tr>");
        out.print("<td>用户编号</td>");
        out.print("<td>用户姓名</td>");
        out.print("<td>用户密码</td>");
        out.print("<td>用户性别</td>");
        out.print("<td>用户邮箱</td>");
        out.print("<td>操作</td>");
        out.print("</tr>");
        //数据行
        for (Users user : userlist
        ) {
            out.print("<tr>");
            out.print("<td>" + user.getUserId() + "</td>");
            out.print("<td>" + user.getUserName() + "</td>");
            out.print("<td>*******</td>");
            out.print("<td>" + user.getSex() + "</td>");
            out.print("<td>" + user.getEmail() + "</td>");
            out.print("<td><a href='/myweb/user/delete?UserId="+user.getUserId()+"'>删除用户</a></td>");
            out.print("</tr>");
        }
        out.print("</table>");
    }
}
