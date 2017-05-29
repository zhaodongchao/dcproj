var user ;
var pwd ;

function pressLogin (e, p) {
   // $("#login_err").hide();
   
    if (window.event) // IE
    {
        keynum = e.keyCode
    }
    else if (e.which) // Netscape/Firefox/Opera
    {
        keynum = e.which
    }
    user = document.getElementById("username");
    pwd = document.getElementById("password");
    if (keynum == 13) {
        if (p == 2){
            if (user.value != ""&& pwd.value != "")
                login();
        }else if (p == 1){
            if( user.value!= "")
                pwd.focus();
        }
    }
}

function login () {
	user = document.getElementById("username").value;
    pwd = document.getElementById("password").value;
    
   // var tokenValue = document.getElementById("token").value;
   // alert(user+'--'+pwd);
    // 待加密，
    //待添加验证码
    
   // alert(tokenValue);
    
   
    var SubmitFrom = document.createElement("form");//创建提交表单
 
    var username = document.createElement("input");
        username.setAttribute("type", "hidden");
        username.setAttribute("name", "j_username");
        username.setAttribute("value", user);
    
    var password = document.createElement("input");
        password.setAttribute("type", "hidden");
        password.setAttribute("name", "j_password");
        password.setAttribute("value", pwd);
  /*      
     var token = document.createElement("input");
        token.setAttribute("type", "hidden");
        token.setAttribute("name", "newtouch.token");
        token.setAttribute("value", tokenValue);   */
        
	    document.body.appendChild(SubmitFrom);
	    SubmitFrom.appendChild(username);
	    SubmitFrom.appendChild(password);
	 //   SubmitFrom.appendChild(token);
	     
	    SubmitFrom.action = "j_spring_security_check" ;
	    SubmitFrom.method = "POST";
	    
	    SubmitFrom.submit();
   
}