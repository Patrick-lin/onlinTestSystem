package com.cooler.testproject.controller;

import com.cooler.testproject.dao.QuestionDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId = request.getParameter("questionId");
        QuestionDao dao = new QuestionDao();
        int result = dao.delete(questionId);
        if (result==1){
            request.setAttribute("msg","试题删除成功");
        }else {
            request.setAttribute("msg","试题删除失败");
        }
        request.getRequestDispatcher("/showMsg.jsp").forward(request,response);
    }
}
