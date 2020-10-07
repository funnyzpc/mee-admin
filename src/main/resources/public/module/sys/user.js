define(function (require, exports, module) {
	var dicts = require("dicts");
	document.querySelector(".menu-rights").parentNode.nextElementSibling.style.display="block";
	function init(){
		module.tablex = require("common");
		module.tablex.init({
			url:app + "/sys/user",
			idField:"id",
			form:{
				title:"用户管理",
				fields:[
		                {name:"id",type: "hidden"},
		                {name:"user_id",label:"用户编号",width:4,validate:{required:"required"},attr:{readonly:"readonly"}},
		                {name:"nick_name",label: "昵称",width:4,/*attr:{readonly:"readonly"},*/nl:true},
		                {name:"user_name",label: "用户登陆名",width:4,validate:{required:"required"}},
		                {name:"email",label: "email",width:4,validate:{required:"required"},nl:true},
		                {name:"password",label: "密码",width:4,validate:{required:"required"}},
		                // {name:"role_id",label: "角色编号",width:4,validate:{required:"required"},nl:true},
		                {name:"status",label: "用户状态",type:"select",width:4,validate:{required:"required"},attr:{"data-dict":"user-status"}},
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
