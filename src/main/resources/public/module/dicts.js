define(function (require, exports, module) {
    // define your dicts
	var dicts = {
	        "menu-level":"01"
		    };
	
	function init(elem){
		if (module.dicts){
			initDictTemplate();
			let $elems = elem ? $(elem) : $(document.body);
			initSelectFiled($elems);
			initDateField($elems);
			initCurrencyField($elems);
			initProjectField($elems);
		}else{
			// $.post(app + "/app/common/dict/list", function(d){
			$.post(app + "/common/dict/list", function(lst){
			    if(!lst){ lst = []; }
				var dict = {};
				for(var i in lst){
					if (!lst[i].series){
					    lst[i].series = {};
					}
					if (!dict[lst[i].series]){
    					dict[lst[i].series] = {};
					}
					// dict[lst[i].series.key][lst[i].key]=lst[i];
					dict[lst[i].series][lst[i].key]=lst[i];
				}
				module.dicts = dict;
				init(elem);
			});
		}
		return exports;
	}
	
	function get(name, id){
		if (!name)
			return dicts;
		if (module.dicts && dicts[name] && module.dicts[dicts[name]])
			return id ? (module.dicts[dicts[name]][id] ? module.dicts[dicts[name]][id].name : undefined) : (true == id ? module.dicts[dicts[name]] : undefined);
		return undefined;			
	}
	
	function initDictTemplate(){
		for(var i in dicts){
	        Handlebars.registerHelper("dict" + i, dictTemplate(dicts[i]));
		}
	}
	
	function dictTemplate(n){
		return function(d){
	        return new Handlebars.SafeString(module.dicts[n][d]?module.dicts[n][d].name : "");		
		}
	}

    /** handlebar注册字典 **/
	Handlebars.registerHelper("currency", function(v){
		return new Handlebars.SafeString(currencyFormat(v));
	});
	
	Handlebars.registerHelper("percent", function(v){
		return new Handlebars.SafeString(percentFormat(v));
	});	
	
	Handlebars.registerHelper("percent2", function(v){
		return new Handlebars.SafeString(percentFormat2(v));
	});

	Handlebars.registerHelper("percent3", function(v){
		return new Handlebars.SafeString(percentFormat3(v));
	});			
	
	Handlebars.registerHelper("sumnumber", function(num1, num2){
		return new Handlebars.SafeString(sumNumber(num1, num2));
	});	

	Handlebars.registerHelper("tonumber", function(num1, num2){
		return new Handlebars.SafeString(toNumber(num1, num2));
	});	
	
	Handlebars.registerHelper("dateStringFmt", function(v){
		return new Handlebars.SafeString(dateStringFmt(v));
	});	
	
	Handlebars.registerHelper("addspace", function(v){
		return new Handlebars.SafeString(addSpace(v));
	});	
	/*
	Handlebars.registerHelper("billstatusdept", function(v1, v2){
		return new Handlebars.SafeString(getBillStatusDept(v1, v2));
	});*/
	
	Handlebars.registerHelper("add_index",function(index){
		this._index = index-1;
		return this._index;
	});
	
	Handlebars.registerHelper("cur_key",function(key){
		this._key = key;
		return this._key;
	});

	/** 日期控件注册 **/
	function initDateField(elem){
		elem.find("input.date").datetimepicker({format : "Y-m-d",timepicker:false});
		elem.find("input.datetime").datetimepicker({format : "Y-m-d H:i:s",timepicker:true});
	}
	
	function initSelectFiled(elem){
		elem.find("select[data-dict]").each(function(){
			var $e = $(this),
				val = ($e.val()==null ? $e.attr("v") : $e.val()),
				dictName = $e.attr("data-dict"),
				// data = module.dicts[dicts[dictName]],
				data = module.dicts[dictName],
				temp = ("<option value=\"\">--</option>");
			for(var i in data){
				let k = data[i].key;
				let	n = data[i].value;
				temp += "<option value=\"" + k + "\"" + (val == k ? "selected" : "") + ">" + n + "</option>";
			}
			$e.html(temp);
		});
	}
	
	function initCurrencyField(elem){
		elem.find("input.currency").each(function () {
	           var str = $(this).val();
	           $(this).keyup(function (evt) {
	               str = $(this).val();
	               //兼容IE和Firefox获得keyBoardEvent对象
	               evt = (evt) ? evt : ((window.event) ? window.event : "")
	               //兼容IE和Firefox获得keyBoardEvent对象的键值
	               let key = evt.keyCode ? evt.keyCode : evt.which;
	               if (key >= 33 && key <= 40) {
	                   evt.preventDefault();
	                   evt.returnValue = false;
	                   return false;
	               }
	               $(this).val(currencyFormat(str));
	           });
	           if(str != "") {
	               $(this).val(currencyFormat(str));
	           }
	       });
	}
	
	function currencyFormat(amtStr) {
		var a, renum = '',j = 0,a1 = '', a2 = '', a3 = '',
			tes = /^-/;
		//if (!isNaN(amtStr)) amtStr= Number((amtStr*1).toFixed(5));		//此句会报错,去除
		a = (amtStr+"").replace(/,/g, "").replace(/[^-\.,0-9]/g, "").replace(/(^\s*)|(\s*$)/g, "");
		a1 = tes.test(a) ? '-' : "";
		a = a.replace(/-/g, "");
		if (a != "0" && a.substr(0, 2) != "0.") a = a.replace(/^0*/g, "");
		j = a.indexOf('.'); if (j < 0) j = a.length; a2 = a.substr(0, j); a3 = a.substr(j); j = 0;
		for (i = a2.length; i > 3; i = i - 3) {
			renum = "," + a2.substr(i - 3, 3) + renum; j++;
		}
       renum = a1 + a2.substr(0, a2.length - j * 3) + renum + a3;
       return renum;
	}
	
	function percentFormat(obj) {
		var renum = obj
		if (!isNaN(renum)) {
			renum= Number((renum * 100).toFixed(5)) + "%"
		}
        return renum;
	}	
	
	function percentFormat2(obj) {
		var renum = obj
		if (!isNaN(renum)) {
			renum= Number((renum * 100).toFixed(2)) + "%"
		}
        return renum;
    }
	
	function percentFormat3(obj) {
		var renum = obj
		if (!isNaN(renum)) {
			renum= Number((renum * 100).toFixed(3)) + "%"
		}
        return renum;
    }		
	
	function sumNumber(num1, num2) {
		// 解决js计算溢出错误 (0.12+0.11=0.22999999999999998)
		// var renum = 0
		if (isNaN(num1)) num1 = 0;
		if (isNaN(num2)) num2 = 0;
		return Number((Number(num1) + Number(num2)).toFixed(5));
	}
	
	function toNumber(obj) {
		var renum = 0
		if (!isNaN(obj)) {
			renum= Number((Number(obj)).toFixed(5))
		}
        return renum;
	}	
	
	function dateStringFmt(obj) {
		var dateString = obj
		if (dateString == null) dateString = "";
		if (dateString.length >=10){
			dateString=dateString.substring(0,10);
		}
        return dateString;
	}

	function currencyTrim(v){
		return (v+"").replace(/,/g, "");
	}

	function addSpace(v){
		if (v == null) v = "";
		if (v != "") v = v + " ";
		return v
	}
	
	function initProjectField(elem){
		function updateProject(form, project){
			if (!project) project = {};
			if (!project.dynamicInfo) project.dynamicInfo = {}; 
			form.find("[name=projectCode]").val(project.code).end()
				.find("[name=\"project.name\"]").val(project.code + "-" + project.name).end()
				.find("[name=\"project.location\"]").val(project.location).end()
				.find("[name=\"project.investIndustry\"]").val(get("project-investIndustry", project.investIndustry)).end()
				.find("[name=\"project.timeLimit\"]").val(project.timeLimit).end()
				.find("[name=\"project.balanceSum\"]").val(currencyFormat(project.balanceSum)).end()
				.find("[name=\"project.realShares\"]").val(currencyFormat(project.realShares)).end()
				.find("[name=\"project.setupDate\"]").val(dateStringFmt(project.setupDate)).end()
				.find("[name=\"project.endDate\"]").val(dateStringFmt(project.endDate)).end()
				//.find("[name=\"project.lastUpdateTime\"]").val(project.lastUpdateTime).end()
				.find("[name=\"project.dynamic.newprojectStatus\"]").val(project.dynamicInfo.projectStatus).end()
				.find("[name=\"project.dynamic.existprojectStatus\"]").val(project.dynamicInfo.projectStatus).end()
				.find("[name=\"project.dynamic.plannedAffairs\"]").val(project.dynamicInfo.plannedAffairs).end()
				.find("[name=\"project.dynamic.expectedDoneDate\"]").val(project.dynamicInfo.expectedDoneDate);
		}
		
		if (!module.projects){
			// $.post(app + "/app/common/project/list.json", function(d){
			$.post(app + "/common/project/list", function(d){
				module.projects = d.data;
				initProjectField(elem);
			});
		}else{
			elem.find("input[name=\"project\"]").each(function(){
				var data =[], property = $(this).attr("data-property"), 
					depart = $(this).attr("data-depart");
				for(var i in module.projects){
					var p = module.projects[i];
					if((p.property == property || !property) && (!depart || p.departId == depart))
						data.push({value:p.code + "-" + p.name, label: p.code + "-" + p.name, d:p});
				}
				
				$(this).autocomplete({
					delay:0,minLenght:2,source:data,autoFocus:true,
					select:function(event, ui){
						if (!ui.item.d.dynamicInfo) ui.item.d.dynamicInfo={};
						updateProject($(this).closest("form"), ui.item.d);
					},
					search:function(event, ui){
						
					}
				}).blur(function(){
					var v = $(this).val(),
						nelem = $(this).parents("form").find("[name=\"project.name\"]"), 
						v1 = nelem.val();
					if (v != v1 && nelem.length == 1){
						updateProject($(this).val("").parents("form"), {});
					}
				});
				
			});
		}
	}
		
	exports.init = init;
	exports.get = get;
	exports.setSelect = function(elem, v){
		var opts = $(elem).find("option[value=\"" + v + "\"]");
		opts.attr("selected", "selected");
	};
	exports.currencyTrim = currencyTrim;
	exports.currencyFormat = currencyFormat;
	exports.percentFormat = percentFormat;
	exports.percentFormat2 = percentFormat2;
	exports.percentFormat3 = percentFormat3;
	exports.sumNumber = sumNumber;
	exports.toNumber = toNumber;
	exports.dateStringFmt = dateStringFmt;
	// exports.getBillStatusDept = getBillStatusDept;
	exports.addSpace = addSpace;
	
	exports.initSelectFiled=initSelectFiled;
	
	exports.getProjects = function(){
		return module.projects;
	}
	
	{
		$.validator.addMethod("project", function(v, elem){
			var code = $(elem).parents("form")[0].projectCode.value;
			return code;
		},"请选择一个项目");
	}
});