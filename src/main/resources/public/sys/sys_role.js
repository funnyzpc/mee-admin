import * as Common  from "../common.js"
import * as FetchUtils  from "../fetch_utils.js"

// 表单参数 这个参数如有表单则必须定义，同时init后会自动将global_opts中的参数合并到当前module下
var module={
    /* 调用Common.init后是否调用一次查询 */
    default_query:true,
    /* 分页表单数据(可选) */
    search_form: {"page_no":1,"page_size":10,"_selected":[]},
    /* 主键字段，删除或者更新的where条件,这个不定义则默认为 id  */
    id_field:"id",
    /* 当前业务模块(页面)扩展函数 id=search-form的扩展事件 */
    events:{  },
    /* id=data-list的行事件,此类事件会自动传入(当前行数据,当前行索引,当前dom对象),也可默认为空 */
    data_events:{ "line_highlight":lineHighlight },
    /* 通用api接口，一般包含增删改查四大接口,给Common内的增删改查之使用，使用时一般加上 module.ctx_path 前缀  */
    /* api必须定义完整的增删改查四大api或者定义一个base api */
    api:{
        /* 通用地址,如果 page、add、del、mod未定义测会通过此配置生成一个默认的增删改查(base+"/page",base+"/delete"...) */
        "base": app+"/sys/sys_role",
    },
    /* 列表查询请求，如果执行的是Common的相关请求则数据会回写到此字段内*/
    data:[],
    // 表单模板
    form_struct:{
        // 指定开启
        on:true,
        // form id，这只是一个prefix后面会加上actions.type
        id:"sys_role_form",
        actions:{
            "add":{"api":app+"/sys/sys_role/add","enc":"json","method":"POST","title":"角色新增","width":"400px"},
            // 修改
            "update":{"api":app+"/sys/sys_role/update","enc":"json","method":"PUT","title":"角色修改","width":"400px"}
        },
        // 生成模板(这里的数据实在init时生成,进入页面后只生成一次)
        templates:{"add":"","update":""},
        fields:{
            // 通用表单模板，如果没有定义add或update则默认使用这个,如果新增或更新表单存在差异则建议单独定义add以及update
            "common":[
                      {name:"id",type: "hidden",label:"角色id"},
                      {name:"name",label:"角色名称(en)",type:"text",col:12,attrs:{required:"required"} },
                      {name:"description",label:"角色描述",type:"text",col:12 },
                      {name:"status",label:"角色状态",type:"radio",col:12,attrs:{required:"required"},"dict_type":"sys_role_status"}
                ]
            }
    },
    // 定义初始化角色数据,这个角色是表单还有查询列表都要用到的
    init_dict:["sys_role_status"],
    // 上面需要初始化角色在调用接口后会回写到此处
    dicts:{ },
};

// 初始化通用模块
function init(){
    Common.init(module);
}

/*
  对于表格体的数据的操作，所有的事件均会有这三个参数传入:
      data: 当前行数据(object)
      idx:  当前行所在所有list数据的索引位置，0表示第一行
      elem: 这个是表单参数
*/
function lineHighlight( data,idx,elem ){
    // 选中的着色
    let tr_nodes = elem.parentNode.parentNode.querySelectorAll("tr");
    for( let i=0;i<tr_nodes.length;i++ ){
        if( idx===i ){
            tr_nodes[i].style.backgroundColor="rgb(180, 255, 180)";
        }else{
            tr_nodes[i].style.backgroundColor="";
        }
    }
}

// 保存角色&菜单关系
function addNew( data ){
    let form_data = Common2.global_module.search_form;
    let params = {"role_id":form_data.role_id,"menu_ids":form_data._selected};
    FetchUtils.fetchPostJson(module_role_menu.api["add"],params,function(res){
        alert(!res?"保存失败,请稍后重试(^,^)！": (res.msg?res.msg:res) );
        return res;
    });
}

export { init }
