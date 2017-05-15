<%--
  Created by IntelliJ IDEA.
  User: zhaodongchao
  Date: 2017/5/4 13:53
  telphone:17621008632
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" href="${pageContext.request.contextPath}/static/image/icon/home.ico">
  <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
  <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  <title>系统主页</title>
</head>
<body>
<div class="container">
  <div class="page-header">
    <h1>hello ${currentUser.username} !</h1> <a href="${pageContext.request.contextPath}/login_out">退出登录</a>
  </div>
  <div class="page default-color0">
    <div class="page-header">product List</div>
    <div class="page">
      <div id="product"></div>
    </div>
  </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
<script>
    $(function () {
        $.ajax({
            type: 'get',
            url: 'http://localhost:8088/ws/rest/products',
            dataType: 'json',
            success: function (data) {
                alert(data);
            }
        });
    })
</script>
</body>
</html>
