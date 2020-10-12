package com.cooler.testproject.dao;

import com.cooler.testproject.entity.Question;
import com.cooler.testproject.entity.Users;
import com.cooler.testproject.jdbc.JDBCutil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class QuestionDao {
    //添加问题
    public int add(Question question, HttpServletRequest request) {
        String insertSql = "insert into question(title,optionA,optionB,optionC,optionD,correctOption)values(?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        //1.先获取全局作用域对象
        ServletContext application = request.getServletContext();
        //2.在获取到他的map集合
        Map connectionMap = (Map) application.getAttribute("connectionMap");
        //3.遍历map集合
        Iterator it = connectionMap.keySet().iterator();
        while (it.hasNext()) {
            connection = (Connection) it.next();
            boolean flag = (boolean) connectionMap.get(connection);
            if (flag) {
                connectionMap.put(connection, false);
                break;
            } else {
                System.out.println(connection + "连接对象被占用");
            }
        }
        //执行数据库操作
        try {
            preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, question.getTitle());
            preparedStatement.setString(2, question.getOptionA());
            preparedStatement.setString(3, question.getOptionB());
            preparedStatement.setString(4, question.getOptionC());
            preparedStatement.setString(5, question.getOptionD());
            preparedStatement.setString(6, question.getCorrectOption());
            result = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCutil.closeSources(connection, preparedStatement, null, request);
        }
        return result;
    }

    //查找所有问题
    public List<Question> findAllQuestion() {
        String findQuestionSql = "select * from question";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List questionList = new ArrayList();
        try {
            connection = JDBCutil.getConnection("mysql");
            preparedStatement = connection.prepareStatement(findQuestionSql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer questionId = (Integer) resultSet.getInt("questionId");
                String title = resultSet.getString("title");
                String optionA = resultSet.getString("optionA");
                String optionB = resultSet.getString("optionB");
                String optionC = resultSet.getString("optionC");
                String optionD = resultSet.getString("optionD");
                String correctOption = resultSet.getString("correctOption");
                Question question = new Question(questionId, title, optionA, optionB, optionC, optionD, correctOption);
                questionList.add(question);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            JDBCutil.closeSources(connection, preparedStatement, resultSet);
        }
        return questionList;
    }
    //删除指定问题
    public int delete(String questionId) {
        String deleteSql = "delete from question where questionId=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            connection = JDBCutil.getConnection("mysql");
            preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setInt(1, Integer.parseInt(questionId));
            result = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            JDBCutil.closeSources(connection, preparedStatement, null);
        }
        return result;
    }

    //通过id查找问题返回给jsp文件
    public Question findById(String questionId){
        String findSql = "select * from question where  questionId=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Question question =null;
        try {
            connection = JDBCutil.getConnection("mysql");
            preparedStatement = connection.prepareStatement(findSql);
            preparedStatement.setInt(1,Integer.valueOf(questionId));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer Id = (Integer) resultSet.getInt("questionId");
                String title = resultSet.getString("title");
                String optionA = resultSet.getString("optionA");
                String optionB = resultSet.getString("optionB");
                String optionC = resultSet.getString("optionC");
                String optionD = resultSet.getString("optionD");
                String correctOption = resultSet.getString("correctOption");
                question = new Question(Id, title, optionA, optionB, optionC, optionD, correctOption);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            JDBCutil.closeSources(connection, preparedStatement, resultSet);
        }
        return question;
    }
    //根据id更新问题
    public int updateById(Question question) {

        String updateSql = "update question set title=?,optionA=?,optionB=?,optionC=?,optionD=?,correctOption=? where questionId=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            connection = JDBCutil.getConnection("mysql");
            preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, question.getTitle());
            preparedStatement.setString(2, question.getOptionA());
            preparedStatement.setString(3, question.getOptionB());
            preparedStatement.setString(4, question.getOptionC());
            preparedStatement.setString(5, question.getOptionD());
            preparedStatement.setString(6, question.getCorrectOption());
            preparedStatement.setInt(7,question.getId());
            result = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            JDBCutil.closeSources(connection, preparedStatement, null);
        }
        return result;
    }
    //随机出四道题目
    public List<Question> findQuestionList() {
        String findQuestionSql = "select * from question order by rand() limit 0,4";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List questionList = new ArrayList();
        try {
            connection = JDBCutil.getConnection("mysql");
            preparedStatement = connection.prepareStatement(findQuestionSql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer questionId = (Integer) resultSet.getInt("questionId");
                String title = resultSet.getString("title");
                String optionA = resultSet.getString("optionA");
                String optionB = resultSet.getString("optionB");
                String optionC = resultSet.getString("optionC");
                String optionD = resultSet.getString("optionD");
                String correctOption = resultSet.getString("correctOption");
                Question question = new Question(questionId, title, optionA, optionB, optionC, optionD, correctOption);
                questionList.add(question);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            JDBCutil.closeSources(connection, preparedStatement, resultSet);
        }
        return questionList;
    }
}
