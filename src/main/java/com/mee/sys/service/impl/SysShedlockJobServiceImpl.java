package com.mee.sys.service.impl;

import com.mee.common.service.SeqGenServiceImpl;
import com.mee.common.util.DateUtil;
import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import com.mee.common.util.SeqGenUtil;
import com.mee.common.util.excel.ExcelWriteUtil;
import com.mee.core.dao.DBSQLDao;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysShedlockJob;
import com.mee.sys.service.SysShedlockJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 集群分佈式鎖-任务配置(SysShedlockJob) 业务接口
 *
 * @author  ash
 * @version v1.1
 * @date    2024-06-18 16:31:16
*/
@Service
public class SysShedlockJobServiceImpl implements SysShedlockJobService {

    /**
    *   日志
    */
    private static final Logger LOG = LoggerFactory.getLogger(SysShedlockJobServiceImpl.class);

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
     *  集群分佈式鎖-任务配置:列表
     * @param page_no    分页
     * @param page_size  分页大小
     * @param application 当前实例应用
     * @param name 任務名稱(即ID)
     * @param host_ip 当前实例应用所属IP
     * @param locked_at 任務開始鎖定
     * @param state 0.close关闭 1.open开启(默认) 局部限制标志位(只限制所有相同实例中的此任务)
     * @param label 任務標識
     * @return 分頁數據
     */
    @Override
    public MeeResult<Page<SysShedlockJob>> list(Integer page_no,Integer page_size ,String application,String name,String host_ip,String state,String label){
      LOG.info("导出接收到参数 {},{},{},{},{},{},{}",page_no,page_size ,application,name,host_ip,state,label);
      Map<String,Object> param = new HashMap<String,Object>(9,1);
      param.put("application",null==application||"".equals(application)?null:application );
      param.put("name",null==name||"".equals(name.trim())?null:name+"%" );
      param.put("host_ip",null==host_ip||"".equals(host_ip)?null:host_ip );
//      param.put("locked_at",null==locked_at||"".equals(locked_at)?null:locked_at );
      param.put("state",null==state||"".equals(state)?null:state );
      param.put("label",null==label||"".equals(label)?null:label );
      //Page list = dbSQLDao.list("com.mee.module.sys.mapper.sys_shedlock_job.findList",param,page_no,page_size);
      Page list = dbSQLDao.list("com.mee.xml.sys.SysShedlockJob.findList",param,page_no,page_size);
      if(null!=list.getData() && !list.getData().isEmpty()){
          LocalDateTime now = DateUtil.now();
          List<SysShedlockJob> dataList = list.getData();
          for( SysShedlockJob item:dataList ){
              // UTC to Local
              if(null!=item.getLocked_at()){
                  item.setLocked_at(item.getLocked_at().plusHours(+8));
              }
              if(null!=item.getLock_until()){
                  item.setLock_until(item.getLock_until().plusHours(+8));
              }
              String callType = item.getCall_type();
              String callValue = item.getCall_value();
              if(null==callValue || null==callValue){
                  continue;
              }
              if("CRON".equals(callType)){
                  LocalDateTime next = CronExpression.parse(callValue).next(now);
                  item.setCall_next(next);
              }else if("RATE".equals(callType)){
                  item.setCall_next( now.plusSeconds(Long.parseLong(callValue)) );
              }else if("DELAY".equals(callType)){
                  item.setCall_next( now.plusSeconds(Long.parseLong(callValue)) );
              }
          }
      }
      return ResultBuild.build(list);
    }

    /**
     * 集群分佈式鎖-任务配置::按主键查询
     *
     * @param application 主键
     * @return 集群分佈式鎖-任务配置
    */
    @Override
    public MeeResult<SysShedlockJob> findByApplication(String application){
      LOG.info("开始查询:{}",application);
      //if( "".equals(application)){
      //  LOG.error("必要参数为空:{}",application);
      //  return ResultBuild.fail("必要参数为空[application]");
      //}
      Map<String,Object> param = new HashMap<String,Object>(2,1);
      param.put("application",application);
      //SysShedlockJob sysShedlockJob = dbSQLDao.queryOne("com.mee.module.sys.mapper.sys_shedlock_job.findByApplication", param);
      SysShedlockJob sysShedlockJob = dbSQLDao.findOne("com.mee.xml.sys.SysShedlockJob.findByApplication", param);
      return ResultBuild.build(sysShedlockJob);
    }

    /**
     * 集群分佈式鎖-任务配置::新增
     *
     * @param sysShedlockJob 集群分佈式鎖-任务配置
     * @return 插入条数
    */
    @Override
    public MeeResult<Integer> add(SysShedlockJob sysShedlockJob){
      LOG.info("接收到参数 {}", sysShedlockJob);
      if(null==sysShedlockJob.getApplication() || null==sysShedlockJob.getName() || null==sysShedlockJob.getState() ){
          return ResultBuild.fail("参数缺失请检查~");
      }
        String data = sysShedlockJob.getData();
        if( null!=data && !"".equals(data.trim())){
            // 必须为json
            if( data.startsWith("{") && !data.endsWith("}") ){
                return ResultBuild.fail("JOB传入数据必须为json格式!");
            }
            if( data.startsWith("[") && !data.endsWith("]")){
                return ResultBuild.fail("JOB传入数据必须为json格式!");
            }
        }
      final LocalDateTime now = DateUtil.now();
      // 获取UTC时间
      LocalDateTime utcNow = dbSQLDao.findOne("com.mee.xml.sys.SysShedlockJob.findUTC", new HashMap());
      utcNow=utcNow.withNano(0);
        // 通用字段
      sysShedlockJob.setUpdate_time(now);
      sysShedlockJob.setLocked_at(utcNow);
      sysShedlockJob.setLock_until(utcNow);
      //int insert_count = dbSQLDao.create("com.mee.module.sys.mapper.sys_shedlock_job.insert",sysShedlockJob);
      int insert_count = dbSQLDao.insert("com.mee.xml.sys.SysShedlockJob.insert",sysShedlockJob);
      return ResultBuild.build(insert_count);
    }


    /**
     * 集群分佈式鎖-任务配置::修改
     *
     * @param sysShedlockJob 集群分佈式鎖-任务配置
     * @return 更新条数
    */
    @Override
    public MeeResult<Integer> update(SysShedlockJob sysShedlockJob){
      LOG.info("接收到参数 {}",sysShedlockJob);
      if( null==sysShedlockJob.getApplication()||null==sysShedlockJob.getName()||null==sysShedlockJob.getState() ){
          return ResultBuild.fail("必要参数缺失，请检查~");
      }
      String data = sysShedlockJob.getData();
      if( null!=data && !"".equals(data.trim())){
          // 必须为json
          if( data.startsWith("{") && !data.endsWith("}") ){
              return ResultBuild.fail("JOB传入数据必须为json格式!");
          }
          if( data.startsWith("[") && !data.endsWith("]")){
              return ResultBuild.fail("JOB传入数据必须为json格式!");
          }
      }
        final LocalDateTime now = DateUtil.now();
      // 通用字段
      sysShedlockJob.setUpdate_time(now);
      //int update_count = dbSQLDao.update("com.mee.module.sys.mapper.sys_shedlock_job.update",sysShedlockJob);
      int update_count = dbSQLDao.update("com.mee.xml.sys.SysShedlockJob.update",sysShedlockJob);
      LOG.info("已更新集群分佈式鎖-任务配置明细：{}->{}条",sysShedlockJob,update_count);
      return ResultBuild.build(update_count);
    }

    /**
     * 集群分佈式鎖-任务配置::删除
     *
     * @id 集群分佈式鎖-任务配置 主键
     * @return 删除条数
    */
    @Override
    public MeeResult<Integer> deleteByApplication(String application,String name){
      LOG.info("开始查询:{}",application);
      if( "".equals(application) || "".equals(name)){
          LOG.error("必要参数为空:application:{},name:{}",application,name);
          return ResultBuild.fail("必要参数为空[application、deleteByApplication]");
      }
      Map<String,Object> param = new HashMap<String,Object>(2,1);
      param.put("application",application);
      param.put("name",name);
      int delete_count = dbSQLDao.delete("com.mee.xml.sys.SysShedlockJob.deleteByApplication", param);
      return ResultBuild.build( delete_count );
    }

    @Override
    public MeeResult<Integer> jobState(String application, String name, String state) {
        if( "".equals(application.trim()) || "".equals(name.trim()) || "".equals(state.trim()) ){
            LOG.error("参数异常:application:{},name:{},state:{}",application,name,state);
            return ResultBuild.fail("必要参数为空[application、name、state]");
        }
        Map<String,Object> param = new HashMap<String,Object>(4,1);
        param.put("application",application);
        param.put("name",name);
        param.put("state",state);
        param.put("update_time",new Date());
        int update_count = dbSQLDao.update("com.mee.xml.sys.SysShedlockJob.update", param);
        return ResultBuild.build( update_count );
    }


    /**
     *  集群分佈式鎖-任务配置:导出
     * @param response   响应体
     * @param page_no    分页
     * @param page_size  分页大小
     * @param application 当前实例应用
     * @param name 任務名稱(即ID)
     * @param host_ip 当前实例应用所属IP
     * @param locked_at 任務開始鎖定
     * @param state 0.close关闭 1.open开启(默认) 局部限制标志位(只限制所有相同实例中的此任务)
     * @param label 任務標識
     * @return 導出excel文件
    */
    @Override
    public void doExport(HttpServletResponse response,Integer page_no,Integer page_size,String application,String name,String host_ip,LocalDateTime locked_at,String state,String label) {
        LOG.info("导出接收到参数 {},{},{},{},{},{},{},{}",page_no,page_size ,application,name,host_ip,locked_at,state,label);
        Map<String,Object> param = new HashMap<String,Object>(9,1);
        param.put("application",null==application||"".equals(application)?null:application );
        param.put("name",null==name||"".equals(name)?null:name );
        param.put("host_ip",null==host_ip||"".equals(host_ip)?null:host_ip );
        param.put("locked_at",null==locked_at||"".equals(locked_at)?null:locked_at );
        param.put("state",null==state||"".equals(state)?null:state );
        param.put("label",null==label||"".equals(label)?null:label );
        //Page list = dbSQLDao.list("com.mee.module.sys.mapper.sys_shedlock_job.findList",param,page_no,page_size);
        Page list = dbSQLDao.list("com.mee.xml.sys.SysShedlockJob.findList",param,page_no,page_size);
        List<SysShedlockJob> data_list = list.getData();
        String[] header_field= { "应用","任务KEY","当前实例应用所属IP","任務開始鎖定","任務鎖定至","任務執行人","状态[0关闭 1开启]","job传入数据","任务描述","定时任务类型","定时任务值","最后更新时间"};
        String[] data_field = { "application","name","host_ip","locked_at","lock_until","locked_by","state","data","label","call_type","call_value","update_time" };

        File file = ExcelWriteUtil.toXlsxByObj( data_list,header_field,data_field);
        String seq = SeqGenUtil.genSeq();
        ResultBuild.toResponse(response,file,"集群分佈式鎖-任务配置_导出_"+seq+".xlsx");
    }

}
