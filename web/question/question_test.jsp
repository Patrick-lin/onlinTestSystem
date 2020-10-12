<%@ page import="java.util.List" %>
<%@ page import="com.cooler.testproject.entity.Question" %><%--
  Created by IntelliJ IDEA.
  User: cooler
  Date: 2020/8/5
  Time: 3:58 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style type="text/css">

</style>
<body>
<%
    List<Question> questionList = (List<Question>) session.getAttribute("questionList");
%>
<form action="/myweb/question/grade">
    <table border="1px" align="center">
    <%
            for (Question q:questionList) {
        %>
            <tr>
                <td>题目编号</td>
                <td><%=q.getId()%></td>
            </tr>
            <tr>
                <td>题目</td>
                <td><%=q.getTitle()%></td>
            </tr>
            <tr>
                <td>A:</td>
                <td><%=q.getOptionA()%></td>
            </tr>
            <tr>
                <td>B:</td>
                <td><%=q.getOptionB()%></td>
            </tr>
            <tr>
                <td>C:</td>
                <td><%=q.getOptionC()%></td>
            </tr>
            <tr>
                <td>D:</td>
                <td><%=q.getOptionD()%></td>
            </tr>
            <tr>
                <td>正确答案:</td>
                <td>
                    <input type="radio" name="correctOption<%=q.getId()%>" value="A" >A
                    <input type="radio" name="correctOption<%=q.getId()%>" value="B" >B
                    <input type="radio" name="correctOption<%=q.getId()%>" value="C" >C
                    <input type="radio" name="correctOption<%=q.getId()%>" value="D" >D
                </td>
            </tr>
        <%
            }
        %>
        <tr>
            <td><input type="submit" value="提交答卷"></td>
            <td><input type="reset" value="清空答卷"></td>
        </tr>
    </table>
</form>
</body>
</html>
