/**
 * 系统主界面菜单切换主要js
 * Created by zhaodongchao on 2017/5/23.
 */
(function ($) {
    $.extend({
        mynamespace: function (namespace) {
            var a = arguments, o = null, i, j, d, rt;
            for (i = 0; i < a.length; ++i) {
                d = namespace.split(".");
                rt = d[0];
                eval("if (typeof " + rt + " == \"undefined\"){" + rt
                    + " = {};} o = " + rt + ";");
                for (j = 1; j < d.length; ++j) {
                    o[d[j]] = o[d[j]] || {};
                    o = o[d[j]]
                }
            }
            return this;
        }
    })
})(jQuery);
$.mynamespace("org.dongchao.framework");

var _framework = org.dongchao.framework;

_framework.init = new function(){
    this.initMenu = function () {
        _framework.layout.initLeftMenu();
    }

};
_framework.layout = new function(){
    this.initLeftMenu = function () {
        var onNodeClick = function (node) {
            alert(123);
        };
       $.ajax({
           url : "/admin/menu/list",
           type : "get",
           dataType : "json",
           async: true,
           success :function (data) {
               $("#tree_menu").treeview({
                   data:data,
                   showCheckbox: true,   //是否显示复选框
                   highlightSelected: true,    //是否高亮选中
                   nodeIcon: 'glyphicon glyphicon-glob',    //节点上的图标
                   emptyIcon: '',    //没有子节点的节点图标
                   unique:true,
                   onNodeChecked: function (event,data) {
                       $("#main_data").load(data.herf);
                   },
                   onNodeSelected: function (event, data) {
                       alert(data.nodeId);
                   }

               });
           },
           error: function () {
               alert("菜单加载失败！")
           }
       });

    }

};



