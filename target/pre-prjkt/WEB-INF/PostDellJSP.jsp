<%@ page import="service.UserService" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 02.04.2020
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete</title>
</head>
<body>
<h1> Delete </h1>
<table style=" width: 100%; border: 4px double black;">
    <tr>
        <td style="border: 1px solid black; text-align: center">
            <a href="/users"> Create </a>
        </td>
        <td style="border: 1px solid black; text-align: center">
            <a href="/read"> Read</a>
        </td>
        <td style="border: 1px solid black; text-align: center">
            <a href="/update"> Update </a>
        </td>
        <td style="border: 1px solid black; text-align: center">
            <a href="/delete"> Delete</a>
        </td>
    </tr>
</table>
<form method="post">
    <p>
        Name: <input type="text" name="name">
    </p>
    <p>
        Age: <input type="number" name="age">
    </p>
    <p>
        Passport: <input type="number" name="passport">
    </p>
    <p><input type="submit" value="Delete"></p>
</form>
<%=        new UserService().delete(request.getParameter("name"),
        request.getParameter("age"),
        request.getParameter("passport"))%>
<% for (User user : new UserService().getAllUsers()) { %>
<p>
    <%= new Gson().toJson(user)%>
</p>
<% } %>
</body>
</html>
