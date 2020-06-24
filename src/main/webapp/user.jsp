
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Page</title>
</head>
<body>
<h3>User Page</h3>
<table border="1" cellpadding="5">
    <tr>
        <th>Name</th>
        <th>ID</th>
        <th>Role</th>
        <th>Password</th>
    </tr>
    <tr>
        <td>${user.name}</td>
        <td>${user.id}</td>
        <td>${user.role}</td>
        <td>${user.password}</td>
    </tr>
</table>
<a href="<c:url value="/logout"/>" > Logout </a>
</body>
</html>