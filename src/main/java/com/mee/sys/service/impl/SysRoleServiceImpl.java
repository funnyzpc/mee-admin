package com.mee.sys.service.impl;

import java.util.Map;
import java.util.HashMap;

import com.mee.common.util.DateUtil;
import com.mee.common.util.MeeResult;
import com.mee.core.configuration.ShiroUtils;
import com.mee.core.dao.DBSQLDao;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mee.common.service.SeqGenServiceImpl;
import com.mee.common.util.ResultBuild;
import org.springframework.transaction.annotation.Transactional;

import java.lang.String;
import java.lang.Integer;
import java.time.LocalDateTime;

/**
 * 系统::角色表(SysRole2) 业务接口
 *
 * @author  shadow
 * @version v1.3
 * @date    2023-05-28 16:45:31
*/
@Service
public class SysRoleServiceImpl /* implements SysRole2Service */ {

    /**
    *   日志
    */
    private static final Logger LOG = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    /**
    *   Dao
    */
    @Autowired
    public DBSQLDao dbSQLDao;
    
    /**
    * 主键生成器
    */
    @Autowired
    public SeqGenServiceImpl seqGenService;

    /**
     * 查询系统::角色表列表
     *
     * @param page_no 第几页
     * @param page_size 请求分页大小
     * @return 系统::角色表分页集合
    */
    //@Override
    public MeeResult list(Integer page_no, Integer page_size, String name, String description, Integer status){
      LOG.info("接收到参数 {},{}, {},{},{},",page_no,page_size ,name,description,status);
      Map<String,Object> param = new HashMap<String,Object>(8,1);
      param.put("name",null==name||"".equals(name)?null:"%"+name+"%" );
      param.put("description",null==description||"".equals(description)?null:"%"+description+"%" );
      param.put("status",status );
      Page list = dbSQLDao.list("com.mee.xml.SysRole.findList",param,page_no,page_size);
      return ResultBuild.build(list);
    }

    /**
     * 系统::角色表::按主键查询
     *
     * @param id 系统::角色表主键
     * @return 系统::角色表
    */
    //@Override
    public MeeResult findById(String id){
      LOG.info("开始查询:{}",id);
      if(null==id || "".equals(id)){
        LOG.error("必要参数为空:{}",id);
        return ResultBuild.fail("必要参数为空[id]");
      }
      Map<String,Object> param = new HashMap<String,Object>(2,1);
      param.put("id",id);
      //SysRole2 sysRole2 = dbSQLDao.queryOne("com.mee.module.sys.mapper.sys_role2.findById", param);
      SysRole sysRole2 = dbSQLDao.findOne("com.mee.xml.SysRole.findById", param);
      return ResultBuild.build(sysRole2);
    }

    /**
     * 系统::角色表::新增
     *
     * @param sysRole2 SysRole2(or Map) 系统::角色表
     * @return 插入条数
    */
    public MeeResult add(SysRole sysRole2){
      LOG.info("接收到参数 {}", sysRole2);
      if(null == sysRole2 || null==sysRole2.getName() || null==sysRole2.getDescription() || null==sysRole2.getStatus() ){
          return ResultBuild.fail("参数缺失请检查~");
      }
      final LocalDateTime now = DateUtil.now();
      final String user_id = ShiroUtils.getUserId();
      // 主键
      sysRole2.setId(seqGenService.genShortPrimaryKey());
      // 通用字段
      sysRole2.setCreate_by(Long.parseLong(user_id));
      sysRole2.setCreate_time(now);
      sysRole2.setUpdate_by(Long.parseLong(user_id));
      sysRole2.setUpdate_time(now);
      int insert_count = dbSQLDao.insert("com.mee.xml.SysRole.insert",sysRole2);
      return ResultBuild.build(insert_count);
    }


    /**
     * 系统::角色表::修改
     *
     * @param sysRole2 SysRole2(or Map) 系统::角色表
     * @return 更新条数
    */
    public MeeResult update(SysRole sysRole2){
      LOG.info("接收到参数 {}",sysRole2);
      if(null == sysRole2 ||null==sysRole2.getId()||null==sysRole2.getName()||null==sysRole2.getDescription()||null==sysRole2.getStatus() ){
          return ResultBuild.fail("必要参数缺失，请检查~");
      }
      final LocalDateTime now = DateUtil.now();
      final String user_id = ShiroUtils.getUserId();

      // 通用字段
      sysRole2.setUpdate_by(Long.parseLong(user_id));
      sysRole2.setUpdate_time(now);
      int update_count = dbSQLDao.update("com.mee.xml.SysRole.update",sysRole2);
      LOG.info("已更新系统::角色表明细：{}->{}条",sysRole2,update_count);
      return ResultBuild.build(update_count);
    }

    /**
     * 系统::角色表::删除
     *
     * @id 系统::角色表 主键
     * @return 删除条数
    */
    @Transactional(rollbackFor = Exception.class,readOnly = false)
    //@Override
    public MeeResult deleteById(String id){
        LOG.info("开始查询:{}",id);
        if(null==id || "".equals(id)){
          LOG.error("必要参数为空:{}",id);
          return ResultBuild.fail("必要参数为空[id]");
        }
        Map<String,Object> param = new HashMap<String,Object>(4,1);
        param.put("role_id",id);
        param.put("id",id);
        // 删除角色&用户
        int delete_role_user_count = dbSQLDao.delete("com.mee.xml.SysRoleUser.deleteByRoleId", param);
        // 删除角色&菜单
        int delete_role_menu_count = dbSQLDao.delete("com.mee.xml.SysRoleMenu.deleteByRoleId", param);
        // 删除角色
        int delete_count = dbSQLDao.delete("com.mee.xml.SysRole.deleteById", param);
        LOG.info("已删除菜单关联{}用户{}条,菜单{}条,角色{}条",id,delete_role_user_count,delete_role_menu_count,delete_count);
        return ResultBuild.build(delete_count);
    }

//    /**
//     * 系统::角色表::批量删除
//     *
//     * @ids 系统::角色表 主键集合
//     * @return 删除条数
//    */
//    //@Override
//    public Map deleteBatch(String[] ids){
//      if( null==ids || 0==ids.length ){
//        LOG.error("必要参数为空:{}",ids);
//        return ResultBuild.fail("必要参数为空[id]");
//      }
//      Map<String,Object> param = new HashMap<String,Object>(2,1);
//      param.put("list",ids);
//      int delete_count = dbSQLDao.delete("com.mee.xml.SysRole.deleteBatch", param);
//      LOG.info("已删除记录{}->{}条",ids,delete_count);
//      return ResultBuild.success(delete_count);
//    }

}
