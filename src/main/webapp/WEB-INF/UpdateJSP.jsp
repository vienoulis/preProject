<%@ page import="service.UserService" %>
<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 04.04.2020
  Time: 1:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<h1>Update</h1>
<form method="post">
    <p>
        <% User user = new UserService().getUserById(Long.parseLong(response.getHeader("userId")));%>
        <input type="hidden" name="userId" value="<%=response.getHeader("userId")%>">
        Name: <input type="text" value="<%= user.getName()%>" name="nameToUpdate">
    </p>
    <p>
        Age: <input type="number" value="<%=user.getAge()%>" name="ageToUpdate">
    </p>
    <p>
        Passport: <input type="number" value="<%=user.getPassport()%>" name="passportToUpdate">
    </p>

    <p>
        <input type="submit" name="doUpdate" value="Update">
    </p>
</form>

</body>
</html>
