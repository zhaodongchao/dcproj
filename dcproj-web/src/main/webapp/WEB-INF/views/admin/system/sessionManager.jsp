<%--
  Created by IntelliJ IDEA.
  User: zhaodongchao
  Date: 2017/5/21 20:07
  telphone:17621008632
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title></title>
</head>
<body>
<div id="contentwrapper" class="panel">
  <div class="page-header">
    <h3>会话管理</h3>
  </div>
  <div class="table-responsive">

    <table class="table table-striped">
      <thead>
      <tr>
        <th></th>
        <th>登录人</th>
        <th>机器IP</th>
        <th>创建时间</th>
        <th>最后访问时间</th>
        <th>已强制退出</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${sessions}" var="session" varStatus="i">
        <tr class="success">
          <td>${i.index+1}</td>
          <td>${session.loginUser["username"]}</td>
          <td>${session.loginIP}</td>
          <td>${session.createTime}</td>
          <td>${session.lastAccessTime}</td>
          <td>否</td>
          <td><a href="#">强制退出</a></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>

</div>

</body>
</html>
