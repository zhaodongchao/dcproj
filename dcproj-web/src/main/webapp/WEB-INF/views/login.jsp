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

  <title>用户登录</title>
  <!-- Bootstrap Core CSS -->
  <link href="${pageContext.request.contextPath}/static/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
${errorMsg}
 <!--此处action为""时，由于是post请求，会被authc过滤器拦截，并且Action的url会认作上一个请求，也就是loginUrl
    在MyFormAuthenticationFilter中登录成功时，会跳转到上一个请求，也就是 /login post 请求
    登录失败时，会跳转到loginUrl ,也就是/login get  请求
 -->
<div class="container">
  <div class="row">
    <div class="col-md-4 col-md-offset-4">
      <div class="login-panel panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title">Please Sign In</h3>
        </div>
        <div class="panel-body">
          <form role="form" action="" method="post">
            <fieldset>
              <div class="form-group">
                <input class="form-control" placeholder="请输入用户名" name="username" type="text" required autofocus>
              </div>
              <div class="form-group">
                <input class="form-control" placeholder="Password" name="password" type="password" required>
              </div>
              <div class="checkbox">
                <label>
                  <input name="rememberMe" type="checkbox" value="Remember Me">Remember Me
                </label>
              </div>
              <button class="btn btn-lg btn-success btn-block" type="submit">Sign in</button>
            </fieldset>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>


