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
${sessions}
<div id="contentwrapper" class="contentwrapper">

  <div class="contenttitle2">
    <h3>Standard Table</h3>
  </div><!--contenttitle-->

  <table cellpadding="0" cellspacing="0" border="1" class="stdtable">
    <colgroup>
      <col class="con0" />
      <col class="con1" />
      <col class="con0" />
      <col class="con1" />
      <col class="con0" />
      <col class="con0" />
      <col class="con0" />
    </colgroup>
    <thead>
    <tr>
      <th></th>
      <th class="head0">SessionId</th>
      <th class="head1">登录人</th>
      <th class="head0">机器IP</th>
      <th class="head0">创建时间</th>
      <th class="head1">最后访问时间</th>
      <th class="head1">已强制退出</th>
      <th class="head1">操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${sessions}" var="session" varStatus="i">
     <tr>
       <td>${i.index+1}</td>
       <td>${session.sessionId}</td>
       <td>${session.loginUser["username"]}</td>
       <td>${session.loginIP}</td>
       <td>${session.createTime}</td>
       <td>${session.lastAccessTime}</td>
       <td></td>
       <td><a href="#">强制退出</a></td>
     </tr>
    </c:forEach>
    </tbody>
  </table>

</div>

</body>
</html>
