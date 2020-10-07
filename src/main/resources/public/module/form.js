define(function (require, exports, module) {
	var fieldType = {};
	/**
		{
		title:"",
		fileds:[{
			name:"", // 字段名称
			label:"", // 标签
			prefix/postfix, // 前后缀
			type:[text/date/hidden/select/checkbox/radio/extend/group], //类型
			validate:{},
			attr:{},
			width:4, // width
			nl:false // do wrap
		}]
		}
	*/
	function init(form){
		var rs ="<form method=\"post\" class=\"form-horizontal\">",cols = 12, fields = form.fields;
		rs += "<div class=\"panel panel-default\">"+
			(form.title ? "<div class=\"panel-heading\">" + form.title + "</div>" : "") +
			"<div class=\"panel-body\">";
		for(var n in fields){
			var f = form = $.extend({}, fields[n]),
				nf = fields[Math.ceil(n) + 1],
				nw = nf ? (nf.width ? nf.width : 4) : 0;
			if (!f.type) f.type="text";
			if (!f.width) f.width = 4;
			if (cols == 12){
				rs += "<div class=\"form-group\">";
				cols = 0;
			}
			rs += fieldType[f.type](f);
			if(cols + f.width > 12 || f.nl || (nf && (cols + f.width + nw) > 12) || (!nf)){
				rs += "</div>";
				cols = 12;
			}else
				cols += f.width;
		}
		rs += "</div><div class=\"panel-bodyextend\" style=\"padding:0px 15px;\" id=\"body-extend\"></div><div class=\"panel-footer\">"+
			"<div class=\"col-sm-offset-1\">"+
				"<button type=\"submit\" name=\"save\" class=\"btn btn-primary\">保存</button> "+
				"<button type=\"button\" name=\"cancel\" class=\"btn btn-default\">返回</button>"+
			"</div>"+
			"</div></div>";
		rs += "</form>";

		return rs;
	}
	
	function createExtend(field){
		var rs = "";
		if(field.validate){
			for(var v in field.validate)
				rs += (v + "=\"" + field.validate[v] + "\"");
		}
		
		if(field.attr){
			for(var a in field.attr)
				rs += (a + "=\"" + field.attr[a] + "\"");
		}
		return rs;
	}
	
	fieldType.text = function(field, clz){
		var n = field.name,
			l = field.label,
			w = field.width - 1
			s = field.style;
		clz = clz ? clz : "";
		return "<label class=\"control-label col-sm-1\" style=\"" + s + "\">" + l + "</label>" +
			"<div class=\"col-sm-" + w + "\">"+
			(field.prefix || field.postfix?"<div class=\"input-group\">" : "") +				
			(field.prefix?"<span class=\"input-group-addon\">" + field.prefix + "</span>" : "") +				
			"<input type=\"text\" name=\"" + n + "\" class=\"form-control input-sm " + (clz) + "\" " + createExtend(field) + ">"+
			(field.postfix?"<span class=\"input-group-addon\">" + field.postfix + "</span>" : "") +				
			(field.prefix || field.postfix?"</div>" : "") +				
			"</div>";
	}

	fieldType.date = function(field){
		return fieldType.text(field, "date");
	}
	fieldType.datetime = function(field){
		return fieldType.text(field, "datetime");
	}
	fieldType.currency = function(field){
		if (!field.postfix)
			field.postfix = "元";
		return fieldType.text(field, "currency");
	}

	fieldType.hidden = function(field){
		var n = field.name;
		field.width = 0;
		return "<input type=\"hidden\" name=\"" + n + "\""  + createExtend(field) + ">";
	}

	fieldType.select = function(field){
		var n = field.name,
			l = field.label,
			w = field.width - 1
			s = field.style;
		return "<label class=\"control-label col-sm-1\" style=\"" + s + "\">" + l + "</label>" +
			"<div class=\"col-sm-" + w + "\">"+
			(field.prefix || field.postfix?"<div class=\"input-group\">" : "") +				
			(field.prefix?"<span class=\"input-group-addon\">" + field.prefix + "</span>" : "") +				
			"<select name=\"" + n + "\" class=\"form-control input-sm\" " + createExtend(field) + "></select>"+
			(field.postfix?"<span class=\"input-group-addon\">" + field.postfix + "</span>" : "") +				
			(field.prefix || field.postfix?"</div>" : "") +				
			"</div>";
	}

	fieldType.checkbox = function(field){
		var n = field.name,
			l = field.label,
			w = field.width - 1
			s = field.style,
			data = field.dict;
			rs = "<label class=\"control-label col-sm-1\" style=\"" + s + "\">" + l + "</label>" +
			"<div class=\"col-sm-" + w + "\" style=\"height:30px;\">";
		for(var i in data)
			rs+="<label class=\"checkbox-inline\">" +
			"<input type=\"checkbox\" style=\"margin-top:3px;\" name=\"" + n + "\" value=\""+ i +  "\">" + data[i] + "</label>";
		rs += "</div>";
		return rs;
	}

	fieldType.radio = function(field){
		var n = field.name,
			l = field.label,
			w = field.width - 1
			s = field.style,
			data = field.dict;
			rs = "<label class=\"control-label col-sm-1\" style=\"" + s + "\">" + l + "</label>" +
			"<div class=\"col-sm-" + w + "\" style=\"height:30px;\">";
		for(var i in data)
			rs+="<label class=\"radio-inline\">" +
			"<input type=\"radio\" style=\"margin-top:3px;\" name=\"" + n + "\" value=\""+ i +  "\">" + data[i] + "</label>";
		rs += "</div>";
		return rs;
	}

	fieldType.extend = function(field){
		var n = field.name,
			l = field.label,
			w = field.width - 1
			s = field.style;
		return "<label class=\"control-label col-sm-1\" style=\"" + s + "\">" + l + "</label>" +
			"<div class=\"col-sm-" + w + "\" data-name=\"" + n + "\">"+
			"</div>";
	}
	fieldType.group = function(field){
		var s = field.style;
		return 	"<div class=\"group\" style=\"" + s + "\">" + field.label + "</div>";
	}
	
	exports.init = init;
});