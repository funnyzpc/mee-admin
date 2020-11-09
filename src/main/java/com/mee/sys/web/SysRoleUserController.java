package com.mee.sys.web;

import com.mee.common.util.ResultBuild;
import com.mee.common.util.excel.ExcelWriteUtil;
import com.mee.core.dao.DBSQLDao;
import com.mee.sys.entity.SysRoleUser;
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
 * 系统::角色用户 管理
 */
@Controller
@RequestMapping("/sys/role_user")
public class SysRoleUserController {
    private static final Logger log = LoggerFactory.getLogger(SysRoleUserController.class);

    @Autowired
    private DBSQLDao dbSQLDao;

    @RequiresPermissions("040104")
    @GetMapping
    public String index(){
        return "sys/role_user";
    }

    @RequiresPermissions("040104")
    @PostMapping
    @ResponseBody
    public Map<String,Object> list(String nick_name, String role_desc,int pageIdx, int pageSize){
        Map<String,Object> params = new HashMap<String,Object>(4,1);
        if(null != nick_name && !"".equals(nick_name)){ params.put("nick_name","%"+nick_name.trim()+"%"); }
        if(null != role_desc && !"".equals(role_desc)){ params.put("role_desc","%"+role_desc.trim()+"%"); }
        return new HashMap<String,Object>(1,1){{
            put("data", dbSQLDao.list("com.mee.xml.SysRoleUser.findList",params,pageIdx,pageSize));
        }};
    }

    /** 导出 **/
    public static final String[] data_field = {"role_name","role_desc","status","nick_name","user_name","email","create_date"};
    public static final String[] header_field= {"角色名称","角色描述","用户状态","用户名称","登录名称","用户email","用户创建时间"};
    // public static final CellFmt[] field_format = {null,null,null,CellFmt.CUSTOM_02,CellFmt.CUSTOM_02,null,null,CellFmt.NUMERIC_02};

    @RequiresPermissions("040104")
    @GetMapping("/export")
    public void export(HttpServletResponse response,String nick_name,String role_desc){
        Map<String,Object> params = new HashMap<String,Object>(4,1);
        if(null != nick_name && !"".equals(nick_name)){ params.put("nick_name","%"+nick_name.trim()+"%"); }
        if(null != role_desc && !"".equals(role_desc)){ params.put("role_desc","%"+role_desc.trim()+"%"); }
        List<SysRoleUser> dataList = dbSQLDao.query("com.mee.xml.SysRoleUser.findList",params);
        File file = ExcelWriteUtil.toXlsxByObj(dataList,header_field,data_field);
        ResultBuild.toResponse(response,file);
    }

}
