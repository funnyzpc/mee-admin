package com.mee.sys.service.impl;

import com.mee.common.service.SeqGenServiceImpl;
import com.mee.common.util.DateUtil;
import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import com.mee.common.util.SeqGenUtil;
import com.mee.common.util.excel.ExcelWriteUtil;
import com.mee.core.dao.DBSQLDao;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysShedlockApp;
import com.mee.sys.service.SysShedlockAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 集群分佈式鎖-应用配置(SysShedlockApp) 业务接口
 *
 * @author  ash
 * @version v1.1
 * @date    2024-06-18 16:31:23
*/
@Service
public class SysShedlockAppServiceImpl implements SysShedlockAppService {

    /**
    *   日志
    */
    private static final Logger LOG = LoggerFactory.getLogger(SysShedlockAppServiceImpl.class);

    /**
    *   DAO
    */
    @Autowired
    public DBSQLDao dbSQLDao;
    
    /**
    * 主键生成器
    */
    @Autowired
    public SeqGenServiceImpl seqGenService;

    /**
     *  集群分佈式鎖-应用配置:列表
     * @param page_no    分页
     * @param page_size  分页大小
     * @param application 当前实例应用
     * @param host_ip 当前实例应用所属IP
     * @param host_name 创建机器
     * @param state 状态 0.关闭 1.开启
     * @return 分頁數據
     */
    @Override
    public MeeResult<Page<SysShedlockApp>> list(Integer page_no,Integer page_size ,String application,String host_ip,String host_name,String state){
      LOG.info("导出接收到参数 {},{},{},{},{},{}",page_no,page_size ,application,host_ip,host_name,state);
      Map<String,Object> param = new HashMap<String,Object>(4,1);
      param.put("application",null==application||"".equals(application)?null:application );
      param.put("host_ip",null==host_ip||"".equals(host_ip)?null:host_ip );
      param.put("host_name",null==host_name||"".equals(host_name)?null:host_name );
      param.put("state",null==state||"".equals(state)?null:state );
      Page list = dbSQLDao.list("com.mee.xml.sys.SysShedlockApp.findList",param,page_no,page_size);
      return ResultBuild.build(list);
    }

    /**
     * 集群分佈式鎖-应用配置::按主键查询
     *
     * @param application 主键
     * @return 集群分佈式鎖-应用配置
    */
    @Override
    public MeeResult<SysShedlockApp> findByApplication(String application){
      LOG.info("开始查询:{}",application);
      Map<String,Object> param = new HashMap<String,Object>(2,1);
      param.put("application",application);
      SysShedlockApp sysShedlockApp = dbSQLDao.findOne("com.mee.xml.sys.SysShedlockApp.findByApplication", param);
      return ResultBuild.build(sysShedlockApp);
    }

    /**
     * 集群分佈式鎖-应用配置::新增
     *
     * @param sysShedlockApp 集群分佈式鎖-应用配置
     * @return 插入条数
    */
    @Override
    public MeeResult<Integer> add(SysShedlockApp sysShedlockApp){
      LOG.info("接收到参数 {}", sysShedlockApp);
      if(null==sysShedlockApp.getApplication() || null==sysShedlockApp.getHost_ip() || null==sysShedlockApp.getState() ){
          return ResultBuild.fail("参数缺失请检查~");
      }
      final LocalDateTime now = DateUtil.now();
//      final String user_id = ShiroUtils.getUserId();
      // 通用字段
      sysShedlockApp.setUpdate_time(now);
      //int insert_count = dbSQLDao.create("com.mee.module.sys.mapper.sys_shedlock_app.insert",sysShedlockApp);
      int insert_count = dbSQLDao.insert("com.mee.xml.sys.SysShedlockApp.insert",sysShedlockApp);
      return ResultBuild.build(insert_count);
    }


    /**
     * 集群分佈式鎖-应用配置::修改
     *
     * @param sysShedlockApp 集群分佈式鎖-应用配置
     * @return 更新条数
    */
    @Override
    public MeeResult<Integer> update(SysShedlockApp sysShedlockApp){
      LOG.info("接收到参数 {}",sysShedlockApp);
      if( null==sysShedlockApp.getApplication()||null==sysShedlockApp.getHost_ip()||null==sysShedlockApp.getState() ){
          return ResultBuild.fail("必要参数缺失，请检查~");
      }
      // 通用字段
      sysShedlockApp.setUpdate_time(DateUtil.now());
      int update_count = dbSQLDao.update("com.mee.xml.sys.SysShedlockApp.update",sysShedlockApp);
      LOG.info("已更新集群分佈式鎖-应用配置明细：{}->{}条",sysShedlockApp,update_count);
      return ResultBuild.build(update_count);
    }

    /**
     * 集群分佈式鎖-应用配置::删除
     *
     * @id 集群分佈式鎖-应用配置 主键
     * @return 删除条数
    */
    @Override
    public MeeResult<Integer> deleteByApplication(String application,String host_ip){
      LOG.info("开始删除:application:{},hostIP:{}",application,host_ip);
      if( "".equals(application) || "".equals(host_ip)){
          LOG.error("必要参数为空:application:{},hostIP:{}",application,host_ip);
          return ResultBuild.fail("必要参数为空[application、hostIP]");
      }
      Map<String,Object> param = new HashMap<String,Object>(2,1);
      param.put("application",application);
      param.put("host_ip",host_ip);
      int delete_count = dbSQLDao.delete("com.mee.xml.sys.SysShedlockApp.deleteByApplication", param);
      return ResultBuild.build( delete_count );
    }

    @Override
    public MeeResult<Integer> appState(String application, String hostIP, String state) {
        if( "".equals(application.trim()) || "".equals(hostIP.trim()) || "".equals(state.trim()) ){
            LOG.error("参数异常:application:{},hostIP:{},state:{}",application,hostIP,state);
            return ResultBuild.fail("必要参数为空[application、hostIP、state]");
        }
        Map<String,Object> param = new HashMap<String,Object>(4,1);
        param.put("application",application);
        param.put("host_ip",hostIP);
        param.put("state",state);
        param.put("update_time",new Date());
        int update_count = dbSQLDao.update("com.mee.xml.sys.SysShedlockApp.update", param);
        return ResultBuild.build( update_count );
    }


    /**
     *  集群分佈式鎖-应用配置:导出
     * @param response   响应体
     * @param page_no    分页
     * @param page_size  分页大小
     * @param application 当前实例应用
     * @param host_ip 当前实例应用所属IP
     * @param host_name 创建机器
     * @param state 状态 0.关闭 1.开启
     * @return 導出excel文件
    */
    @Override
    public void doExport(HttpServletResponse response,Integer page_no,Integer page_size,String application,String host_ip,String host_name,String state) {
        LOG.info("导出接收到参数 {},{},{},{},{},{}",page_no,page_size ,application,host_ip,host_name,state);
        Map<String,Object> param = new HashMap<String,Object>(4,1);
        param.put("application",null==application||"".equals(application)?null:application );
        param.put("host_ip",null==host_ip||"".equals(host_ip)?null:host_ip );
        param.put("host_name",null==host_name||"".equals(host_name)?null:host_name );
        param.put("state",null==state||"".equals(state)?null:state );
        Page list = dbSQLDao.list("com.mee.xml.sys.SysShedlockApp.findList",param,page_no,page_size);
        List<SysShedlockApp> data_list = list.getData();
        String[] header_field= { "当前实例应用","当前实例应用所属IP","创建机器","状态\n0.关闭 1.开启","备注信息","创建及更新时间" };
        String[] data_field = { "application","host_ip","host_name","state","label","update_time"};
        File file = ExcelWriteUtil.toXlsxByObj( data_list,header_field,data_field);
        String seq = SeqGenUtil.genSeq();
        ResultBuild.toResponse(response,file,"集群分佈式鎖-应用配置_导出_"+seq+".xlsx");
    }

}
