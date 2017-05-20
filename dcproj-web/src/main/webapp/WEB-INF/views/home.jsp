<%--
  Created by IntelliJ IDEA.
  User: zhaodongchao
  Date: 2017/5/4 13:53
  telphone:17621008632
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>系统主页</title>
  <link rel="icon" href="${pageContext.request.contextPath}/static/image/icon/home.ico">
  <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/bootstrap-3.3.7/css/bootstrap.min.css">
  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="${pageContext.request.contextPath}/static/plugins/jquery12.4/jquery.min.js"></script>
  <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
  <script src="${pageContext.request.contextPath}/static/plugins/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="page-header">
  <shiro:guest>
    <h1>you need to login ! <a href="${pageContext.request.contextPath}/login">登录</a></h1>
  </shiro:guest>
  <shiro:user>
    <h1>hello ${currentUser.username} ! <a href="${pageContext.request.contextPath}/login_out">退出登录</a></h1>
    <script>
        $(function () {
            $.ajax({
                type: 'get',
                url: 'http://localhost:8088/ws/rest/products',
                dataType: 'json',
                success: function (data) {
                    var table_body = $("#product_table_body");
                    var product_table_data = '' ;
                    $.each(data,function(i){
                        product_table_data += "<tr><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td>"+data[i].price+"</td></tr>" ;
                    });
                    table_body.html(product_table_data);
                }
            });
        });
    </script>
  </shiro:user>
</div>

<div class="container-fluid">
      <table class="table table-bordered">
        <thead>
          <tr>
            <th>ID</th>
            <th>Product Name</th>
            <th>Price</th>
          </tr>
        </thead>
        <tbody id="product_table_body"></tbody>
      </table>
</div>

</body>
</html>
