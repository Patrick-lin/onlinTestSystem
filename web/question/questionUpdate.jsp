<%@ page import="com.cooler.testproject.entity.Question" %><%--
  Created by IntelliJ IDEA.
  User: cooler
  Date: 2020/8/3
  Time: 1:30 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <form action="/myweb/question/update">试题增加
        <hr>
        <table border="1px">
            <tr>
                <td>题目编号</td>
                <td><input type="text" name="questionId" id="questionId" READONLY value="${requestScope.question.id}"></td>
            </tr>
            <tr>
                <td>题目</td>
                <td><input type="text" name="title" id="title" value="${requestScope.question.title}"></td>
            </tr>
            <tr>
                <td>A:</td>
                <td><input type="text" name="optionA" id="optionA" value="${requestScope.question.optionA}"></td>
            </tr>
            <tr>
                <td>B:</td>
                <td><input type="text" name="optionB" id="optionB" value="${requestScope.question.optionB}"></td>
            </tr>
            <tr>
                <td>C:</td>
                <td><input type="text" name="optionC" id="optionC" value="${requestScope.question.optionC}"></td>
            </tr>
            <tr>
                <td>D:</td>
                <td><input type="text" name="optionD" id="optionD" value="${requestScope.question.optionD}"></td>
            </tr>
            <tr>
                <td>正确答案:</td>
                <td>
                    <input type="radio" name="correctOption" value="A" ${"A" eq question.correctOption ? "checked" : ""}>A
                    <input type="radio" name="correctOption" value="B" ${"B" eq question.correctOption ? "checked" : ""}>B
                    <input type="radio" name="correctOption" value="C" ${"C" eq question.correctOption ? "checked" : ""}>C
                    <input type="radio" name="correctOption" value="D" ${"D" eq question.correctOption ? "checked" : ""}>D
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="更新试题"></td>
                <td><input type="reset" name="reset" value="重置信息"></td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>
