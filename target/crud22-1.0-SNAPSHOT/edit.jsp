<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit agent</title>
</head>
<body>
<h3>Edit agent</h3>
<form method="post" >
    <input type="hidden" value="${user.id}" name="id" />
    <label>Name</label><br>
    <input name="name" value="${user.name}" /><br><br>
<%--    <label>Nickname</label><br>--%>
<%--    <input name="nickname" value="${user.nickname}" /><br><br>--%>
    <label>Role</label><br>
    <input name="role" value="${user.role}" /><br><br>
    <label>Password</label><br>
    <input name="password" value="${user.password}" /><br><br>
    <input type="submit" value="Send" />
</form>
</body>
</html>