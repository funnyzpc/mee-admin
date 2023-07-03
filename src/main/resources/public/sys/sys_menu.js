import * as Common  from "../common.js"
import * as FetchUtils  from "../fetch_utils.js"
import * as Form  from "../form.js"

// var show_dicts=[{"l":"是","v":"1"},{"l":"否","v":"0"}];
// 表单参数 这个参数如有表单则必须定义，同时init后会自动将global_opts中的参数合并到当前module下
var module={
    /* 调用Common.init后是否调用一次查询 */
    default_query:true,
    /* 分页表单数据(可选) */
    search_form: {"page_no":0,"page_size":10,"idx":1,"_selected":[]},
    /* 主键字段，删除或者更新的where条件,这个不定义则默认为 id  */
    id_field:"id",
    /* 当前业务模块(页面)扩展函数 id=search-form的扩展事件 */
    events:{  },
    /* id=data-list的行事件,此类事件会自动传入(当前行数据,当前行索引,当前dom对象),也可默认为空 */
    data_events:{"switch_dom":switchDom,"add_line":addLine},
    /* 通用api接口，一般包含增删改查四大接口,给Common内的增删改查之使用，使用时一般加上 module.ctx_path 前缀  */
    /* api必须定义完整的增删改查四大api或者定义一个base api */
    api:{
        /* 列表分页查询：GET请求 */
        "page":app+"/sys/sys_menu/list",
        /* 添加：POST请求 */
        "add": app+"/sys/sys_menu/add",
        /* 删除: DELETE请求 */
        "delete": app+"/sys/sys_menu/delete",
        /* 修改: PUT请求 */
        "update": app+"/sys/sys_menu/update",
        /* 通用地址,如果 page、add、del、mod未定义测会通过此配置生成一个默认的增删改查(base+"/page",base+"/delete"...) */
        "base": app+"/sys/sys_menu",
    },
    /* 列表查询请求，如果执行的是Common的相关请求则数据会回写到此字段内*/
    data:[],
    // 表单模板
    form_struct:{
        // 指定开启
        on:true,
        // form id，这只是一个prefix后面会加上actions.type
        id:"sys_menu_form",
        // 宽度(这个是dialog的宽度,可不用定义，不定时则为"",不建议写auto), 这个挪到了action了，如需则在action中配置
        // example 600px
        // width:"",
        // 表单提交地址,这个跟下面的fields内一一对应
        actions:{
            // 新增
            // show_before_func(type,action,data): 表单生成前的事件(事件返回false则不会执行后续代码)
            // show_after_func(type,action,dialog_dom) : 表单生成后的事件(事件返回false则不会执行后续代码)
            // submit_before_func(type,action,dialog_dom): 定义表单生成后与表单提交前的事件(事件返回false则不会执行后续代码)
            // submit_after_func(type,action,dialog_dom) :  定义表单提交后的事件(事件返回false则不会执行后续代码)
            "add":{"api":app+"/sys/sys_menu/add","enc":"json","method":"POST","title":"菜单新增","show_before_func":showBeforeFunc,/*"submit_before_func":submitBeforeAddFunc,"submit_after_func":submitAfterAddFunc */ },
            // 修改
            "update":{"api":app+"/sys/sys_menu/update","enc":"json","method":"PUT","title":"菜单修改","show_before_func":updateShowBeforeFunc}
        },
        // 生成模板(这里的数据实在init时生成,进入页面后只生成一次)
        templates:{"add":"","update":""},
        fields:{
            // 通用表单模板，如果没有定义add或update则默认使用这个,如果新增或更新表单存在差异则建议单独定义add以及update
            "common":[
                {name:"id",label:"菜单",type: "hidden"},
                {name:"pid",label:"父ID",col:6,attrs:{required:"required"},attrs:{readonly:"readonly"}},
                {name:"type",label: "菜单类型",type:"select",col:6,attrs:{required:"required"},nl:true,"dict_type":"sys_menu_type"},
                {name:"title",label: "菜单(目录/菜单/按钮)",col:12,attrs:{required:"required"}},
                {name:"icon",label: "菜单图标",col:6,attrs:{required:"required"},nl:true},
                {name:"path",label: "路径",col:6,attrs:{required:"required"}},
                {name:"target",label: "打开目标",type:"select",col:6,attrs:{required:"required"},"dict_type":"sys_menu_target"},
                {name:"permission",label: "权限标识",type:"text",col:6,attrs:{required:"required"},attrs:{"data-dict":"user_status"}},
                {name:"sub_count",label: "子级个数",type:"hidden",col:4,attrs:{required:"required"},attrs:{"data-dict":"user_status"}},
                {name:"show",label: "是否显示",type:"radio",col:6,attrs:{required:"required"},"dict_type":"sys_menu_show"},
                ],
            "add":[
                //{name:"id",label:"菜单",type: "hidden"},
                // 因为可能是从行新曾则必须有pid
                {name:"pid",label:"父ID",type: "hidden",col:6,attrs:{required:"required",readonly:"readonly"}},
                {name:"sub_count",label: "子级个数",type:"hidden",col:4},
                // 扩展字段，仅用于展示
                {name:"pid_desc",label:"父级",col:12,attrs:{readonly:"readonly"}},
                {name:"type",label: "菜单类型",type:"select",col:12,attrs:{required:"required"},"dict_type":"sys_menu_type",nl:true},
                {name:"title",label: "名称(目录/菜单/按钮)",col:12,attrs:{required:"required"}},
                {name:"icon",label: "菜单图标",col:12,nl:true},
                {name:"path",label: "路径",col:12},
                {name:"target",label: "打开目标",type:"select",col:12,"dict_type":"sys_menu_target"},
                {name:"permission",label: "权限标识",type:"text",col:6},
                {name:"sort",label: "排序",type:"number",col:6,attrs:{required:"required"}},
                {name:"show",label: "是否显示",type:"radio",col:6,attrs:{required:"required"},"dict_type":"sys_menu_show"},
                //{name:"多选",label: "多选",type:"checkbox",col:6,attrs:{required:"required"},"dict_type":"sys_menu_show"},
                //{name:"文本域",label: "文本域",type:"textarea",col:12},
                ],
            "update":[
                {name:"id",label:"菜单", type: "hidden"},
                {name:"pid",label:"父ID",type: "hidden",col:6,attrs:{required:"required"},attrs:{readonly:"readonly"}},
                {name:"sub_count",label: "子级个数",type:"hidden",col:4,attrs:{required:"required"}},
                // 扩展字段，仅用于展示
                {name:"pid_desc",label:"父级",col:12,attrs:{readonly:"readonly"}},
                {name:"type",label: "菜单类型",type:"select",col:12,attrs:{required:"required"},nl:true,"dict_type":"sys_menu_type"},
                {name:"title",label: "名称(目录/菜单/按钮)",col:12,attrs:{required:"required"}},
                {name:"icon",label: "菜单图标",col:12,nl:true},
                {name:"path",label: "路径",col:12},
                {name:"target",label: "打开目标",type:"select",col:12,"dict_type":"sys_menu_target"},
                {name:"permission",label: "权限标识",type:"text",col:6},
                {name:"sort",label: "排序",type:"number",col:6,attrs:{required:"required",}},
                {name:"show",label: "是否显示",type:"radio",col:6,attrs:{required:"required"},"dict_type":"sys_menu_show"},
                ],
            }
    },
    // 定义初始化字典数据,这个字典是表单还有查询列表都要用到的
    init_dict:["sys_menu_type","sys_menu_target"],
    // 上面需要初始化字典在调用接口后会回写到此处
    // example: {"sys_common_status":{"0":{"dict_id":15,"v":"0","detail_id":10,"l":"成功"},"1":{"dict_id":15,"v":"1","detail_id":11,"l":"失败"}},"sys_job_status":{"0":{"dict_id":9,"v":"0","detail_id":14,"l":"正常"},"1":{"dict_id":9,"v":"1","detail_id":15,"l":"暂停"}}}
    dicts:{ "sys_menu_show":{"1":{"v":"1","l":"是"},"0":{"v":"0","l":"否"}} },

};

// 初始化通用模块
function init(){
//    Form.init(module.form_struct);
    // ctxPath 参数在 wrapper/header.html 中定义~
    Common.init(module);
}

// 表单之前做的处理
function showBeforeFunc( type,action,data ){
    // 默认显示
    data.show="1";
    // 如果是从某个节点新增的则不能让 pid=0
    if( !data.pid ){
        // 新增顶级的pid=0
        data.pid=0;
    }
    return true;
}

/*
  行添加
  对于表格体的数据的操作，所有的事件均会有这三个参数传入:
      data: 当前行数据(object)
      idx:  当前行所在所有list数据的索引位置，0表示第一行
      elem: 这个是表单参数
*/
function addLine( data,idx,elem ){
    let _data = {};
    // 新增子级必须要有pid
    _data.pid=data.id;
    if( !data.pid_desc ){
        //找 pid的title
        let id = data.id;
        _data.pid_desc=""+id+"-"+(data.title);
    }
    Common.toAdd(_data,idx,elem);
}

function updateShowBeforeFunc( type,action,data  ){
    if( "0"===data.pid ){
        data.pid_desc=""+data.pid+"-TOP";
    }else{
        let pid_desc = "";
        let _datas = Common.global_module.data;
        for( let idx in _datas ){
            if( _datas[idx].id===data.pid ){
                pid_desc=_datas[idx].title;
                break;
            }
        }
        data.pid_desc=pid_desc;
        //data.pid_desc=""+data.id+"-"+(data.title);
    }
    return true;
}

// ----------- new -----------
/*
    切换dom
    data: 当前行数据(object)
    idx:  当前行所在所有list数据的索引位置，0表示第一行
    elem: 这个是当前dom对象
*/
function switchDom(data,idx,elem){
    let data_id = elem.getAttribute("data-id");
    let field_open = elem.getAttribute("field-open");
    if( "0"===field_open ){
        // 展开
        elem.innerText="▼";
        elem.setAttribute("field-open","1");
        document.querySelectorAll("tr[data-pid='"+data_id+"']").forEach(item=>{
                item.style.display="";
            });
    }else{
        // 收缩
        elem.innerText="▶";
        elem.setAttribute("field-open","0");
        locking_dom(data_id);
    }
};

// 收缩
function locking_dom(pid){
    document.querySelectorAll("tr[data-pid='"+pid+"']").forEach(item=>{
        item.style.display="none";
        // 他的下一级是否存在，存在则收缩
        let data_id = item.getAttribute("data-id");
        // 修正箭头方向
        let arrow_dom = item.querySelector("a[field-open='1']");
        if( arrow_dom ){
            arrow_dom.innerText="▶";
            arrow_dom.setAttribute("field-open","0");
        }
        // 递归调用收缩
        locking_dom(data_id);
    });
}


export {
    switchDom,
    init
}
