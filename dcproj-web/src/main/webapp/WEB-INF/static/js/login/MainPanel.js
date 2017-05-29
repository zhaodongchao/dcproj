Ext.namespace("Main");
Main.MainPanel = Ext.extend(Ext.Panel, {
    title : "系统主界面",
	layout:"border",
    collapsible : false,
    frame : true,
    id:'Main.MainPanel',
    initComponent : function(){
       //主界面采用border布局，分为north，south，west,center(border布局必须要有center)
    	this.northPart = new Ext.Panel( {
			region : 'north',
			border : false,
			html : '<div style="background:url('+BasePath+'images/main_north.gif) repeat-x; height:78px;">' +
					'<div align="right" style="height: 78"> <a href="'+BasePath+'loginout">Log Out</a></div>' +
					'</div>',
			height : 80
		});
		
    	this.southPart = new Ext.Panel( {
			region : 'south',
			html : '<div style="background:url('+BasePath+'images/main_south.gif) repeat-x; height:33px; ">'
					+ '<div style="float:left;font:normal 12px tahoma, arial, sans-serif, 宋体;margin:10px 0 0 10px;">'
					+ 'Power By:	<span style="color:blue">赵东朝</span> &nbsp;</div>'
					+ '<div	style="float:right;margin:10px;font:normal 12px tahoma, arial, sans-serif, 宋体;" >'
					+ '版权所有：<a href="http://www.morik.com">www.morik.com</a></div>'
					+ '</div>',
			height : 35
		});
		
		
	var rootNode = new Ext.tree.AsyncTreeNode({
		  id:'root',
	      text:'主菜单'
	      
	    });	
	    //从后台数据库获取子节点的序列  
	    //向后台请求数据时，会默认的向后台传一个变量名为node，值为此节点id的数据
	var nodeLoader  = new Ext.tree.TreeLoader({
	     dataUrl:BasePath+'sm/menu' ,
	     baseParams: {} 
	}) ;
    //在树的节点加载时添加事件
    nodeLoader.on('beforeload',this.beforeload_handler);
    nodeLoader.on('load',this.load_handler);
    nodeLoader.on("loadexception",this.loadException_handler);
    
	this.simpleTree = new Ext.tree.TreePanel({
	    useArrows: true,
	    autoScroll: true,
	    animate: true,
	    enableDD: false,//不允许拖拽
	    containerScroll: true,
	    border: false,
	    root:rootNode,
	    loader:nodeLoader

	});
	//给菜单的节点添加点击事件，并指定URL显示的页面
	this.simpleTree.on("click",this.node_click);
    	this.leftmenu = new Main.LeftMenu( {
			title : '我的办公系统',
			items : [ {
				title : '办公桌',
				items : [this.simpleTree]
			}]

});
        this.centerTab =new Main.CenterTabPanel({
    		id:'MainPanel.centerTab',
			style : 'padding:0 6px 0 0',
			autoScroll : true,
			region : 'center',
			activeTab : 0,
			resizeTabs : true,
			inTabWidth : 100,
			tabWidth : 90,
			enableTabScroll : true,
			items : [{
				title : '我的首页',
				html : '<div style="width:1166px; height:428px; background:url('+BasePath+'images/3.jpg);"></div>'
			}]
        });
        this.items=[this.northPart,this.southPart, this.leftmenu, this.centerTab] ;
        Main.MainPanel.superclass.initComponent.call(this);
      
        rootNode.expand();  //将根节点上的节点展开
        },
      beforeload_handler:function(tree,node){
    	//alert(node.text);
    	
    },
      load_handler:function(tree,node,response){
    	
     },
      loadException_handler:function(tree,node,response){
    	
      var status = response.status;
      var text = response.responseText;

    switch(status){
        case 404:
            alert("请求url不可用。");
            break;
        case 200:
            if (text.length > 0){
                Ext.MessageBox.alert("提示", text);
            }
            break;
        default:
            Ext.MessageBox.alert("提示", status + "," + text);
            break;
        }
     },
     node_click:function(node){
        
        var pageUrl = node.attributes.skiphref;
        var level = node.attributes.level ;
        
       if(level==1){
       	  return ;
          }
      
        if (pageUrl==undefined||pageUrl==""){
		        return;
		    }
		 
       var frameId = node.id ;
       var sheetId = "sheet_" + frameId;
       var centerTab = Ext.getCmp('MainPanel.centerTab');
       var tapFrame = Ext.get(frameId) ;
       if(null==tapFrame||undefined==tapFrame){
        var sframe = "<iframe id='" + frameId + "' name='" + frameId + "' src='" +BasePath+ pageUrl + "' width='100%' height='100%' frameborder=0>";
        centerTab.add({id:sheetId, title:node.text, closable:true, html:sframe});
       }else{
         tapFrame.dom.src = BasePath+ pageUrl ;
       }
       var sheet = centerTab.getItem(sheetId);
       centerTab.setActiveTab(sheet);
     }
});