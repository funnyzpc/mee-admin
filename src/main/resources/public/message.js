import * as MeeUtils  from "./mee_utils.js"

/**
*   简单提示框，基于dialog
*/
var _example={
    // 可选，如果一个页面有多个的时候为非必填
    "id":"dialog_id",
    // 可选，默认为"提示"，内容可以为文字也可以为html
    "title":"标题",
    // 必填，可以是html也可以是一段文字
    "content":"<p>hello</p>",
    // 这个也是可选，默认为"好"，仅仅只是改变按钮文字而已，点击之后会自动关闭
    "btn_text":"好/关闭",
    // 可选，如果写了则必须有auto_close_wait
    "auto_close":true,
    // 可选，见auto_close
    "auto_close_wait":3000,
    // 可选,宽度
    "width":"200px",
    // 可选，数据，如果有则会被渲染
    "data":{}
}

// 显示dialog
function showDialog( param ){
    if( !param || !param.content ){
        alert("未配置必要参数[content]");
        throw new Error("对于查询表单字典查询项目,字段[content]不可为空！")
    }
    let dialog_id = param.dialog_id?param.dialog_id:("d"+new Date().getTime());
    let width = param.width?param.width:"";
    let _top = param.top?param.top:"-30%";
    let auto_close  = (param.auto_close && true===param.auto_close)?true:false;
    let auto_close_wait = param.auto_close_wait?param.auto_close_wait:3000;
    let title = param.title?param.title:"";
    let content = param.content;
    let close_name = param.close_name?param.close_name:"关闭";
    let dialog_str = buildDom( dialog_id,width,title,content,close_name,_top );
    let _data = param.data?param.data:{};

    // 写入dom至body
    let template = Handlebars.compile( dialog_str );
    let html = template( _data );
    // ‘beforeend’: 插入元素内部的最后一个 子节点 之后 @see http://tcatche.site/2018/03/dom_native_elements_and_node_modify/
    // let data_dialog = document.querySelector("#data-dialog");
    let data_dialog = document.querySelector("body");
    data_dialog.insertAdjacentHTML("beforeend",html);

    // 注册关闭按钮
    let dialog_dom = document.querySelector("#"+dialog_id);
    dialog_dom.querySelector("button[name=_dialog_cancel]")
        .addEventListener("click",function (){
            // 直接移除，避免dom id冲突
            dialog_dom.remove();
        },false);

    // 自动关闭
    if( true===auto_close ){
        autoClose( dialog_dom,auto_close_wait );
    }

    // 显示dialog
    dialog_dom.showModal();

}

// 自动关闭dialog,自动关闭默认是3秒
function autoClose( dialog_dom,auto_close_wait ){
    setTimeout(function(){
       dialog_dom.remove();
    }, auto_close_wait);
}

// 构建dom
function buildDom( dialog_id,width,title,content,close_name,_top ){
    return `
        <dialog id="${dialog_id}" style="width:${width};top:${_top}" >
            <div class="dialog-main">
                <div class="dialog-header">
                    ${title}
                </div>
                <div class="dialog-body">
                    ${content}
                </div>
                <div class="dialog-footer row">
                    <div>
                        <button type="button" name="_dialog_cancel" class="btn btn-sm btn-primary">${close_name}</button>
                    </div>
                </div>
            </div>
        </dialog>
    `;
}

export { showDialog }