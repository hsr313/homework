<%--
  Created by IntelliJ IDEA.
  User: dr3
  Date: 2020/3/20
  Time: 1:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>主页</title>
    <script type="application/javascript" src="../js/jquery.min.js"></script>
    <script type="application/javascript" src="../js/home.js"></script>

</head>
<body>
    当前用户：${USER_SESSION.username}
    <a href="${pageContext.request.contextPath}/resume/logout">退出</a><br>
    <input type="button" id = "add" value="新增" onclick="window.open('../statics/page/add.html','_self')"/>

    <table  id="ttt" style="height:400px;margin-bottom: 0px;" class="table table-bordered">
        <thead>
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>地址</th>
            <th>电话</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="t_body"></tbody>
    </table>
</body>

</html>
