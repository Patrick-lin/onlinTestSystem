package com.cooler.testproject.controller;

import com.cooler.testproject.dao.QuestionDao;
import com.cooler.testproject.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求头中的参数信息,封装成一个对象
        //2.将请求头中的参数信息传递给dao类实现数据库更新
        Integer questionId = Integer.valueOf(request.getParameter("questionId"));
        String title = request.getParameter("title");
        String optionA = request.getParameter("optionA");
        String optionB = request.getParameter("optionB");
        String optionC = request.getParameter("optionC");
        String optionD = request.getParameter("optionD");
        String correctOption = request.getParameter("correctOption");
        Question question = new Question(questionId,title,optionA,optionB,optionC,optionD,correctOption);
        QuestionDao dao = new QuestionDao();
        int result = dao.updateById(question);
        //将测试结果在jsp页面中显示
        if (result==1){
            request.setAttribute("msg","试题更新成功");
        }else {
            request.setAttribute("msg","试题更新失败");
        }
        request.getRequestDispatcher("/showMsg.jsp").forward(request,response);
    }
}
