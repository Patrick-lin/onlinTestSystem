package com.cooler.testproject.listener;

import com.cooler.testproject.jdbc.JDBCutil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //1.先获取全局对象application
        ServletContext appliction = sce.getServletContext();
        Map connectionMap = new HashMap();
        //2.创建多个连接对象
        for (int i = 0; i < 20; i++) {
            try {

                Connection connection = JDBCutil.getConnection("mysql");
                //设置布尔标记来判断该连接对象是否别占用
                connectionMap.put(connection, true);
                //输出20个连接对象
                System.out.println("我是连接对象："+connection);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        //将map放入全局作用域对象中
        appliction.setAttribute("connectionMap",connectionMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        Map connectionMap = (Map) application.getAttribute("connectionMap");
        Set connectionKeys = connectionMap.keySet();
        Iterator it = connectionKeys.iterator();
        while(it.hasNext()){
            Connection connection = (Connection) it.next();
            //全局作用域对象销毁时自动关闭资源
            if(connection!=null){
                System.out.println(connection+"连接对象先走一步");
            }
        }
    }
}
