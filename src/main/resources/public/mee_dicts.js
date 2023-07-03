import * as FetchUtils  from "./fetch_utils.js"
import * as MeeUtils  from "./mee_utils.js"


// 构建url参数 ["aa","bb","cc"] to aa,bb,cc
function buildParam( data ){
    if( !data || !Array.isArray(data) ){
        console.log("data不是数组:"+JSON.stringify(data) );
        return "";
    }
    let str = "";
    for( let idx in data ){
        if(0==idx){
            str=data[idx];
        }else{
            str=str+","+data[idx];
        }
    }
    return str;
}

// 注册字典
function registerDictTemplate(dicts){
    for(let dict_name in dicts){
        Handlebars.registerHelper(dict_name, dictTemplate(dicts,dict_name));
//        Handlebars.registerHelper(dict_name, function(dict_name){
//            return function(v){
//                return new Handlebars.SafeString(dicts[dict_name][v]?dicts[dict_name][v].l : "");
//            }
//        });
    }
}

// 注册每一项数据
function dictTemplate(dicts,dict_name){
    return function(v){
        return new Handlebars.SafeString(dicts[dict_name][v]?dicts[dict_name][v].l : "");
    }
}

// 合并字典（自定义字典以及接口字典 ）
// 字典项放在当前模块的一个必要原因是用户自行定义的字典也需要注册到handlebar！！！
function mergeDicts(_global_module,new_dicts){
    if( new_dicts && "object"===(typeof new_dicts)  ){
        //_global_module.dicts=MeeUtils.addProperty(new_dicts,_global_module.dicts);
        MeeUtils.addProperty(new_dicts,_global_module.dicts);
    }
}

function init(_global_module,callback){
    //["sys_common_status","sys_menu_type"];
    let dict_array = _global_module.init_dict;
    let dicts = _global_module.dicts;
    let params = "names="+buildParam(dict_array);
    let url = app+"/common/dict";

    // 判断是否为空，防止重复请求
    if( !dict_array || dict_array.length===0 ){
        // 注册handlebar字典
        registerDictTemplate(dicts);
        if( callback ){
            console.log("register dicts finished...");
            return callback({});
        }
        return;
    }

    // 执行请求
    // 组装数据
    // {"sys_common_status":{"0":{"dict_id":15,"dict_sort":1,"v":"0","name":"sys_common_status","detail_id":10,"l":"成功"},"1":{"dict_id":15,"dict_sort":2,"v":"1","name":"sys_common_status","detail_id":11,"l":"失败"}},"sys_yes_no":{"Y":{"dict_id":11,"dict_sort":27,"v":"Y","name":"sys_yes_no","detail_id":36,"l":"是"},"N":{"dict_id":11,"dict_sort":28,"v":"N","name":"sys_yes_no","detail_id":37,"l":"否"}}}
    FetchUtils
        .fetchGetWithPromise(url,params)
        .then((res)=>{
        // 合并接口字典
        mergeDicts(_global_module,res);
        // 注册handlebar字典
        registerDictTemplate(dicts);
        if( callback ){
            console.log("register dicts finished...");
            callback(res);
        }
    });
    // 填充表单参数
    // return Promise...
}

export { init }
