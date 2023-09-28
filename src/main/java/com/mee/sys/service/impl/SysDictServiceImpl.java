package com.mee.sys.service.impl;

import com.mee.common.service.SeqGenServiceImpl;
import com.mee.common.util.DateUtil;
import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import com.mee.core.configuration.ShiroUtils;
import com.mee.core.dao.DBSQLDao;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysDict;
import com.mee.sys.service.SysDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据字典(SysDict2) 业务接口
 *
 * @author  shadow
 * @version v1.0
 * @date    2023-05-15 10:27:36
*/
@Service
public class SysDictServiceImpl implements SysDictService {

    /**
    *   日志
    */
    private static final Logger LOG = LoggerFactory.getLogger(SysDictServiceImpl.class);

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
     * 查询数据字典列表
     * @param page_no .
     * @param page_size .
     * @param name .
     * @param description .
     * @return .
     */
    @Override
    public MeeResult<Page<SysDict>> list(Integer page_no, Integer page_size , String name, String description){
      LOG.info("接收到参数 {},{}, {},{},",page_no,page_size,name,description);
      Map<String,Object> param = new HashMap<String,Object>(9,1);
      param.put("name",null==name||"".equals(name)?null:"%"+name+"%" );
      param.put("description",(null==description||"".equals(description))?null:"%"+description+"%" );
      Page<SysDict> list = dbSQLDao.list("com.mee.xml.SysDict.findList",param,page_no,page_size);
      return ResultBuild.build(list);
    }

    /**
     * 数据字典::按主键查询
     * @param id .
     * @return .
     */
    @Override
    public MeeResult<SysDict> findById(String id){
      LOG.info("开始查询:{}",id);
      if(null==id || "".equals(id)){
        LOG.error("必要参数为空:{}",id);
        return ResultBuild.fail("必要参数为空[id]");
      }
      Map<String,Object> param = new HashMap<String,Object>(2,1);
      param.put("id",id);
      SysDict sysDict = dbSQLDao.findOne("com.mee.xml.SysDict.findById", param);
      return ResultBuild.build(sysDict);
    }

    /**
     * 数据字典::新增
     *
     * @param sysDict 数据字典
     * @return 插入条数
    */
    @Override
    public MeeResult<Integer> add(SysDict sysDict){
      LOG.info("接收到参数 {}", sysDict);
      if(null == sysDict || null==sysDict.getName() || null==sysDict.getDescription() ){
          return ResultBuild.fail("参数缺失请检查~");
      }
      final LocalDateTime now = DateUtil.now();
      final String user_id = ShiroUtils.getUserId();
      // 主键
      sysDict.setId(seqGenService.genShortPrimaryKey());
      // 通用字段
      sysDict.setCreate_by(Long.parseLong(user_id));
      sysDict.setUpdate_by(Long.parseLong(user_id));
      sysDict.setCreate_time(now);
      sysDict.setUpdate_time(now);
      int insert_count = dbSQLDao.insert("com.mee.xml.SysDict.insert",sysDict);
      return ResultBuild.build(insert_count);
    }


    /**
     * 数据字典::修改
     *
     * @param SysDict2(or Map) 数据字典
     * @return 更新条数
    */
    @Override
    public MeeResult<Integer> update(SysDict sysDict){
      LOG.info("接收到参数 {}",sysDict);
      if(null == sysDict ||null==sysDict.getId()||null==sysDict.getName() ){
          return ResultBuild.fail("必要参数缺失，请检查~");
      }
      final LocalDateTime now = DateUtil.now();
      final String user_id = ShiroUtils.getUserId();

      // 通用字段
      sysDict.setUpdate_by(Long.parseLong(user_id));
      sysDict.setUpdate_time(now);
      int update_count = dbSQLDao.update("com.mee.xml.SysDict.update",sysDict);
      LOG.info("已更新数据字典明细：{}->{}条",sysDict,update_count);
      return ResultBuild.build(update_count);
    }

    /**
     * 数据字典::删除
     *
     * @id 数据字典 主键
     * @return 删除条数
    */
    @Override
    public MeeResult<Integer> deleteById(String id){
      LOG.info("开始查询:{}",id);
      if(null==id || "".equals(id)){
          LOG.error("必要参数为空:{}",id);
          return ResultBuild.fail("必要参数为空[id]");
      }
      Map<String,Object> param = new HashMap<String,Object>(4,1);
      param.put("id",id);
      param.put("dict_id",id);
      int delete_count = dbSQLDao.delete("com.mee.xml.SysDict.deleteById", param);
      int delete_detail_count = dbSQLDao.delete("com.mee.xml.SysDictDetail.deleteById", param);
      LOG.info("已删除{}字典项{}条，字典明细{}条",id,delete_count,delete_detail_count);
      return ResultBuild.build( delete_count );
    }

    /**
     * 数据字典::批量删除
     *
     * @ids 数据字典 主键集合
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
      int delete_count = dbSQLDao.delete("com.mee.xml.SysDict.deleteBatch", param);
      LOG.info("已删除记录{}->{}条",ids,delete_count);
      return ResultBuild.build(delete_count);
    }

}
