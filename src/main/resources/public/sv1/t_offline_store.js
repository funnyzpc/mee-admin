import * as Common  from "../common.js"

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
    data_events:{   },
    /* 通用api接口，一般包含增删改查四大接口,给Common内的增删改查之使用，使用时一般加上 module.ctx_path 前缀  */
    /* api必须定义完整的增删改查四大api或者定义一个base api */
    api:{
        /* 列表分页查询：GET请求 */
        "page": ctxPath+"/sv1/t_offline_store/list",
        /* 删除: DELETE请求 */
        "delete": ctxPath+"/sv1/t_offline_store/delete",
        /* 通用地址,如果 page、add、del、mod未定义测会通过此配置生成一个默认的增删改查(base+"/page",base+"/delete"...) */
        "base": ctxPath+"/sv1/t_offline_store",
    },
    /* 列表查询请求，如果执行的是Common的相关请求则数据会回写到此字段内*/
    data:[],
    // 表单模板
    form_struct:{
        // 指定开启
        on:true,
        // form id，这只是一个prefix后面会加上actions.type
        id:"t_offline_store_form",
        actions:{
            "add":{"api":ctxPath+"/sv1/t_offline_store/add","enc":"json","method":"POST","title":"新增店铺","width":"600px"},
            // 修改
            "update":{"api":ctxPath+"/sv1/t_offline_store/update","enc":"json","method":"PUT","title":"修改店铺","width":"600px"},
            // 导入
            "import":{"api":app+"/sv1/t_offline_store/import","enc":"form","method":"POST","title":"导入数据"/*,"width":"400px"*/}
        },
        // 生成模板(这里的数据实在init时生成,进入页面后只生成一次)
        templates:{"add":"","update":""},
        fields:{
            "add":[
                  //{name:"id",type: "hidden",label:"主键"},
                  {name:"open_date",label:"开店时间",type:"date",col:6,attrs:{required:"required"} },
                  {name:"code",label:"编号",col:6,attrs:{required:"required"} },
                  {name:"name",label:"名称",col:6,attrs:{required:"required"} },
                  {name:"nick_name",label:"店铺简称/昵称",col:6 },
                  {name:"addr",label:"店铺地址",col:12 },
                  {name:"brand",label:"经营品牌",type:"select",col:6,"dict_type":"t_offline_store_brand" },
                  {name:"status",label:"状态",type:"select",col:6,attrs:{required:"required"},"dict_type":"common_status" },
                  {name:"description",label:"备注",type:"textarea",col:12 }

                ],
            "update":[
                    {name:"id",type: "hidden",label:"主键"},
                    {name:"open_date",label:"开店时间",type:"date",col:6,attrs:{required:"required"} },
                    {name:"code",label:"编号",col:6,attrs:{required:"required"} },
                    {name:"name",label:"名称",col:6,attrs:{required:"required"} },
                    {name:"nick_name",label:"店铺简称/昵称",col:6,attrs:{required:"required"} },
                    {name:"addr",label:"店铺地址",col:12 },
                    {name:"brand",label:"经营品牌",type:"select",col:6,"dict_type":"t_offline_store_brand" },
                    {name:"status",label:"状态",type:"select",col:6,attrs:{required:"required"},"dict_type":"common_status" },
                    {name:"description",label:"备注",type:"textarea",col:12},

                    {name:"create_by",label:"创建人",col:6,attrs:{readonly:"readonly" } },
                    {name:"create_time",label:"创建时间",col:6,attrs:{ readonly:"readonly"} },
                    {name:"update_by",label:"更新人",col:6,attrs:{readonly:"readonly" } },
                    {name:"update_time",label:"更新时间",col:6,attrs:{readonly:"readonly" } }
                ],
             "import":[
                  {name:"file",label:"文件",col:12,type:"file",attrs:{required:"required",title:"非空,若导入失败请删除excel数据行下方空白行～"}},
                ],
            }
    },
    // 定义初始化角色数据,这个角色是表单还有查询列表都要用到的
    init_dict:["t_offline_store_brand","common_status"],
    // 上面需要初始化角色在调用接口后会回写到此处
    dicts:{ },
};

// 初始化
function init(){
    Common.init(module);
}

export { init }
