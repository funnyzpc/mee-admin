import * as Common  from "../common.js"

// 表单参数 这个参数如有表单则必须定义，同时init后会自动将global_opts中的参数合并到当前module下
var module={
    default_query:true,
    search_form: {"page_no":1,"page_size":15,"_selected":[]},
    id_field:"name",
    events:{  },
    data_events:{  },
    api:{
        "base": app+"/sys/sys_shedlock",
    },
    data:[],
    form_struct:{
        // 指定开启
        on:true,
        // form id，这只是一个prefix后面会加上actions.type
        id:"sys_user_form",
        actions:{
            // 修改
            "update":{"api":app+"/sys/sys_shedlock/update","enc":"json","method":"PUT","title":"用户修改","width":"480px"}
        },
        // 生成模板(这里的数据实在init时生成,进入页面后只生成一次)
        templates:{"add":"","update":""},
        fields:{
            "update":[
                  {name:"name",type: "text",label:"任務名稱(即ID)",col:12,attrs:{readonly:"readonly"}},
                  {name:"label",label:"任務標識",col:12,attrs:{required:"required"} },
                  {name:"locked_at",label:"任務開始鎖定",col:12,attrs:{readonly:"readonly"} },
                  {name:"lock_until",label:"任務鎖定至",col:12,attrs:{readonly:"readonly"} },
                  {name:"locked_by",label:"任務執行人",col:12,attrs:{readonly:"readonly"} },
                  {name:"status",label:"状态",type:"select",col:12,attrs:{required:"required"},"dict_type":"_status"}
                ],
            }
    },
    init_dict:[ ],
    dicts:{ "_status":{"1":{v:"1",l:"正常"},"0":{v:"0",l:"关闭"}}},
};


// 初始化通用模块
function init(){
    Common.init(module);
}

export { init }
