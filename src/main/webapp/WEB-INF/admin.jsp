<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
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
    <p>
        Password: <input type="text" name="password">
    </p>
    <p>
        Role: <input type="checkbox"  name="role"> admin
    </p>
    <p><input type="submit" name="add" value="Create"></p>
</form>
<c:forEach items="${test}" var='user'>
    <form method="post">
        <p>
            <input type="hidden" value="${user.id}" name="userId">
            <c:out value="${user.toGson()}"/>
            <input formaction="/delete" type="submit" name="delete" value="Delete">
            <input formaction="/update" formmethod="get" type="submit" name="update" value="Update">
        </p>
    </form>
</c:forEach>
</body>
</html>