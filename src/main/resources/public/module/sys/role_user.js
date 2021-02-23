define(function (require, exports, module) {
	function init(){
		module.tablex = require("common");
		module.tablex.init({
			url:app + "/sys/role_user",
			idField:"id",
			form:{
				title:"角色&用户",
				fields:[ ]},
			events:{  }
		});
		module.tablex.doQuery();
	}
	
	exports.init = init;
});
