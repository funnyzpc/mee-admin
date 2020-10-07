package com.mee.sys.web;

import com.mee.common.util.ResultBuild;
import com.mee.core.dao.DBSQLDao;
import com.mee.sys.entity.SysMenu;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author funnyzpc
 * @description 菜单管理
 */
@Controller
@RequestMapping("/sys/menu")
public class SysMenuController {
    private static final Logger log = LoggerFactory.getLogger(SysMenuController.class);

    @Autowired
    private DBSQLDao dbsqlDao;

    @RequiresPermissions("040103")
    @GetMapping
    public String index(){
        return "sys/menu";
    }

    @RequiresPermissions("040103")
    @PostMapping
    @ResponseBody
    public Map<String,Object> list(String name,String path,int pageIdx, int pageSize){
        Map<String,Object> params = new HashMap<String,Object>(3,1);
        if(null != name && !"".equals(name)){ params.put("name",name+"%"); }
        if(null != path && !"".equals(path)){ params.put("path",path+"%"); }
        return new HashMap<String,Object>(1,1){{
            put("data",dbsqlDao.list("com.mee.xml.SysMenu.findList",params,pageIdx,pageSize));
        }};
    }

    /** 所有菜单信息 **/
    @RequiresPermissions("040103")
    @PostMapping("/menuAll")
    @ResponseBody
    public Map<String,Object> menuAll(){
        return new HashMap<String,Object>(1,1){{
            put("data",dbsqlDao.query("com.mee.xml.SysMenu.findList"));
        }};
    }

    @RequiresPermissions("040103")
    @RequestMapping("/save")
    @ResponseBody
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Map<String,Object> save(SysMenu sysMenu){
        // 参数校验
        if(null == sysMenu || StringUtils.isEmpty(sysMenu.getName()) || StringUtils.isEmpty(sysMenu.getCode())){
            return ResultBuild.fail("缺少参数");
        }
        if("-1".equals(sysMenu.getParent_code())){
            sysMenu.setParent_code(null);
        }
        if(null == sysMenu.getId() || "".equals(sysMenu.getId().trim())){
            // 是否重复
            Map<String,Object> queryParam = new HashMap<String,Object>(1,1){{
               put("code",sysMenu.getCode());
            }};
            if(dbsqlDao.query("com.mee.xml.SysMenu.findList",queryParam).size()>0){
                return ResultBuild.fail("权限编号重复,请检查:"+sysMenu.getCode());
            }
            // 插入数据
            String recordId = dbsqlDao.create("com.mee.xml.SysMenu.insert",sysMenu);
            log.info("创建sys_dict结果:{}",recordId);
            return ResultBuild.SUCCESS;
        }
        // 更新数据
        int updateCount = dbsqlDao.update("com.mee.xml.SysMenu.update",sysMenu);
        log.info("更新sys_dict结果:{}",updateCount);
        return ResultBuild.SUCCESS;
    }

    @RequiresPermissions("040103")
    @RequestMapping("/delete")
    @ResponseBody
    public Map delete(String id,String code){
        // 参数校验
        if(StringUtils.isEmpty(id) || StringUtils.isEmpty(code)){
            log.error("缺少参数 id:{} or code:{}",id,code);
            return ResultBuild.FAIL;
        }

        // 是否存在子节点
        Map<String,Object> queryParam = new HashMap<String,Object>(1,1){{
            put("code",code);
        }};
        int childNodeCount = dbsqlDao.queryOne("com.mee.xml.SysMenu.findChildNodeCount",queryParam);
        if(childNodeCount>0){
            return ResultBuild.fail("存在子节点，请删除子节点后再删除当前节点~");
        }
        // 检查是否有已经分配的菜单角色关系
        List<Object>  relationList = dbsqlDao.query("com.mee.xml.SysRole.findRelation",queryParam);
        if(relationList.size()>0){
            String relationItem="";
            for(Object item:relationList){
                relationItem = relationItem+","+item;
            }
            return ResultBuild.fail("菜单已分配至角色,请解除角色后删除:"+relationItem);
        }
        Map<String,Object> params = new HashMap<String,Object>(1,1){{
            put("id",id.trim());
        }};
        int recordCount = dbsqlDao.delete("com.mee.xml.SysMenu.delete",params);
        log.info("删除sys_menu结果:{}",recordCount);
        return ResultBuild.SUCCESS;
    }


}
