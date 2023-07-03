import * as Common  from "../common.js"

// 表单参数 这个参数如有表单则必须定义，同时init后会自动将global_opts中的参数合并到当前module下
var module={
    default_query:true,
    search_form: {"page_no":1,"page_size":15,"_selected":[]},
    id_field:"id",
    events:{  },
    data_events:{  },
    api:{
        "base": app+"/sys/sys_log",
    },
    data:[],
    form_struct:{
        on:false
    },
    init_dict:[ ],
    dicts:{ },
};


// 初始化通用模块
function init(){
    Common.init(module);
}

export { init }
