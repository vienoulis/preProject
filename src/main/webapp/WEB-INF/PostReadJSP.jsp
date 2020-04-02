<%@ page import="com.google.gson.Gson" %>
<%@ page import="model.User" %>
<%@ page import="service.UserService" %><%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 02.04.2020
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Read</title>
</head>
<body>


<h1> Read </h1>
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
<p>
<form method="post">
    Get all where name: <input type="text" name="name">
    <input type="submit" value="Read">
</form>
</p>
<p>
<form method="post">
    Get all where age: <input type="number" name="age">
    <input type="submit" value="Read">
</form>
</p>
<p>
<form method="post">
    Get all where passport: <input type="number" name="passport">
    <input type="submit" value="Read">
</form>
<% for (User user : new UserService().getAllSortUsers(request.getParameter("name"), request.getParameter("age"), request.getParameter("passprt"))) { %>
<p>
    <%= new Gson().toJson(user)%>
</p>
<% } %>
</body>
</html>
