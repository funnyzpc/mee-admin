package com.mee.sys.service.impl;

import com.mee.common.service.SeqGenServiceImpl;
import com.mee.common.util.DateUtil;
import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import com.mee.core.configuration.ShiroUtils;
import com.mee.core.dao.DBSQLDao;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysRoleMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色菜单关联(SysRoleMenu2) 业务接口
 *
 * @author  shadow
 * @version v1.3
 * @date    2023-05-28 16:45:29
*/
@Service
public class SysRoleMenuServiceImpl {

    /**
    *   日志
    */
    private static final Logger LOG = LoggerFactory.getLogger(SysRoleMenuServiceImpl.class);

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
     * 查询角色菜单关联列表
     *
     * @param page_no 第几页
     * @param page_size 请求分页大小
     * @return 角色菜单关联分页集合
    */
    public MeeResult<Page<SysRoleMenu>> list(Integer page_no, Integer page_size , String menu_id, String role_id){
      LOG.info("接收到参数 {},{}, {},{},",page_no,page_size,menu_id,role_id);
      Map<String,Object> param = new HashMap<String,Object>(4,1);
      param.put("menu_id",(null==menu_id||"".equals(menu_id))?null:menu_id );
      param.put("role_id",(null==role_id||"".equals(role_id))?null:role_id );
      Page<SysRoleMenu> list = dbSQLDao.list("com.mee.xml.SysRoleMenu.findList",param,page_no,page_size);
      return ResultBuild.build(list);
    }

    /**
     * 角色菜单关联::按主键查询
     *
     * @param id 角色菜单关联主键
     * @return 角色菜单关联
    */
    //@Override
    public MeeResult<SysRoleMenu> findById(String id){
      LOG.info("开始查询:{}",id);
      if(null==id || "".equals(id)){
        LOG.error("必要参数为空:{}",id);
        return ResultBuild.fail("必要参数为空[id]");
      }
      Map<String,Object> param = new HashMap<String,Object>(2,1);
      param.put("id",id);
      SysRoleMenu sysRoleMenu = dbSQLDao.findOne("com.mee.xml.SysRoleMenu.findById", param);
      return ResultBuild.build(sysRoleMenu);
    }

    /**
     * 角色菜单关联::新增
     *
     * @param params sysRoleMenu2 SysRoleMenu2(or Map) 角色菜单关联
     * @return 插入条数
    */
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public MeeResult add(Map<String,Object> params){
      LOG.info("接收到参数 {}", params);
      if(null == params || !params.containsKey("role_id") || !params.containsKey("menu_ids") ){
          return ResultBuild.fail("参数缺失请检查~[1]");
      }
      final String role_id = (String)params.get("role_id");
      final List<String> menu_ids = (List)params.get("menu_ids");
      final LocalDateTime now = DateUtil.now();
      final String user_id = ShiroUtils.getUserId();
      if( "".equals(role_id) || menu_ids.isEmpty() ){
          return ResultBuild.fail("参数缺失请检查~[2]");
      }
      List<SysRoleMenu>  data_list = new ArrayList<>(menu_ids.size());
      for( String menu_id:menu_ids ){
          SysRoleMenu item = new SysRoleMenu();
          item.setId(seqGenService.genShortPrimaryKey());
          item.setMenu_id(menu_id);
          item.setRole_id(role_id);
          item.setCreate_by(Long.parseLong(user_id));
          item.setCreate_time(now);
          data_list.add(item);
      }

      Map<String,Object> delete_param = new HashMap<>();
      delete_param.put("role_id",role_id);
      // 先删除
      int delete_count = dbSQLDao.delete("com.mee.xml.SysRoleMenu.deleteByRoleId",delete_param);
      // 再新增
//      Map<String,Object> _param = new HashMap<>();
//      _param.put("list",data_list);
//      int insert_count = dbSQLDao.create("com.mee.xml.SysRoleMenu.insertBatch",_param);
      int insert_count = dbSQLDao.insert("com.mee.xml.SysRoleMenu.insertBatch",data_list);
      LOG.info("已删除{}角色明细{}条，新增{}条",role_id,delete_count,insert_count);
      return ResultBuild.build(insert_count);
    }

    /**
     * 角色菜单关联::修改
     *
     * @param sysRoleMenu2 SysRoleMenu2(or Map) 角色菜单关联
     * @return 更新条数
    */
    public MeeResult update(SysRoleMenu sysRoleMenu2){
      LOG.info("接收到参数 {}",sysRoleMenu2);
      if(null == sysRoleMenu2 ||null==sysRoleMenu2.getId()||null==sysRoleMenu2.getMenu_id()||null==sysRoleMenu2.getRole_id() ){
          return ResultBuild.fail("必要参数缺失，请检查~");
      }
      final LocalDateTime now = DateUtil.now();
      final String user_id = ShiroUtils.getUserId();
      sysRoleMenu2.setCreate_by(Long.parseLong(user_id));
      sysRoleMenu2.setCreate_time(now);

      int update_count = dbSQLDao.update("com.mee.xml.SysRoleMenu.update",sysRoleMenu2);
      LOG.info("已更新角色菜单关联明细：{}->{}条",sysRoleMenu2,update_count);
      return ResultBuild.build(update_count);
    }

    /**
     * 角色菜单关联::删除
     *
     * @id 角色菜单关联 主键
     * @return 删除条数
    */
    //@Override
    public MeeResult deleteById(String id){
      LOG.info("开始查询:{}",id);
      if(null==id || "".equals(id)){
          LOG.error("必要参数为空:{}",id);
          return ResultBuild.fail("必要参数为空[id]");
      }
      Map<String,Object> param = new HashMap<String,Object>(2,1);
      param.put("id",id);
      int delete_count = dbSQLDao.delete("com.mee.xml.SysRoleMenu.deleteById", param);
      return ResultBuild.build(delete_count);
    }

    /**
     * 角色菜单关联::批量删除
     *
     * @ids 角色菜单关联 主键集合
     * @return 删除条数
    */
    public MeeResult deleteBatch(String[] ids){
      if( null==ids || 0==ids.length ){
        LOG.error("必要参数为空:{}",ids);
        return ResultBuild.fail("必要参数为空[id]");
      }
      Map<String,Object> param = new HashMap<String,Object>(2,1);
      param.put("list",ids);
      int delete_count = dbSQLDao.delete("com.mee.xml.SysRoleMenu.deleteBatch", param);
      LOG.info("已删除记录{}->{}条",ids,delete_count);
      return ResultBuild.build(delete_count);
    }

    public MeeResult<List<SysRoleMenu>> listByRoleId(String role_id) {
        LOG.info("接收到参数 {}",role_id);
        Map<String,Object> param = new HashMap<String,Object>(2,1);
        param.put("role_id",role_id);
        List<SysRoleMenu> list = dbSQLDao.find("com.mee.xml.SysRoleMenu.findList",param);
        return ResultBuild.build(list);
    }

}
