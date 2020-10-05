<%--
  Created by IntelliJ IDEA.
  User: bai
  Date: 2020/10/5
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--
通过 jsp:useBean 的方式创建 People 对象
--%>
<jsp:useBean id="people" class="com.bai.pojo.People" scope="page"/>
<jsp:setProperty name="people" property="id" value="1"/>
<jsp:setProperty name="people" property="name" value="白"/>
<jsp:setProperty name="people" property="age" value="18"/>
<jsp:setProperty name="people" property="address" value="上海"/>

id:<jsp:getProperty name="people" property="id"/>
姓名:<jsp:getProperty name="people" property="name"/>
年龄:<jsp:getProperty name="people" property="age"/>
地址:<jsp:getProperty name="people" property="address"/>

</body>
</html>
