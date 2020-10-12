package com.cooler.testproject.controller;

import com.cooler.testproject.dao.QuestionDao;
import com.cooler.testproject.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数信息中的id信息
        String questionId = request.getParameter("questionId");
        //调用dao中的更新函数，将sql语句推送到数据库服务器中执行
        QuestionDao dao = new QuestionDao();
        Question question = dao.findById(questionId);
        request.setAttribute("question",question);
        request.getRequestDispatcher("/question/questionUpdate.jsp").forward(request,response);
    }
}
