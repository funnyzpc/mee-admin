import * as Common  from "../common.js"
import * as Common2  from "../common.js?r=002"
import * as FetchUtils  from "../fetch_utils.js"
import * as Form  from "../form.js"

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
    data_events:{ "show_detail":showDetail ,"after_delete_func":afterDeleteFunc},
    /* 通用api接口，一般包含增删改查四大接口,给Common内的增删改查之使用，使用时一般加上 module.ctx_path 前缀  */
    /* api必须定义完整的增删改查四大api或者定义一个base api */
    api:{
        /* 列表分页查询：GET请求 */
        "page":app+"/sys/sys_dict/list",
        /* 添加：POST请求 */
        "add": app+"/sys/sys_dict/add",
        /* 删除: DELETE请求 */
        "delete": app+"/sys/sys_dict/delete",
        /* 修改: PUT请求 */
        "update": app+"/sys/sys_dict/update",
        /* 通用地址,如果 page、add、del、mod未定义测会通过此配置生成一个默认的增删改查(base+"/page",base+"/delete"...) */
        "base": app+"/sys/sys_dict",
    },
    /* 列表查询请求，如果执行的是Common的相关请求则数据会回写到此字段内*/
    data:[],
    // 表单模板
    form_struct:{
        // 指定开启
        on:true,
        // form id，这只是一个prefix后面会加上actions.type
        id:"sys_dict_form",
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
            "add":{"api":app+"/sys/sys_dict/add","enc":"json","method":"POST","title":"字典新增","width":"400px"},
            // 修改
            "update":{"api":app+"/sys/sys_dict/update","enc":"json","method":"PUT","title":"字典修改","width":"400px"}
        },
        // 生成模板(这里的数据实在init时生成,进入页面后只生成一次)
        templates:{"add":"","update":""},
        fields:{
            // 通用表单模板，如果没有定义add或update则默认使用这个,如果新增或更新表单存在差异则建议单独定义add以及update
            "common":[
                    {name:"id",type: "hidden",label:"主键(字典ID)"},
                    {name:"name",label:"字典名称",col:12,attrs:{required:"required"} },
                    {name:"description",label:"描述",col:12,attrs:{required:"required"} },
//                    {name:"create_by",label:"创建者",width:4,validate:{required:false} },
//                    {name:"update_by",label:"更新者",width:4,validate:{required:false} },
//                    {name:"create_time",label:"创建日期",width:4,validate:{required:required} },
//                    {name:"update_time",label:"更新时间",width:4,validate:{required:false} }
                ]
            }
    },
    // 定义初始化字典数据,这个字典是表单还有查询列表都要用到的
    init_dict:[],
    // 上面需要初始化字典在调用接口后会回写到此处
    // example: {"sys_common_status":{"0":{"dict_id":15,"v":"0","detail_id":10,"l":"成功"},"1":{"dict_id":15,"v":"1","detail_id":11,"l":"失败"}},"sys_job_status":{"0":{"dict_id":9,"v":"0","detail_id":14,"l":"正常"},"1":{"dict_id":9,"v":"1","detail_id":15,"l":"暂停"}}}
    dicts:{ },

};


// 字典明细表单
var module_detail={
    /* 各个区域dom id，这个id是在对应业务html中配置的，如果只有一个查询列表则无需声明以下三个配置字段 */
    // 搜索表单dom id
    search_form_id:"data-search2",
    // table列表dom id
    data_list_id:"data-list2",
    // 模板dom id
    template_id:"template-data-list2",
    /* 调用Common.init后是否调用一次查询 */
    default_query:false,
    /* 分页表单数据(可选) */
    search_form: {"page_no":1,"page_size":10,"_selected":[],"dict_id":""},
    /* 主键字段，删除或者更新的where条件,这个不定义则默认为 id  */
    id_field:"id",
    /* 当前业务模块(页面)扩展函数 id=search-form的扩展事件 */
    events:{  },
    /* id=data-list的行事件,此类事件会自动传入(当前行数据,当前行索引,当前dom对象),也可默认为空 */
    data_events:{  },
    /* 通用api接口，一般包含增删改查四大接口,给Common内的增删改查之使用，使用时一般加上 module.ctx_path 前缀  */
    /* api必须定义完整的增删改查四大api或者定义一个base api */
    api:{
        /* 通用地址,如果 page、add、del、mod未定义测会通过此配置生成一个默认的增删改查(base+"/page",base+"/delete"...) */
        "base": app+"/sys/sys_dict_detail",
    },
    /* 列表查询请求，如果执行的是Common的相关请求则数据会回写到此字段内*/
    data:[],
    // 表单模板
    form_struct:{
        // 指定开启
        on:true,
        // form id，这只是一个prefix后面会加上actions.type
        id:"sys_dict_detail_form",
        // 表单提交地址,这个跟下面的fields内一一对应
        actions:{
            "add":{"api":app+"/sys/sys_dict_detail/add","enc":"json","method":"POST","title":"字典子项新增","width":"400px","show_before_func":detailAddShowBeforFunc},
            // 修改
            "update":{"api":app+"/sys/sys_dict_detail/update","enc":"json","method":"PUT","title":"字典子项修改","width":"400px"}
        },
        // 生成模板(这里的数据实在init时生成,进入页面后只生成一次)
        templates:{"add":""},
        fields:{
            // 通用表单模板，如果没有定义add或update则默认使用这个,如果新增或更新表单存在差异则建议单独定义add以及update
            "common":[
                    {name:"id",type: "hidden",label:"主键(字典ID)"},
                    {name:"dict_id",type: "hidden",label:"字典项ID"},
                    {name:"label",label:"标签",col:12,attrs:{required:"required"} },
                    {name:"value",label:"值",col:12,attrs:{required:"required"} },
                    {name:"dict_sort",label:"排序",type:"number",col:12,attrs:{required:"required"} },
                ]
            }
    },
    // 定义初始化字典数据,这个字典是表单还有查询列表都要用到的
    init_dict:[],
    // 上面需要初始化字典在调用接口后会回写到此处
    dicts:{ },

};

// 初始化通用模块
function init(){
    Common.init(module);
    // 初始化字典项表单
    Common2.init(module_detail);
}

/*
  对于表格体的数据的操作，所有的事件均会有这三个参数传入:
      data: 当前行数据(object)
      idx:  当前行所在所有list数据的索引位置，0表示第一行
      elem: 这个是表单参数
*/
function showDetail( data,idx,elem ){
    // 选中的着色
    let tr_nodes = elem.parentNode.parentNode.querySelectorAll("tr");
    for( let i=0;i<tr_nodes.length;i++ ){
        if( idx===i ){
            //tr_nodes[i].style.backgroundImage="linear-gradient(to right,#10ff10, #8fff8f,#fefffe)";
            tr_nodes[i].style.backgroundColor="rgb(143, 255, 143)";
        }else{
            //tr_nodes[i].style.backgroundImage="";
            tr_nodes[i].style.backgroundColor="";
        }
    }
    // 更新字典明细 title
    let query_id = module_detail.search_form_id;
    let text = `字典明细::${data.description}(${data.name})`;
    document.querySelector(`#${query_id}`).firstElementChild.textContent=text;
    // 必须赋值
    // module_detail.search_form.dict_id=data.id;
    Common2.global_module.search_form.dict_id=data.id;
    Common2.doQuery();
}

// 明细新增前
function detailAddShowBeforFunc( type,action,data ){
    // 新增必须有字典id
    let dict_id = Common2.global_module.search_form.dict_id;
    if( !dict_id || -1===dict_id ){
        alert("请先选择一个字典项！");
        return false;
    }
    data.dict_id=Common2.global_module.search_form.dict_id;
    return true;
}

// 字典删除
function afterDeleteFunc( data,idx,elem,res ){
   if( res && 1===res.status){
     // 刷新字典明细
     Common2.global_module.search_form.dict_id=-1;
     Common2.doQuery();
     return true;
   }
   return false;
}

export { init }
