define(function (require, exports, module) {
	var menuForm = {
			title:"菜单",
			fields:[
				{name:"parent_code",label: "菜单父编号", width:4,validate:{maxlength:50},attr:{readonly:"readonly"},nl:true},
            	{name:"parent_name",label: "菜单父名称", width:4,validate:{maxlength:50},attr:{disabled:"disabled"},nl:true},
	            {name:"id",type: "hidden"},
	            {name:"name",label: "菜单名称",width:4,validate:{required:true,maxlength:50},nl:true},
	            {name:"code",label: "菜单编号",width:4,validate:{required:"required"},nl:true},
	            {name:"show_flag",label: "是否显示",type:"radio",width:4,validate:{required:"required"},dict:{0:"隐藏",1:"显示"},nl:true}
	            ]};

    /** 界面初始化处理 **/
	function loadList(){
		var $pnl = $("#data-list").html("<div class=\"loading\"></div>");
		$(".form-search").find("button").click(function(event){
			if (event) event.preventDefault();
			switch($(this).attr("name")){
			case "new": doNew(event); break;
			case "mod":doMod(event); break;
			case "del":doDel(event); break;
			}
		});
		loadTree();
	}

    /** 修改 **/
    function doMod(event){
        if (event){
            event.preventDefault();
        }
        if(!module.checked){
            alert("请先选中一个~");
            return;
        }
        if("-1"==module.checked.id){
              alert("当前节点无法修改，请选择其他节点");
              return;
        }
        showForm(module.checked);
    }

    function doNew(event){
        if (event){
            // 取消默认事件
            event.preventDefault();
        }
        if(!module.checked){
            alert("请先选中一个~");
            return;
        }
        showForm({"parent_code":module.checked.code,"parent_name":module.checked.name});
    }

    /** 删除 **/
    function doDel(event){
        if (event){
            // 取消默认事件
            event.preventDefault();
        }
        if(!module.checked){
            alert("请先选中一个~");
            return;
        }
        if("-1"==module.checked.id){
           alert("当前节点无法删除，请选择其他节点");
           return;
        }
        $.post(app + "/sys/menu/delete",module.checked, function(d){
            if (0==d.status){
            	alert(d.msg);
            	return;
            }
            loadTree();
        });
    }

    /** 加载结构树 **/
	function loadTree(){
        $(".form-search").show();
        // 移除结构数据
        module.menus="";
        // 清空加载样式
        let $form = $("#data-list");
        $form[0].className="";
		// 加载菜单树
		showMenuTree($form);
		return $form;
	}

    /** 表单编辑界面 **/
	function showForm(data){
		$(".form-search").hide();
		var $form = $("#data-list").html(require("form").init(menuForm))
			.find("form");
		$form.find("button[name=cancel]").click(doCancel).end().validate({submitHandler: doSave});
		$form.resetForm(data);
		var $auth = $form.find("div[data-name=rights]");
		return $form;
	}

    /** 著录界面界面取消操作 **/
	function doCancel(event){
		if (event){
		    event.preventDefault();
		}
		$(".form-search").show();
		module.menus="";
		let dateList = $("#data-list");
		dateList[0].className="";
		showMenuTree(dateList);
	}

	/** 保存 **/
	function doSave(form, event){
		if (event){
		    event.preventDefault();
		}
		var $form = $(form), paramsArray = $form.serializeArray(), params = {};
		for ( var i in paramsArray){
		    params[paramsArray[i].name] = paramsArray[i].value;
		}
		$.post(app + "/sys/menu/save", params, function(d){
		    if(d && 1!==d.status){
		        alert(d.msg);
		        return;
		    }
		    alert("保存成功！");
			loadTree();
		});
	}

	function showMenuTree(form){
        var $auth = form.html("<div class=\"loading\"></div>").addClass("rights");
        if(module.menus){
            var $authTree = $auth.jstree({
                core:{data:module.menus,check_callback:true,multiple:false},
                // 禁止级联选择
                checkbox: { cascade: "", three_state: false },
                "themes" : {"theme" : "default"},
                plugins:["checkbox","themes"]
            }).bind("select_node.jstree", function (e,data) {
                // checked 数据
                module.checked=data.node.original.ext;
             });
        }else{
            $.post(app + "/sys/menu/menuAll", function(d){
            // {id: "0101", text: "角色管理", parent: "01"}
                let dataList = [];
                let state={opened: true};
                // root Node
                dataList[0] = {id: "-1", text: "<b style='color:red;'>添加根节点</b>",parent: "#",ext:{id:"-1",code:"-1",name:"添加根节点"},state:state};
                for(let i in d.data){
                    // push data
                    let  item = d.data[i];
                    dataList.push({ext:item,
                                    id:item.code,
                                    text:item.name+(1==item.show_flag?"":"(<span style='color:red;'>隐藏中</span>)")+"[<b>"+item.code+"</b>]",
                                    parent:null==item.parent_code?"#":item.parent_code,
                                    state:state});
                }
                module.menus = dataList;
                showMenuTree(form);
            });
        }

    }

	exports.init = loadList;
});