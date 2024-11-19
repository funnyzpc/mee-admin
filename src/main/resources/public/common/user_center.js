import * as Common  from "../common.js"
import * as MeeUtils  from "../mee_utils.js"
import * as FetchUtils  from "../fetch_utils.js"
import { enc }  from "../../static/js/chaos.js"

var module={
    default_query:true,
    id_field:"id",
    api:{
        "base": app+"/common/user_center",
    },
    form_struct:{
        // 关闭
        on:false,
    },
    init_dict:["sys_user_status","sys_user_gender","del_flag"],
};


// 解锁登录
function updateInfo( ){
    let form_dom = document.querySelector(".user_info form");
    let param = MeeUtils.formToJson( form_dom );
    //console.log(param);
    if( !param || !param.id ) {
        alert("必要参数为空！");
        return;
    }
    FetchUtils.fetchPutJson(app+"/common/user_center/update_user_info",param,function(res){
        if( !res || res.status!==1 ){
            alert(res?res.msg:"解锁操作失败");
            return;
        }
        alert(res.msg);
        //刷新页面重新登陆
        window.location.reload();
    });
}

// 更新密码
function editPwd(){
    let form_dom = document.querySelector(".edit_pwd form");
    let param = MeeUtils.formToJson( form_dom );
    //console.log(param);
    if( !param || !param.old_pwd || !param.pwd1 || !param.pwd2 ) {
        alert("必要参数为空！");
        return;
    }
    param.old_pwd=enc(param.old_pwd);
    param.pwd1=enc(param.pwd1);
    param.pwd2=enc(param.pwd2);
    //console.log(param);
    FetchUtils.fetchPutJson(app+"/common/user_center/update_user_pwd",param,function(res){
        if( !res || res.status!==1 ){
            alert(res?res.msg:"解锁操作失败");
            return;
        }
        alert(res.msg);
        //刷新页面重新登陆
        window.location.reload();
    });
}

// window 默认函数
var _ct = ['user_info','edit_pwd'];
window.to_content=function(idx){
    let switch_dom = document.querySelectorAll(".u_switch ul li a");
    let content_dom =document.querySelectorAll(".u_content div");
    // 切换标签颜色 及 切换内容
    for( let i=0;i<_ct.length;i++ ){
        if( idx === i){
            switch_dom[i].style="color:white;background-color:black;display:block;";
            content_dom[i].style.display="";
        }else{
            switch_dom[i].style="";
            content_dom[i].style.display="none";
        }
    }
}

// 初始化通用模块
function init(){
    Common.init(module);
}

export { init,updateInfo,editPwd }
