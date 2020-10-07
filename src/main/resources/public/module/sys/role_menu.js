define(function (require, exports, module) {
	var dicts = require("dicts");
	// 显示菜单
    document.querySelector(".menu-system").parentNode.nextElementSibling.style.display="block";
	function init(){
		module.tablex = require("common");
		module.tablex.init({
			url:app + "/sys/dict",
			idField:"id",
			toolbar:{"sync": doSync},
			form:{
				title:"数据字典管理",
				fields:[
				        /** id series series_desc key value desc del_flag create_date create_by **/
		                {name:"id",type: "hidden"},
		                {name:"series",label:"系列",width:4,validate:{required:"required"},attr:{readonly:"readonly"}},
		                {name:"series_desc",label: "系列描述",width:4,attr:{readonly:"readonly"},nl:true},
		                {name:"key",label: "KEY",width:4,validate:{required:"required"}},
		                {name:"value",label: "VALUE",width:4,validate:{required:"required"},nl:true},
		                {name:"desc",label: "字典描述",width:4,validate:{required:"required"},nl:true},
		                {name:"create_date",label:"创建日期",width:4,validate:{required:"required"},attr:{readonly:"readonly"}},
		                {name:"create_by",label: "创建人",width:4,validate:{required:"required"},attr:{readonly:"readonly"}},
				        ]},
			events:{
				form0:form0,
				// form2:showForm,
				save0:beforeSave,

			}
		});
	}

	function form0(form,d){
		if(!d){
           // 新建
           let  dom = form.get(0);
           dom.querySelector("input[name=series]").removeAttribute("readonly");
           dom.querySelector("input[name=series_desc]").removeAttribute("readonly");

           // 移除描述和创建人
           let create_date_dom = dom.querySelector("input[name=create_date]").parentNode;
           create_date_dom.previousSibling.remove();
           create_date_dom.remove();

           let create_by_dom = dom.querySelector("input[name=create_by]").parentNode;
           create_by_dom.previousSibling.remove();
           create_by_dom.remove();

		}else{
		   // 修改

		}
	}

	function beforeSave(form,args,module){
	    /** 日期一定要转换 **/
	    if(args.id && args.create_date){
	        // args.create_date = new Date(args.create_date).getTime();
	    }
	}

	function doSync(event){
		if (event) event.preventDefault();
		var $dlg = $("<div><div class=\"loading\"></div></div>").dialog({modal:true, closeOnEscape:false});
		$dlg.dialog("widget").find(".ui-dialog-titlebar").remove().end().find(".ui-resizable-handle").remove();
		$.post(app + "/app/admin/user/sync.json", function(d){
			if (d.error)
				alert(d.error.msg);
			else
				alert("同步完成");
		}).always(function(){
			$dlg.dialog("destroy");
		});
	}
	exports.init = init;
});