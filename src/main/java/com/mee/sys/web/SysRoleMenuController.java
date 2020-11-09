package com.mee.sys.web;

import com.mee.common.util.ResultBuild;
import com.mee.common.util.excel.ExcelWriteUtil;
import com.mee.core.dao.DBSQLDao;
import com.mee.sys.entity.SysRoleMenu;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统::角色菜单 管理
 */
@Controller
@RequestMapping("/sys/role_menu")
public class SysRoleMenuController {
    private static final Logger log = LoggerFactory.getLogger(SysRoleMenuController.class);

    @Autowired
    private DBSQLDao dbSQLDao;

    @RequiresPermissions("040105")
    @GetMapping
    public String index(){
        return "sys/role_menu";
    }

    @RequiresPermissions("040105")
    @PostMapping
    @ResponseBody
    public Map<String,Object> list(String role_desc, String name,int pageIdx, int pageSize){
        Map<String,Object> params = new HashMap<String,Object>(4,1);
        if(null != role_desc && !"".equals(role_desc)){ params.put("role_desc","%"+role_desc.trim()+"%"); }
        if(null != name && !"".equals(name)){ params.put("name","%"+name.trim()+"%"); }
        return new HashMap<String,Object>(1,1){{
            put("data", dbSQLDao.list("com.mee.xml.SysRoleMenu.findList",params,pageIdx,pageSize));
        }};
    }

    /** 导出 **/
    public static final String[] data_field = {"role_name","role_desc","name","code"};
    public static final String[] header_field= {"  角色  "," 角色描述 "," 菜单名称 ","菜单编号"};

    @RequiresPermissions("040105")
    @GetMapping("/export")
    public void export(HttpServletResponse response,String role_desc, String name){
        Map<String,Object> params = new HashMap<String,Object>(4,1);
        if(null != role_desc && !"".equals(role_desc)){ params.put("role_desc","%"+role_desc.trim()+"%"); }
        if(null != name && !"".equals(name)){ params.put("name","%"+name.trim()+"%"); }
        List<SysRoleMenu> dataList = dbSQLDao.query("com.mee.xml.SysRoleMenu.findList",params);
        File file = ExcelWriteUtil.toXlsxByObj(dataList,header_field,data_field);
        ResultBuild.toResponse(response,file);
    }

}
