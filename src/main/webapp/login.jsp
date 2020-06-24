<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Login Page</title>
</head>

<h2>Login Page:</h2>
<p></p>
<form action='<c:url value="/"/>' method=post>
    <p><strong>Name: </strong>
        <input type="text" name="name" size="25">
    <p>
    <p><strong>Password: </strong>
        <input type="password" size="15" name="password">
    <p>
    <p>
        <input type="submit" value="Submit">
        <input type="reset" value="Reset">
</form>
</html>