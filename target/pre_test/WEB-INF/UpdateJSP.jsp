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
    <title>Admin</title>
</head>
<body>
<h1>Update</h1>
<form method="post" action="/admin/update">
    <table>
    <tr>
        <input type="hidden" name="userId" value="${userUpdated.id}">
        <td>Name:</td> <td><input type="text" value="${userUpdated.name}" name="nameToUpdate"></td>
    </tr>
    <tr>/
        <td>Age:</td> <td><input type="number" value="${userUpdated.age}" name="ageToUpdate"></td>
    </tr>
    <tr>
        <td>Passport:</td> <td><input type="number" value="${userUpdated.passport}" name="passportToUpdate"></td>
    </tr>
    <tr>
       <td> Password:</td> <td><input type="text" name="passwordToUpdate"></td>
    </tr>
    </table>
    <p>
        Role: <input type="checkbox" name="roleToUpdate"> admin
    </p>

    <p>
        <input type="submit" name="doUpdate" value="Update">
    </p>
</form>

</body>
</html>
