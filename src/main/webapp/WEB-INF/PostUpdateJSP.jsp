<%@ page import="com.google.gson.Gson" %>
<%@ page import="model.User" %>
<%@ page import="service.UserService" %><%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 02.04.2020
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<h1>Update</h1>
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
    Update user where name: <input type="text" name="name">
    age: <input type="number" name="age">
    passport: <input type="number" name="passport">
    new passport <input type="number" name="newPassport">
    <input type="submit" value="Update">
</form>
<form method="post">
    Update user where name: <input type="text" name="name">
    age: <input type="number" name="age">
    passport: <input type="number" name="passport">
    new age <input type="number" name="newAge">
    <input type="submit" value="Update">
</form>

    <%= new UserService().update(request.getParameter("name"),
            request.getParameter("age"),
            request.getParameter("passport"),
            request.getParameter("newPassport"),
            request.getParameter("newAge")) %>

</body>
</html>
