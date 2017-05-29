<%@ page language="java" import="java.util.Random" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
    <%
      String path = request.getContextPath();
      String BasePath = request.getScheme() +"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   
      
      //生成随机类
      Random random = new Random();
      //取随机产生的16位加密使用的Key,Iv值
      String key = "";
      String iv = "";
      for (int i = 0; i < 16; i ++){
          String rand = String.valueOf(random.nextInt(10));
          key += rand;
          rand = String.valueOf(random.nextInt(10));
          iv += rand;
      }
      //将认证码存入SESSION
   /*    HttpSession sn = request.getSession(false); //没有不创建
      if (sn != null){
          sn.setAttribute("EncryptKey", key);
          sn.setAttribute("EncryptIv", iv);
      } */
      
      
      //清除缓存，每次访问该页面时都从服务器端读取
      response.setHeader("Pragma","No-cache");
      response.setHeader("Cache-Control","no-cache");
      response.setDateHeader("Expires", 0);
      //response.setHeader("SET-COOKIE", "JSESSIONID=" + sessionId + "; HttpOnly");

      String imgUrl =  BasePath+"createVerifyCode/code";
      String loginUrl = BasePath + "login_url";
   
      %>
<title>网站管理员登陆</title>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #1D3647;
}
</style>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/sm/login/js/login.js"></script>
<link href=<%=BasePath+ "images/images/skin.css"%> rel="stylesheet" type="text/css">
<body>
<script type="text/javascript">
function changeImg(){
	document.getElementById("verifyImg").src ='<%= imgUrl %>'+ '?v=' + Math.floor(Math.random() * 100000);
}
var loginUrl = '<%=loginUrl%>';
var loginKey = '<%=key %>';
var loginIv  = '<%=iv %>';
</script>
<table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="42" valign="top"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
      <tr>
        <td width="1%" height="21">&nbsp;</td>
        <td height="42">&nbsp;</td>
        <td width="17%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg">
      <tr>
        <td width="49%" align="right"><table width="91%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
            <tr>
              <td height="138" valign="top"><table width="89%" height="427" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="149">&nbsp;</td>
                </tr>
                <tr>
                  <td height="80" align="right" valign="top"><img src=<%=BasePath+ "images/images/logo.png"%> width="279" height="68"></td>
                </tr>
                <tr>
                  <td height="198" align="right" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="35%">&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p>1- 地区商家信息网门户站建立的首选方案...</p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p>2- 一站通式的整合方式，方便用户使用...</p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p>3- 强大的后台系统，管理内容易如反掌...</p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td width="30%" height="40"><img src=<%=BasePath+ "images/images/icon-demo.gif"%> width="16" height="16"><a href="http://www.865171.cn" target="_blank" class="left_txt3"> 使用说明</a> </td>
                      <td width="35%"><img src=<%=BasePath+"images/images/icon-login-seaver.gif"%> width="16" height="16"><a href="http://www.865171.cn" class="left_txt3"> 在线客服</a></td>
                    </tr>
                  </table></td>
                </tr>
              </table></td>
            </tr>
            
        </table></td>
        <td width="1%" >&nbsp;</td>
        <td width="50%" valign="bottom">
        <table width="100%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="4%">&nbsp;</td>
              <td width="96%" height="38"><span class="login_txt_bt">登陆系统</span></td>
            </tr>
            <tr><td> <td width="50%" colspan="2" ><font color="red" size="2">${error}</font></td></td></tr>
            <tr>
              <td height="21"colspan="2" width="100%">
              <table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
                  <tr>
                    <td height="164" colspan="2" align="middle">
                        <table cellSpacing="0" cellPadding="0" width="100%" border="0" height="143" id="table212">
                          <tr>
                            <td width="13%" height="38" class="top_hui_text"><span class="login_txt">管理员：&nbsp;&nbsp; </span></td>
                            <td height="38" colspan="1" class="top_hui_text"><input name="login_username" id="login_username" class="editbox4" value="" size="20" onkeypress="pressLogin(event,1)">                            </td>
                            <td> </td>
                          </tr>
                          <tr>
                            <td width="13%" height="35" class="top_hui_text"><span class="login_txt"> 密 码： &nbsp;&nbsp; </span></td>
                            <td height="35" colspan="2" class="top_hui_text"><input class="editbox4" type="password" id="login_password" size="20" name="login_password" onkeypress="pressLogin(event,2)">
                              <img src=<%=BasePath+ "images/images/luck.gif"%> width="19" height="18"> </td>
                          </tr>
                          <tr>
                            <td width="13%" height="35" >
                            <span class="login_txt">验证码：</span>
                            </td>
                            <td class="top_hui_text">
                             <input name=login_verifycode id="login_verifycode" type="text" maxLength=6 size="20" onkeypress="pressLogin(event,3)">
                            </td>
                            <td height="35" >
                               &nbsp;&nbsp; <img title="点击刷新" src="<%= imgUrl %>" id = "verifyImg" onclick="changeImg();">
                            </td>
                          </tr>
                          <tr>
                            <td width="13%" colspan="1" height="35" ></td>
                            <td  width="35%" align="center"><img src="<%= BasePath+"images/images/Login_but.gif"%>" onclick="login()"></td>
                            <td width="67%" class="top_hui_text"></td>
                          </tr>
                        </table>
                        <br>
                   </td>
                  </tr>
                  <tr>
                    <td width="433" height="164" align="right" valign="bottom"><img src=<%=BasePath+ "images/images/login-wel.gif"%> width="242" height="138"></td>
                    <td width="57" align="right" valign="bottom">&nbsp;</td>
                  </tr>
              </table>
              </td>
            </tr>
          </table>
          </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="20"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
      <tr>
        <td align="center"><span class="login-buttom-txt">Copyright &copy; 2009-2010 www.865171.cn</span></td>
      </tr>
    </table></td>
  </tr>
</table>
