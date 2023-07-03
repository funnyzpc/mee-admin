import * as Common  from "../common.js"
import * as Common2  from "../common.js?r=module_role_menu"
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
    data_events:{ "show_role_and_menu_data":showRoleAndMenuData },
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


// 角色明细表单
var module_role_menu={
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
    default_query:true,
    /* 分页表单数据(可选) */
    search_form: {"page_no":1,"page_size":10,"_selected":[],"role_id":""},
    /* 主键字段，删除或者更新的where条件,这个不定义则默认为 id  */
    id_field:"id",
    /* 当前业务模块(页面)扩展函数 id=search-form的扩展事件 */
    events:{ "add_new":addNew },
    /* id=data-list的行事件,此类事件会自动传入(当前行数据,当前行索引,当前dom对象),也可默认为空 */
    data_events:{ "switch_dom":switchDom  },
    /* 通用api接口，一般包含增删改查四大接口,给Common内的增删改查之使用，使用时一般加上 module.ctx_path 前缀  */
    /* api必须定义完整的增删改查四大api或者定义一个base api */
    api:{
        /* 列表分页查询：GET请求 */
        "page":app+"/sys/sys_menu/list",
        /* 添加：POST请求 */
        "add": app+"/sys/sys_role_menu/add",
        /* 通用地址,如果 page、add、del、mod未定义测会通过此配置生成一个默认的增删改查(base+"/page",base+"/delete"...) */
        "base": app+"/sys/sys_role_menu",
    },
    /* 列表查询请求，如果执行的是Common的相关请求则数据会回写到此字段内*/
    data:[],
    form_struct:{  on:false },
    // 定义初始化角色数据,这个角色是表单还有查询列表都要用到的
    init_dict:["sys_menu_type","sys_menu_show"],
    // 上面需要初始化角色在调用接口后会回写到此处
    dicts:{ },

};

// 初始化通用模块
function init(){
    Common.init(module);
    // 初始化角色项表单
    Common2.init(module_role_menu);
}

/*
  对于表格体的数据的操作，所有的事件均会有这三个参数传入:
      data: 当前行数据(object)
      idx:  当前行所在所有list数据的索引位置，0表示第一行
      elem: 这个是表单参数
*/
function showRoleAndMenuData( data,idx,elem ){
    // 选中的着色
    let tr_nodes = elem.parentNode.parentNode.querySelectorAll("tr");
    for( let i=0;i<tr_nodes.length;i++ ){
        if( idx===i ){
            //tr_nodes[i].style.backgroundColor="#10ff10, #8fff8f,#fefffe)";
            tr_nodes[i].style.backgroundColor="#10ff10";
        }else{
            tr_nodes[i].style.backgroundColor="";
        }
    }
    // 更新角色明细 title
    let query_id = module_role_menu.search_form_id;
    let text = `角色&菜单 => ${data.description}(${data.name})`;
    document.querySelector(`#${query_id}`).firstElementChild.textContent=text;
    // 必须赋值
    // module_role_menu.search_form.dict_id=data.id;
    Common2.global_module.search_form.role_id=data.id;
    //Common2.doQuery();
    FetchUtils.fetchGet(app+"/sys/sys_role_menu/listByRoleId",{"role_id":data.id},function (res){
        if( !res || res.status!==1 ){
            alert("角色&菜单数据获取数据失败（*m*）");
            return;
        }
        // 构建role_id数组
        let arr = buildArray(res.data);
        // 执行checkbox=true
        let query_id = Common2.global_module.data_list_id;
        let input_doms = document.querySelectorAll(`#${query_id} table>tbody>tr>td>input`)
        for( let idx=0;idx<input_doms.length;idx++ ){
            let value = input_doms[idx].value;
            if( arr.includes(value) ){
                // 这里必须要执行点击，否则_selected[]中无数据！！！
                // 当已经选中的时候无需再点击
                if( input_doms[idx].checked===false ){
                    input_doms[idx].click();
                }
            }else{
                // 防止切换时仍被选中的问题,再点一次即为取消选中
                if( input_doms[idx].checked===true ){
                    input_doms[idx].click();
                }
            }
        }
    });
}

// 构建role_id数组
function buildArray(data_list ){
    let arr = [];
    for( let idx in data_list ){
        arr.push( data_list[idx].menu_id );
    }
    return arr;
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
