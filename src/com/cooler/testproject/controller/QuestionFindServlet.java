package com.cooler.testproject.controller;

import com.cooler.testproject.dao.QuestionDao;
import com.cooler.testproject.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class QuestionFindServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDao dao = new QuestionDao();
        List<Question> questionList = dao.findAllQuestion();
        request.setAttribute("questionList",questionList);
        request.getRequestDispatcher("/question/questionFind.jsp").forward(request,response);
    }
}
