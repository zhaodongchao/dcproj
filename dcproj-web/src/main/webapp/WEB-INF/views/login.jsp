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
  <meta name="author" content="">
  <link rel="icon" href="${pageContext.request.contextPath}/static/image/icon/home.ico">

  <title>Signin Template for Bootstrap</title>

  <!-- Bootstrap core CSS -->
  <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">


  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath}/static/css/signin.css" rel="stylesheet">

</head>

<body>
${errorMsg}
<div class="container">

  <form class="form-signin" action="${pageContext.request.contextPath}/system/login" method="post">
    <h2 class="form-signin-heading">Please sign in</h2>
    <label for="username" class="sr-only">用户名</label>
    <input type="text" id="username" name="username" class="form-control" placeholder="请输入用户名" required autofocus>
    <label for="password" class="sr-only">Password</label>
    <input type="password" id="password" name="password" class="form-control" placeholder="请输入登录密码" required>
    <div class="checkbox">
      <label>
        <input type="checkbox" value="remember-me" name="rememberMe"> Remember me
      </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  </form>

</div>
</body>
</html>

