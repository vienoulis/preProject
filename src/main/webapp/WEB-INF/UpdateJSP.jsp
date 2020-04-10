<%@ page import="model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 04.04.2020
  Time: 1:40
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Update</title>
</head>
<body>
<h1>Update</h1>
<form method="post">
    <p>
        <input type="hidden" name="userId" value="<%=response.getHeader("userId")%>">
        Name: <input type="text" value="${userUpdated.name}" name="nameToUpdate">
    </p>
    <p>
        Age: <input type="number" value="${userUpdated.age}" name="ageToUpdate">
    </p>
    <p>
        Passport: <input type="number" value="${userUpdated.passport}" name="passportToUpdate">
    </p>
    <p>
        Password: <input type="text" name="passwordToUpdate">
    </p>
    <p>
        Role: <input type="checkbox"  name="roleToUpdate"> admin
    </p>

    <p>
        <input type="submit" name="doUpdate" value="Update">
    </p>
</form>

</body>
</html>
