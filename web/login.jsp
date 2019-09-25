<%--
  Created by IntelliJ IDEA.
  User: nick
  Date: 25.09.2019
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

    <form action="/loginServlet" method="post">
        Login: <input type="text" name="user"><br>
        Password: <input type="password" name="pass"><br>
        <input type="submit" value="Enter">

    </form>

</body>
</html>
