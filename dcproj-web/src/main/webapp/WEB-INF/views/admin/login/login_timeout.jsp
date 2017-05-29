<%@ page language="java" contentType="text/html; charset=utf-8"
				 pageEncoding="utf-8"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    <%
      String path = request.getContextPath();
      String BasePath = request.getScheme() +"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   
      
      //清除缓存，每次访问该页面时都从服务器端读取
      response.setHeader("Pragma","No-cache");
      response.setHeader("Cache-Control","no-cache");
      response.setDateHeader("Expires", 0);
      
 /*      Map<String,String> mp = (Map<String,String>)request.getSession().getAttribute("newtouch.token");
     
      //获取mp的key值，及token值
         
      String token = "";
     if(mp.containsKey("newtouch.token")){
    	 token = mp.getOrDefault("newtouch.token", "你重复提交了");
     } */
      %>
<!DOCTYPE HTML>
<html>
<base href="<%=BasePath %>">
<head>
<title>Home</title>
<!-- Custom Theme files -->
<link href="${pageContext.servletContext.contextPath }/css/style.css" rel="stylesheet" type="text/css" media="all"/>
<!-- Custom Theme files -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/sm/login/js/login.js"></script>
</head>
<%-- <input type="hidden" id="token" value="<%=token%>"/> --%>
<body>
<div class="login">
	<h2>Login for this System</h2>
	<div class="login-top">
		<h1>LOGIN FORM</h1>
		
			<input type="text"  value="${requestScope.error}" id="username" onfocus="this.value='';" onblur="if(this.value=='') {this.value = 'please input you username';}" onkeypress="pressLogin(event,1)">
			<input type="password" value="password" id="password" onfocus="this.value='';" onblur="if(this.value=='') {this.value = '';}" onkeypress="pressLogin(event,2)">
	    <div class="forgot">
	    	<a href="#">forgot Password</a>
	    	<input type="submit" value="Login"  onclick="login();" />
	    </div>
	</div>
	<div class="login-bottom">
		<h3>New User &nbsp;<a href="#">Register</a>&nbsp Here</h3>
	</div>
</div>	
<div class="copyright">
	<p>Copyright &copy; 2015.Company name All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p>
</div>

<c:out value="${requestScope.error}"></c:out>
</body>
</html>