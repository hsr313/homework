<%--
  Created by IntelliJ IDEA.
  User: dr3
  Date: 2020/3/20
  Time: 1:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script></script>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
    ${msg}
    <form action="${pageContext.request.contextPath}/resume/login" method="post">
        用户名：<input type="text" name="username"><br>
        密&nbsp;&nbsp;&nbsp;码:
        <input type="password" name="password"><br>
        <input type="submit" value="登录">
    </form>
</body>
</html>
