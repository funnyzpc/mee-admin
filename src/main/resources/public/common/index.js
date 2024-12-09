import { fetchPutForm,fetchGet }  from "../fetch_utils.js"

/** 全量菜单数据 **/
var _menu_data = [];
/** 左侧菜单数据 **/
var _menu_sidebar = [];
function addEvent(dom){
    // 目录切换 mee-menu-item-dir
    let dir_dom_list = dom.querySelectorAll("#mee-menu-content .mee-menu-item-dir");
    dir_dom_list.forEach(dom_item=>{
        dom_item.addEventListener("click",function(){
            // 获取节点当前状态(dom_is_show)
            let dom_is_show = this.getAttribute("dom_is_show");
            if(dom_is_show==="1"){
                // 对于显示的 置为 隐藏(姊妹节点)
                this.nextElementSibling.style.display="none";
                // 子节点全部隐藏
                // this.nextElementSibling.querySelectorAll(".mee-menu-item-dir");
                // 重置节点参数
                this.setAttribute("dom_is_show","0");
                // 改变箭头方向 ˆˇ ▲▼
                // this.querySelector(".mee-menu-item-dir-tag").innerHTML=MENU_SPACE[1]+"▼";
                this.querySelector(".mee-menu-item-dir-tag").innerHTML="▼";
            }else{
                // 对于隐藏的 置为 显示(姊妹节点)
                this.nextElementSibling.style.display="";
                // 重置节点参数
                this.setAttribute("dom_is_show","1");
                // 改变箭头方向
                // this.querySelector(".mee-menu-item-dir-tag").innerHTML=MENU_SPACE[1]+"▲";
                this.querySelector(".mee-menu-item-dir-tag").innerHTML="▲";
            }
        });
    });

    // 菜单点击 mee-menu-item
    let menu_dom_list = dom.querySelectorAll("#mee-menu-content .mee-menu-item");
    menu_dom_list.forEach(dom_item=>{
        dom_item.addEventListener("click",function(){
             // 获取节点当前状态(dom_is_show)
            let menu_id = this.getAttribute("menu_id");
            // 点击的添加背景色
            //this.style.backgroundColor="blue";
            this.style.backgroundImage="linear-gradient(to right, blue, rgb(77, 77, 215), rgb(244, 246, 255))";
            this.style.textShadow="black 2px 2px 4px";
            // 其他menu节点取消背景色
            dom.querySelectorAll("#mee-menu-content .mee-menu-item").forEach(other_item=>{
                // 获取节点当前状态(dom_is_show)
                let other_menu_id = other_item.getAttribute("menu_id");
                if( menu_id !== other_menu_id ){
                    //other_item.style.backgroundColor="";
                    other_item.style.backgroundImage="";
                    other_item.style.textShadow="";
                }
            });

        });
    });
 }
/** 构造左侧菜单 **/
function loadSidebarMenu(menu_data){
   let color = 40+8*(menu_data[0].level);
   let menu_str = menu_data[0].is_top === true ? "<ul>" : "<ul style='display:none;background-color:rgb("+color+","+color+","+color+");'>";
   for(let idx in  menu_data){
       let _item = menu_data[idx];
       // 不构造按钮的dom
       if(_item && 3!==_item.type && "3"!==_item.type){
           menu_str+=buildItem(menu_data[idx]);
       }
   }
   return ( menu_str + "</ul>" );
}

function buildItem(data_item){
    let level = data_item.level;
    if( data_item.has_children === true ){
        return  "<li>"+
                    "<div data-has-child='true' dom_is_show='0' dom_level='"+level+"' class='mee-menu-item-dir'>"+buildItemDir(data_item,level)+"</div>"+
                    loadSidebarMenu(data_item.children) +
                "</li>";
    }else{
        //  ▲▼
        return "<li class='mee-menu-item' menu_id='"+data_item.id+"'>"+buildHref(data_item,level) +"</li>";
    }
}

function buildItemDir(item,level){
   let width  = level*8;
   return "<span style='padding-left:"+width+"px'>"+item.title +"</span>"+"<span class='mee-menu-item-dir-tag'> ▼</span>";
}

function buildHref(item,level){
   // return MENU_SPACE[level]+
   let width  = level*8;
   // 拼接context-path
   let href = (item.target==="_content" && !item.path.startsWith("http"))?(ctxPath+item.path) : item.path;
   return "<a class='mee-menu-item-text' href='"+href+"' "+
          "style='display: block;padding-left:"+width+"px' "+
          "target='"+item.target+"' >"+
          item.title+
          "</a>";
}
/**格式化菜单结构数据**/
function filterMenu(data){
 let result = [];
 let level = 0;
 for(let i=0;i<data.length;i++){
   //if( !data[i].hidden===true ){
   // 隐藏的以及按钮不作为左侧菜单显示
   if( !data[i].hidden===true && data[i].type!==3 ){
      let item = data[i];
      item.is_top=true;
      item.level=level;
      if( !item.children || item.children.length==0 ){
        item.has_children=false;
      }else{
        item.children=filterItem(item.children,level+1);
        item.has_children=( item.children && item.children.length>0)?true:false;
      }
      result.push(item);
   }
 }
 return result;
}

function filterItem(sub_data,level){
 let result = [];
 for( let i=0;i<sub_data.length;i++){
   // 踢掉不显示的以及按钮
//   if( !sub_data[i].hidden===true ){
   if( !sub_data[i].hidden===true && sub_data[i].type!==3 ){
      let item = sub_data[i];
      item.level=level;
      if( !item.children || item.children.length==0 ){
        item.has_children=false;
      }else{
        item.children=filterItem(item.children,level+1);
        item.has_children=( item.children && item.children.length>0)?true:false;
      }
      result.push(item);
   } // if
 } // for
 return result;
}

/**
* 加载顶部导航菜单
*/
function loadNavbarMenu(){
  let navbar_dom = document.querySelector("#navbar-menu");
  let navbar_html = "";
  for(let idx in _menu_data ){
    // 不显示的就不显示，按钮也是
    if( 0===_menu_data[idx].show || 3===_menu_data[idx].type){
        continue;
    }
    let target_field=_menu_data[idx].target;
    let href="";
    let _path=_menu_data[idx].path;
    switch(target_field){
        case "_content":
            href=(_path && _path.startsWith("http"))?_path:(ctxPath+_path);
            target_field="target='_content'";
            break;
        case "_blank":
            href=_path;
            target_field="target='_blank'";
            break;
        default:
            href="#";
            target_field="";
            break;
    }
    navbar_html = navbar_html + "<li menu-idx='"+idx+"' menu-id='"+_menu_data[idx].id+"' menu-target='"+_menu_data[idx].target+"' ><a href='"+href+"' "+target_field+" >"+_menu_data[idx].title+"</a></li>";
  }
  navbar_dom.innerHTML=navbar_html;
  let nodes = navbar_dom.childNodes;
  // register event
  for( let idx=0;idx<nodes.length;idx++ ){
    nodes[idx].addEventListener("click",function(){
        let click_item = this;
        let menu_id = click_item.getAttribute("menu-id");
        let menu_target = click_item.getAttribute("menu-target");
        let menu_idx = click_item.getAttribute("menu-idx");
        if( "_content"===menu_target ){
        // 如果打开的是页面则需要把左侧菜单整体隐藏掉，同时iframe的宽度也调整为100%;
            document.querySelector("#mee-menu").style.display="none";
            document.querySelector("#mee-content").style.width="100%";
        }else if( !_menu_data[menu_idx].children || !_menu_data[menu_idx].children.length ){
        // 如果不存在子菜单 则要把左侧菜单整体隐藏掉，同时iframe的宽度也调整为100% ，同时将iframe置为空
            document.querySelector("#mee-menu").style.display="none";
            document.querySelector("#mee-content").style.width="100%";
            document.querySelector("#_content").src="";
        }else{
        // 其他情况需要把左侧菜单以显示，同时iframe的宽度调整为96%;
            document.querySelector("#mee-menu").style.display="";
            document.querySelector("#mee-content").style.width="84%";
            document.querySelector("#_content").src="";
            // 加载左侧菜单
            _menu_sidebar=filterMenu(_menu_data[menu_idx].children);
            let sidebar_html=loadSidebarMenu(_menu_sidebar);
            let menu_dom = document.querySelector("#mee-menu").querySelector("#mee-menu-content");
            menu_dom.innerHTML=sidebar_html;// buildMenu(sidebar_html);
            // 注册事件
            addEvent(menu_dom);
        }
        nodes.forEach(item=>{
            let item_menu_id = item.getAttribute("menu-id");
            if( menu_id===item_menu_id ){
               item.style="background-color:blue;font-weight:bold;";
               //item.querySelector("a").style="color:black";
            }else{
               item.style="";
               //item.querySelector("a").style="";
            }
        });
    })
  }
  // 点击第一个菜单
  nodes[0].click();

}

// 切换到用户中心
function toUserCenter(){
    // 取消顶部菜单active
    // alert('toUserCenter');
    let navbar_dom = document.querySelector("#navbar-menu");
    let nodes = navbar_dom.childNodes;
    nodes.forEach(item=>{
        item.style="";
    });
    // 如果打开的是页面则需要把左侧菜单整体隐藏掉，同时iframe的宽度也调整为100%;
    document.querySelector("#mee-menu").style.display="none";
    document.querySelector("#mee-content").style.width="100%";
};

function init(){
//    let menu_dom = document.querySelector("#mee-menu");
    // 组装数据
    fetchGet(ctxPath+"/index/menu",{},function(res){
        // _menu_data=filterMenu(res.data);
        _menu_data=res.data;
        // 顶部菜单
        loadNavbarMenu();
    });
 }
 export { init,toUserCenter }
