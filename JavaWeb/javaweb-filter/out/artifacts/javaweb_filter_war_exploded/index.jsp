<%--
  Created by IntelliJ IDEA.
  User: bai
  Date: 2020/10/6
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
当前网站在线人数 <span style="color: blue;"><%=this.getServletConfig().getServletContext().getAttribute("online_count")%></span>
</body>
</html>
