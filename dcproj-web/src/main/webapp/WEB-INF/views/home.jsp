<%--
  Created by IntelliJ IDEA.
  User: zhaodongchao
  Date: 2017/5/4 13:53
  telphone:17621008632
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h1>hello ${currentUser.username} !</h1> <a href="${pageContext.request.contextPath}/login_out">退出登录</a>
</body>
</html>
