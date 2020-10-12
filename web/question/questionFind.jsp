<%@ page import="com.cooler.testproject.entity.Question" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: cooler
  Date: 2020/8/2
  Time: 11:15 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>试题查询</title>
</head>
<style type="text/css">
    tr{
        align: center;
    }
</style>
<body>
<%--通过请求对象获取传过来的列表实现格式化输出--%>
<%
    List<Question> questionList= (List<Question>) request.getAttribute("questionList");
%>
<table border='2' align='center'>
    <tr>
        <td>题目编号</td>
        <td>题目</td>
        <td>A</td>
        <td>B</td>
        <td>C</td>
        <td>D</td>
        <td>答案</td>
        <td colspan="2">用户操作</td>
    </tr>
<%
    for (Question q:questionList) {
%>
    <tr>
        <td><%=q.getId()%></td>
        <td><%=q.getTitle()%></td>
        <td><%=q.getOptionA()%></td>
        <td><%=q.getOptionB()%></td>
        <td><%=q.getOptionC()%></td>
        <td><%=q.getOptionD()%></td>
        <td><%=q.getCorrectOption()%></td>
        <td><a href='/myweb/question/delete?questionId=<%=q.getId()%>'>删除试题</a></td>
        <td><a href='/myweb/question/show?questionId=<%=q.getId()%>'>更新试题</a></td>
    </tr>
    <%
    }
    %>

</table>
</body>
</html>
