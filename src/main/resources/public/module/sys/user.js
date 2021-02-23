define(function (require, exports, module) {
	var dicts = require("dicts");
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
		                {name:"status",label: "用户状态",type:"select",width:4,validate:{required:"required"},attr:{"data-dict":"user_status"}},
				        ]},
			events:{form0:form0},
			toolbar:{"unlock":unlock}
		});
		module.tablex.doQuery();
	}

	function form0(form){
		form.find("input[name=password]").attr({type:"password"});
	}
	    function unlock(event){
            if (event){
                event.preventDefault();
            }
            let data = module.tablex.getSelected();
            if(0==data.length){
               alert("请选择一条记录");
               return;
            }
           let formData = new FormData();
           // 韩束抖音投放日报
           formData.append("user_name",data[0].user_name);

            fetch(app + "/sys/user/unlock",{
                method: 'POST',
                body: formData
            })
            .then(response => response.json())
            .then(data =>
                function () {
                    alert(data.msg);
                }()
            )
            .catch(error => console.log("请求超时,请刷新后重试~")
            );
        }
	exports.init = init;
});
