import * as FetchUtils  from "./fetch_utils.js"
import * as MeeUtils  from "./mee_utils.js"
import * as MeeDicts  from "./mee_dicts.js"
import * as Form  from "../form.js"
import * as SearchFormFields  from "../search_form_fields.js"

/*通用api及参数定义，init后会自动合并到各自的模块:module */
var global_module = {
    /* 调用Common.init后是否调用一次查询 */
    default_query:true,
    /* 分页表单的默认参数 _selected:已选择的数据体id，一般定义多选框需要此参数*/
    search_form:{"page_no":1,"page_size":10,"_total":0,"_page_count":0,"_selected":[]},
    /* 主键字段,如果module定义了则会覆盖此字段 */
    id_field:"id",
    /* 表头事件，默认是 module中定义的会合并此 */
    events:{"query": doQuery,"add":toAdd,"export":doExport,"import":doImport,"delete_batch":deleteBatch,/*分页方法start*/"previous_page":previousPage,"first_page":firstPage,"last_page":lastPage,"next_page":nextPage,"_page_size":pageSize},
    /* select_one为单选, select_all为多选 */
    data_events:{"select_one":doSelectOne,"select_all":doSelectAll,"update":toUpdate,"delete":toDelete},
    /* 增删改查api,一般定义非表单类api */
    api:{
        /* 列表分页查询：GET请求 */
        "page":"/list",
        /* 删除: DELETE请求 */
        "delete": "/delete",
        /* 批量删除: DELETE+BODY请求 */
        "delete_batch": "/delete_batch",
        /* 导入：GET请求 */
        "export":"/export",
    },
    /* 列表查询请求，如果执行的是Common的相关请求则数据会回写到此字段内*/
    data:[],
    // 定义初始化字典数据,这个字典是表单还有查询列表都要用到的
    init_dict:[],
    // 上面需要初始化字典在调用接口后会回写到此处
    // example: {"sys_common_status":{"0":{"dict_id":15,"v":"0","detail_id":10,"l":"成功"},"1":{"dict_id":15,"v":"1","detail_id":11,"l":"失败"}},"sys_job_status":{"0":{"dict_id":9,"v":"0","detail_id":14,"l":"正常"},"1":{"dict_id":9,"v":"1","detail_id":15,"l":"暂停"}}}
    dicts:{ },

};

function init(module){

    // 合并参数
    mergeProperty(module);

    // 初始化字典
    MeeDicts.init(global_module,function(dicts){
        // 查询表单字段字典项生成
        initSearchFormDicts();
        // 注册查询表单事件
        registerSearchForm();

        // 注册表行事件( 在加载table之后=>registerBodyEvent )

        // 生成新增、修改form表单
        buildFormStruct(global_module);

        /* 合并分页事件(search_form) */
        /* 默认查询 */
        if( global_module.default_query===true ){
            doQuery();
        }
    });
}

// 查询表单字段字典项生成
function initSearchFormDicts(){
    //let search_form_region_id = global_module.search_form_region?global_module.search_form_region:"data-search";
    let query_id = global_module.search_form_id;
    if( global_module.dicts ){
        SearchFormFields.init(query_id,global_module.dicts);
    }
}

// 生成修改、新增表单模板
function buildFormStruct(){
    // 必须是on=true的
    //if( _global_module.form_struct && true===_global_module.form_struct.on){
    if( global_module.form_struct && false!==global_module.form_struct.on){
        //Form.init(module.form_struct);
        Form.init( global_module );
    }
}

// 注册查询表单事件
function registerSearchForm(){
    let query_id = global_module.search_form_id?global_module.search_form_id:"data-search";
    /* 初始化查询表单事件 */
    // let search_form_dom = document.querySelector("#search_form_region_id form");
    let search_form_dom = document.querySelector(`#${query_id} form`);
    // 如果没有查询表单则无需注册事件
    if( !search_form_dom ){
        return;
    }
    //let search_form_items = search_form_dom.querySelectorAll("button,input[type=button]");
    let search_form_items = search_form_dom.querySelectorAll("*[_func]");
    search_form_items.forEach(function (item){
        let func = item.getAttribute("_func");
        let func_event = item.getAttribute("_func_event");
        //let name = item.name;
        //let func_event = "";
        //let fn = module.events[name]?module.events[name]:global_module.events[name];
        let fn = global_module.events[func];
        if( fn ){
            // item.addEventListener("click", fn, true);
            // 注册事件,默认就是点击也可以自定义
            item.addEventListener( func_event?func_event:"click",
            ()=>{
                if ( event && event.preventDefault ) {
                    event.preventDefault();
                }
                fn( item,search_form_dom,global_module.search_form )
            }
            //function (){
            //    if ( event && event.preventDefault ) {
            //        event.preventDefault();
            //    }
            //    fn( item,search_form_dom,global_module.search_form )
            //}
            , false);
        }else{
            // reset是不需要注册为单独的方法
            //if( name && "reset"!==name){
            if( name ){
                //alert("ERROR:button[name="+name+"]方法不存在!")
                alert("ERROR:*[_func="+func+"]方法不存在!")
            }
        }
    });
}

// 合并module 用户态的module合并到global_module
function mergeProperty(module){
    //global_module.ctx_path=module.ctx_path;
    global_module.default_query=(true===module.default_query || false===module.default_query)?module.default_query:global_module.default_query;
    global_module.form_struct=module.form_struct?module.form_struct:{};
    // 用户态的property合并到global的property
    MeeUtils.addProperty(module.events,global_module.events);
    MeeUtils.addProperty(module.data_events,global_module.data_events);
    // MeeUtils.addProperty(module.search_form,global_module.search_form);
    MeeUtils.addPropertyOverwritten(module.search_form,global_module.search_form);
    MeeUtils.addPropertyOverwritten(module.api,global_module.api);
    if( module.default_query && (true===module.default_query || false===module.default_query) ){
        global_module.default_query=module.default_query;
    }
    if( module.id_field ){
        global_module.id_field=module.id_field;
    }
    // api fill base
    let has_common = module.api["base"]?true:false;
    for( let key in global_module.api ){
        if( !module.api[key] ){
            // 填充一个base
            if( has_common ){
                global_module.api[key]=module.api["base"]+global_module.api[key];
            }else{
                throw new Error(`api未定义 module.api[${key}]，请检查!`)
            }
        }
    }
    // 合并init_dict,默认为空 这里面定义的字典项最终是从接口过来
    global_module.init_dict=module.init_dict?module.init_dict:[];
    // 合并来自用户自己定义的字典项
    //global_module.dicts=MeeUtils.addProperty(module.dicts,global_module.dicts);
    MeeUtils.addProperty(module.dicts,global_module.dicts);
    // 合并标识id
    global_module.search_form_id=module.search_form_id?module.search_form_id:"data-search";
    global_module.data_list_id=module.data_list_id?module.data_list_id:"data-list";
    global_module.template_id=module.template_id?module.template_id:"template-data-list";

}

// fetch请求并注册table行数据
function load( params ){
    let query_id = global_module.data_list_id;
    global_module.search_form._selected=[]; // 重置为空，否则分页后数据仍保留
    document.querySelector(`#${query_id}`).innerHTML="<div class=\"loading\"></div>";
    FetchUtils.fetchGet(global_module.api.page,params,function (d){
        // 登录超时
        if(!d || 1!==d.status){
           if( d instanceof TypeError ){
             // 一般这种错误是因为刷新过快导致的，这里不再alert
             console.log(d);
           }else{
             //alert("登入超时[new1]"+d);
             alert(d.msg);
           }
           //return window.location.href=app+"/login";
           return;
        }
        // 记录分页数据
        global_module.data=(d.data && d.data.data)?d.data.data:d.data;
        try{
            // 处理分页
            toPageData(d.data);
            // 构建table
            showList(d.data);
            // 注册数据行事件
            registerBodyEvent();
            // 注册底部分页事件
            registerPageEvent();
        }catch(error){
            debugger;
            console.error(error);
        }
    });
}

// search form补充分页信息
function toPageData( d ){
    if( !d ||  ""===d.pageCount || isNaN(d.pageCount) ){
        console.log("不含分页信息...");
        return;
    }
    // 当前第几页
    global_module.search_form.page_no=d.index;
    // 当前页请求记录数
    global_module.search_form.page_size=d.size;
    // 记录总数(非分页)
    global_module.search_form._total=d.total;
    // 总分页数(所有查询数据可以分多少页)
    global_module.search_form._page_count=d.pageCount;
}

// 注册数据体事件
function registerBodyEvent(){
    /* 初始化数据体事件 */
    let query_id = global_module.data_list_id?global_module.data_list_id:"data-list";
    let body_event = document.querySelector(`#${query_id}`).querySelectorAll("table>tbody>tr");
    let idx = 0;
    body_event.forEach(function (tr_item,idx){
        // 自定义事件
        tr_item.querySelectorAll("a[func],button,td[func]").forEach(function (item){
            let name = item.getAttribute("func");
            if(name && name!=="" /*&& !name.startsWith("window.") */ ){
                // support : `delete` or `delete()` or `delete(param1,param2)`
                name = (-1===name.indexOf("(")) ? name : name.substr(0,name.indexOf("("));
                if( name==="" ){
                    name=item.getAttribute("onClick");// 兼容这样的name: <a href="#" onclick="delete">删除</a>
                }
                if( name ){
                    let fn = global_module.data_events[name];
                    if( fn ){
                        // 注册事件
                        item.addEventListener("click",function (){
                            if (event) {  event.preventDefault(); }
                            fn(global_module.data[idx],idx,this)
                        },false)
                    }else{
                        console.log("registerBodyEvent::函数未能注册上:"+name);
                        // alert("ERROR:button[name="+name+"]不存在!")
                    }
                }
            }
        });

        // 单选（首列）
        let checkbox_elem = tr_item.querySelector("td:first-child input[type=checkbox]");
        if(checkbox_elem){
            checkbox_elem.addEventListener("click",
                ()=>{
                       //doSelectOne(this,checkbox_elem)
                       doSelectOne(checkbox_elem)
                     }
                     ,false);
        }

    });
    // 全选
    let checkbox_event = document
        .querySelector(`#${query_id}`)
        .querySelector("table>thead>tr>th:first-child input[type=checkbox]");
    if(checkbox_event){
        checkbox_event.addEventListener("click",function (){
                                doSelectAll(body_event,this)
                            },false);
    }
}

// 全选/非全选
function doSelectAll(body_elem,elem){
    let is_checked = elem.checked;
    // 选中每一行
    body_elem.forEach(function (item,idx){
        let checkbox_item = item.querySelector("td:first-child input[type=checkbox]");
        if(checkbox_item && is_checked){
            // 选中所有
            checkbox_item.checked=true;
            // 添加所有
            global_module.search_form._selected.push(checkbox_item.value);
        }else{
            // 取消所有选中
            checkbox_item.checked=false;
            // 清空已选择的
            global_module.search_form._selected=[];
        }
    });
    // alert(JSON.stringify(global_module.search_form._selected));
}

// 选中一个/非选中一个
function doSelectOne(elem){
    let is_checked = elem.checked;
    if( is_checked ){
        // 选中一个
        // push一个
        global_module.search_form._selected.push(elem.value);
    }else{
        // 取消一个
        let value = elem.value;
        let idx = -1;
        if(value && (idx=global_module.search_form._selected.indexOf(value))>=0){
            // debugger;
            let list = [];
            for(let idx in global_module.search_form._selected ){
               if(  global_module.search_form._selected[idx]===value ){
                 continue;
               }
               list.push(global_module.search_form._selected[idx]);
            }
            // 数组内删除一个
            // global_module.search_form._selected.splice(idx,1);
            global_module.search_form._selected=list;
        }
    }
    //alert(JSON.stringify(global_module.search_form._selected));
}

function registerPageEvent(){
    /* 初始化数据体事件 */
    let query_id = global_module.data_list_id;
    let page_dom = document.querySelector(`#${query_id} #_page-form`);
    if( !page_dom ){
        return;
    }
    let page_item_dom = page_dom.querySelectorAll("button,select");
    // 如果只有一页则禁用所有分页按钮
    if( global_module.search_form._page_count<=1 ){
        page_item_dom.forEach(function (item,idx){
            if( "SELECT"===item.nodeName ){
                // 仍然可以切换分页条数
                let fn = global_module.events["_page_size"];
                item.addEventListener("change",function (){
                    if (event) {  event.preventDefault(); };
                    fn(this);
                },false);
            }else{
                item.setAttribute("disabled","disabled");
            }
        });
        return;
    }
    // 注册所有按钮事件
    page_item_dom.forEach(function (item,idx){
        let name = item.name;
        if(name && name!==""){
            let fn = global_module.events[name];
            if( fn ){
                // 注册事件,如果select需要注册change事件
                if( "_page_size"===name ){
                    item.addEventListener("change",function (){
                        if (event) {  event.preventDefault(); };
                        fn(this);
                    },false)
                }else{
                    item.addEventListener("click",function (){
                        if (event) {  event.preventDefault(); };
                        fn(this);
                    },false)
                }
            }else{
                console.log("registerPageEvent::函数未能注册上:"+name)
            }
        }
    });

    // 动态切换按钮状态调整
    let _page_count = global_module.search_form._page_count;
    let _page_no = global_module.search_form.page_no;
    if( 1===_page_no ){
        // 第一页禁用上一页以及首页 两个按钮
        page_dom.querySelectorAll("button[name=first_page],button[name=previous_page]")
            .forEach(function(item,idx){
                item.setAttribute("disabled","disabled");
            });
    }else if( _page_count===_page_no ){
        // 最后一页禁用 下一页，末页
        page_dom.querySelectorAll("button[name=next_page],button[name=last_page]")
            .forEach(function(item,idx){
                item.setAttribute("disabled","disabled");
            });
    }else{
        // 无此种情况，不处理
    }

}

// 构建table
function showList(data,temp_name){
    let query_id = global_module.template_id?global_module.template_id:"template-data-list";
    let data_list_id = global_module.data_list_id;
    let str = document.querySelector(`#${query_id}`).innerHTML;
    let template = Handlebars.compile(str);
    //let html = template( data.status? data.data:data );
    let html = template( data );
    document.querySelector(`#${data_list_id}`).innerHTML=html;
}

// 数据查询
function doQuery(/*event*/){
//    if ( event && event.preventDefault ){
//        event.preventDefault();
//    }
    //let search_form_region_id = global_module.search_form_region?global_module.search_form_region:"data-search";
    let query_id = global_module.search_form_id;
    /* 初始化查询表单事件 */
    let search_form_dom = document.querySelector(`#${query_id} form`);
    // 合并表单数据
    Object.assign(global_module.search_form,MeeUtils.formToJson( search_form_dom ));
    // first page
    global_module.search_form.page_no=1;
    load( global_module.search_form );
}

// 数据导出
function doExport(/*event*/){
//    if (event){
//        event.preventDefault();
//    }
    //let search_form_region_id = global_module.search_form_region?global_module.search_form_region:"data-search";
    let query_id = global_module.search_form_id;
    /* 初始化查询表单事件 */
    let search_form_dom = document.querySelector(`#${query_id} form`);
    // 合并表单数据
    Object.assign(global_module.search_form,MeeUtils.formToJson( search_form_dom ));
    // 构建URL参数
    let param_str = "page_size=10000&page_no=1&"+FetchUtils.buildParam( global_module.search_form  )

    window.open(global_module.api.export+"?"+param_str);
}

// dialog表单修改数据
function toUpdate( data,idx,dom ){
    //  type,data,submit_func
    Form.invokeForm("update",data,global_module.form_struct,/* async */ function (  dialog_dom,action,data ){
        // 执行表单数据新增(fetch请求)
        // 新增
        //"add":{"api":"/sys_menu2/add","enc":"json","method":"POST","title":"菜单新增"},
        let fc = null;
        if( "POST"===action.method ){
            fc = ("json"===action.enc)?FetchUtils.fetchPostJson:FetchUtils.fetchPostForm;
        }else if( "PUT"===action.method ){
            fc = ("json"===action.enc)?FetchUtils.fetchPutJson:FetchUtils.fetchPutForm;
        }else{
            throw new Error("添加数据暂不支持[PUT、POST]之外的method")
        }
        return fc(action.api,data,function(res){
            if( res && 1===res.status ){
                // 关闭弹窗
                dialog_dom.close();
                // 刷新 如果调doQuery可能需要翻页，这里直接跳转到当前页
                load( global_module.search_form );
            }
            alert(!res?"修改失败,请稍后重试(^,^)！":res.msg?res.msg:res);
            return res;
        });
    });
}

// dialog表单新增数据
function toAdd(data){
    // data可能是数据也可能是按钮事件信息(event)
    if( data.preventDefault ){
        // 阻止默认事件并重置data
        data.preventDefault();
        data={};
    }
    //  type,data,submit_func
    Form.invokeForm("add",data,global_module.form_struct,doAdd);
}

//// 执行表单数据新增(fetch请求)
function doAdd( dialog_dom,action,data ){
    // 执行表单数据新增(fetch请求)
    // 新增
    //"add":{"api":"/sys_menu2/add","enc":"json","method":"POST","title":"菜单新增"},
    let fc = null;
    if( "POST"===action.method ){
        fc = ("json"===action.enc)?FetchUtils.fetchPostJson:FetchUtils.fetchPostForm;
    }else if( "PUT"===action.method ){
        fc = ("json"===action.enc)?FetchUtils.fetchPutJson:FetchUtils.fetchPutForm;
    }else{
        throw new Error("添加数据暂不支持[PUT、POST]之外的method")
    }
    return fc(action.api,data,function(res){
        if( res && 1===res.status ){
            // 关闭弹窗
            dialog_dom.close();
            // 刷新 如果调doQuery可能需要翻页，这里直接跳转到当前页
            load( global_module.search_form );
        }
        alert(!res?"新增失败,请稍后重试(^,^)！":res.msg?res.msg:res);
        return res;
    });
}

// 导入数据
function doImport( data ){
    // data可能是数据也可能是按钮事件信息(event)
    if( data.preventDefault ){
        // 阻止默认事件并重置data
        data.preventDefault();
    }
    //  type,data,submit_func
    Form.invokeForm("import",data,global_module.form_struct,function (  dialog_dom,action,_data ){
        // 导入数据只能走form+post请求方式,这里也必须包装下
        let form_data = new FormData(_data);
        FetchUtils.fetchPostForm(action.api,form_data,function(res){
            if( res && 1===res.status ){
                // 关闭弹窗
                dialog_dom.close();
                // 刷新 如果调doQuery可能需要翻页，这里直接跳转到当前页
                load( global_module.search_form );
            }
            alert(!res?"导入数据失败,请稍后重试(^。^)！":res.msg?res.msg:res);
        });
    });
}

/*
  行删除
  对于表格体的数据的操作，所有的事件均会有这三个参数传入:
      data: 当前行数据(object)
      idx:  当前行所在所有list数据的索引位置，0表示第一行
      elem: 这个是表单参数
*/
function toDelete( data,idx,elem ){
  if (!confirm("您确认删除此项数据？删除后将不可恢复！")){
          return;
  }
  let primary_field = global_module.id_field;
  let primary_value = global_module.data[idx][primary_field]
  if( !primary_field || !primary_value ){
    alert("主键字段未定义或主键为空(*m*)");
    return;
  }

  // 01.删除之前做的事情
  let before_delete_func = global_module.data_events.before_delete_func;
  if( before_delete_func && true!==before_delete_func( data,idx,elem ) ){
      console.log("before_delete_func执行结果为false，在此终止");
      return;
  }

  let param = {};
  param[primary_field]=(""+primary_value);
  FetchUtils.fetchDelete(global_module.api["delete"]+`?${primary_field}=${primary_value}`,param,function(res){
    // 02.删除之后做的事情
    let after_delete_func = global_module.data_events.after_delete_func;
    if( after_delete_func && true!==after_delete_func( data,idx,elem,res ) ){
        console.log("after_delete_func执行结果为false，在此终止");
        return;
    }
    if( res && 1===res.status){
        // 如果调doQuery可能需要翻页，这里直接跳转到当前页
        load(global_module.search_form);
    }else{
        alert(!res?"删除失败,请稍后重试(^,^)！":res.msg?res.msg:res);
    }
  });
}

/**
*   批量删除
*   参数1.dom       点击的dom对象
*   参数2.form_dom  dom所在的form对象
*   参数3.search_form   查询表单数据
**/
function deleteBatch( dom,form_dom,search_form ){
    if (!confirm("您确认删除此项数据？删除后将不可恢复！")){
        return;
    }
    if( !search_form || !search_form._selected || search_form._selected.length==0 ){
        alert("批量删除至少选中一个");
        return;
    }
    let param = search_form._selected;
    FetchUtils.fetchDelete(global_module.api.delete_batch,param,function( res ){
        if( res && 1===res.status){
            load(global_module.search_form);
        }else{
            alert(!res?"批量删除失败,请稍后重试(^,^)！":res.msg?res.msg:res);
        }
    });
}

// =========== 分页函数::START ===========
// 首页
function firstPage(elem){
    global_module.search_form.page_no=1;
    load(global_module.search_form);
};

// 末页
function lastPage(elem){
    global_module.search_form.page_no=global_module.search_form._page_count;
    load(global_module.search_form);
}

// 上一页
function previousPage(elem){
    if( global_module.search_form.page_no<=1 ){
        alert("已经是第一页了（@,@）")
        return;
    }
    // -1
    global_module.search_form.page_no--;
    load(global_module.search_form);
};

// 下一页
function nextPage(elem){
    if( global_module.search_form.page_no >= global_module.search_form._page_count ){
        alert("已经是最后一页了（*m*）");
        return;
    }
    // +1
    global_module.search_form.page_no++;
    load(global_module.search_form);
};

// 分页条数变化
function pageSize( elem ){
    global_module.search_form.page_size=elem.value;
    load(global_module.search_form);
}
// =========== 分页函数::END ===========


export {init,showList,load,doQuery,toAdd,toUpdate,global_module}
