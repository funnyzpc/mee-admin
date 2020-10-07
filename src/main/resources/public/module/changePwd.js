define(function (require, exports, module) {
	function init(isInitial, isExpired){
		// 是否初始化
		module.isInitial = isInitial;
		// 是否过期
		module.isExpired = isExpired;
		var changePwdForm = {
			title: isInitial ? "请修改初始密码" : isExpired ? "密码已过期请修改" : "修改密码",
			fields:[
                {name:"userid",label:"用户代码",type:"hidden"},
                {name:"pwd_old",label: "原密码",width:4,nl:true/*,validate:{required:true,minlength:"1"}*/},
                {name:"pwd1",label: "新密码",width:4,nl:true,validate:{required:"required", password:"password", minlength:"6"}},
                {name:"pwd2",label: "确认新密码",width:4,validate:{required:"required", password2:"password2"}}
		        ]};
		
		var $form = $("#user-form").html(require("form").init(changePwdForm)).find("form");
		$form.find("input[type=text]").attr({type:"password"}).end().find("button[name=cancel]").text("重置");
		$form.find("button[name=cancel]").click(doCancel).end().validate({submitHandler: doSave});
	}
	
	function doCancel(event){
		if (event) event.preventDefault();
		$("#user-form form")[0].reset();
	}

	function doSave(form, event){
		if (event) event.preventDefault();
		var $form = $(form), paramsArray = $form.serializeArray(), params = {};
		for ( var i in paramsArray){
		    params[paramsArray[i].name] = paramsArray[i].value;
		}
		$.post(app + "/changePwd", params, function(d){
			if (d && 1!=d.status){
				alert("修改失败:" + d.error.msg);
				return;
			}
			
			if (module.isInitial || module.isExpired){
				//OLD window.location = app + "/logout";
				window.location.replace(app + "/main");
			}else{
				alert("修改成功,请重新登录.");
				window.location.replace(app + "/logout");
			}
		});
		return false;
	}
	
	jQuery.validator.addMethod("password", function(v, elem){
		// 原密码不做强验证
		if("pwd_old" == elem.name){
            if(v.length>0){
                return true;
            }
            alert("原密码不可为空");
            return false;
		}
		//return /[a-z|A-Z]/.test(v) && /[0-9]/.test(v);
		//密码验证改为"密码必须由大写字母、小写字母、数字、符号4种中的3种或以上组成"
		let Counter = 0;
		if (/[a-z]/.test(v)) Counter++;
		if (/[A-Z]/.test(v)) Counter++;
		if (/[0-9]/.test(v)) Counter++;
		if (/[\~\`\·\！\!@\#\￥\$%\……\^&\*\(\)\（\）\_\-\——\+\=\【\】\[\]\{\}\|\、\\\：\:\;\；\"\”\“\’\'\'\<\>\《\》\,\，\。\.\?\？\/]/.test(v)) Counter++;
		if (Counter >= 3) {
			return true;
		} else {
			return false;
		}		
	}, "密码必须由大写字母、小写字母、数字、符号4种中的3种或以上组成");
	
	jQuery.validator.addMethod("password2", function(v, elem){
		var v1 = $(elem).parents("form").find("input[name=pwd1]").val();
		return v1 == v;
	},"密码必须一致");

	exports.init = init;
});