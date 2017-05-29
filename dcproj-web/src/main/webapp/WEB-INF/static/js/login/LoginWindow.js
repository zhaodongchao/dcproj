var BasePath='';
Ext.onReady(function() {  
	BasePath = document.getElementById("BasePath").value;

    Ext.QuickTips.init();
	Ext.lib.Ajax.defaultPostHeader += '; charset=utf-8';
    Ext.BLANK_IMAGE_URL =BasePath+'images/s.gif';
      
    var myform = new Ext.form.FormPanel({  
                bodyStyle : 'padding-top:60px',  
                defaultType : 'textfield',  
                labelAlign : 'right',  
                labelWidth : 80,  
            //    labelPad : 2,  
                frame : true,  
                url : BasePath+'j_spring_security_check',
                method : 'POST',  
                waitMsg : '正在登录......',  
                /**
                 * 增加表单键盘事件，键盘按键10或者13会触发subjectForm方法  
                 */ 
                keys : [ {  
                    key : [ 10, 13 ],  
                    fn : submitForm  
                } ],  
                defaults : {  
                    allowBlank : false,  
                    width : 150  ,
                    height:25,
                    labelStyle: 'font-weight:bold;'

                },  
                items : [  /*{ 
                	   height:85,
                	   width:385,
                       html:'<div height=85 width=385></div>',
                       xtype:'panel'
                },*/
                	{ 
                    cls : 'user',  
                    name : 'j_username',  
                    id:'login.username',
                    fieldLabel : '用户名',  
                    blankText : '用户名不能为空'  
                }, {  
                    cls : 'key',  
                    name : 'j_password',  
                    id : 'login.password', 
                    fieldLabel : '密 码',  
                    blankText : '密码不能为空',  
                    inputType : 'password'  
                }, {  
                    cls : 'key',  
                    name : 'j_verifycode',  
                    id : 'login.verifycode',  
                    fieldLabel : '验证码',  
                    blankText : '验证码不能为空'  
                } ],  
                buttons : [ {  
                    text : '确定',  
                    id : 'login.submit',  
                    handler : submitForm//鼠标按键提交表单  
                }, {  
                    text : '重置',  
                    id : 'login.reset',  
                    handler : reset  
                } ]  
            });  
         
           function reset(){
           myform.getForm().reset();
        
        }
        
        function submitForm(){
        	var loginform = myform.getForm();//获取basicForm对象
          if(loginform.isValid()){
         // 	var name = loginform.findField("login.username").getValue();
          //	loginform.findField("login.username").setValue("1111111111111111111");
           //   alert(name);
                loginform.submit();  
                   }
        }       
	var win = new Ext.Window({
							title:'系统入口',
						    baseCls : 'x-window',
						    resizable : false,
						    draggable : false,
						    closable : true,
						    modal:true,
						    width:400,
						    height:300,
						    layout:'fit',
						    items:[myform]
	
	
	});  
    win.show();  
    
               var bd = Ext.getDom('login.verifycode');  
               var bd2 = Ext.get(bd.parentNode);  
               bd2.createChild({  
						        tag : 'img',  
						        src : BasePath+'createVerifyCode/code',  
						        align : 'absbottom'  
						    });  
        	
     });





