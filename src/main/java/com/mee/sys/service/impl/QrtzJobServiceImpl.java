package com.mee.sys.service.impl;

import com.mee.common.service.SeqGenServiceImpl;
import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import com.mee.core.dao.DBSQLDao;
import com.mee.core.model.Page;
import com.mee.sys.service.QrtzJobService;
import org.quartz.Scheduler;
import org.quartz.impl.QrtzApp;
import org.quartz.impl.QrtzExecute;
import org.quartz.impl.QrtzJob;
import org.quartz.impl.StdScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * 定时任务::任务配置表(QrtzJob) 业务接口
 *
 * @author  ash
 * @version v1.1
 * @date    2024-09-14 16:11:41
*/
@Service
public final class QrtzJobServiceImpl implements QrtzJobService {

    /**
    *   日志
    */
    private static final Logger LOG = LoggerFactory.getLogger(QrtzJobServiceImpl.class);

    /**
    *   DAO
    */
    public final DBSQLDao dbSQLDao;
    
    /**
    * 主键生成器
    */
    public final SeqGenServiceImpl seqGenService;
    /**
     * quartz定时任务api
     */
    private final Scheduler scheduler;

    public QrtzJobServiceImpl(DBSQLDao dbSQLDao, SeqGenServiceImpl seqGenService, DataSource dataSource) {
        this.dbSQLDao = dbSQLDao;
        this.seqGenService = seqGenService;
        this.scheduler=new StdScheduler(dataSource);
    }
    //    /**
//     * 数据源，给quartz配置用
//     */
//    private final DataSource dataSource;
//    /**
//     * Scheduler quartz的操作对象
//     */
//    private final Scheduler scheduler;
//
//    public QrtzJobServiceImpl(HikariDataSource dataSource) {
//        this.dataSource = dataSource;
//        this.scheduler = new StdScheduler(dataSource);
//    }

    /**
     *  定时任务::任务配置表:列表
     * @param page_no    分页
     * @param page_size  分页大小
     * @param application 调度名称/应用名称
     * @param state 任务状态 传入_默认INIT(EXECUTING.执行中 PAUSED.暂停 COMPLETE.完成 ERROR.异常 INIT.初始化/未启动）
     * @param job_class 任务全类名
     * @param job_data 任务数据
     * @param job_description 任务描述
     * @return 分頁數據
     */
    @Override
    public MeeResult<Page<QrtzJob>> list(Integer page_no,Integer page_size ,String application,String state,String job_class,String job_data,String job_description){
        LOG.info("导出接收到参数 {},{},{},{},{},{},{}",page_no,page_size ,application,state,job_class,job_data,job_description);
        Map<String,Object> param = new HashMap<String,Object>(6,1);
        param.put("application",null==application||"".equals(application)?null:"%"+application+"%" );
        param.put("state",null==state||"".equals(state)?null:state );
        param.put("job_class",null==job_class||"".equals(job_class)?null:"%"+job_class+"%" );
        param.put("job_data",null==job_data||"".equals(job_data)?null:"%"+job_data+"%" );
        param.put("job_description",null==job_description||"".equals(job_description)?null:"%"+job_description+"%" );
        Page<QrtzJob> list = dbSQLDao.list("com.mee.xml.sys.QrtzJob.findList",param,page_no,page_size);
        return ResultBuild.build(list);
    }

//    /**
//     * 定时任务::任务配置表::按主键查询
//     *
//     * @param id 主键
//     * @return 定时任务::任务配置表
//    */
//    @Override
//    public MeeResult<QrtzJob> findById(String id){
//      LOG.info("开始查询:{}",id);
//      //if( "".equals(id)){
//      //  LOG.error("必要参数为空:{}",id);
//      //  return ResultBuild.fail("必要参数为空[id]");
//      //}
//      Map<String,Object> param = new HashMap<String,Object>(2,1);
//      param.put("id",id);
//      //QrtzJob qrtzJob = dbSQLDao.queryOne("com.mee.module.sys.mapper.qrtz_job.findById", param);
//      QrtzJob qrtzJob = dbSQLDao.findOne("com.mee.xml.sys.QrtzJob.findById", param);
//      return ResultBuild.build(qrtzJob);
//    }

    /**
     * 定时任务::任务配置表::新增
     *
     * @param qrtzJob 定时任务::任务配置表
     * @return 插入条数
    */
    @Override
    public MeeResult<Integer> add(QrtzJob qrtzJob){
      LOG.info("接收到参数 {}", qrtzJob);
      if(null==qrtzJob.getApplication()||null==qrtzJob.getState()||null==qrtzJob.getJobClass() ){
          return ResultBuild.fail("参数缺失请检查~");
      }
//      // check json
//      String job_data = qrtzJob.getJobData();
//      if(null!=job_data && !"".equals((job_data=job_data.trim()))  ){
//          if( (job_data.startsWith("{") && job_data.endsWith("}") && JacksonUtil.toObject(job_data,HashMap.class)!=null) ||
//                (job_data.startsWith("[") && job_data.endsWith("]") && JacksonUtil.toObject(job_data,ArrayList.class)!=null) ){
//
//          }else{
//              return ResultBuild.fail("异常的任务数据!");
//          }
//      }
//      qrtzJob.setJobData(job_data);
//      // 主键
//      qrtzJob.setId(Long.parseLong(seqGenService.genShortPrimaryKey()));
//      qrtzJob.setUpdateTime(System.currentTimeMillis()/1000*1000);
//      // 通用字段
//      int insert_count = dbSQLDao.insert("com.mee.xml.sys.QrtzJob.insert",qrtzJob);
      Object[] result = scheduler.addJob(qrtzJob);
      int insertCount = (int)result[0];
      if(insertCount>0){
          return ResultBuild.build(insertCount);
      }else{
          return ResultBuild.fail((String) result[1]);
      }
    }


    /**
     * 定时任务::任务配置表::修改
     *
     * @param qrtzJob 定时任务::任务配置表
     * @return 更新条数
    */
    @Override
    public MeeResult<Integer> update(QrtzJob qrtzJob){
        LOG.info("接收到参数 {}",qrtzJob);
        if( null==qrtzJob.getId()|| null==qrtzJob.getState()  ){
          return ResultBuild.fail("必要参数缺失，请检查~");
        }
//        // check json
//        String job_data = qrtzJob.getJobData();
//        if(null!=job_data && !"".equals((job_data=job_data.trim()))  ){
//            // 只有这两种json格式才正确
//            if( (job_data.startsWith("{") && job_data.endsWith("}") && JacksonUtil.toObject(job_data,HashMap.class)!=null) ||
//                    (job_data.startsWith("[") && job_data.endsWith("]") && JacksonUtil.toObject(job_data,ArrayList.class)!=null) ){
//
//            }else{
//                return ResultBuild.fail("异常的任务数据!");
//            }
//        }
//        qrtzJob.setJobData(job_data);
//        // 通用字段
//        qrtzJob.setUpdateTime(System.currentTimeMillis()/1000*1000);
//        int update_count = dbSQLDao.update("com.mee.xml.sys.QrtzJob.update",qrtzJob);
        Object[] result = scheduler.updateJob(qrtzJob);
        int update_count = (int)result[0];
        LOG.info("已更新定时任务::任务配置表明细：{}->{}条",qrtzJob,update_count);
        if(update_count>0){
            return ResultBuild.build(update_count);
        }else{
            return ResultBuild.fail((String)result[1]);
        }
    }

    /**
     * 定时任务::任务配置表::删除
     *
     * @id 定时任务::任务配置表 主键
     * @return 删除条数
    */
    @Override
    public MeeResult<Integer> deleteById(String id){
        LOG.info("开始查询:{}",id);
        if( "".equals(id)){
            LOG.error("必要参数为空:{}",id);
            return ResultBuild.fail("必要参数为空[id]");
        }
//        Map<String,Object> param = new HashMap<String,Object>(2,1);
//        param.put("pid",id);
        // 如果有execute存在则不可删除
        List<QrtzExecute> qrtz_executes = scheduler.getExecuteByJobId(id);
//        List<QrtzExecute> qrtz_executes = dbSQLDao.find("com.mee.xml.sys.QrtzExecute.findList", param);
        if(null!=qrtz_executes && !qrtz_executes.isEmpty()){
            return ResultBuild.fail("存在执行项,请先删除执行项！");
        }
//        param.clear();
//        param.put("id",id);
//        int delete_count = dbSQLDao.delete("com.mee.xml.sys.QrtzJob.deleteById", param);
        int delete_count =scheduler.deleteJob(id);
        if(delete_count>0){
            return ResultBuild.build(delete_count);
        }else{
            return ResultBuild.fail("删除任务配置失败!");
        }
    }

    @Override
    public MeeResult<Map> listApp(){
        List<QrtzApp> app_list = scheduler.getAllApp();
//        List<QrtzApp> app_list = dbSQLDao.find("com.mee.xml.sys.QrtzApp.findList", new HashMap());
        TreeMap<String, Map> result_list = new TreeMap<>();
        for(QrtzApp item:app_list){
            String application = item.getApplication();
            TreeMap<String, String> data = new TreeMap<>();
            data.put("v",application);
            data.put("l",application);
            result_list.put(item.getApplication(),data);
        }
        return ResultBuild.build(result_list);
    }

    @Override
    public MeeResult<Integer> updateJobState(String job_id,String state) {
        Object[] result = scheduler.updateJobStateInAll(job_id,state);
        int updateCount = (int)result[0];
        if(updateCount>0){
            return ResultBuild.build(updateCount);
        }else{
            return ResultBuild.fail((String)result[1]);
        }
    }


}
