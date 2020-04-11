<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<h1>Users</h1>
<form method="post" action="/admin">
    <table>
        <tr>
         <td> Name:</td><td> <input type="text" name="name"></td>

        </tr>
        <tr>
            <td> Age:</td><td> <input type="number" name="age"></td>
        </tr>
        <tr>
            <td>Passport: </td><td><input type="number" name="passport"></td>
        </tr>
        <tr>
            <td> Password:</td><td> <input type="text" name="password"></td>
        </tr>
    </table>
    <p>
        Role: <input type="checkbox" name="role"> admin
    </p>
    <p><input type="submit" name="add" value="Create"></p>
</form>
<table>
    <tr>
        <td><h4>Name: </h4></td>
        <td><h4>Age: </h4></td>
        <td><h4>Passport: </h4></td>
        <td><h4>Role: </h4></td>
    </tr>
    <c:forEach items="${test}" var='user'>
        <form method="post">
            <input type="hidden" value="${user.id}" name="userId">
            <tr>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.age}"/></td>
                <td><c:out value="${user.passport}"/></td>
                <td><c:out value="${user.role}"/></td>
                <td><input formaction="/admin/delete" type="submit" name="delete" value="Delete"></td>
                <td><input formaction="/admin/update" formmethod="get" type="submit" name="update" value="Update"></td>
            </tr>
        </form>
    </c:forEach>
</table>
</body>
</html>