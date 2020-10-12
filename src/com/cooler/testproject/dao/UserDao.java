package com.cooler.testproject.dao;

import com.cooler.testproject.entity.Users;
import com.cooler.testproject.jdbc.JDBCutil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDao {
    //用户注册
    public int add(Users user){
        String insertSql = "insert into users(userName,password,sex,email)" + "values(?,?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        int result = 0;
        try {
            connection = JDBCutil.getConnection("mysql");
            preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getSex());
            preparedStatement.setString(4,user.getEmail());
            result = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            JDBCutil.closeSources(connection,preparedStatement,null);
        }
        return result;
    }
    //重载方法，遵守开闭原则
    public int add(Users user, HttpServletRequest request){
        String insertSql = "insert into users(userName,password,sex,email)" + "values(?,?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        int result = 0;
        //1.先获取全局作用域对象
        ServletContext application = request.getServletContext();
        //2.在获取到他的map集合
        Map connectionMap = (Map) application.getAttribute("connectionMap");
        //3.遍历map集合
        Iterator it = connectionMap.keySet().iterator();
        while(it.hasNext()){
            connection = (Connection) it.next();
            boolean flag = (boolean) connectionMap.get(connection);
            if (flag) {
                connectionMap.put(connection,false);
                break;
            }
            else {
                System.out.println(connection+"连接对象被占用");
            }
        }
        //执行数据库操作
        try {
            preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getSex());
            preparedStatement.setString(4,user.getEmail());
            result = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCutil.closeSources(connection,preparedStatement,null,request);
        }
        return result;
    }
    //查询所有用户信息
    public List<Users> findAllUSers(){
        String findUsersSql = "select * from Users";
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        ResultSet resultSet = null;
        List userList = new ArrayList();
        try {
            connection = JDBCutil.getConnection("mysql");
            preparedStatement = connection.prepareStatement(findUsersSql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer userId = resultSet.getInt("userId");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String sex = resultSet.getString("sex");
                String email = resultSet.getString("email");
                Users user = new Users(userId,userName,password,sex,email);
                userList.add(user);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }finally {
            JDBCutil.closeSources(connection,preparedStatement,resultSet);
        }
        return userList;
    }
    //实现用户的删除
    public int deleteUser(String userId){
        String deleteSql = "delete from Users where userId=?";
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        int result = 0;
        try {
            connection = JDBCutil.getConnection("mysql");
            preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setInt(1,Integer.valueOf(userId));
            result = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        }finally {
            JDBCutil.closeSources(connection,preparedStatement,null);
        }
        return result;
    }
    //实现用户的登录验证
    public boolean loginUser(String userName,String password){
        String selectSql = "select * from Users where userName=? and password=?";
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        ResultSet resultSet = null;
        boolean exist = false;
        try {
            connection = JDBCutil.getConnection("mysql");
            preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery(); //这个结果集无论有无用户都不会为空，所以只能判断后面是否有元素来判断
            exist = resultSet.next();
        } catch (Exception exception) {
            exception.printStackTrace();
        }finally {
            JDBCutil.closeSources(connection,preparedStatement,resultSet);
        }
        return exist ? true:false;
    }
}
