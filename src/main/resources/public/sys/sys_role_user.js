import * as Common  from "../common.js"
import * as Common2  from "../common.js?r=module_role_user"
import * as Common3  from "../common.js?r=module_user"
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
    data_events:{ "show_role_and_user_data":showRoleAndUserData },
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
        // 指定开启/关闭
        on:false,
    },
    // 定义初始化角色数据,这个角色是表单还有查询列表都要用到的
    init_dict:[],
    // 上面需要初始化角色在调用接口后会回写到此处
    dicts:{ },

};


// 角色&用户明细表单
var module_role_user={
    /* 各个区域dom id，这个id是在对应业务html中配置的，如果只有一个查询列表则无需声明以下三个配置字段 */
    // 搜索表单dom id
    search_form_id:"data-search2",
    // table列表dom id
    data_list_id:"data-list2",
    // 模板dom id
    template_id:"template-data-list2",

    /* 当前应用context-path 具体见springboot配置::server.servlet.context-path */
    ctx_path:app,
    /* 调用Common.init后是否调用一次查询 */
    default_query:false,
    /* 分页表单数据(可选) */
    search_form: {"page_no":1,"page_size":10,"_selected":[],"role_id":""},
    /* 主键字段，删除或者更新的where条件,这个不定义则默认为 id  */
    id_field:"id",
    /* 当前业务模块(页面)扩展函数 id=search-form的扩展事件 */
    events:{ "add_new":addNew },
    /* id=data-list的行事件,此类事件会自动传入(当前行数据,当前行索引,当前dom对象),也可默认为空 */
    data_events:{  },
    /* 通用api接口，一般包含增删改查四大接口,给Common内的增删改查之使用，使用时一般加上 module.ctx_path 前缀  */
    /* api必须定义完整的增删改查四大api或者定义一个base api */
    api:{
        "page":app+"/sys/sys_role_user/list",
        "add": app+"/sys/sys_role_user/add",
        "delete": app+"/sys/sys_role_user/delete",
        "base": app+"/sys/sys_role_user",
    },
    /* 列表查询请求，如果执行的是Common的相关请求则数据会回写到此字段内*/
    data:[],
    form_struct:{  on:false },
    // 定义初始化角色数据,这个角色是表单还有查询列表都要用到的
    init_dict:[ "sys_user_status","del_flag" ],
    // 上面需要初始化角色在调用接口后会回写到此处
    dicts:{ },

};

// 用户查询表单
var module_user={
    /* 各个区域dom id，这个id是在对应业务html中配置的，如果只有一个查询列表则无需声明以下三个配置字段 */
    // 搜索表单dom id
    search_form_id:"data-search3",
    // table列表dom id
    data_list_id:"data-list3",
    // 模板dom id
    template_id:"template-data-list3",

    /* 当前应用context-path 具体见springboot配置::server.servlet.context-path */
    ctx_path:app,
    /* 调用Common.init后是否调用一次查询 */
    default_query:false,
    /* 分页表单数据(可选) */
    search_form: {"page_no":1,"page_size":12,"_selected":[],"role_id":""},
    /* 主键字段，删除或者更新的where条件,这个不定义则默认为 id  */
    id_field:"id",
    /* 当前业务模块(页面)扩展函数 id=search-form的扩展事件 */
    events:{ "add_user_batch":addUserBatch },
    /* id=data-list的行事件,此类事件会自动传入(当前行数据,当前行索引,当前dom对象),也可默认为空 */
    data_events:{ "add_user":addUser },
    /* 通用api接口，一般包含增删改查四大接口,给Common内的增删改查之使用，使用时一般加上 module.ctx_path 前缀  */
    /* api必须定义完整的增删改查四大api或者定义一个base api */
    api:{
        /* 列表分页查询：GET请求 */
        "page":app+"/sys/sys_role_user/list_user",
        /* 添加：POST请求 */
        "add": app+"/sys/sys_role_user/add",
        /* 通用地址,如果 page、add、del、mod未定义测会通过此配置生成一个默认的增删改查(base+"/page",base+"/delete"...) */
        "base": app+"/sys/sys_role_user",
    },
    /* 列表查询请求，如果执行的是Common的相关请求则数据会回写到此字段内*/
    data:[],
    form_struct:{  on:false },
    // 定义初始化角色数据,这个角色是表单还有查询列表都要用到的
    init_dict:[ ],
    // 上面需要初始化角色在调用接口后会回写到此处
    dicts:{ },

};

// 初始化通用模块
function init(){
    Common.init(module);
    // 初始化角色项表单
    Common2.init(module_role_user);
    // 用户项表单
    Common3.init(module_user);
}

/*
  对于表格体的数据的操作，所有的事件均会有这三个参数传入:
      data: 当前行数据(object)
      idx:  当前行所在所有list数据的索引位置，0表示第一行
      elem: 这个是表单参数
*/
function showRoleAndUserData( data,idx,elem ){
    // 选中的着色
    let tr_nodes = elem.parentNode.parentNode.querySelectorAll("tr");
    for( let i=0;i<tr_nodes.length;i++ ){
        if( idx===i ){
            tr_nodes[i].style.backgroundColor="#8fff8f";
        }else{
            tr_nodes[i].style.backgroundColor="";
        }
    }
    // 更新角色明细 title
    let query_id = module_role_user.search_form_id;
    let text = `角色&用户 => ${data.name}(${data.description})`;
    document.querySelector(`#${query_id}`).firstElementChild.textContent=text;

    // 待添加至角色 title
    let query_id2 = module_user.search_form_id;
    let text2 = `用户 TO ${data.name}(${data.description})`;
    document.querySelector(`#${query_id2} h4 span`).textContent=text2;

    // 必须赋值
    Common2.global_module.search_form.role_id=data.id;
    Common3.global_module.search_form.role_id=data.id;
    Common2.doQuery();
}

// 保存角色&用户关系
function addNew( data,item2,item3 ){
    // 默认事件
    if( data.preventDefault ){
        data.preventDefault();
    }
    let role_id = Common3.global_module.search_form.role_id;
    if( !role_id ){
        alert("请先选择一个角色");
        return;
    }
    Common3.doQuery();
    document.querySelector("#_user-main-dialog").showModal();
}

// 执行添加用户操作(批量)
function addUserBatch(){
    let search_form = Common3.global_module.search_form;
    if( !search_form._selected || !search_form._selected.length===0 ){
        alert("请至少选择一个！");
        return;
    }
    let param = {"role_id":search_form.role_id,user_ids:search_form._selected };
    FetchUtils.fetchPostJson(app+"/sys/sys_role_user/add",param,function( res ){
        if( !res || 1!==res.status ){
            alert( !res?"添加失败,请稍后重试(^,^)！":res.msg?res.msg:res );
        }else{
            alert(res.msg);
            Common2.doQuery();
            Common3.doQuery();
        }
    });
}

// 添加用户至角色用户
function addUser( data,idx,elem ){
    let search_form = Common3.global_module.search_form;
    let param = {"role_id":search_form.role_id,user_ids:[data.id] };
    FetchUtils.fetchPostJson(app+"/sys/sys_role_user/add",param,function( res ){
       if( !res || 1!==res.status ){
           alert( !res?"添加失败,请稍后重试(^,^)！":res.msg?res.msg:res );
       }else{
           alert(res.msg);
           Common2.doQuery();
           Common3.doQuery();
       }
    });
}

export { init }
