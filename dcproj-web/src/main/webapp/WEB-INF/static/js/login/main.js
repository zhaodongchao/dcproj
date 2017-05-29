var BasePath = '' ;
Ext.onReady(function(){

	BasePath = document.getElementById("BasePath").value ;
	Ext.lib.Ajax.defaultPostHeader += '; charset=utf-8';
    Ext.BLANK_IMAGE_URL = BasePath+'images/s.gif';//设置此图片路径，防止ext初始化的时候从网络上加载
    
    initMainPanel();
});

 function initMainPanel(){
  var mainPanel = new Main.MainPanel();
    var viewport = new Ext.Viewport({
        layout:'fit',
        border:false,
        items:[mainPanel]
    });
 }