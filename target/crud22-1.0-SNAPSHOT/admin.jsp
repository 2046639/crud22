<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users</title>

    <style>
        .fig {
            text-align: center;
        }
    </style>

</head>
<div align="center">
    <h2>Users List</h2>
    <p><a href='<c:url value="/admin/create" />'>Create new</a></p>
    <a href="<c:url value="/logout"/>" > Logout </a>
    <table border="1" cellpadding="5">
        <tr>
            <th>Name</th>
            <th>ID</th>
            <th>Role</th>
            <th>Password</th>
            <th>Action</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.name}</td>
                <td>${user.id}</td>
                <td>${user.role}</td>
                <td>${user.password}</td>
                <td>

                    <form method="get" action='<c:url value="/admin/edit?id=${user.id}" />' style="display:inline;">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="submit" value="Edit">
                    </form>
                    <form method="post" action='<c:url value="/admin/delete" />' style="display:inline;">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="<c:url value="/user"/>" > User Page </a><p>
    <a href="<c:url value="/logout"/>" > Logout </a>
    <h3>&#169; 2020 Si vis pacem, para bellum</h3>
</div>
</body>
</html>