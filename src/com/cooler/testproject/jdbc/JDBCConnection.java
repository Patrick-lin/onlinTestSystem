package com.cooler.testproject.jdbc;

import java.sql.*;
import java.util.ResourceBundle;


/*数据库连接步骤：
1：加载驱动
2：获取连接对象
3：获取数据库操作对象 (使用preparedStatement可以防止SQL注入)
4：执行数据库语句
5：处理查询结果集
6：关闭资源
* */
public class JDBCConnection {
    /*执行数据库DML语句：update，insert，delete
     * */
    public static void mySQL_DML(String sql, String fileurl) {
        ResourceBundle properties = ResourceBundle.getBundle(fileurl);  //获取资源绑定器
        String url = properties.getString("url");
        String username = properties.getString("username");
        String password = properties.getString("password");
        String driver = properties.getString("driver");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password); //获取数据连接对象
            ps = connection.prepareStatement(sql); //获取数据库连接对象
            int rows = ps.executeUpdate(); //执行数据库语句
            System.out.println("记录更新了：" + rows + " 条");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
    //查询语句
    public static void mySQL_DQL(String sql, String fileurl) {
        ResourceBundle properties = ResourceBundle.getBundle(fileurl);  //获取资源绑定器
        String url = properties.getString("url");
        String username = properties.getString("username");
        String password = properties.getString("password");
        String driver = properties.getString("driver");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null; //获取结果集
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password); //获取数据连接对象
            ps = connection.prepareStatement(sql); //获取数据库连接对象,使用preparedStatement可以预编译SQL语句
            resultSet = ps.executeQuery();   //这里就不用传入SQL语句
            while (resultSet.next()) {
                System.out.println("姓名：" + resultSet.getString("ename") + "    工资：" + resultSet.getString("sal") +
                        "   工号：" + resultSet.getString("empno"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    System.out.println("获取结果集成功");
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
            if (ps != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

}
