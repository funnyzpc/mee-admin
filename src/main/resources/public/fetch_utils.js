
/* POST+json */
function fetchPostJson( url,data,callback ){
    fetch(url,{
       credentials:'include',
       method: 'POST',
       body: JSON.stringify(data),
       headers: new Headers({
           'Content-Type': 'application/json'
         })
    })
    .then(response => response.json())
    .then(data => callback(data))
    .catch(error => console.log("请求超时,请刷新后重试~")
    );
}
/* POST+form */
function fetchPostForm( url,data,callback ){
    fetch(url,{
       credentials:'include',
       method: 'POST',
       body: data,
    })
    .then(response => response.json())
    .then(data => callback(data))
    .catch(error => console.log("fetchPostForm::请求超时,请刷新后重试~")
    );
}

/* GET+json */
function fetchGet(url,param,callback) {
    let param_str = buildParam(param);
    fetch(url+"?"+param_str,{ credentials:'include',method: 'GET' })
        .then((response) => response.json())
        .then((data)=>{
            callback(data)
        }).catch(error =>  {
            console.log("请求超时,请刷新后重试[3]"+error);
            callback(error)
        });
}

/* GET+json */
function fetchGetWithPromise(url,param) {
    let param_str = buildParam(param);
    return new Promise((resolve,reject) => {
        fetch(url+"?"+param_str,{ credentials:'include',method: 'GET' })
            .then(res => res.json())
            .then(data => resolve(data))
            .catch(error => reject(error))
        });
}

/* PUT+form */
function fetchPutForm(url,form,callback) {
//    fetch(url,{ method: 'PUT',body: new FormData(form) ,headers:{"content-type":"application/x-www-form-urlencoded"}})
    fetch(url,{ credentials:'include',method: 'PUT',body: new FormData(form) })
        .then((response) => response.json())
        .then((data)=>{
            callback(data)
        }).catch(error =>  {
            console.log("请求超时,请刷新后重试[3]"+error);
            callback(error)
        });
}

/* PUT+JSON */
function fetchPutJson(url,data,callback) {
    fetch(url,{ credentials:'include',
                method: 'PUT',
                body: JSON.stringify(data),
                headers: new Headers({ 'Content-Type': 'application/json'}) })
    .then((response) => response.json())
    .then((data)=>{
        callback(data)
    }).catch(error =>  {
        console.log("请求超时,请刷新后重试[3]"+error);
        callback(error)
    });
}

/* DELETE+json */
function fetchDelete( url,data,callback ){
    fetch(url,{
       credentials:'include',
       method: 'DELETE',
       body: data?JSON.stringify(data):"{}", /* 兼容各种参数*/
       headers: new Headers({
           'Content-Type': 'application/json'
         })
    })
    .then(response => response.json())
    .then(data => callback(data))
    .catch(error => console.log("请求超时,请刷新后重试~")
    );
}

/* 构建url参数 for GET request,example: {"aa"1,"bb":true} to aa=1&bb=true */
function buildParam(data){
    if( !data || ""===data ){
        return "";
    }
    if( (typeof data) === "string" ){
        return data;
    }
    let param = "";
    for(let key in data){
        if( ""===param ){
            param =  key+"="+data[key];
        }else{
            param = param + "&"+( key+"="+data[key]);
        }
    }
    return param;
}
export { fetchPostJson,fetchPostForm,fetchPutForm,fetchPutJson,fetchGet,fetchGetWithPromise,fetchDelete,buildParam }