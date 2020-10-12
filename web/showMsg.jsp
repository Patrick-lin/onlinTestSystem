<%--
  Created by IntelliJ IDEA.
  User: cooler
  Date: 2020/8/2
  Time: 9:51 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>展示测试结果</title>
</head>
<body>
<center>
    <h1>
<%--        <%=(String) request.getAttribute("msg")%>--%>
<%--        EL表达式--%>
        ${requestScope.msg}
    </h1>
</center>
</body>
</html>
