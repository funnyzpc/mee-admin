
// Object属性追加
function addProperty(from,to){
	if(!from){
		console.log("待合并的对象from存在空");
		return !to?{}:to;
	}
	if(!to){
		return to = from;
	}
	for (let p in from){
		if( !to[p] || null===to[p] || undefined===to[p]  ){
			// 这里只做追加，有相同属性的不再覆盖
			to[p]=from[p];
		}
	}
	return to;
}

// 强制覆盖
function addPropertyOverwritten(from,to){
	if(!from){
		console.log("待合并的对象from存在空");
		return !to?{}:to;
	}
	if(!to){
		return to = from;
	}
	for (let p in from){
		// 这里强制覆盖
		if( from[p]){
			to[p]=from[p];
		}
	}
	return to;
}

// 表单转json
function formToJson(form){
   // 做兼容，如果是上一级dom则往下照
   if( "FORM"!==form.nodeName ){
       form=form.querySelector("form");
   }
   let elem = form.elements;
   let data = {};
   for( let idx=0;idx<elem.length;idx++){
        let type = elem[idx].type;
        switch(type) {
            case 'hidden':
                data[elem[idx].name]=elem[idx].value;
                continue;
            case 'email':
            case 'number':
            case 'text':
            case 'password':
                data[elem[idx].name]=elem[idx].value;
                continue;
            case 'textarea':
                data[elem[idx].name]=elem[idx].value;
                continue;
            case 'input':
                data[elem[idx].name]=elem[idx].value;
                continue;
            case 'radio':
                // 只能取checked=true的value
                if( true===elem[idx].checked ){
                    data[elem[idx].name]=elem[idx].value;
                }
                continue;
            case 'select':
            case 'select-one':
                data[elem[idx].name]=elem[idx].value;
                continue;
            case 'checkbox':
                data[elem[idx].name]=(true===elem[idx].checked)?elem[idx].getAttribute("selected-value"):elem[idx].getAttribute("not-selected-value");
                continue;
            case 'submit':
            case 'reset':
                // 忽略
                continue;
            case 'date':
                data[elem[idx].name]=elem[idx].value;
                continue;
            case 'datetime-local':
                // IOS 格式的数据必须格式化后给后端
                let _v = elem[idx].value;
                if( _v && _v.indexOf("T")!==-1 ){
                    _v=_v.replace("T"," ");
                }
                // 部分瀏覽器在秒為00時候無法獲取秒，這裏需要補充秒位
                if( _v && _v.length==16 ){
                    _v=_v+":00";
                }
                //data[elem[idx].name]=elem[idx].value;
                data[elem[idx].name]=_v;
                continue;
            default :
                // debugger;
                console.log("未知的节点："+elem[idx]);
                continue;
        }
   }
   return data;
}

export { addProperty,addPropertyOverwritten,formToJson }