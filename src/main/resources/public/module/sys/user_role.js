define(function (require, exports, module) {
	function init(){
		$("#search-form").submit(doList);
		$.post(app + "/app/admin/user/list.json", function(d){
			var data =[];
			for(var i in d.data)
				data.push({value:d.data[i].userid + " - " + d.data[i].name, label: d.data[i].userid + " - " + d.data[i].name, d:d.data[i]});
			$("input[name=\"user\"]").autocomplete({
				minLenght:2,source:data,
				select:function(event, ui){
					var arrayuserid =ui.item.value.split(" - ");
					$(this).closest("form").find("[name=userid]").val(arrayuserid[0]);
				}
			});
		});
		$.post(app+"/app/admin/userrole/roles.json", function(d){
			module.roles = d.data;
		});
	}
	
	function doList(event){
		if (event) event.preventDefault();
		var userid = $(this.userid).val();
		if (!userid)
			return;
		
		var $pnl = $("#data-list").html("<div class=\"loading\"></div>");
		$.post(app + "/app/admin/userrole/list.json", {userid:userid}, function(d){
			$pnl.template("#template-data-list", module.roles, function(name, data, txt){
				$pnl.html(txt).find("tr").click(function(event){
					var $elem = $(this).find("input");
						$elem[0].checked ? $elem.removeAttr("checked") : $elem.attr("checked", true);  
				});
				for(var i in d.data){
					$pnl.find("input[value=" + d.data[i].roleId + "]").attr("checked","checked");
				}
				$pnl.find("button").click(doSave);
			});	
		});
	}
	
	function doSave(event){
		if (event) event.preventDefault();
		var ids = [], userid = $($("#search-form")[0].userid).val();
		$("#data-list").find("input:checked").each(function(){
			ids.push($(this).val());
		});
		$.post(app + "/app/admin/userrole/save.json", {userid: userid, roles: ids.toString()});
	}
	
	exports.init = init;
});