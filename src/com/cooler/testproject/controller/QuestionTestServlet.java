package com.cooler.testproject.controller;

import com.cooler.testproject.dao.QuestionDao;
import com.cooler.testproject.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuestionTestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用dao类中的查询方法，将四条题目存储到session对象中，并请求转发给jsp文件
        QuestionDao dao = new QuestionDao();
        List<Question> questionList = dao.findQuestionList();
        HttpSession session = request.getSession(false);
        session.setAttribute("questionList",questionList);
        request.getRequestDispatcher("/question/question_test.jsp").forward(request,response);
    }
}
