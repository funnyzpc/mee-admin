/** seajs **/
seajs.config({ "base": app + "/public/module/"/*, 'map': [['.js', '.js?v=1.0']]*/});

(function ($) {
	/** ajax 配置 **/
	{$.ajaxSetup({contentType: "application/x-www-form-urlencoded; charset=utf-8"});}

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

        Handlebars.registerHelper("helperMissing", function (name, d) {
            if (cctemplate[name])
                return cctemplate[name](d);
            else
                return new Handlebars.SafeString("Unknown template " + name);
        });

        $.template = function (name, data, proc) {
            if (cctemplate[name]) {
                proc(cctemplate[name](data));
            } else {
                var t = $(name);
                if (t.length) {
                    cctemplate[name] = Handlebars.compile(t.html());
                    proc(cctemplate[name](data));
                } else {
                    $.get("/template/" + name + ".html", function (txt) {
                        $("<div>" + txt + "</div>").find(">div").each(function (i, e) {
                            var t = $(e);
                            cctemplate[t.attr("id")] = Handlebars.compile(t.html());
                        });
                        proc(cctemplate[name](data));
                    }).fail(function () {
                        proc("template failed!")
                    });
                }
            }
        };
        $.fn.template = function (name, data, proc) {
            return this.each(function () {
                var me = $(this);
                $.template(name, data, function (txt) {
                    proc ? proc(name, data, txt) : me.html(txt);
                });
            });
        };
    }
    /** form **/
    {
        function form2Json(f) {
            var serializeObj = {},
                array = f.serializeArray();
            $(array).each(function () {
                var e = f[0][this.name],
                    e = e && e.length ? e[0] : e;
                if (e && /checkbox/i.test(e.type))
                    this.value = ("on" == this.value);
                if (serializeObj[this.name]) {
                    if ($.isArray(serializeObj[this.name])) {
                        serializeObj[this.name].push(this.value);
                    } else {
                        serializeObj[this.name] = [serializeObj[this.name], this.value];
                    }
                } else {
                    serializeObj[this.name] = this.value;
                }
            });
            return serializeObj;
        };

        var form_set_val = {
            input: function (e, v) {
                var t = e.type.toLowerCase();
                form_set_val[t] ? form_set_val[t](e, v) : $(e).val(v);
            },
            textarea: function (e, v) {
                $(e).val(v);
            },
            select: function (e, v) {
                $(e).val(v);
            },
            checkbox: function (e, v) {
                $(e).prop("checked", v);
            },
            radio: function (e, v) {
                $(e).prop("checked", e.value == v);
            }
        }

        $.json2str = function (o) {
            var arr = [];
            var fmt = function (s) {
                if (typeof s == "object" && s != null) return json2str(s);
                return /^(string|number)$/.test(typeof s) ? "\"" + s.replace(/\"/g, "\\\"") + "\"" : s;
            }
            for (var i in o) arr.push("\"" + i + "\":" + fmt(o[i]));
            return "{" + arr.join(",") + "}";
        }

        // reset from
        $.fn.resetForm = function (o, notAll) {
            var $f = this;
            if (o) {
                $f.find(":input").each(function (i, e) {
                    var tag = e.tagName.toLowerCase(),
                        n = e.name;
                    if (n && form_set_val[tag])
                    	if (o[n] || !notAll)
                    		form_set_val[tag](e, o[n]);
                });
            } else {
                $f.each(function () {
                    this.reset();
                });
            }
            return this;
        };
    }
    {
    	// for ie8/7/6 不支持isArray
    	function isArrayFn(value){
    		if (typeof Array.isArray === "function") {
    			return Array.isArray(value); 
    		}else{ 
    			return Object.prototype.toString.call(value) === "[object Array]"; 
    		} 
    	} 
        var json2str = function (o) {
            var arr = [];
            var fmt = function (s) {
                if (typeof s == "object" && s != null) return json2str(s);
                return /^(string)$/.test(typeof s) ? "\"" + s.replace(/\"/g, "\\\"") + "\"" : s;
            }
            if (isArrayFn(o)){
            	for (var i in o) arr.push(fmt(o[i]));
            	return "[" + arr.join(",") + "]";
            }else{
            	for (var i in o) arr.push("\"" + i + "\":" + fmt(o[i]));
            	return "{" + arr.join(",") + "}";
            }
        };
        $.json2str = json2str;
    }

    /** Hash2functon **/
    {
        $(window).hashchange(function () {
            var hash = location.hash;
            if (!/^#!.+/.test(hash)) return;
            var p1 = hash.lastIndexOf("/"),
                path = hash.substr(2, p1 - 2),
                method = hash.substr(p1 + 1),
                args = method.split(","),
                method = args[0],
                args = args.slice(1);
            seajs.use(path, function (o) {
                if (o && method)
                    o[method](args);
            });
        }).hashchange();
    }

    /** Cookie **/
    {
        jQuery.cookie = function (name, value, options) {
            if (typeof value != 'undefined') { // name and value given, set cookie
                options = options || {};
                if (value === null) {
                    value = '';
                    options.expires = -1;
                }
                var expires = '';
                if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
                    var date;
                    if (typeof options.expires == 'number') {
                        date = new Date();
                        date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
                    } else {
                        date = options.expires;
                    }
                    expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
                }
                var path = options.path ? '; path=' + options.path : '';
                var domain = options.domain ? '; domain=' + options.domain : '';
                var secure = options.secure ? '; secure' : '';
                document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
            } else { // only name given, get cookie
                var cookieValue = null;
                if (document.cookie && document.cookie != '') {
                    var cookies = document.cookie.split(';');
                    for (var i = 0; i < cookies.length; i++) {
                        var cookie = jQuery.trim(cookies[i]);
                        // Does this cookie string begin with the name we want?
                        if (cookie.substring(0, name.length + 1) == (name + '=')) {
                            cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                            break;
                        }
                    }
                }
                return cookieValue;
            }
        }
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
    
	{
		/* Chinese initialisation for the jQuery UI date picker plugin. */
		/* Written by Cloudream (cloudream@gmail.com). */
		(function( factory ) {
			if ( typeof define === "function" && define.amd ) {

				// AMD. Register as an anonymous module.
				define([ "../datepicker" ], factory );
			} else {

				// Browser globals
				factory( jQuery.datepicker );
			}
		}(function( datepicker ) {
			if (!datepicker) return;
			datepicker.regional['zh-CN'] = {
				closeText: '关闭',
				prevText: '&#x3C;上月',
				nextText: '下月&#x3E;',
				currentText: '今天',
				monthNames: ['一月','二月','三月','四月','五月','六月',
				'七月','八月','九月','十月','十一月','十二月'],
				monthNamesShort: ['一月','二月','三月','四月','五月','六月',
				'七月','八月','九月','十月','十一月','十二月'],
				dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
				dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
				dayNamesMin: ['日','一','二','三','四','五','六'],
				weekHeader: '周',
				dateFormat: 'yy-mm-dd',
				firstDay: 1,
				isRTL: false,
				showMonthAfterYear: true,
				yearSuffix: '年'};
			datepicker.setDefaults(datepicker.regional['zh-CN']);
	
			return datepicker.regional['zh-CN'];
	
		}));
	}
	
	/** Sidebar **/
    {
    	/*
		var catalog = "",
			catalogs = window.location.pathname.split('/'),
			$sidebar = $("#sidebar");		
		for(var i in catalogs){
			if (catalogs[i] && i > 1){
				if (catalog)
					catalog += "/";
				catalog+=catalogs[i];
				$sidebar.find("li[data-catalog=\"" + catalog + "\"]").addClass("active");			
			}
		}
		*/
		var path = window.location.pathname,
			$sidebar = $(".sidebar");
		//$sidebar.find("a[href*=\"" + path + "\"]").parents("li").addClass("active");
		//由于admin/userrole和admin/user的地址,开头相同,改为不用匹配的方式  duyong
		$sidebar.find("a[href=\"" + path + "\"]").parent("li").addClass("active");
/*
		catalog = $(document.body).attr("data-catalog");
		$("#navbar-menu").find("li[data-catalog=" + catalog + "]").addClass("active");
*/
		$("#navbar-menu").find("li").each(function(){
			var catalogs=$(this).attr("data-catalog").split(" ");
			for(var c in catalogs){
				var catalog = app + catalogs[c],
					idx = path.indexOf(catalog);
				if(0 == idx) $(this).addClass("active");
			}
		});
    }
    
    /** validate **/
    {
    	/*
    	 * Translated default messages for the jQuery validation plugin.
    	 * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
    	 */
    	$.extend($.validator.messages, {
    		required: "不能为空",
    		remote: "请修正此栏位",
    		email: "请输入有效的电子邮件",
    		url: "请输入有效的网址",
    		date: "请输入有效的日期",
    		dateISO: "请输入有效的日期 (YYYY-MM-DD)",
    		number: "请输入正确的数字",
    		digits: "请输入整数",
    		creditcard: "请输入有效的信用卡号码",
    		equalTo: "你的输入不相同",
    		extension: "请输入有效的后缀",
    		maxlength: $.validator.format("最多 {0} 个字"),
    		minlength: $.validator.format("最少 {0} 个字"),
    		rangelength: $.validator.format("请输入长度为 {0} 至 {1} 之間的字串"),
    		range: $.validator.format("请输入 {0} 至 {1} 之间的数值"),
    		max: $.validator.format("请输入不大于 {0} 的数值"),
    		min: $.validator.format("请输入不小于 {0} 的数值")
    	});

    	$.validator.setDefaults({
    	    highlight: function(element) {
    	        $(element).closest('div').addClass('has-error');
    	    },
    	    unhighlight: function(element) {
    	        $(element).closest('div').removeClass('has-error');
    	    },
    	    errorElement: 'span',
    	    errorClass: 'help-block',
    	    errorPlacement: function(error, element) {
    	        if(element.parent('.input-group').length) {
    	            error.insertAfter(element.parent());
    	        } else {
    	            error.insertAfter(element);
    	        }
    	    }
    	});
    }
})(jQuery);
