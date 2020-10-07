define(function (require, exports, module) {
	/**
	 // You need to see this:
	 * {
	 * 	url:"",
	 *  idField:"",
	 * 	form:{},
	 * 	toolbar:{query:function(event)},
	 * 	event:{
	 *  // 第一次查询前
	 * 	fquery0:function(args){},
	 *  // 查询前
	 * 	query0:function(args){},
	 *  // 查询后
	 * 	query2:function(){},
	 *  // 加载表单前
	 *  beforeForm:function(){form, data}
	 *  // 加载表单数据前(只有编辑的时候data才会有值)
	 *  form0:function(){form, data}
	 *  // 加载表单数据后
	 *  form2:function(){form, data}
	 *  // 保存前
	 *  save0:function(form, args, module)
	 *  // 保存后
	 *  save2:function(form, data)
	 *  // 删除前
	 *  delete0:function(args)
	 *  // 删除后
	 *  delete2:function(data)
	 *  // 取消前
	 *  cancel2:function(module)
	 * }
	 * }
	**/
	var globalOpts = {
		form:{},
		idField:"id",
		toolbar:{query: doQuery, "new":doNew, "exp": doExp, "imp": doImp, "mod": doMod, "del":doDel},
		events:{}
	}
	
	function init(opts){
		require("dicts").init();
		module.opts = $.extend({}, globalOpts, opts);
		$("#search-form").submit(doQuery).find("button").click(function(event){
			if (event) event.preventDefault();
			var n = $(this).attr("name"),
				proc = module.opts.toolbar[n]?module.opts.toolbar[n]:globalOpts.toolbar[n];
			if(proc){
			    proc(event);
			}
		});
	}
	
	function load(querys, pageIdx, pageSize){
		querys["pageIdx"] = pageIdx;
		querys["pageSize"] = pageSize;
		// var $pnl = $("#data-list").html("<div class=\"loading\"></div>");
		// add loading
		$("#data-list").html("<div class=\"loading\"></div>");
		if (module.opts.events.query0)
			if(false == module.opts.events.query0(querys))
				return;
		// $.post(module.opts.url + "/query.json", querys, function(d) {
		$.post(module.opts.url, querys, function(d) {
			module.page = d.data;
			if (module.opts.events.query2)
				if(false == module.opts.events.query2(d.data))
					return;
			showList();
		});
	}
	
	function showList(tempName){
		$(".form-search").show();
		$("#data-list").template(tempName ? tempName : "#template-data-list", module.page, function(name, data, txt){
			$("#data-list").html(txt)
				.find("tr").click(function(event){
					let $elem = $(this).find("input");
					if(event && !/input/i.test(event.srcElement.tagName))
						if ($elem.length!=0 && $elem[0].name !="selall") {
							$elem[0].checked ? $elem.removeAttr("checked") : $elem.attr("checked", true);  
						}
				}).end()
				.find("[name=\"selall\"]").click(function(event){
					this.checked ? $(":input[name=\"sel\"]").attr("checked",true) : $("input[name=\"sel\"]").removeAttr("checked");
				}).end()
				.find("form.form-paging").find("select").html("<option value=\"10\">10</option><option value=\"15\">15</option>"
					+ "<option value=\"20\">20</option><option value=\"50\">50</option><option value=\"100\">100</option>").end()
				;
		});
		
		//以下部分为翻页功能
		let $form = $("form.form-paging")
		$form.find("select").val($form.find("select").attr("pageSize"));
		$form.find("select").change(function(){goPage(event, "gopage")});
		if (!module.page){
		    module.page = {};	//避免以下语句报错
		}
		$form.find("button[name=firstpage]").attr("disabled", module.page.index == 1).click(function(){goPage(event,"firstpage")});
		$form.find("button[name=prevpage]").attr("disabled", module.page.index <= 1).click(function(){goPage(event, "prevpage")});
		$form.find("button[name=nextpage]").attr("disabled", module.page.index >= module.page.pageCount).click(function(){goPage(event, "nextpage")});
		$form.find("button[name=lastpage]").attr("disabled", module.page.index == module.page.pageCount).click(function(){goPage(event, "lastpage")});
		$form.find("button[name=gopage]").click(function(){goPage(event, "gopage")});
	}

	function goPage(event, PageParam){
		// if (event) if (event.preventDefault) event.preventDefault();
		if (event && event.preventDefault) {
		    event.preventDefault();
		}
		let $form = $("form.form-paging"), pageIdx = $form.find("input[name=pageIdx]").val(),
		pageSize = $form.find("select").val(), pageCount=module.page.pageCount;
		if ($.isNumeric(pageIdx)) {
			pageIdx = parseInt(pageIdx);
		} else {
			pageIdx = 1
		}
		if (PageParam == "firstpage") {
			pageIdx = 1;
		} else if (PageParam == "prevpage") {
			pageIdx = pageIdx - 1;
		} else if (PageParam == "nextpage") {
			pageIdx = pageIdx + 1;
		} else if (PageParam == "lastpage") {
			pageIdx = pageCount;
		} 
		load(module.querys, pageIdx, pageSize);
	}
	
	function getSelected(){
		var $sel = $("#data-list input[name=sel]:checked"),
			datas = [];
		$sel.each(function(){
			var idx = $(this).val() - 1,
				d = module.page.data[idx];
			datas.push(d);
		});
		return datas;
	}
		
	function doQuery(event){
		if (event) event.preventDefault();
		var $form = $(".form-search"), paramsArray = $form.serializeArray(), params = {};
		for (var i in paramsArray)
			params[paramsArray[i].name] = paramsArray[i].value;
		module.querys = params;
		module.querys.pageIdx = 1;
		module.querys.pageSize = 10;
		if (module.opts.events.fquery0)
			if(false == module.opts.events.fquery0(module))
				return;		
		load(params, module.querys.pageIdx, module.querys.pageSize);
	}
	
	function doExp(event){
		if (event) event.preventDefault();
		var $form = $("#search-form"), params = $form.serialize();
		window.open(module.opts.url + "/export?pageIdx=1&pageSize=100000&" + params);
	}

	function doImp(event){
		if (event) event.preventDefault();
		var $elem = $("<input type=\"file\" name=\"file\">"),
			$dlg = $elem.dialog({
				modal:true,title:"上传文件",
				buttons:{
					"上传": function(){}
				},
				close:function(){
					$(this).dialog("destroy");
				}
			}),
			$btn = $dlg.dialog("widget").find(".ui-dialog-buttonset").find("button");
		$elem.ajaxfileupload({
			action:module.opts.url + "/import",
			valid_extensions:["xls","xlsx","csv","zip","pdf","jpeg","dmp"],
			submit_button:$btn,
			onComplete:function(txt){
				var rs = ("object" == typeof(string) ? JSON.parse(txt) : txt);
				if (rs.status == false){
					alert(rs.message);
					return;
				}
				
				if (rs.error){
					alert(rs.error.msg);
					return;
				}
				
				$elem.dialog("close");
				alert("导入成功");
			}
		});
	}

	function showForm(d){
		$(".form-search").hide();
		if (module.opts.events.beforeForm)
			if (false == module.opts.events.beforeForm(module.opts.form, d))
				return;
		var $form = $("#data-list").html(require("form").init(module.opts.form)).find("form");
		require("dicts").init($form);
		$form.find("button[name=cancel]").click(doCancel).end().validate({submitHandler: doSave});
		if (module.opts.events.form0)
			if (false == module.opts.events.form0($form, d))
				return;
		if(d) $form.resetForm(d);
		if (module.opts.events.form2)
			if (false == module.opts.events.form2($form, d))
				return;
	}
	
	function doCancel(event){
		if (event){
		    event.preventDefault();
		}
		if (module.opts.events.cancel2)
			if (false == module.opts.events.cancel2(module))
				return;
		showList();
	}
	
	function doNew(){
		module.exports.showFormType = 0		//显示不同的著录界面类型,0-一般著录界面,1、2..其他
		showForm();
	}
	
	function doMod(){
		var datas =getSelected();
		if (1 != datas.length){
			alert("请先选择一条记录!");
			return;
		}
		//show different edit type,0.default,1、2..其他
		module.exports.showFormType = 0
		showForm(datas[0]);
	}
	
	function doDel(){
		var datas =getSelected();
		if (0 == datas.length){
			alert("请至少选择一条记录!");
			return;
		}
		if (!confirm("您确认要删除选中的数据吗？删除的数据将不能恢复!"))
			return;
		if (module.opts.events.delete0)
			if (false == module.opts.events.delete0(datas))
				return;
		var ids = [], args = {};
		for(var i in datas)
			ids.push(datas[i][module.opts.idField]);
		args[module.opts.idField] = ids.toString();
		// $.post(module.opts.url + "/delete.json", args, function(d){
		$.post(module.opts.url + "/delete", args, function(d){
			if (d.error){
				alert("删除失败:" + d.error.msg);
				return;
			}
			if(0==d.status){
			    alert("删除失败:" + d.msg);
            	return;
			}
			if (module.opts.events.delete2)
				module.opts.events.delete2(d);
			doQuery();
		});			
	}
	
	function doSave(form, event){
		if (event) event.preventDefault();
		let $form = $(form), paramsArray = $form.serializeArray(), params = {};
		for ( var i in paramsArray)
			params[paramsArray[i].name] = paramsArray[i].value;
		if (module.opts.events.save0)
			if (false == module.opts.events.save0($form, params, module))
				return;
		/*
		let saveMethod = "/save.json";
		if (module.exports.showFormType && module.exports.showFormType == 1){
			saveMethod = "/save1.json";
		}
		*/
        let saveMethod = "/save";
        if (module.exports.showFormType && module.exports.showFormType == 1){
            saveMethod = "/save1";
        }
		
		$.post(module.opts.url + saveMethod, params, function(d){
			if (d.error){
				alert("保存出错:" + d.error.msg);
				return;
			}
			if (module.opts.events.save2)
				if(false == module.opts.events.save2($form, d.data))
					return;
			var i = -1;
			if (module.page){
			    /*
				for(var j in module.page.data){
					if (module.page.data[j][module.opts.idField] == d.data[module.opts.idField]){
						i = j;
						break;
					}
				}
				if  (i > -1) {
					module.page.data[i] = d.data;
				} else {
					module.page.data.push(d.data);
				}
				*/

				//showList();
				//改为重新从数据库中加载数据,有查询条件时showList方法无法去除条件以外的数据。
				load(module.querys, module.page.index, module.page.size);
			}	
			else{
				doQuery();
			}
		});
	}
	
	exports.init = init;
	exports.showList = showList;
	exports.showForm = showForm;
	exports.getSelected = getSelected;
	exports.load = load;
	exports.doQuery = doQuery;
});
