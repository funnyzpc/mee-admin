define(function (require, exports, module) {
	var dicts = require("dicts");
	function init(){
		module.tablex = require("common");
		module.tablex.init({
			url:app + "/sys/log",
			idField:"id",
			form:{
				title:"日志管理",
				fields:[

				]},
			events:{
				form0:form0
			}
		});
		module.tablex.doQuery();
	}

    Handlebars.registerHelper("user-status", function(d1){
        if(0 == d1){ return new Handlebars.SafeString("无效用户");  }
        if(1 == d1){ return new Handlebars.SafeString("有效用户");  }
        if(2 == d1){ return new Handlebars.SafeString("未激活用户");  }
        return new Handlebars.SafeString("NONE");
    });
	function form0(form){
		form.find("input[name=password]").attr({type:"password"});
	}
	
	exports.init = init;
});
