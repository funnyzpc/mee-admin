package com.mee.sys.service.impl;

import com.mee.common.enums.DeleteFlagEnum;
import com.mee.common.enums.StatusEnum;
import com.mee.common.service.SeqGenServiceImpl;
import com.mee.common.util.DateUtil;
import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import com.mee.core.configuration.ShiroUtils;
import com.mee.core.dao.DBSQLDao;
import com.mee.core.model.Page;
import com.mee.sys.dto.SysRoleUserDTO;
import com.mee.sys.entity.SysRoleUser;
import com.mee.sys.entity.SysUser;
import com.mee.sys.service.SysRoleUserService;
import com.mee.sys.vo.SysRoleUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统::角色用户表(SysRoleUser2) 业务接口
 *
 * @author  shadow
 * @version v1.3
 * @date    2023-05-28 16:45:32
*/
@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {

    /**
    *   日志
    */
    private static final Logger LOG = LoggerFactory.getLogger(SysRoleUserServiceImpl.class);

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
     * 查询系统::角色用户表列表
     *
     * @param page_no 第几页
     * @param page_size 请求分页大小
     * @return 系统::角色用户表分页集合
    */
    @Override
    public MeeResult<Page<SysRoleUserVO>> list(Integer page_no, Integer page_size , String user_name, String nick_name, String user_id, String role_id){
      LOG.info("接收到参数 {},{}, {},{},",page_no,page_size,user_id,role_id);
      Map<String,Object> param = new HashMap<String,Object>(4,1);
      param.put("user_name",(null==user_name||"".equals(user_name))?null:"%"+user_name+"%" );
      param.put("nick_name",(null==nick_name||"".equals(nick_name))?null:"%"+nick_name+"%" );
      param.put("user_id",null==user_id||"".equals(user_id)?null:user_id );
      param.put("role_id",null==role_id||"".equals(role_id)?null:role_id );
      Page<SysRoleUserVO> list = dbSQLDao.list("com.mee.xml.SysRoleUser.findList2",param,page_no,page_size);
      return ResultBuild.build(list);
    }


    /**
     * 查询系统::角色用户表列表
     *
     * @param page_no 第几页
     * @param page_size 请求分页大小
     * @return 系统::角色用户表分页集合
     */
    @Override
    public MeeResult<Page<SysUser>> listUser(Integer page_no,Integer page_size ,String user_name,String phone,String role_id){
        LOG.info("接收到参数 {},{}, {},{},",page_no,page_size,phone,role_id);
        Map<String,Object> param = new HashMap<String,Object>(8,1);
        param.put("user_name",(null==user_name||"".equals(user_name))?null:"%"+user_name+"%" );
        param.put("phone",null==phone||"".equals(phone)?null:phone );
        param.put("role_id",role_id );
        // 未删除的
        param.put("del_flag", DeleteFlagEnum.NORMAL.code );
        // 启用的
        param.put("status", StatusEnum.VALID.code );
        Page<SysUser> list = dbSQLDao.list("com.mee.xml.SysUser.findListForRole",param,page_no,page_size);
        return ResultBuild.build(list);
    }

    /**
     * 系统::角色用户表::按主键查询
     *
     * @param id 系统::角色用户表主键
     * @return 系统::角色用户表
    */
    @Override
    public MeeResult<SysRoleUser> findById(String id){
      LOG.info("开始查询:{}",id);
      if(null==id || "".equals(id)){
        LOG.error("必要参数为空:{}",id);
        return ResultBuild.fail("必要参数为空[id]");
      }
      Map<String,Object> param = new HashMap<String,Object>(2,1);
      param.put("id",id);
      SysRoleUser sysRoleUser = dbSQLDao.findOne("com.mee.xml.SysRoleUser.findById", param);
      return ResultBuild.build(sysRoleUser);
    }

    /**
     * 系统::角色用户表::新增
     *
     * @param sysRoleUserDTO SysRoleUser2(or Map) 系统::角色用户表
     * @return 插入条数
    */
    @Override
    public MeeResult<Void> add(SysRoleUserDTO sysRoleUserDTO){
        // check
        if( null==sysRoleUserDTO.getRole_id() || null==sysRoleUserDTO.getUser_ids() || sysRoleUserDTO.getUser_ids().isEmpty() ){
            return ResultBuild.fail("必要参数缺失，请至少选择一个！");
        }
        String role_id = sysRoleUserDTO.getRole_id();
        List<String> user_ids = sysRoleUserDTO.getUser_ids();
        Map<String,Object> param = new HashMap<>(2,1);
        param.put("role_id",role_id);
        List<String> user_id_list = dbSQLDao.find("com.mee.xml.SysRoleUser.findUserIdsByRoleId", param);
        final LocalDateTime now = DateUtil.now();
        final Long opt_user_id = Long.parseLong( ShiroUtils.getUserId() );
        for( String user_id:user_ids ){
            if( !user_id_list.contains(user_id) ){
                this.saveRoleUser(role_id,user_id,now,opt_user_id);
            }
        }
        return ResultBuild.SUCCESS();
    }

    public void saveRoleUser(String role_id,String user_id,LocalDateTime time,Long by){
        SysRoleUser sysRoleUser2 = new SysRoleUser()
                .setId(seqGenService.genShortPrimaryKey())
                .setRole_id(role_id)
                .setUser_id(user_id)
                .setCreate_time(time)
                .setCreate_by(by);
        int insert_count = dbSQLDao.insert("com.mee.xml.SysRoleUser.insert", sysRoleUser2);
        LOG.info("已写入{},{}条",sysRoleUser2,insert_count);
    }

    /**
     * 系统::角色用户表::修改
     *
     * @param sysRoleUser SysRoleUser2(or Map) 系统::角色用户表
     * @return 更新条数
    */
    @Override
    public MeeResult<Integer> update(SysRoleUser sysRoleUser){
      LOG.info("接收到参数 {}",sysRoleUser);
      if( null==sysRoleUser.getId()||null==sysRoleUser.getUser_id()||null==sysRoleUser.getRole_id() ){
          return ResultBuild.fail("必要参数缺失，请检查~");
      }
      final LocalDateTime now = DateUtil.now();
      final String user_id = ShiroUtils.getUserId();
      sysRoleUser.setCreate_by(Long.parseLong(user_id));
      sysRoleUser.setCreate_time(now);

      int update_count = dbSQLDao.update("com.mee.xml.SysRoleUser.update",sysRoleUser);
      LOG.info("已更新系统::角色用户表明细：{}->{}条",sysRoleUser,update_count);
      return ResultBuild.build(update_count);
    }

    /**
     * 系统::角色用户表::删除
     *
     * @id 系统::角色用户表 主键
     * @return 删除条数
    */
    @Override
    public MeeResult<Integer> deleteById(String id){
      LOG.info("开始查询:{}",id);
      if(null==id || "".equals(id)){
          LOG.error("必要参数为空:{}",id);
          return ResultBuild.fail("必要参数为空[id]");
      }
      Map<String,Object> param = new HashMap<String,Object>(2,1);
      param.put("id",id);
      int delete_count = dbSQLDao.delete("com.mee.xml.SysRoleUser.deleteById", param);
      return ResultBuild.build( delete_count );
    }

    /**
     * 系统::角色用户表::批量删除
     *
     * @ids 系统::角色用户表 主键集合
     * @return 删除条数
    */
    @Override
    public MeeResult<Integer> deleteBatch(String[] ids){
      if( null==ids || 0==ids.length ){
        LOG.error("必要参数为空:{}",ids);
        return ResultBuild.fail("必要参数为空[id]");
      }
      Map<String,Object> param = new HashMap<String,Object>(2,1);
      param.put("list",ids);
      int delete_count = dbSQLDao.delete("com.mee.xml.SysRoleUser.deleteBatch", param);
      LOG.info("已删除记录{}->{}条",ids,delete_count);
      return ResultBuild.build(delete_count);
    }

}
