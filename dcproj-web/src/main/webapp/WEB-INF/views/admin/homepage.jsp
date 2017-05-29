<%--
  Created by IntelliJ IDEA.
  User: zhaodongchao
  Date: 2017/5/26 13:52
  telphone:17621008632
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="zhaodongchao">
  <title>系统主页</title>
  <c:set var="ctx" value="${pageContext.request.contextPath}"/>
  <!-- Bootstrap Core CSS -->
  <link href="${cxt}/static/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <link href="${cxt}/static/plugins/bootstrap-treeview/bootstrap-treeview.min.css" rel="stylesheet">

  <link href="${cxt}/static/css/dashboard.css" rel="stylesheet" type="text/css">

</head>
<body onload="_framework.init.initMenu()">
<!-- Fixed navbar -->
<nav class="navbar navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">我的办公桌</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#about">About</a></li>
        <li><a href="#contact">Contact</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li class="dropdown-header">Nav header</li>
            <li><a href="#">Separated link</a></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>
    </div><!--/.nav-collapse -->
  </div>
</nav>
<div class="container-fluid">
  <div class="row">
    <div class="col-sm-3 col-md-2 sidebar">
      <div id="tree_menu"></div>
    </div>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
       <div class="row">
         <ol class="breadcrumb">
           <li><a href="#">Home</a></li>
           <li><a href="#">2013</a></li>
           <li class="active">十一月</li>
         </ol>
       </div>
      <div id="main_data" class="row container">
        hahaha
      </div>
    </div>
  </div>
</div>
<!-- jQuery -->
<script src="${cxt}/static/plugins/jquery12.4/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${cxt}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<!-- Just to make our placeholder images work. -->
<script src="${cxt}/static/plugins/bootstrap-treeview/bootstrap-treeview.min.js"></script>

<script src="${cxt}/static/js/framework/framework.js"></script>

</body>

</html>
