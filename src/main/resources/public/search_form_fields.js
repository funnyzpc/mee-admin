/**
    查询表单字段字典项构造
*/
// 初始化表单 (查询表单数据)
function init( form_id,dicts ){
    let form_dom = document.querySelector("#"+form_id);
    if( !form_dom || !form_dom.querySelector("form") ){
        console.log("无需构建查询表单字典项！")
        return;
    }
    let field_doms = form_dom.querySelectorAll("span[_dom_type]");
    for( let idx=0;idx<field_doms.length;idx++ ){
        // <span dom_type="select" field_name="target" dict_type="sys_menu_target">
        let item = field_doms[idx];
        let type = item.getAttribute("_dom_type");
        let name = item.getAttribute("_field_name");
        let dict_type = item.getAttribute("_dict_type");
        let dict = dicts[dict_type];
        if( !type || !name || !dict_type ){
            throw new Error("对于查询表单字典查询项目,字段[_dom_type、_field_name、_dict_type]均不可为空！")
        }
        // 字典配置是否为空
        if( !dict ){
            throw new Error(`表单${form_id}字典配置项${dict_type}为空,请检查！`);
        }
        switch(type){
            case "select":
                item.innerHTML=buildSelect( name,dict );
                break;
            case "radio":
                item.innerHTML=buildRadio( name,dict );
                break;
            case "checkbox":
                item.innerHTML=buildCheckbox( name,dict );
                break;
            default:
                throw new Error(`不支持的dom构建类型[${type}]`);
        }
    }
}

// 下拉框
function buildSelect( name,dict ){
    let option_str = "<option value=''>--</option>";
    if( dict ){
        for( let v in dict ){
          let l = dict[v].l;
          option_str=option_str+`<option value="${v}">${l}</option>`;
        }
    }
    return `
    <select name="${name}" style="min-width:120px;">
        ${option_str}
    </select>`;
}

// 单选
function buildRadio( name,dict ){
    let radio_str = "";
    if( dict ){
        for( let v in dict ){
          let l = dict[v].l;
          radio_str=radio_str+`<input type="radio" name="${name}" value="${v}">${l}&nbsp;&nbsp;`;
        }
    }
    return radio_str;
}

// 多选框
function buildCheckbox( name,dict ){
    let checkbox_str = "";
    if( dict ){
        for( let v in dict ){
          let l = dict[v].l;
          checkbox_str=checkbox_str+`<input type="checkbox" name="${name}" value="${v}">${l}</input>&nbsp;`;
        }
    }
    return checkbox_str;
}

export { init }