<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录系统</title>
</head>
<body>
<span style="color: red">${errorMsg}</span>
    <form action="/login" method="post">
        账号：<input type="text" name="userName"><br>
        密码：<input type="password" name="password">
        <br>
        <input type="submit" value="朕要登录">
    </form>
</body>
</html>
