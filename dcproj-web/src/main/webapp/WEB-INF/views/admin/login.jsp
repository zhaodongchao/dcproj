<%--
  Created by IntelliJ IDEA.
  User: zhaodongchao
  Date: 2017/5/13 17:28
  telphone:17621008632
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
  <meta name="description" content="">
  <meta name="author" content="zhaodongchao">
  <link rel="icon" href="${pageContext.request.contextPath}/static/images/icon/home.ico">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/signin.css">
  <title>用户登录</title>
</head>

<body>
${errorMsg}
<!--此处action为""时，由于是post请求，会被authc过滤器拦截，并且Action的url会认作上一个请求，也就是loginUrl
   在MyFormAuthenticationFilter中登录成功时，会跳转到上一个请求，也就是 /login post 请求
   登录失败时，会跳转到loginUrl ,也就是/login get  请求
-->

<div class="container">
  <section class="main">
    <form class="form-3" action="${pageContext.request.contextPath}/ss_login" method="post">
      <p class="clearfix">
        <label for="login">Username</label>
        <input type="text" name="username" id="login" placeholder="Username">
      </p>
      <p class="clearfix">
        <label for="password">Password</label>
        <input type="password" name="password" id="password" placeholder="Password">
      </p>
      <p class="clearfix">
        <input type="checkbox" name="rememberMe" id="remember">
        <label for="remember">Remember me</label>
      </p>
      <p class="clearfix">
        <input type="submit" name="submit" value="Sign in">
      </p>
    </form>
    ​
  </section>

</div>
</body>
</html>


