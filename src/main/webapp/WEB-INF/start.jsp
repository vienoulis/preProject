<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 10.04.2020
  Time: 3:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Auth</title>
</head>
<body>
<form method="get" action="/admin">
    <p>
        Name: <input type="text" name="authName">
    </p>
    <p>
        Pass: <input type="password" name="authPassword">
    </p>
    <input type="submit" value="LogIn">
</form>
</body>
</html>
