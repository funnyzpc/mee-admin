(function($){  
    $.fn.fixTable = function(options){ 
		var defaults = {  
			fixColumn: 0,  
			width:0,
			height:0  
		};    
	    var opts = $.extend(defaults, options); 
			var _this = $(this);
			var _clone = _this.clone();
			var _columnClone = _this.clone();
			var _columnDataClone = _this.clone();
			_this.wrap(function() {
               return $("<div id='_fixTableMain'></div>");
            });
			$("#_fixTableMain").css({
				"width":defaults["width"],
				"height":defaults["height"],
				"overflow":"scroll",
				"position":"relative"
			});
			$("#_fixTableMain").wrap(function() {
               return $("<div id='_fixTableBody'></div>");
            });
			$("#_fixTableBody").css({
			//	"background-color":"yellow",
				"width":defaults["width"],
				"height":defaults["height"],
				"overflow":"hidden",
				"position":"relative"
			});
			$("#_fixTableBody").append("<div id='_fixTableHeader'></div>");
			$(_clone).height($(_clone).find("thead").height());
			$("#_fixTableHeader").append(_clone);
			$("#_fixTableHeader").css({
			//	"background-color":"gray",
				"overflow":"hidden",
				"width":defaults["width"]-17,
				//"height":_clone.find("thead").find("tr").height()*2+1,
				"height":90,			//直接设置固定行的高度
				"position":"absolute",
				"z-index":"88888",
				"top":"0"
			});
			
			$("#_fixTableBody").append("<div id='_fixTableColumn'></div>");
			
			var _fixColumnNum = defaults["fixColumn"];
			var _fixColumnWidth = 0;
			$($(_this).find("thead").find("tr").find("th")).each(function(index, element) {
               	if((index+1)<=_fixColumnNum){
					_fixColumnWidth += $(this).width()+4;
				}
            });
			
			$("#_fixTableColumn").css({
				"overflow":"hidden",
				//"width":_fixColumnWidth,
				"width":306,	//直接设置固定列的宽度
				"height":defaults["height"]-17,
				"position":"absolute",
				"z-index":"99999",
				"top":"0",
				"left":"0"
			});
			
			
			$("#_fixTableColumn").append("<div id='_fixTableColumnHeader'></div>");
			$("#_fixTableColumnHeader").css({
			//	"background-color":"#abc123",
				//"width":$("#_fixTableColumn").width(),
				"width":306,			//直接设置固定列的宽度
				//"height":_this.find("thead").find("tr").height()*2+1,
				"height":90,			//直接设置固定行的高度
				"overflow":"hidden",
				"position":"absolute",
				"z-index":"999999",
			});
			$("#_fixTableColumnHeader").append(_columnClone);
			
			$("#_fixTableColumn").append("<div id='_fixTableColumnBody'></div>");
			$("#_fixTableColumnBody").css({
				"background-color":"#f5f5f5",
				//"width":$("#_fixTableColumn").width(),
				"width":306,		//直接设置固定列的宽度
				"height":defaults["height"]-17,
				"overflow":"hidden",
				"position":"absolute",
				"z-index":"99999",
				"top":"0"
			});
			$("#_fixTableColumnBody").append(_columnDataClone);
			$("#_fixTableMain").scroll(function(e) {
                $("#_fixTableHeader").scrollLeft($(this).scrollLeft());
				$("#_fixTableColumnBody").scrollTop($(this).scrollTop());
				$("#toolbar-list").hide();
            });
		};
    
})(jQuery); 