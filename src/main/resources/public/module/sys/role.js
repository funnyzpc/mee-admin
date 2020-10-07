define(function (require, exports, module) {
    // 展开目录
	document.querySelector(".menu-rights").parentNode.nextElementSibling.style.display="block";
	var roleForm = {
			title:"角色",
			fields:[
	            {name:"id",type: "hidden"},
	            {name:"role_name",label: "角色名称", validate:{required:true,maxlength:32}},
	            {name:"status",label: "角色状态",type:"radio",width:4,validate:{required:"required"},dict:{0:"角色关闭",1:"角色开启"},nl:true},
	            {name:"role_desc",label: "角色描述", width:10,validate:{maxlength:50},nl:true},
	            {name:"rights",label: "菜单",type:"extend",width:4},
	            {name:"users",label: "用户",type:"extend",width:4}
	            ]};
	
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
		showList();
	}
	
	function showList(){
		$.post(app + "/sys/role", function(d) {
			module.data = d.data;
			$(".form-search").show();
			$("#data-list").template("#template-data-list", module.data, function(name, data, txt){
				$("#data-list").html(txt).find("tr").click(function(event){
					var $elem = $(this).find("input");
					if(event && !/input/i.test(event.srcElement.tagName)){
						$elem[0].checked ? $elem.removeAttr("checked") : $elem.attr("checked", true);
					}
				});
			});
		});		
	}
	
	function showForm(data){
		$(".form-search").hide();
		var $form = $("#data-list").html(require("form").init(roleForm))
			.find("form");
		$form.find("button[name=cancel]").click(doCancel).end().validate({submitHandler: doSave});
		$form.resetForm(data);
		var $auth = $form.find("div[data-name=rights]");
		// 加载菜单
		showRightsTree(data, $form);
		// 加载用户
		showUsersTree(data, $form);
		// 加载部门
		// showDeptsTree(data, $form);
		return $form;
	}

	function showRightsTree(data, form){
        var $auth = form.find("div[data-name=rights]").html("<div class=\"loading\"></div>").addClass("rights");
        if(module.rights){
            var $authTree = $auth.jstree({
                core:{data:module.rights},
                // 禁止级联选择
                checkbox: { cascade: "", three_state: false },
                plugins:["checkbox"]
            }).bind("ready.jstree", function(){
              if(data){
                  $.post(app + "/sys/role/roleMenus", {id: data.id}, function(d){
                      for(var i in d.data)
                          $authTree.jstree("select_node", d.data[i].menu_code);
                  });
              }
            });
        }else{
            $.post(app + "/sys/role/menus", function(d){
            // {id: "0101", text: "角色管理", parent: "01"}
                for(var i in d.data){
                    d.data[i].state = {opened: true};
                    if (!d.data[i].parent) d.data[i].parent = "#";
                }
                module.rights = d.data;
                showRightsTree(data, form);
            });
        }
    }
	function showUsersTree(data, form){
		var $user = form.find("div[data-name=users]").html("<div class=\"loading\"></div>").addClass("users");
		if(module.users){
			var $userTree = $user.jstree({
				core:{data:module.users},
                // 禁止级联选择
                checkbox: { cascade: "", three_state: false },
				plugins:["checkbox"]
			}).bind("ready.jstree", function(){
				if(data){
					$.post(app + "/sys/role/roleUsers", {"role_id": data.id+""}, function(d){
						for(var i in d.roleUsers){
							$userTree.jstree("select_node", "user_" + d.roleUsers[i].user_id);
						}
					});
				}
			});
		}else{
			$.post(app + "/sys/role/users", function(d){
				var users = [];
				/*
				for(var i in d.orgs){
					var org = d.orgs[i];
					users.push({id:"dept_" + org.id, text:org.name, state:{"opened":true}, data: org, parent:"#"});
				}*/
				for(var i in d.users){
					let u = d.users[i];
					users.push({id:"user_"+u.id, text: u.text, data: u/*, parent:"dept_" + u.org.id*/});
				}
				
				module.users = users;
				showUsersTree(data, form);
			});
		}
	}

	//显示部门Tree
	function showDeptsTree(data, form){
		var $dept = form.find("div[data-name=depts]").html("<div class=\"loading\"></div>").addClass("users");
		if(module.depts){
			var $deptTree = $dept.jstree({
				core:{data:module.depts},
				plugins:["checkbox"]
			}).bind("ready.jstree", function(){
				if(data){
					$.post(app + "/app/admin/role/roledepts.json", {roleId: data.id}, function(d){
						for(var i in d.depts)
							$deptTree.jstree("select_node",  d.depts[i].deptId);
					});
				}
			});
		}else{
			$.post(app + "/app/admin/role/depts.json", function(d){
				var depts = [];
				for(var i in d.orgs){
					var org = d.orgs[i];
					depts.push({id:org.code, text:org.name, state:{"opened":true}, data: org, parent:"#"});
				}
				
				module.depts = depts;
				showDeptsTree(data, form);
			});
		}
	}
	
	function doNew(event){
		if (event) event.preventDefault();
		showForm();
	}

	function doMod(event){
		if (event) event.preventDefault();
		var $sel = $("#data-list input[name=sel]:checked");
		if ($sel.val()==undefined){
			return;
		}
		if ($sel.length != 1){
			alert("请先选择一条记录!");
			return;
		}
		var idx = $sel.val() - 1,
			data = module.data[idx];
			$form = showForm(data);
	}
	
	function doDel(event){
		if (event) event.preventDefault();
		var $sel = $("#data-list input[name=sel]:checked");
		if (0 == $sel.length){
			alert("请至少选择一条记录!");
			return;
		}
		if (!confirm("您确认要删除选中的数据吗？此操作将删除角色的用户及菜单关系！")){
	        	return;
		}
		/*
		var ids = [];
		$sel.each(function(){
			var idx = $(this).val() - 1,
				d = module.data[idx];
			ids.push(d.id);
		});
        */
		let item = module.data[$sel.val()-1];
		$.post(app + "/sys/role/delete", {"role_id":item.id,"role_name":item.role_name}, function(d){
		    if(d && 0==d.status){
                alert(d.msg);
                return;
		    }
			showList();
		});			
	}
	
	function doSave(form, event){
		if (event) event.preventDefault();
		var $form = $(form), paramsArray = $form.serializeArray(), params = {},
			$rightTree = $form.find("div[data-name=rights]").jstree(),
			$userTree = $form.find("div[data-name=users]").jstree();
		    // $deptTree = $form.find("div[data-name=depts]").jstree();
		for ( var i in paramsArray)
			params[paramsArray[i].name] = paramsArray[i].value;
		
		//判断权限Tree是否正确加载,未正确加载则不保存(网上没找到合适的方法,自己找的,不一定准确)
		if (!$rightTree._data.core.ready) {
			alert("权限信息没有正确加载,不能保存！");
			return;
		}
		//判断用户Tree是否正确加载,未正确加载则不保存
		if (!$userTree._data.core.ready) {
			alert("用户信息没有正确加载,不能保存！");
			return;
		}
		//判断部门Tree是否正确加载,未正确加载则不保存
		/*
		if (!$deptTree._data.core.ready) {
			alert("部门信息没有正确加载,不能保存！");
			return;
		}
		*/
		params.menus = $rightTree.get_checked().toString();
		params.users = [];
		$($userTree.get_checked()).each(function(){
			if (/^user_/.test(this)) params.users.push(this.substring(5));
		});
		params.users = params.users.toString();
		// params.depts = $deptTree.get_checked().toString();
		$.post(app + "/sys/role/saveAll", params, function(d){
			showList();
		});		
	}
	
	function doCancel(event){
		if (event) {
		    event.preventDefault();
		}
		showList();
	}

	exports.init = loadList;
});