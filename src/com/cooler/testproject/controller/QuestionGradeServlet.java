package com.cooler.testproject.controller;

import com.cooler.testproject.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuestionGradeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int score = 0;
        //获取问题列表
        List<Question> questionList = (List<Question>) session.getAttribute("questionList");
        for (Question q:questionList
             ) {
            String correctOption = q.getCorrectOption();
            Integer id = q.getId();
            String param = "correctOption"+id;
            String option = request.getParameter(param);
            if (correctOption.equals(option)){
                score+=25;
            }else {

            }
        }
        request.setAttribute("msg","你此次考试的分数为："+score+"分");
        request.getRequestDispatcher("/showMsg.jsp").forward(request,response);
    }
}
