<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Enter Error Page</title>
</head>
<body>
<c:url var="url" value="/admin.jsp"/>
<h2>Invalid loginName or role. Role must be "admin" OR "user"</h2>

<p>Click here to <a href="${url}">Try Again</a></p>
</body>
</html>
