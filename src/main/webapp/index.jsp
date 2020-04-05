<%@ page import="model.User" %>
<%@ page import="service.UserService" %>
<%@ page import="com.google.gson.Gson" %><%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 02.04.2020
  Time: 1:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h1>Users</h1>
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
    <p><input type="submit" name="add" value="Create"></p>
</form>
<% for (User user : UserService.getInstance().getAllUsers()) { %>
<form method="post">
    <p>
        <input type="hidden" name="userId" value=<%=user.getId()%>>
        <%= new Gson().toJson(user)%>
        <input type="submit" name="delete" value="Delete">
        <input type="submit" name="update" value="Update">
    </p>
</form>
<% } %>
</body>
</html>
