<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add person</title>
</head>
<body>
<h3>New agent</h3>
<form method="post" action='<c:url value="/admin/create" />'>
    <label>Name</label><br>
    <input name="name"/><br><br>
    <label>Role</label><br>
    <input name="role" /><br><br>
    <label>Password</label><br>
    <input name="password" /><br><br>
    <input type="submit" value="Add" />
</form>
</body>
</html>