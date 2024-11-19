import * as Common  from "../common.js?r=app"
import * as Common2 from "../common.js?r=node"
import * as FetchUtils  from "../fetch_utils.js"

// 表单参数 这个参数如有表单则必须定义，同时init后会自动将global_opts中的参数合并到当前module下
var module={
    /* 调用Common.init后是否调用一次查询 */
    default_query:true,
    /* 分页表单数据(可选) */
    search_form: {"page_no":1,"page_size":5,"_selected":[]},
    /* 主键字段，删除或者更新的where条件,这个不定义则默认为 id  */
    id_field:"application",
    /* 当前业务模块(页面)扩展函数 id=search-form的扩展事件 */
    events:{  },
    /* id=data-list的行事件,此类事件会自动传入(当前行数据,当前行索引,当前dom对象),也可默认为空 */
    data_events:{ "app_state":doAppState,"show_node":showNode,"add_sub":addSub},
    /* 通用api接口，一般包含增删改查四大接口,给Common内的增删改查之使用，使用时一般加上 module.ctx_path 前缀  */
    /* api必须定义完整的增删改查四大api或者定义一个base api */
    api:{
        /* 通用地址,如果 page、add、del、mod未定义测会通过此配置生成一个默认的增删改查(base+"/page",base+"/delete"...) */
        "base": ctxPath+"/sys/qrtz_app",
        /* 列表分页查询：GET请求 */
        "page": ctxPath+"/sys/qrtz_app/list",
        /* 删除: DELETE请求 */
        "delete": ctxPath+"/sys/qrtz_app/delete",
    },
    /* 列表查询请求，如果执行的是Common的相关请求则数据会回写到此字段内*/
    data:[],
    // 表单模板
    form_struct:{
        // 指定开启
        on:true,
        // form id，这只是一个prefix后面会加上actions.type
        id:"qrtz_app_form",
        actions:{
            "add":{"api":ctxPath+"/sys/qrtz_app/add","enc":"json","method":"POST","title":"新增定时任务::应用","width":"480px"},
//            // 修改
//            "update":{"api":ctxPath+"/sys/qrtz_app/update","enc":"json","method":"PUT","title":"修改定时任务::应用","width":"480px"},
        },
        // 生成模板(这里的数据实在init时生成,进入页面后只生成一次)
        templates:{"add":"","update":""},
        fields:{
            "add":[
                 {name:"application",attrs:{required:"required"},label:"调度名称/应用名称","title":"对应quartz的配置: spring.quartz.properties.org.quartz.scheduler.instanceName",col:12,attrs:{"placeholder":"eg: mee_admin"}},
                 {name:"state",label:"状态",type:"select",attrs:{required:"required"},"dict_type":"qrtz_app_state","title":"如果是N则当前应用下的所有任务不执行",col:12},
                ],
/*            "update":[
                   {name:"application",type: "hidden",label:"调度名称/应用名称",col:12},
                   {name:"state",label:"状态",type:"select","dict_type":"qrtz_app_state",col:12},
                   {name:"create_by",label:"创建人",col:6,attrs:{readonly:"readonly" } ,col:12},
                   {name:"create_time",label:"创建时间",col:6,attrs:{ readonly:"readonly"},col:12 },
                   {name:"update_by",label:"更新人",col:6,attrs:{readonly:"readonly" },col:12 },
                   {name:"update_time",label:"更新时间",col:6,attrs:{readonly:"readonly" },col:12 }
                ]*/
            }
    },
    // 定义初始化角色数据,这个角色是表单还有查询列表都要用到的
    init_dict:[],
    // 上面需要初始化角色在调用接口后会回写到此处
    dicts:{ "qrtz_app_state":{"Y":{"v":"Y","l":"开启"},"N":{"v":"N","l":"关闭"} } },
};

// 表单参数 这个参数如有表单则必须定义，同时init后会自动将global_opts中的参数合并到当前module下
var module_node={
    /* 各个区域dom id，这个id是在对应业务html中配置的，如果只有一个查询列表则无需声明以下三个配置字段 */
    // 搜索表单dom id
    search_form_id:"data-search2",
    // table列表dom id
    data_list_id:"data-list2",
    // 模板dom id
    template_id:"template-data-list2",
    /* 调用Common.init后是否调用一次查询 */
    default_query:true,
    /* 分页表单数据(可选) */
    search_form: {"page_no":1,"page_size":10,"_selected":[]},
    /* 主键字段，删除或者更新的where条件,这个不定义则默认为 id  */
    id_field: ["application","hostIp"],
    /* 当前业务模块(页面)扩展函数 id=search-form的扩展事件 */
    events:{  },
    /* id=data-list的行事件,此类事件会自动传入(当前行数据,当前行索引,当前dom对象),也可默认为空 */
    data_events:{"node_state":doNodeState },
    /* 通用api接口，一般包含增删改查四大接口,给Common内的增删改查之使用，使用时一般加上 module.ctx_path 前缀  */
    /* api必须定义完整的增删改查四大api或者定义一个base api */
    api:{
        /* 列表分页查询：GET请求 */
        "page": ctxPath+"/sys/qrtz_node/list",
        /* 删除: DELETE请求 */
        "delete": ctxPath+"/sys/qrtz_node/delete",
        /* 通用地址,如果 page、add、del、mod未定义测会通过此配置生成一个默认的增删改查(base+"/page",base+"/delete"...) */
        "base": ctxPath+"/sys/qrtz_node",
    },
    /* 列表查询请求，如果执行的是Common的相关请求则数据会回写到此字段内*/
    data:[],
    // 表单模板
    form_struct:{
        // 指定开启
        on:true,
        // form id，这只是一个prefix后面会加上actions.type
        id:"qrtz_node_form",
        actions:{
            "add":{"api":ctxPath+"/sys/qrtz_node/add","enc":"json","method":"POST","title":"新增定时任务::节点实例","width":"480px"},
            // 修改
            "update":{"api":ctxPath+"/sys/qrtz_node/update","enc":"json","method":"PUT","title":"修改定时任务::节点实例","width":"480px"},
            // 导入
//            "import":{"api":app+"/sys/qrtz_node/import","enc":"form","method":"POST","title":"导入定时任务::节点实例数据"}
        },
        // 生成模板(这里的数据实在init时生成,进入页面后只生成一次)
        templates:{"add":"","update":""},
        fields:{
            "add":[
                  {name:"application",label:"应用/调度名称",type:"text",col:12,attrs:{required:"required",readonly:"readonly"},"title":"对应quartz的配置: spring.quartz.properties.org.quartz.scheduler.instanceName" },
                  {name:"hostIp",label:"实例IP",type:"text",col:12,attrs:{required:"required","placeholder":"eg: 192.168.1.11"},"title":"机器IP，如果没有quartz启动时会自动生成一个，如果有则必须对应" },
                  {name:"hostName",label:"实例机器名称",type:"text",col:12,attrs:{required:"required","placeholder":"eg: host01"}},
                  {name:"state",label:"状态",type:"select","dict_type":"qrtz_node_state",col:12,attrs:{required:"required"},"title":"如果是N则当前节点不执行任何任务"},
                ],
            "update":[
                   {name:"application",type: "text",col:12,label:"应用/调度名称",attrs:{required:"required",readonly:"readonly"},"title":"对应quartz的配置: spring.quartz.properties.org.quartz.scheduler.instanceName"},
                   {name:"hostIp",label:"实例机器名称",type:"text",col:12,attrs:{required:"required",readonly:"readonly"},"title":"机器IP，如果没有quartz启动时会自动生成一个，如果有则必须对应" },
                   {name:"hostName",label:"实例机器名称",type:"text",col:12,attrs:{required:"required"} },
                   {name:"state",label:"状态",type:"select","dict_type":"qrtz_node_state",col:12,attrs:{required:"required"},"title":"如果是N则当前节点不执行任何任务"}
                ],
             /*"import":[
                  {name:"file",label:"文件",col:12,type:"file",attrs:{required:"required",title:"非空,若导入失败请删除excel数据行下方空白行～"}},
                ],*/
            }
    },
    // 定义初始化角色数据,这个角色是表单还有查询列表都要用到的
    init_dict:[],
    // 上面需要初始化角色在调用接口后会回写到此处
    dicts:{ "qrtz_node_state":{"Y":{"v":"Y","l":"开启"},"N":{"v":"N","l":"关闭"}} },
};

// 初始化
function init(){
  Common.init(module);
  // 初始化任务明细
  Common2.init(module_node);
}

///*
//    切换dom
//    data: 当前行数据(object)
//    idx:  当前行所在所有list数据的索引位置，0表示第一行
//    elem: 这个是当前dom对象
//*/
//function switchDom(data,idx,elem){
//    let data_id = elem.getAttribute("data-id");
//    let field_open = elem.getAttribute("field-open");
//    // let arrow_elem = elem.querySelector(`#arrow-${data_id}`);
//    // let node_elem = elem.querySelector(`#node-${data_id}`);
//    let node_elem = elem.parentNode.nextElementSibling;
//    if( "0"===field_open ){
//        // 展开
//        elem.setAttribute("field-open","1");
//        elem.innerText="▼";
//        node_elem.style.display="";
//    }else{
//        // 收缩
//        elem.innerText="▶";
//        elem.setAttribute("field-open","0");
//        node_elem.style.display="none";
//    }
//};

function doAppState( data,idx,elem  ){
  if(!data || !data.state){
    alert("必要参数为空!")
    return;
  }
  data.state = ("N"===data.state)?"Y":"N";
  FetchUtils.fetchPutJson(app+"/sys/qrtz_app/app_state",data,function(res){
        if( !res || res.status!==1 ){
            alert(res?res.msg:"操作失败");
            return;
        }
        let search_form = Common.global_module.search_form;
        Common.load(search_form);
   });
}

function doNodeState( data,idx,elem  ){
  if(!data || !data.state){
    alert("必要参数为空!")
    return;
  }
  data.state=("N"===data.state)?"Y":"N";
   FetchUtils.fetchPutJson(app+"/sys/qrtz_node/node_state",data,function(res){
        if( !res || res.status!==1 ){
            alert(res?res.msg:"操作失败");
            return;
        }
        let search_form = Common2.global_module.search_form;
        Common2.load(search_form);
   });
}

/*
  对于表格体的数据的操作，所有的事件均会有这三个参数传入:
      data: 当前行数据(object)
      idx:  当前行所在所有list数据的索引位置，0表示第一行
      elem: 这个是表单参数
*/
function showNode( data,idx,elem ){
    let application=data.application;
    // 更新字典明细 title
    let query_id = module_node.search_form_id;
    document.querySelector(`#${query_id}`).querySelector("input[name=application]").value=application;
    // 必须赋值
    // module_detail.search_form.dict_id=data.id;
    // Common2.global_module.search_form.dict_id=data.id;
    Common2.doQuery();
}


/*
  节点添加
  对于表格体的数据的操作，所有的事件均会有这三个参数传入:
      data: 当前行数据(object)
      idx:  当前行所在所有list数据的索引位置，0表示第一行
      elem: 这个是表单参数
*/
function addSub( data,idx,elem ){
    let _data = {"application":data.application};
    Common2.toAdd(_data,idx,elem);
}
export { init }
