/**
 * 系统主界面菜单切换主要js
 * Created by zhaodongchao on 2017/5/23.
 */
(function ($) {
    $.extend({
        mynamespace: function () {
            var a = arguments, o = null, i, j, d, rt;
            for (i = 0; i < a.length; ++i) {
                d = a[i].split(".");
                rt = d[0];
                eval("if (typeof " + rt + " == \"undefined\"){" +
                          rt + " = {}; } " +
                     "    o = " + rt + ";");

                for (j = 1; j < d.length; ++j) {
                    o[d[j]] = o[d[j]] || {};
                    o = o[d[j]]
                }
                alert(this);
                return this ;
            }
        }
    })
})(jQuery);