import * as Common  from "../common.js?r=app"
import * as Common2 from "../common.js?r=job"
import * as FetchUtils  from "../fetch_utils.js"

// 表单参数 这个参数如有表单则必须定义，同时init后会自动将global_opts中的参数合并到当前module下
var module={
    /* 调用Common.init后是否调用一次查询 */
    default_query:true,
    /* 分页表单数据(可选) */
    search_form: {"page_no":1,"page_size":10,"_selected":[]},
    /* 主键字段，删除或者更新的where条件,这个不定义则默认为 id  */
    id_field:"application",
    /* 当前业务模块(页面)扩展函数 id=search-form的扩展事件 */
    events:{  },
    /* id=data-list的行事件,此类事件会自动传入(当前行数据,当前行索引,当前dom对象),也可默认为空 */
    data_events:{ "app_state":doAppState,"show_job":showJob,"delete_app":deleteApp },
    /* 通用api接口，一般包含增删改查四大接口,给Common内的增删改查之使用，使用时一般加上 module.ctx_path 前缀  */
    /* api必须定义完整的增删改查四大api或者定义一个base api */
    api:{
        /* 列表分页查询：GET请求 */
        "page": ctxPath+"/sys/sys_shedlock_app/list",
        /* 删除: DELETE请求 */
        "delete": ctxPath+"/sys/sys_shedlock_app/delete",
        /* 通用地址,如果 page、add、del、mod未定义测会通过此配置生成一个默认的增删改查(base+"/page",base+"/delete"...) */
        "base": ctxPath+"/sys/sys_shedlock_app",
    },
    /* 列表查询请求，如果执行的是Common的相关请求则数据会回写到此字段内*/
    data:[],
    // 表单模板
    form_struct:{
        // 指定开启
        on:true,
        // form id，这只是一个prefix后面会加上actions.type
        id:"sys_shedlock_app_form",
        actions:{
            // 添加
            "add":{"api":ctxPath+"/sys/sys_shedlock_app/add","enc":"json","method":"POST","title":"新增集群分佈式鎖-应用配置","width":"600px"},
            // 修改
            "update":{"api":ctxPath+"/sys/sys_shedlock_app/update","enc":"json","method":"PUT","title":"修改集群分佈式鎖-应用配置","width":"600px"},
        },
        // 生成模板(这里的数据实在init时生成,进入页面后只生成一次)
        templates:{"add":"","update":""},
        fields:{
            "add":[
                   {name:"application",label:"应用",col:12,attrs:{"required":"required"}},
                   {name:"host_ip",label:"实例IP",col:12},
                   {name:"host_name",label:"实例机器",col:12 },
                   {name:"state",label:"状态",type:"select","dict_type":"shedlock_state",attrs:{"required":"required"},col:12 },
                   {name:"label",label:"备注",col:12 }
                ],
            "update":[
                  {name:"application",label:"应用",col:12,attrs:{"readonly":"readonly"}},
                  {name:"host_ip",label:"实例IP",col:12,attrs:{"readonly":"readonly"}},
                  {name:"host_name",label:"实例机器",col:12,attrs:{"readonly":"readonly"} },
                  {name:"state",label:"状态",type:"select","dict_type":"shedlock_state",attrs:{"required":"required"},col:12 },
                  {name:"label",label:"备注",col:12 },
                  {name:"update_time",label:"更新时间",col:6,attrs:{readonly:"readonly" },col:12 },
                ]
            }
    },
    // 定义初始化角色数据,这个角色是表单还有查询列表都要用到的
    init_dict:["shedlock_state"],
    // 上面需要初始化角色在调用接口后会回写到此处
    dicts:{ },
};
var module_job={
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
    id_field:"id",
    /* 当前业务模块(页面)扩展函数 id=search-form的扩展事件 */
    events:{  },
    /* id=data-list的行事件,此类事件会自动传入(当前行数据,当前行索引,当前dom对象),也可默认为空 */
    data_events:{ "job_state":doJobState,"delete_job":deleteJob  },
    /* 通用api接口，一般包含增删改查四大接口,给Common内的增删改查之使用，使用时一般加上 module.ctx_path 前缀  */
    /* api必须定义完整的增删改查四大api或者定义一个base api */
    api:{
        /* 列表分页查询：GET请求 */
        "page": ctxPath+"/sys/sys_shedlock_job/list",
        /* 删除: DELETE请求 */
        "delete": ctxPath+"/sys/sys_shedlock_job/delete",
        /* 通用地址,如果 page、add、del、mod未定义测会通过此配置生成一个默认的增删改查(base+"/page",base+"/delete"...) */
        "base": ctxPath+"/sys/sys_shedlock_job",
    },
    /* 列表查询请求，如果执行的是Common的相关请求则数据会回写到此字段内*/
    data:[],
    // 表单模板
    form_struct:{
        // 指定开启
        on:true,
        // form id，这只是一个prefix后面会加上actions.type
        id:"sys_shedlock_job_form",
        actions:{
            "add":{"api":ctxPath+"/sys/sys_shedlock_job/add","enc":"json","method":"POST","title":"新增集群分佈式鎖-任务配置","width":"600px"},
            // 修改
            "update":{"api":ctxPath+"/sys/sys_shedlock_job/update","enc":"json","method":"PUT","title":"修改集群分佈式鎖-任务配置","width":"600px"},
        },
        // 生成模板(这里的数据实在init时生成,进入页面后只生成一次)
        templates:{"add":"","update":""},
        fields:{
            "add":[
                   {name:"application",label:"应用",attrs:{"required":"required"},col:12},
                   {name:"name",label:"任务KEY",attrs:{"required":"required"},col:12},
                   {name:"host_ip",label:"实例IP"},
//                   {name:"locked_at",label:"锁定开始"},
//                   {name:"lock_until",label:"锁定结束"},
                   {name:"locked_by",label:"当前实例应用"},
                   {name:"state",label:"实例状态",type:"select",col:12,"dict_type":"shedlock_state",attrs:{"required":"required"}},
                   {name:"data",label:"JOB传入数据",type:"textarea",col:12},
                   {name:"label",label:"任务备注",col:12},

                ],
            "update":[
                   {name:"application",label:"应用",attrs:{"required":"required"},col:12},
                   {name:"name",label:"任务KEY",attrs:{"required":"required"},col:12},
                   {name:"host_ip",label:"实例IP",attrs:{"readonly":"readonly"}},
                   {name:"locked_at",label:"锁定开始",attrs:{"readonly":"readonly"}},
                   {name:"lock_until",label:"锁定结束",attrs:{"readonly":"readonly"}},
                   {name:"locked_by",label:"当前实例应用",attrs:{"readonly":"readonly"}},
                   {name:"state",label:"实例状态",type:"select",col:12,"dict_type":"shedlock_state",attrs:{"required":"required"}},
                   {name:"data",label:"JOB传入数据",type:"textarea",col:12,},
                   {name:"label",label:"任务备注",col:12,},
                ]
            }
    },
    // 定义初始化角色数据,这个角色是表单还有查询列表都要用到的
    init_dict:["shedlock_state"],
    // 上面需要初始化角色在调用接口后会回写到此处
    dicts:{ },
};

// 初始化
function init(){
    // 初始化任务实例
    Common.init(module);
    // 初始化任务明细
    Common2.init(module_job);
}

function doJobState( data,idx,elem  ){
  if(!data || !data.state){
    alert("参数为空!")
    return;
  }
  data.state="0"===data.state?"1":"0";
   FetchUtils.fetchPutJson(app+"/sys/sys_shedlock_job/job_state",data,function(res){
        if( !res || res.status!==1 ){
            alert(res?res.msg:"操作失败");
            return;
        }
        let search_form = Common2.global_module.search_form;
        Common2.load(search_form);
   });
}

function doAppState( data,idx,elem  ){
  if(!data || !data.state){
    alert("必要参数为空!")
    return;
  }
  data.state="0"===data.state?"1":"0";
   FetchUtils.fetchPutJson(app+"/sys/sys_shedlock_app/app_state",data,function(res){
        if( !res || res.status!==1 ){
            alert(res?res.msg:"操作失败");
            return;
        }
        let search_form = Common.global_module.search_form;
        Common.load(search_form);
   });
}
/*
  对于表格体的数据的操作，所有的事件均会有这三个参数传入:
      data: 当前行数据(object)
      idx:  当前行所在所有list数据的索引位置，0表示第一行
      elem: 这个是表单参数
*/
function showJob( data,idx,elem ){
    let application=data.application;
    // 更新字典明细 title
    let query_id = module_job.search_form_id;
    document.querySelector(`#${query_id}`).querySelector("input[name=application]").value=application;
    // 必须赋值
    // module_detail.search_form.dict_id=data.id;
    // Common2.global_module.search_form.dict_id=data.id;
    Common2.doQuery();
}

function deleteApp(data,idx,elem){
  if (!confirm("您确认删除应用配置数据？删除后将不可恢复！")){
          return;
  }
   let application = data.application;
   let host_ip = data.host_ip;
   let appApi = module.api["delete"]
   FetchUtils.fetchDelete(appApi+"?application="+application+"&host_ip="+host_ip,"{}",function(res){
        if( !res || res.status!==1 ){
            alert(res?res.msg:"操作失败");
            return;
        }
        let search_form = Common.global_module.search_form;
        Common.load(search_form);
   });
}

function deleteJob(data,idx,elem){
  if (!confirm("您确认删除此任务数据？删除后将不可恢复！")){
          return;
  }
   let application = data.application;
   let name = data.name;
   let jobApi = module_job.api["delete"]
   FetchUtils.fetchDelete(jobApi+"?application="+application+"&name="+name,"{}",function(res){
        if( !res || res.status!==1 ){
            alert(res?res.msg:"操作失败");
            return;
        }
        let search_form = Common2.global_module.search_form;
        Common2.load(search_form);
   });
}

export { init }
