
	/** Handlebars模板配置 **/
    {
        var cctemplate = {};
        Handlebars.registerHelper("date", function (d) {
            return new Handlebars.SafeString(d ? new Date(d).Format("yyyy-MM-dd") : "");
        });
        Handlebars.registerHelper("time", function (d) {
            return new Handlebars.SafeString(d ? new Date(d).Format("hh:mm:ss") : "");
        });
        Handlebars.registerHelper("datetime", function (d) {
            return new Handlebars.SafeString(d ? new Date(d).Format("yyyy-MM-dd hh:mm:ss") : "");
        });
        Handlebars.registerHelper("equal", function (d1, d2, options) {
            if (d1 == d2){
               return options.fn(this);
            }
            return options.inverse(this);
        });
        Handlebars.registerHelper("cmp_gt",function(x1,x2,options){
          //debugger;
          if( !x1 ){
            return options.inverse(this);
          }
    　　　　if(x1>x2){
    　　　　　　//知足条件执行
    　　　　　　return options.fn(this);
    　　　　}else{
    　　　　　　//不知足执行{{else}}部分
    　　　　　　return options.inverse(this);
    　　　　}
        });

        // 这个地方不能保留，否则会产生模板无法渲染的问题
//        Handlebars.registerHelper("helperMissing", function (name, d) {
//            if (cctemplate[name]){
//                return cctemplate[name](d);
//            }else if( d ){
//                // 待验证
//                return d;
//            }else{
//                return new Handlebars.SafeString("Unknown template " + name);
//            }
//        });

    }


    /** Date format **/
    {
        // 对Date的扩展，将 Date 转化为指定格式的String   
        // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
        // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
        // 例子：   
        // (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
        // (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
        Date.prototype.Format = function (fmt) { //author: meizz   
            var o = {
                "M+": this.getMonth() + 1,                 //月份   
                "d+": this.getDate(),                    //日   
                "h+": this.getHours(),                   //小时   
                "m+": this.getMinutes(),                 //分   
                "s+": this.getSeconds(),                 //秒   
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度   
                "S": this.getMilliseconds()             //毫秒   
            };
            if (/(y+)/.test(fmt))
                fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt))
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
        }
    }
    
//	{
//		/* Chinese initialisation for the jQuery UI date picker plugin. */
//		/* Written by Cloudream (cloudream@gmail.com). */
//		(function( factory ) {
//			if ( typeof define === "function" && define.amd ) {
//
//				// AMD. Register as an anonymous module.
//				define([ "../datepicker" ], factory );
//			} else {
//
//				// Browser globals
//				factory( jQuery.datepicker );
//			}
//		}(function( datepicker ) {
//			if (!datepicker) return;
//			datepicker.regional['zh-CN'] = {
//				closeText: '关闭',
//				prevText: '&#x3C;上月',
//				nextText: '下月&#x3E;',
//				currentText: '今天',
//				monthNames: ['一月','二月','三月','四月','五月','六月',
//				'七月','八月','九月','十月','十一月','十二月'],
//				monthNamesShort: ['一月','二月','三月','四月','五月','六月',
//				'七月','八月','九月','十月','十一月','十二月'],
//				dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
//				dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
//				dayNamesMin: ['日','一','二','三','四','五','六'],
//				weekHeader: '周',
//				dateFormat: 'yy-mm-dd',
//				firstDay: 1,
//				isRTL: false,
//				showMonthAfterYear: true,
//				yearSuffix: '年'};
//			datepicker.setDefaults(datepicker.regional['zh-CN']);
//
//			return datepicker.regional['zh-CN'];
//
//		}));
//	}
	
//	/** Sidebar **/
//    {
//    	/*
//		var catalog = "",
//			catalogs = window.location.pathname.split('/'),
//			$sidebar = $("#sidebar");
//		for(var i in catalogs){
//			if (catalogs[i] && i > 1){
//				if (catalog)
//					catalog += "/";
//				catalog+=catalogs[i];
//				$sidebar.find("li[data-catalog=\"" + catalog + "\"]").addClass("active");
//			}
//		}
//		*/
//		var path = window.location.pathname,
//			$sidebar = $(".sidebar");
//		//$sidebar.find("a[href*=\"" + path + "\"]").parents("li").addClass("active");
//		//由于admin/userrole和admin/user的地址,开头相同,改为不用匹配的方式  duyong
//		$sidebar.find("a[href=\"" + path + "\"]").parent("li").addClass("active");
///*
//		catalog = $(document.body).attr("data-catalog");
//		$("#navbar-menu").find("li[data-catalog=" + catalog + "]").addClass("active");
//*/
//		$("#navbar-menu").find("li").each(function(){
//			var catalogs=$(this).attr("data-catalog").split(" ");
//			for(var c in catalogs){
//				var catalog = app + catalogs[c],
//					idx = path.indexOf(catalog);
//				if(0 == idx) $(this).addClass("active");
//			}
//		});
//    }
    
//    /** validate **/
//    {
//    	/*
//    	 * Translated default messages for the jQuery validation plugin.
//    	 * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
//    	 */
//    	$.extend($.validator.messages, {
//    		required: "不能为空",
//    		remote: "请修正此栏位",
//    		email: "请输入有效的电子邮件",
//    		url: "请输入有效的网址",
//    		date: "请输入有效的日期",
//    		dateISO: "请输入有效的日期 (YYYY-MM-DD)",
//    		number: "请输入正确的数字",
//    		digits: "请输入整数",
//    		creditcard: "请输入有效的信用卡号码",
//    		equalTo: "你的输入不相同",
//    		extension: "请输入有效的后缀",
//    		maxlength: $.validator.format("最多 {0} 个字"),
//    		minlength: $.validator.format("最少 {0} 个字"),
//    		rangelength: $.validator.format("请输入长度为 {0} 至 {1} 之間的字串"),
//    		range: $.validator.format("请输入 {0} 至 {1} 之间的数值"),
//    		max: $.validator.format("请输入不大于 {0} 的数值"),
//    		min: $.validator.format("请输入不小于 {0} 的数值")
//    	});
//
//    	$.validator.setDefaults({
//    	    highlight: function(element) {
//    	        $(element).closest('div').addClass('has-error');
//    	    },
//    	    unhighlight: function(element) {
//    	        $(element).closest('div').removeClass('has-error');
//    	    },
//    	    errorElement: 'span',
//    	    errorClass: 'help-block',
//    	    errorPlacement: function(error, element) {
//    	        if(element.parent('.input-group').length) {
//    	            error.insertAfter(element.parent());
//    	        } else {
//    	            error.insertAfter(element);
//    	        }
//    	    }
//    	});
//    }
//})(jQuery);
//{
//   laydate.render({
//     elem: 'input[type=_datetime]',type:'datetime'
//   });
//   laydate.render({
//     elem: 'input[type=_date]',type:'date'
//   });
//}
