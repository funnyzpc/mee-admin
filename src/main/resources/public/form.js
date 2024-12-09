//import * as FetchUtils  from "../fetch_utils.js"
import * as MeeUtils  from "./mee_utils.js"

/*
* dialog 表单构造( 添加和修改 ),此json定义在各个业务模块的module中
*/
var form_struct_example = {
                // form id
                id:"sys_menu2_edit_form",
                // 宽度
                width:"",
                // 表单提交地址
                actions:{
                    // 新增
                    // show_before_func(type,action,data): 定义表单生成后与表单提交前的事件(事件返回false则不会执行后续代码)
                    // show_after_func(type,action,dialog_dom): 定义表单生成后与表单提交前的事件(事件返回false则不会执行后续代码)
                    // submit_before_func(type,action,dialog_dom): 定义表单生成后与表单提交前的事件(事件返回false则不会执行后续代码)
                    // submit_after_func(type,action,dialog_dom):  定义表单提交后的事件(事件返回false则不会执行后续代码)
                    "add":{"api":"/sys_menu2/add","enc":"json","method":"POST","title":"菜单新增",/*"show_before_func":function (){},"submit_before_func":function(){},"submit_after_func":function(){}*/ },
                    // 修改
                    "update":{"api":"/sys_menu2/update","enc":"json","method":"POST","title":"菜单修改"}
                },
                // 生成模板(这里的数据实在init时生成,进入页面后只生成一次)
                templates:{"add":"","update":""},
                fields:{
                        // 通用表单模板，如果没有定义add或update则默认使用这个,如果新增或更新表单存在差异则建议单独定义add以及update
                        "common":[
                            {name:"id",type: "hidden",label:"主键"},
                            {name:"user_id",label:"用户编号",col:4,attrs:{required:"required"},attr:{readonly:"readonly"}},
                            {name:"nick_name",label: "昵称",col:4,/*attr:{readonly:"readonly"},*/nl:true},
                            {name:"user_name",label: "用户登陆名",col:4,attrs:{required:"required"}},
                            {name:"email",label: "email",col:4,attrs:{required:"required"},nl:true},
                            {name:"password",label: "密码",col:4,attrs:{required:"required"}},
                            // {name:"role_id",label: "角色编号",col:4,attrs:{required:"required"},nl:true},
                            {name:"status",label: "用户状态",type:"select",col:4,attrs:{required:"required"},attr:{"data-dict":"user_status"}},
                            ],
                        // 新增表单模板,对应的提交地址为 action=add
                        "add":[
                            {name:"id",type: "hidden",label:"主键"},
                            {name:"user_id",label:"用户编号",col:6,attrs:{required:"required"},attr:{readonly:"readonly"}},
                            {name:"nick_name",label: "昵称",col:6,/*attr:{readonly:"readonly"},*/nl:true},
                            {name:"user_name",label: "用户登陆名",col:6,attrs:{required:"required"}},
                            {name:"email",label: "email",col:6,attrs:{required:"required"},nl:true},
                            {name:"password",label: "密码",col:6,attrs:{required:"required"}},
                            // {name:"role_id",label: "角色编号",col:4,attrs:{required:"required"},nl:true},
                            {name:"status",label: "用户状态",type:"select",col:6,attrs:{required:"required"},attr:{"data-dict":"user_status"}},
                            ],
                        // 更新表单模板,对应的提交地址为 action=add
                        "update":[
                            {name:"id",type: "hidden",label:"主键"},
                            {name:"user_id",label:"用户编号",col:4,attrs:{required:"required"},attr:{readonly:"readonly"}},
                            {name:"nick_name",label: "昵称",col:4,/*attr:{readonly:"readonly"},*/nl:true},
                            {name:"user_name",label: "用户登陆名",col:4,attrs:{required:"required"}},
                            {name:"email",label: "email",col:4,attrs:{required:"required"},nl:true},
                            {name:"password",label: "密码",col:4,attrs:{required:"required"}},
                            // {name:"role_id",label: "角色编号",col:4,attrs:{required:"required"},nl:true},
                            {name:"status",label: "用户状态",type:"select",col:4,attrs:{required:"required"},attr:{"data-dict":"user_status"}},
                            ],
                        }
                };

// 因为各个模块之间需要隔离，所以form.js中不存在状态
//var _form_struct={};

// 初始化表单 (模板数据)
// form struct (表单结构)
function init( _global_module ){
    // 这里的form_struct 方便后续invoker
    let _form_struct=_global_module.form_struct;
    let _dicts = _global_module.dicts;
    // 验证表单(检查缺失字段)
    checkFormStruct(_form_struct);

    // 构建表单
    for( let key in _form_struct.actions ){
        let action = _form_struct.actions[key];
        let field_list = _form_struct.fields[key];
        let form_str = buildForm(key,action,field_list,_dicts);
        _form_struct.templates[key]=form_str;
    }

//    // 这里保存状态方便后续invoker
//    _form_struct=form_struct;
}

// 检查传入的模板
function checkFormStruct( form_struct ){
    if( !form_struct ){
        throw new Error('模板结构为空，请检查!')
    }
    // id action fields
    if( !form_struct.id || !form_struct.actions || !form_struct.fields ){
        throw new Error('模板结构必要参数为空[id,actions,fields]!')
    }
    // {"api":"/sys_menu2/add","enc":"json","method":"POST","title":"菜单新增"},

    // check action
    let count = 0;
    let keys=[];
    for( let key in form_struct.actions ){
        count++;
        keys.push(key);
        let item = form_struct.actions[key];
        if( !item.api ||!item.enc ||!item.method ||!item.title ){
            throw new Error(`模板结构必要参数为空 Struct.action.${key}=>[api、enc、method、title]`)
        }
        form_struct.actions[key].method=form_struct.actions[key].method.toUpperCase();
    }
    if( count===0 ){
        throw new Error('模板结构必要参数为空:action')
    }

    // check fields
    count = 0;
    for( let key in form_struct.fields ){
        count++;
        let item_list = form_struct.fields[key];
        for( let idx in item_list ){
            if( !item_list[idx] || !item_list[idx].name || !item_list[idx].label ){
                throw new Error(`模板结构必要参数为空 Struct.fields.${key}[${idx}]=>[name、label]`)
            }
        }
    }
    if( count===0 ){
        throw new Error('模板结构必要参数为空:fields')
    }

//    // 修真字段配置 [common to add OR common to update]
//    if( form_struct.fields["common"] ){
//      // 根据 form_struct::actions 构建 form_struct::fields结构
//        for( let idx in keys ){
//            // 不存在则填充一个common的fields
//            if( !form_struct.fields[keys[idx]] ){
//                form_struct.fields[keys[idx]]=form_struct.fields["common"]
//            }else{
//                throw new Error(`模板结构.action.${keys[idx]}项不在fields.${keys[idx]}`);
//            }
//        }
//    }

    // 根据 form_struct::actions 构建 form_struct::fields结构
    let has_common = (form_struct.fields["common"] && form_struct.fields["common"][0])?true:false;
    for( let idx in keys ){
        // 这样做保证了每个action项都有common的field可以使用，如果只定义了actions且没有没有定义任何fields项时 一定要抛错！
        if( !form_struct.fields[keys[idx]] ){
            if( has_common ){
                form_struct.fields[keys[idx]]=form_struct.fields["common"]
            }else{
                throw new Error(`模板结构.action.${keys[idx]}项不在fields.${keys[idx]}`);
            }
        }
    }

    // 补充 dialog_id 以及 form_id
    let id_prefix = form_struct.id?form_struct.id:"";
    for( let key in form_struct.actions ){
        form_struct.actions[key].dialog_id=id_prefix+"_"+key+"_dialog";
        form_struct.actions[key].form_id=id_prefix+"_"+key+"_form";
    }

}

// 执行表单渲染
function invokeForm( type,data,_form_struct,submit_func ){
    // check
    if( !type || !submit_func ){
        throw new Error("必要参数不可为空[type、submit_func]");
    }

    // 渲染模板数据
    let action = _form_struct.actions[type];
    let dialog_str = _form_struct.templates[type];
    let dialog_id = _form_struct.actions[type].dialog_id;
    if( !dialog_str ){
        throw new Error(`模板未初始化[${type}]`);
    }

    // 移除old form,这里只移除当前id的dialog,也可以在点关闭或成功保存后直接移除(.close())
    //document.querySelector("#data-dialog").innerHTML="";
    let dialog_old_dom = document.querySelector("#"+dialog_id);
    if( dialog_old_dom ){
        dialog_old_dom.remove();
    }

    // 01.模板生成前做的事情
    let show_before_func = action.show_before_func;
    if( show_before_func && true!==show_before_func(type,action,data) ){
        console.log("show_before_func执行结果为false，在此终止");
        return;
    }

    // 写入dom至body
    let template = Handlebars.compile(dialog_str);
    let html = template( data );
    // ‘beforeend’: 插入元素内部的最后一个 子节点 之后 @see http://tcatche.site/2018/03/dom_native_elements_and_node_modify/
    let data_dialog = document.querySelector("#data-dialog");
    data_dialog.insertAdjacentHTML("beforeend",html);

    // 注册字段事件
    let dialog_dom=data_dialog.querySelector("#"+dialog_id);
    registerFieldFunc( dialog_dom,_form_struct,type,data?data:{} );

    // 02.模板生成后做的事情
    let show_after_func = action.show_after_func;
    if( show_after_func && true!==show_after_func(type,action,data,dialog_dom) ){
        console.log("show_after_func执行结果为false，在此终止");
        return;
    }

    // 注册表单事件(保存/更新、取消、验证字段)
    dialog_dom.querySelector("button[name=_dialog_submit]")
        .addEventListener("click",function (){
            if (event) {
              event.preventDefault();
            }

            // 03.提交之前做的事情
            let submit_before_func = action.submit_before_func;
            if( submit_before_func && true!==submit_before_func(type,action,dialog_dom) ){
                console.log("submit_before_func执行结果为false，在此终止");
                return;
            }
            // 执行提交
            preSubmitData( dialog_dom,_form_struct,type,submit_func );
            // 04.提交之后做的事情
            let submit_after_func = action.submit_after_func;
            if( submit_after_func && true!==submit_after_func(type,action,dialog_dom) ){
                console.log("submit_after_func执行结果为false，在此终止");
                return;
            }

           },false);
    dialog_dom.querySelector("button[name=_dialog_cancel]")
        .addEventListener("click",function (){
            if (event) {
              event.preventDefault();
            }
            //dialog_dom.close();
            dialog_dom.remove();
           },false);
    // 显示dialog
    dialog_dom.showModal();
}

// 注册字段函数(可以给每个输入框或者选择框注册函数)
function registerFieldFunc( dialog_dom,form_data,type,data ){
  let fields = form_data.fields[type];
  for( let i=0;i<fields.length;i++ ){
    let func = fields[i].func;
    if( func ){
        func( dialog_dom,type,data );
    }
  }
}

// 预新增数据(表单点保存) dialog_dom,_form_struct,type,data submit_func
function preSubmitData( dialog_dom,form_struct,type,submit_func ){
    let action = form_struct.actions[type];

    // 校验表单
    let _form=dialog_dom.querySelector("form");
    if( false===_form.reportValidity() ){
        console.log("表单校验不通过！");
        return ;
    }

    // 序列化数据(可能是json也可能是FormData)
    let data = serialForm( _form,action )

    // 接口预处理(表单,请求地址,函数,参数,beforeNext)
    if( action.beforeFunc && false===action.beforeFunc(dialog_dom,data) ){
        // 可能是数据预处理也可能是其他操作
        console.log("预提交不通过...")
        return;
    }
    console.log("fetch请求参数为:"+JSON.stringify(data));
    // 交给fetch处理(请求地址,函数,参数,beforeNext)
    submit_func(dialog_dom,action,data,function (res){
        if( action.afterFunc ){
            action.afterFunc(dialog_dom,data,res);
        }
    });
}

// 获取待提交数据(可能是json也可能是FormData)
function serialForm( _form_dom,action ){
  let enc = action.enc?action.enc.toUpperCase():"JSON";
  if( "JSON"===enc ){
    return MeeUtils.formToJson(_form_dom);
    // 如果是get请求是否要拼接请求param? TODO...
  }else{
    // 这里只提交form的dom本体后续会在fetch请求时包装为FormData(new FormData(form))
    return _form_dom;
  }
}

// 构建表单
// function buildForm( form_data,type ){
function buildForm( type,action,field_list,dicts ){
    // header
    let header_str=buildHeader( type,action );
    // body
    let body_str=buildBody( type,action,field_list,dicts );
    // footer
    let footer_str=buildFooter( type );
    // 主结构
    return buildDialog( action,header_str,body_str,footer_str,field_list.length )
}

// 生成主结构
// function buildDialog( form_data,type,header_str,body_str,footer_str){
function buildDialog( action,header_str,body_str,footer_str,field_count){
    let dialog_width=(action.width?action.width:"");
    let dialog_top= action.top?action.top:(field_count>10?"-8%":"-30%");
    let dialog_id=action.dialog_id;
    let form_id=action.form_id;
    let form_method=(action.method?action.method:"POST");
    return `<dialog id="${dialog_id}" style="width:${dialog_width};top:${dialog_top}">
                <form method="${form_method}" id="${form_id}">
                    <div class="dialog-main">
                        <div class="dialog-header">
                            ${header_str}
                        </div>
                        <div class="dialog-body">
                            ${body_str}
                        </div>
                        <div class="dialog-footer">
                            ${footer_str}
                        </div>
                    </div>
                </form>
            </dialog>`;
}

// 构建表单各个field(type=[input,select,radio,checkbox])的属性
function buildAttrs(field){
  if( !field || !field.attrs ){
    return "";
  }
  let attr_str =" ";
  for( let key in field.attrs ){
    let value = field.attrs[key];
    attr_str=attr_str+( ` ${key}="${value}" `)
  }
  //  console.log(attr_str);
  return attr_str;
}

// 必填项着重星号
function buildRequiredStr( field ){
  if( !field || !field.attrs ){
    return "";
  }
  let req_value = field.attrs.required;
  if( "required"===req_value || true===req_value ){
    return "<span style='color:red;'>*</span>";
  }
  return "";
}

// 构建字段信息
// function buildBody( form_data,type ){
function buildBody( type,action,field_list,dicts ){
  let field_str = "";//"<div class=\"row\">";
  // dom是否关闭(为true则签名一个标签已经关闭，现在需要新起一个strart标签 )
  let col_count=0;
  for( let i=0;i<field_list.length;i++  ){
    let field = field_list[i];
    let dict = field.dict_type?dicts[field.dict_type]:{};
    let attrs = buildAttrs(field);
    let required_str = buildRequiredStr(field);
    let field_type =  field.type;
    let _title_elem = field.title && ""!==field.title.trim ? `title="${field.title}" style="color:#1717a0;"` : "";

    // 第一个
    if( 0===i ){
        // 再起一个row的div标签
        field_str =  `<!-- body start --><div class="row">`;
    }

    // 隐藏的字段直接关闭dom 且不计算col
    if( "hidden"===field_type){
        field_str = field_str + `<input type="hidden" name="${field.name}" value="{{${field.name}}}" ${attrs} field_idx="${i}"/>`;
        continue; // 直接结束
    }

    //  默认只占一半
    let field_col = field.col?field.col:6;

    // 如果 col=12 (一整行) 则需要关闭row
    if( col_count===12 || (col_count+field_col)>12 ){
        // 结束row 并创建一个 row start dom
        field_str=field_str+` </div> <div class="row"> `;
        // 重置 col
        col_count=0;
    }

    switch(field_type){
        case "radio":
           field_str = field_str + buildRadio( i,field ,field_col,attrs,dict,required_str );
           break;
        case "checkbox":
           field_str = field_str + buildCheckbox( i,field ,field_col,attrs,dict,required_str );
           break;
        case "textarea":
           field_str = field_str + buildTextarea( i,field ,field_col,attrs,required_str );
           break;
        case "select":
          field_str = field_str + buildSelect( i,field ,field_col,attrs,dict,required_str );
          break;
        case "datetime-local":
          field_str = field_str + `
                       <div class="col-sm-${field_col}">
                           <label class="control-label" ${_title_elem} >${field.label} ${required_str}</label>
                           <input type="datetime-local" step="1" name="${field.name}" class="form-control" style="background-color:rgb(252,252,252);height: 33px;" value="{{${field.name}}}" ${attrs} field_idx="${i}">
                       </div>`;
          break;
        case "file":
           field_str = field_str + buildInputFile( i,field ,field_col,attrs,required_str );
           break;
        default:
           // 可见的field
           let _default_type = (field_type?field_type:"text");
           field_str = field_str + `
                     <div class="col-sm-${field_col}">
                         <label class="control-label" ${_title_elem} >${field.label} ${required_str}</label>
                         <input type="${_default_type}" name="${field.name}" class="form-control input-sm" value="{{${field.name}}}" ${attrs} field_idx="${i}">
                     </div>`;
           break;
    }
    // 计算col
    col_count = col_count+field_col;
  }
  return (field_str+`</div><!-- body end -->`);
}

// 构建下拉框
function buildSelect( idx,field ,field_col,attrs,dict,required_str){
    let option_str = "<option value=''>--</option>";
    let _title_elem = field.title && ""!==field.title.trim ? `title="${field.title}" style="color:#1717a0;"` : "";
    if( dict ){
        for( let v in dict ){
          let l = dict[v].l;
          option_str=option_str+`<option value="${v}" {{#equal ${field.name} "${v}"}}selected{{/equal}} title="${v} ${l}">${l}</option>`;
        }
    }
    return `<div class="col-sm-${field_col}">
            <label class="control-label" ${_title_elem} >${field.label} ${required_str}</label>
            <div style="width:100%;">
                <select name="${field.name}" value="{{${field.name}}}" field_idx="${idx}" ${attrs} class="form-control">
                    ${option_str}
                </select>
            </div>
        </div>
        `;
}

// 构建单选框
function buildRadio( idx,field ,field_col,attrs,dict,required_str ){
    // 之际从dicts数组拿，拿不到就是空( "" )
    let radio_str = ""
    let _title_elem = field.title && ""!==field.title.trim ? `title="${field.title}" style="color:#1717a0;"` : "";
    if( dict ){
        for( let v in dict ){
          let l = dict[v].l;
          radio_str=radio_str+`<input type="radio" name="${field.name}" value="${v}" field_idx="${idx}" ${attrs} {{#equal ${field.name} "${v}"}}checked{{/equal}} title="${v} ${l}">${l} &nbsp;&nbsp;`;
        }
    }
    return `<div class="col-sm-${field_col}">
                <label class="control-label" ${_title_elem} >${field.label} ${required_str}</label>
                <div style="width:100%;">
                    ${radio_str}
                </div>
            </div>`;
}

// 构建多选框
function buildCheckbox( idx,field ,field_col,attrs,dict,required_str ){
    // 之际从dicts数组拿，拿不到就是空( "" )
    let checkbox_str = ""
    let _title_elem = field.title && ""!==field.title.trim ? `title="${field.title}" style="color:#1717a0;"` : "";
    if( dict ){
        for( let v in dict ){
          let l = dict[v].l;
          //    <input type="checkbox" aria-hidden="false" name="多选" value="2">香蕉</input>
          checkbox_str=checkbox_str+`<input type="checkbox" name="${field.name}" value="${v}" field_idx="${idx}" ${attrs} {{#equal ${field.name} "${v}"}}checked{{/equal}} title="${v} ${l}">${l}</input>&nbsp;&nbsp;`;
        }
    }
    return `<div class="col-sm-${field_col}">
                <label class="control-label" ${_title_elem} >${field.label} ${required_str}</label>
                <div style="width:100%;">
                    ${checkbox_str}
                </div>
            </div>`;
}

// 构建文本域
function buildTextarea( idx,field ,field_col,attrs,required_str ){
    let _title_elem = field.title && ""!==field.title.trim ? `title="${field.title}" style="color:#1717a0;"` : "";
    return `<div class="col-sm-${field_col}">
        <label class="control-label" ${_title_elem} >${field.label} ${required_str}</label>
        <textarea type="text" name="${field.name}" class="form-control input-sm" value="{{${field.name}}}" ${attrs} field_idx="${idx}"></textarea>
        </div>`;
}

// 构建文本输入域
function buildInputFile( idx,field ,field_col,attrs,required_str ){
    let _title_elem = field.title && ""!==field.title.trim ? `title="${field.title}" style="color:#1717a0;"` : "";
    return `<div class="col-sm-${field_col}">
                 <label class="control-label" ${_title_elem} >${field.label} ${required_str}</label>
                 <input type="file" name="${field.name}" class="form-control" value="{{${field.name}}}" ${attrs} field_idx="${idx}" >
             </div>`;
}

/**
*   构建头信息
*   form_data 表单模板
*   type      指定模板类型
*/
function buildHeader( type,action ){
    return `  <h4>${action.title}</h4> `;
}

/**
*   构建脚信息
*   form_data 表单模板
*   type      指定模板类型
*/
function buildFooter( type ){
    let type_text = "提交";
    switch( type ){
        case "add":
            type_text="新增";
            break;
        case "update":
            type_text="更新";
            break;
        case "import":
            type_text="导入";
            break;
        default:
            type_text="提交";
            break;
    }
    let footer_str = `
                        <button type="button" name="_dialog_submit" class="btn btn-sm btn-primary">${type_text}</button>
                        &nbsp;
                        <button type="button" name="_dialog_cancel" class="btn btn-sm btn-light">关闭</button>
                `;
    return footer_str;
}

export { init,invokeForm }
