package com.mee.module.sv1.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.mee.common.util.*;
import com.mee.common.util.excel.ExcelReadUtil;
import com.mee.common.util.excel.ExcelWriteUtil;
import com.mee.core.configuration.ShiroUtils;
import com.mee.core.dao.DBSQLDao;
import com.mee.core.model.Page;
import com.mee.module.sv1.entity.TOfflineStore;
import com.mee.module.sv1.service.TOfflineStoreService;
import com.mee.sys.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mee.common.service.SeqGenServiceImpl;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.lang.String;
import java.lang.Integer;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 测试::线下店铺(TOfflineStore) 业务接口
 *
 * @author  shadow
 * @version v1.0
 * @date    2023-06-16 11:25:46
*/
@Service
public class TOfflineStoreServiceImpl implements TOfflineStoreService {

    /**
    *   日志
    */
    private static final Logger LOG = LoggerFactory.getLogger(TOfflineStoreServiceImpl.class);

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
     *  查询测试::线下店铺列表
     * @param page_no 分页
     * @param page_size
     * @param open_date
     * @param code
     * @param name
     * @param nick_name
     * @param addr
     * @param brand
     * @param status 状态
     * @return 测试::线下店铺分页集合
     */
    @Override
    public MeeResult<Page<TOfflineStore>> list(Integer page_no, Integer page_size , LocalDate open_date, String code, String name, String nick_name, String addr, Integer brand, Integer status){
      LOG.info("接收到参数 {},{}, {},{},{},{},{},{},{}",page_no,page_size,open_date,code,name,nick_name,addr,brand,status);
      Map<String,Object> param = new HashMap<String,Object>(15,1);
//      param.put("id",null==id||"".equals(id)?null:id );
      param.put("open_date",null==open_date||"".equals(open_date)?null:open_date );
      param.put("code",null==code||"".equals(code)?null:code );
      param.put("name",null==name||"".equals(name)?null:name );
      param.put("nick_name",null==nick_name||"".equals(nick_name)?null:nick_name );
      param.put("addr",null==addr||"".equals(addr)?null:addr );
      param.put("brand",null==brand||"".equals(brand)?null:brand );
      param.put("status",null==status||"".equals(status)?null:status );
//      param.put("description",null==description||"".equals(description)?null:description );
      Page<TOfflineStore> list = dbSQLDao.list("com.mee.xml.sv1.TOfflineStore.findList",param,page_no,page_size);
      return ResultBuild.build(list);
    }

    /**
     * 按主键查询测试::线下店铺
     * @param id 主键
     * @return 表数据{"aa":1}
     */
    @Override
    public MeeResult<TOfflineStore> findById(String id){
      LOG.info("开始查询:{}",id);
      if(null==id || "".equals(id)){
        LOG.error("必要参数为空:{}",id);
        return ResultBuild.fail("必要参数为空[id]");
      }
      Map<String,Object> param = new HashMap<String,Object>(2,1);
      param.put("id",id);
      TOfflineStore tOfflineStore = dbSQLDao.findOne("com.mee.xml.sv1.TOfflineStore.findById", param);
      return ResultBuild.build(tOfflineStore);
    }

    /**
     * 测试::线下店铺::新增
     *
     * @param tOfflineStore 测试::线下店铺
     * @return 插入条数
    */
    @Override
    public MeeResult<Integer> add(TOfflineStore tOfflineStore){
      LOG.info("接收到参数 {}", tOfflineStore);
      if( null==tOfflineStore.getOpen_date() || null==tOfflineStore.getCode() || null==tOfflineStore.getName() || null==tOfflineStore.getStatus() ){
          return ResultBuild.fail("参数缺失请检查~");
      }
      final LocalDateTime now = DateUtil.now();
      final String user_id = ShiroUtils.getUserId();
      // 主键
      tOfflineStore.setId(seqGenService.genMediumPrimaryKey());
      // 通用字段
      tOfflineStore.setCreate_by(Long.parseLong(user_id));
      tOfflineStore.setCreate_time(now);
      tOfflineStore.setUpdate_by(Long.parseLong(user_id));
      tOfflineStore.setUpdate_time(now);
      int insert_count = dbSQLDao.insert("com.mee.xml.sv1.TOfflineStore.insert",tOfflineStore);
      return ResultBuild.build(insert_count);
    }


    /**
     * 测试::线下店铺::修改
     *
     * @param tOfflineStore 测试::线下店铺
     * @return 更新条数
    */
    @Override
    public MeeResult<Integer> update(TOfflineStore tOfflineStore){
      LOG.info("接收到参数 {}",tOfflineStore);
      if( null==tOfflineStore.getId()||null==tOfflineStore.getOpen_date()||null==tOfflineStore.getCode()||null==tOfflineStore.getName()||null==tOfflineStore.getStatus() ){
          return ResultBuild.fail("必要参数缺失，请检查~");
      }
      final LocalDateTime now = DateUtil.now();
      final String user_id = ShiroUtils.getUserId();

      // 通用字段
      tOfflineStore.setUpdate_by(Long.parseLong(user_id));
      tOfflineStore.setUpdate_time(now);

      int update_count = dbSQLDao.update("com.mee.xml.sv1.TOfflineStore.update",tOfflineStore);
      LOG.info("已更新测试::线下店铺明细：{}->{}条",tOfflineStore,update_count);
      return ResultBuild.build(update_count);
    }

    /**
     * 测试::线下店铺::删除
     *
     * @id 测试::线下店铺 主键
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
      int delete_count = dbSQLDao.delete("com.mee.xml.sv1.TOfflineStore.deleteById", param);
      return ResultBuild.build( delete_count );
    }

    /**
     * 测试::线下店铺::批量删除
     *
     * @ids 测试::线下店铺 主键集合
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
      int delete_count = dbSQLDao.delete("com.mee.xml.sv1.TOfflineStore.deleteBatch", param);
      LOG.info("已删除记录{}->{}条",ids,delete_count);
      return ResultBuild.build(delete_count);
    }

    @Override
    public void doExport(HttpServletResponse response, Integer page_no, Integer page_size, LocalDate open_date, String code, String name, String nick_name, String addr, Integer brand, Integer status) {
        LOG.info("接收到参数 {},{}, {},{},{},{},{},{},{}",page_no,page_size,open_date,code,name,nick_name,addr,brand,status);
        Map<String,Object> param = new HashMap<String,Object>(8,1);
        param.put("open_date",null==open_date||"".equals(open_date)?null:open_date );
        param.put("code",null==code||"".equals(code)?null:code );
        param.put("name",null==name||"".equals(name)?null:name );
        param.put("nick_name",null==nick_name||"".equals(nick_name)?null:nick_name );
        param.put("addr",null==addr||"".equals(addr)?null:addr );
        param.put("brand",null==brand||"".equals(brand)?null:brand );
        param.put("status",null==status||"".equals(status)?null:status );

        Page list = dbSQLDao.list("com.mee.xml.sv1.TOfflineStore.findList",param,page_no,page_size);
        List<SysUser> data_list = list.getData();

        //String[] header_field= { "开店时间","编号","名称","店铺简称/昵称","店铺地址","品牌","状态0.关闭 1.开启","备注","更新人","更新时间","创建人","创建时间" };
        String[] header_field= { "开店时间\nopen_date","编号\ncode","名称\nname","店铺简称/昵称\nnick_name","店铺地址\naddr","品牌\nbrand","状态0.关闭 1.开启\nstatus","备注\ndescription","更新人\nupdate_by","更新时间\nupdate_time","创建人\ncreate_by","创建时间\ncreate_time" };
        String[] data_field = { "open_date","code","name","nick_name","addr","brand","status","description","update_by","update_time","create_by","create_time" };

        File file = ExcelWriteUtil.toXlsxByObj( data_list,header_field,data_field);
        String seq = SeqGenUtil.genSeq();
        ResultBuild.toResponse(response,file,"线下店铺_"+seq+".xlsx");
    }

    @Override
    public MeeResult<String> doImport(MultipartFile file)throws Exception {
        List<Map> data_list = ExcelReadUtil.xlsx2Map(file.getInputStream(), 8);
        LOG.info("解析到数据为:\n{}", JacksonUtil.toJsonString(data_list));
        if( null==data_list || data_list.isEmpty() ){
            return ResultBuild.fail("读取到数据为空，请检查～");
        }
        final String user_id = ShiroUtils.getUserId();
        //final String now = DateUtil.nowDay().format( DateUtil.FORMAT_DAY);
        final LocalDateTime now = DateUtil.now();
        for( Map<String,Object> item:data_list ){
            if( null==item.get("open_date") || null==item.get("code") || null==item.get("name") || null==item.get("status") ){
                return ResultBuild.fail("必要参数缺失请检查~");
            }
            item.put("id",seqGenService.genMediumPrimaryKey());
            item.put("create_by",user_id);
            item.put("create_time",now);
            item.put("update_by",user_id);
            item.put("update_time",now);
        }
        int insert_count = dbSQLDao.insert("com.mee.xml.sv1.TOfflineStore.insertBatch", data_list);
        LOG.info("t_offline_store写入数据{}条", insert_count);
        return ResultBuild.success("成功导入:"+data_list.size() + "条数据");
    }

}
