import * as Common  from "../common.js"
import * as FetchUtils  from "../fetch_utils.js"
import { enc }  from "../../static/js/chaos.js"
import * as Message  from "../message.js"


// 表单参数 这个参数如有表单则必须定义，同时init后会自动将global_opts中的参数合并到当前module下
var module={
    /* 调用Common.init后是否调用一次查询 */
    default_query:true,
    /* 分页表单数据(可选) */
    search_form: {"page_no":1,"page_size":15,"_selected":[]},
    /* 主键字段，删除或者更新的where条件,这个不定义则默认为 id  */
    id_field:"id",
    /* 当前业务模块(页面)扩展函数 id=search-form的扩展事件 */
    events:{  },
    /* id=data-list的行事件,此类事件会自动传入(当前行数据,当前行索引,当前dom对象),也可默认为空 */
    data_events:{ "change_status":changeStatus,"unlock":unlock,"reset_pwd":resetPwd ,"role_info":roleInfo},
    /* 通用api接口，一般包含增删改查四大接口,给Common内的增删改查之使用，使用时一般加上 module.ctx_path 前缀  */
    /* api必须定义完整的增删改查四大api或者定义一个base api */
    api:{
        /* 通用地址,如果 page、add、del、mod未定义测会通过此配置生成一个默认的增删改查(base+"/page",base+"/delete"...) */
        "base": app+"/sys/sys_user",
    },
    /* 列表查询请求，如果执行的是Common的相关请求则数据会回写到此字段内*/
    data:[],
    // 表单模板
    form_struct:{
        // 指定开启
        on:true,
        // form id，这只是一个prefix后面会加上actions.type
        id:"sys_user_form",
        actions:{
            "add":{"api":app+"/sys/sys_user/add","enc":"json","method":"POST","title":"用户新增","width":"600px","beforeFunc":addBeforeFunc},
            // 修改
            "update":{"api":app+"/sys/sys_user/update","enc":"json","method":"PUT","title":"用户修改","width":"600px"},
            // 导入
            "import":{"api":app+"/sys/sys_user/import","enc":"form","method":"POST","title":"导入数据","width":"400px"}
        },
        // 生成模板(这里的数据实在init时生成,进入页面后只生成一次)
        templates:{"add":"","update":""},
        fields:{
            "add":[
                  {name:"id",type: "hidden",label:"表ID/用户ID"},
                  //{name:"dept_id",label:"部门ID(保留字段暂不使用)",col:6,attrs:{required:false} },
                  {name:"user_name",label:"用户名称",col:6,attrs:{"required":"required","placeholder":"eg: 秋风梧"} },
                  {name:"nick_name",label:"用户昵称",col:6 },
                  {name:"gender",label:"性别",type:"select",col:6,"dict_type":"sys_user_gender" },
                  {name:"phone",label:"手机号码",type:"text",col:6,attrs:{"pattern":"^\\d{11}$","placeholder":"必须为11位数字","title":"必须为11位数字"} },
                  {name:"email",label:"用户email",type:"email",col:6,attrs:{"placeholder":"eg: zhangSun@mee.com"} },
                  //{name:"password",label:"用户密码",col:6,attrs:{required:true} },
                  //{name:"register_date",label:"用户注册时间",col:6,attrs:{required:true} },
                  //{name:"last_login_date",label:"最后登录时间",col:6,attrs:{required:false} },
                  //{name:"pwd_reset_time",label:"密码最后重置时间",col:6,attrs:{required:false} },
                  {name:"status",label:"状态",type:"select",col:6,attrs:{required:"required"},"dict_type":"sys_user_status" },
                  {name:"pwd1",label:"设置密码",col:6,attrs:{required:"required",minlength:"5"},"title":"密码不可少于5位!"},
                  {name:"pwd2",label:"确认密码",col:6,attrs:{required:"required",minlength:"5"},"title":"密码不可少于5位!"},

                  // {name:"del_flag",label:"删除标记1.正常 0.删除",col:6,attrs:{required:false} },
                  // {name:"create_time",label:"创建时间",col:6,attrs:{required:false} },
                  // {name:"create_by",label:"创建人",col:6,attrs:{required:false} },
                  // {name:"update_time",label:"创建时间",col:6,attrs:{required:false} },
                  // {name:"update_by",label:"创建人",col:6,attrs:{required:false} }
                ],
            "update":[
                  {name:"id",type: "hidden",label:"表ID/用户ID"},
                  //{name:"dept_id",label:"部门ID(保留字段暂不使用)",col:6,attrs:{required:false} },
                  {name:"user_name",label:"用户名称",col:6,attrs:{readonly:"readonly",required:"required","placeholder":"eg: 秋风梧"} },
                  {name:"nick_name",label:"用户昵称",col:6},
                  {name:"gender",label:"性别",type:"select",col:6,"dict_type":"sys_user_gender" },
                  {name:"phone",label:"手机号码",type:"text",col:6,attrs:{"pattern":"^\\d{11}$","placeholder":"必须为11位数字","title":"必须为11位数字"} },
                  {name:"email",label:"用户email",type:"email",col:6,attrs:{"placeholder":"eg: zhangSun@mee.com"} },
                  //{name:"password",label:"用户密码",col:6,attrs:{required:true} },
                  {name:"register_date",label:"用户注册时间",col:6,attrs:{readonly:"readonly"} },
                  {name:"last_login_date",label:"最后登录时间",col:6,attrs:{readonly:"readonly"} },
                  {name:"pwd_reset_time",label:"密码最后重置时间",col:6,attrs:{readonly:"readonly"}},
                  {name:"status",label:"状态",type:"select",col:6,attrs:{required:"required"},"dict_type":"sys_user_status" },
                  {name:"del_flag",label:"删除",type:"select",col:6,attrs:{disabled:"disabled",readonly:"readonly"},"dict_type":"del_flag" },
                  {name:"create_time",label:"创建时间",col:6,attrs:{readonly:"readonly"} },
                  {name:"create_by",label:"创建人",col:6,attrs:{readonly:"readonly"} },
                  {name:"update_time",label:"创建时间",col:6 ,attrs:{readonly:"readonly"} },
                  {name:"update_by",label:"创建人",col:6,attrs:{readonly:"readonly"} },
                ],
             "import":[
                  {name:"name",label:"用户名称",col:12,type:"text",attrs:{required:"required"} },
                  {name:"file",label:"用户昵称",col:12,type:"file",attrs:{required:"required"}},
                ],
            }
    },
    // 定义字典项，init时会自动到后台查询字典
    init_dict:["sys_user_status","sys_user_gender","del_flag"],
    // 定义字典，最终会合并到common的dicts中，init_dict中的字典项最终也会合并到dicts
    //dicts:{"sys_job_status2":{"0":{"dict_id":9,"v":"0","detail_id":14,"l":"正常"},"1":{"dict_id":9,"v":"1","detail_id":15,"l":"暂停"}}},

};

// 改变状态
function changeStatus( data,idx,elem ){
    FetchUtils.fetchPutJson(app+"/sys/sys_user/change_status",data,function(res){
        if( !res || res.status!==1 ){
            alert(res?res.msg:"操作失败");
            return;
        }
        let search_form = Common.global_module.search_form;
        Common.load(search_form);
    });
}

// 解锁登录
function unlock(  data,idx,elem ){
    FetchUtils.fetchPutJson(app+"/sys/sys_user/unlock?user_name="+data.user_name,data,function(res){
        if( !res || res.status!==1 ){
            alert(res?res.msg:"解锁操作失败");
            return;
        }
        alert(res.msg);
    });
}

// 重置密码
function resetPwd( data,idx,elem ){
    let ch = "@"+window.btoa(Math.random().toString(5).slice(3,10)).substr(0,3)+Math.random().toString(5).slice(4,6);
    let pwd=prompt(`${data.user_name}(${data.nick_name})重置登录密码为:`,ch);//prompt方法定义为person变量
    if( !pwd ){
        // null or cancel
    }else if(pwd.length<5){
        alert("密码不可以少于5位:"+pwd+" ,请重新设置(^、^)");
    }else{
        let _pwd = enc(pwd);
        FetchUtils.fetchPutJson(app+`/sys/sys_user/reset_pwd?id=${data.id}&pwd=${_pwd}`,{},function(res){
            if( !res || res.status!==1 ){
                alert(res?res.msg:"重置失败");
                return;
            }
            alert("重置成功，请妥善保存密码: "+pwd);;
        });
    }
}

// 角色信息
function roleInfo( data,idx,elem ){
    let content =`
        <div class="row">
            <div> {{user_name}}-{{nick_name}} </div>
            <table class="table table-bordered table-condensed table-hover">
                <thead><tr><th>角色名称</th><th>角色描述</th></tr></thead>
                <tbody>
                    {{#each roles}}
                    <tr> <td title="{{id}}">{{name}}</td> <td>{{description}}</td> </tr>
                    {{/each}}
                </tbody>
            </table>
        </div>
    `;
    let msg_cfg = { "title":"<h5>角色信息</h5>","content":content,"auto_close":false,"close_name":"&nbsp;好&nbsp; ","width":"480px"};
    // 有就直接用
    if( data.roles ){
        msg_cfg.data=data;
        Message.showDialog( msg_cfg );
        return;
    }
    FetchUtils.fetchGet(app+"/sys/sys_user/getRoles",{"id":data.id},function(res){
        if( !res || 1!==res.status ){
            alert(!res?"獲取失敗,请稍后重试(^,^)！":res.msg?res.msg:res);
            return;
        }
        data.roles=res.data;
        msg_cfg.data=data;
        Message.showDialog( msg_cfg );
    });
}

// add提交前
function addBeforeFunc( dialog_dom,data ){
    if( !data || !data.pwd1 || !data.pwd2 || data.pwd1!==data.pwd2 ){
        alert("密码为空或不一致，请检查！");
        return false;
    }
    data.pwd1=enc(data.pwd1);
    data.pwd2=enc(data.pwd2);
    return true;
}

// 初始化通用模块
function init(){
    Common.init(module);
}

export { init }
