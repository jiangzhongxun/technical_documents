<%--
  Created by IntelliJ IDEA.
  User: bai
  Date: 2020/10/2
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    账号: <input type="text" name="username"> <br>
    密码: <input type="password" name="password"> <br>
    爱好:
    <input type="checkbox" name="hobbys" value="英雄联盟"> 英雄联盟
    <input type="checkbox" name="hobbys" value="穿越火线"> 穿越火线
    <input type="checkbox" name="hobbys" value="吃鸡"> 吃鸡
    <br>
    <input type="submit">
</form>
</body>
</html>
