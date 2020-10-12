package com.cooler.testproject.jdbc;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Map;
import java.util.ResourceBundle;

public class JDBCutil {
    //传入配置文件获取连接对象
    public static Connection getConnection(String fileurl) throws Exception {
        ResourceBundle properties = ResourceBundle.getBundle(fileurl);  //获取资源绑定器
        String url = properties.getString("url");
        String username = properties.getString("username");
        String password = properties.getString("password");
        String driver = properties.getString("driver");
        Connection connection = null;
        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password); //获取数据连接对象
        return connection;
    }

    //关闭资源方法
    public static void closeSources(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    //重载closeSource方法
    public static void closeSources(Connection connection,Statement statement,ResultSet resultSet,HttpServletRequest request) {
        if (resultSet != null) {
            try {
                System.out.println("resultSet资源"+resultSet+"关闭了");
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(statement!=null){
            try {
                System.out.println("statement资源"+statement+"关闭了");
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        ServletContext appliction = request.getServletContext();
        Map connectionMap = (Map)appliction.getAttribute("connectionMap");
        //将connetion对象状态设置为空闲
        connectionMap.put(connection,true);
    }

}
