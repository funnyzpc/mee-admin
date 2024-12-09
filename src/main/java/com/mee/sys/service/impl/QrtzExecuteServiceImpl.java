package com.mee.sys.service.impl;


import com.mee.common.service.SeqGenServiceImpl;
import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import com.mee.core.dao.DBSQLDao;
import com.mee.core.model.Page;
import com.mee.sys.service.QrtzExecuteService;
import org.quartz.Scheduler;
import org.quartz.impl.QrtzExecute;
import org.quartz.impl.StdScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;


/**
 * 定时任务::执行配置表(QrtzExecute) 业务接口
 *
 * @author  ash
 * @version v1.1
 * @date    2024-09-14 16:11:46
*/
@Service
public class QrtzExecuteServiceImpl implements QrtzExecuteService {

    /**
    *   日志
    */
    private static final Logger LOG = LoggerFactory.getLogger(QrtzExecuteServiceImpl.class);

    /**
    *   DAO
    */
    public DBSQLDao dbSQLDao;
    
    /**
    * 主键生成器
    */
    public SeqGenServiceImpl seqGenService;
    /**
     * quartz定时任务api
     */
    private final Scheduler scheduler;

    public QrtzExecuteServiceImpl(DBSQLDao dbSQLDao, SeqGenServiceImpl seqGenService, DataSource dataSource) {
        this.dbSQLDao = dbSQLDao;
        this.seqGenService = seqGenService;
        this.scheduler=new StdScheduler(dataSource);
    }

    /**
     *  定时任务::执行配置表:列表
     * @param page_no    分页
     * @param page_size  分页大小
     * @param pid 关联任务(QRTZ_JOB::ID)
     * @param job_type 任务类型
     * @param state 任务状态 传入_默认INIT(EXECUTING.执行中 PAUSED.暂停 COMPLETE.完成 ERROR.异常 INIT.初始化/未启动）
     * @return 分頁數據
     */
    @Override
    public MeeResult<Page<QrtzExecute>> list(Integer page_no,Integer page_size ,Long pid,String job_type,String state){
      LOG.info("导出接收到参数 {},{},{},{},{}",page_no,page_size ,pid,job_type,state);
      Map<String,Object> param = new HashMap<String,Object>(14,1);
      param.put("pid",null==pid||"".equals(pid)?null:pid );
      param.put("job_type",null==job_type||"".equals(job_type)?null:job_type );
      param.put("state",null==state||"".equals(state)?null:state );
      Page<QrtzExecute> list = dbSQLDao.list("com.mee.xml.sys.QrtzExecute.findList",param,page_no,page_size);
      return ResultBuild.build(list);
    }

//    /**
//     * 定时任务::执行配置表::按主键查询
//     *
//     * @param id 主键
//     * @return 定时任务::执行配置表
//    */
//    @Override
//    public MeeResult<QrtzExecute> findById(String id){
//      LOG.info("开始查询:{}",id);
//      //if( "".equals(id)){
//      //  LOG.error("必要参数为空:{}",id);
//      //  return ResultBuild.fail("必要参数为空[id]");
//      //}
//      Map<String,Object> param = new HashMap<String,Object>(2,1);
//      param.put("id",id);
//      //QrtzExecute qrtzExecute = dbSQLDao.queryOne("com.mee.module.sys.mapper.qrtz_execute.findById", param);
//      QrtzExecute qrtzExecute = dbSQLDao.findOne("com.mee.xml.sys.QrtzExecute.findById", param);
//      return ResultBuild.build(qrtzExecute);
//    }

    /**
     * 定时任务::执行配置表::新增
     *
     * @param qrtzExecute 定时任务::执行配置表
     * @return 插入条数
    */
    @Override
    public MeeResult<Integer> add(QrtzExecute qrtzExecute){
      LOG.info("接收到参数 {}", qrtzExecute);
      if(null==qrtzExecute.getPid()||null==qrtzExecute.getJobType()||null==qrtzExecute.getState() ){
          return ResultBuild.fail("必要参数缺失请检查~");
      }
//        final Long pid = qrtzExecute.getPid();
//        HashMap<String, Object> param = new HashMap<>(1,1);
//        param.put("id",pid);
//        QrtzJob qrtzJob = dbSQLDao.findOne("com.mee.xml.sys.QrtzJob.findList", param);
//        if( null==qrtzJob ){
//            return ResultBuild.fail("对应的任务配置不存在~");
//        }
//        final long now = System.currentTimeMillis()/1000*1000;
        final String _job_type = qrtzExecute.getJobType();
//        final Long _start_time = qrtzExecute.getStartTime();
//        final Long _end_time = qrtzExecute.getEndTime();
//        qrtzExecute.setStartTime(null==_start_time || _start_time<1 ? now : _start_time);
//        qrtzExecute.setEndTime(null==_end_time || _end_time<1 ? -1L : _end_time);
//        qrtzExecute.setTimeTriggered(0); // 执行次数为0
//        qrtzExecute.setHostIp("HI"+SeqGenUtil.genSeq()); // todo ...
//        qrtzExecute.setHostName("HN"+SeqGenUtil.genSeq()); // todo ...
        qrtzExecute.setPrevFireTime(-1L);
        if(qrtzExecute.getStartTime()==null || qrtzExecute.getStartTime()<1 ){
            qrtzExecute.setStartTime(System.currentTimeMillis()/1000*1000+10000);// 必须要加10S 否则会丢失执行次数
        }
        if( "CRON".equals(_job_type) ){
            String zone_id = qrtzExecute.getZoneId();
            TimeZone zoneDefault = TimeZone.getDefault();
            qrtzExecute.setZoneId(null==zone_id||"".equals(zone_id.trim())?(null!=zoneDefault?zoneDefault.getID():"Asia/Shanghai"):zone_id);
            qrtzExecute.setRepeatCount(null);
            qrtzExecute.setRepeatInterval(null);
        }else if("SIMPLE".equals(_job_type) ){
            Integer _repeat_count = qrtzExecute.getRepeatCount();
            Integer _repeat_interval = qrtzExecute.getRepeatInterval();
            if(null==_repeat_interval || _repeat_interval<10 ){
                return ResultBuild.fail("SIMPLE任务:执行时间间隔! ");
            }
            qrtzExecute.setCron(null);
            qrtzExecute.setZoneId(null);
            qrtzExecute.setRepeatCount( null==_repeat_count || _repeat_count<1 ? -1 : _repeat_count );
        }else{
            return ResultBuild.fail("不支持的任务类型! "+_job_type);
        }
//      // 主键
//      qrtzExecute.setId(Long.parseLong(seqGenService.genShortPrimaryKey()));
//      int insert_count = dbSQLDao.insert("com.mee.xml.sys.QrtzExecute.insert",qrtzExecute);
      Object[] result = scheduler.addExecute(qrtzExecute);
      int insert_count = (int)result[0];
      if(insert_count>0){
          return ResultBuild.build(insert_count);
      }else{
          return ResultBuild.fail((String) result[1]);
      }
    }

    /**
     * 定时任务::执行配置表::修改
     *
     * @param qrtzExecute 定时任务::执行配置表
     * @return 更新条数
    */
    @Override
    public MeeResult<Integer> update(QrtzExecute qrtzExecute){
      LOG.info("接收到参数 {}",qrtzExecute);
      if( null==qrtzExecute.getId() || null==qrtzExecute.getPid()||null==qrtzExecute.getJobType()||null==qrtzExecute.getState()){
          return ResultBuild.fail("必要参数缺失，请检查~");
      }
      // 获取罪行的执行时间
        QrtzExecute qrtzExecute_ = scheduler.getExecuteByExecuteId(qrtzExecute.getId());
        qrtzExecute.setTimeTriggered(qrtzExecute_.getTimeTriggered());
        qrtzExecute.setPrevFireTime(qrtzExecute_.getPrevFireTime());
        qrtzExecute.setNextFireTime(qrtzExecute_.getNextFireTime());
        Object[] result = scheduler.updateExecute(qrtzExecute);
      int  update_count = (int)result[0];
      LOG.info("已更新定时任务::执行配置表明细：{}->{}条",qrtzExecute,update_count);
        if(update_count>0){
            return ResultBuild.build(update_count);
        }else{
            return ResultBuild.fail((String)result[1]);
        }
    }

    /**
     * 定时任务::执行配置表::删除
     *
     * @id 定时任务::执行配置表 主键
     * @return 删除条数
    */
    @Override
    public MeeResult<Integer> deleteById(String id){
      LOG.info("开始查询:{}",id);
      if( "".equals(id)){
          LOG.error("必要参数为空:{}",id);
          return ResultBuild.fail("必要参数为空[id]");
      }
//      Map<String,Object> param = new HashMap<String,Object>(2,1);
//      param.put("id",id);
//      int delete_count = dbSQLDao.delete("com.mee.xml.sys.QrtzExecute.deleteById", param);
      int delete_count = scheduler.deleteExecute(id);
        if(delete_count>0){
            return ResultBuild.build(delete_count);
        }else{
            return ResultBuild.fail("删除执行项失败!");
        }
    }

    @Override
    public MeeResult<Integer> updateExecuteState(String executeId, String state) {
        final int updateCount = scheduler.updateExecuteState(executeId,state);
        if(updateCount>0){
            return ResultBuild.build(updateCount);
        }else{
            return ResultBuild.fail("操作失败,请刷新后重试!");
        }
    }

//    /**
//     * 定时任务::执行配置表::批量删除
//     *
//     * @ids 定时任务::执行配置表 主键集合
//     * @return 删除条数
//    */
//    @Override
//    public MeeResult<Integer> deleteBatch(String[] ids){
//      if( 0==ids.length ){
//        LOG.error("必要参数为空:{}",ids);
//        return ResultBuild.fail("必要参数为空[id]");
//      }
//      Map<String,Object> param = new HashMap<String,Object>(2,1);
//      param.put("list",ids);
//      //int delete_count = dbSQLDao.delete("com.mee.module.sys.mapper.qrtz_execute.deleteBatch", param);
//      int delete_count = dbSQLDao.delete("com.mee.xml.sys.QrtzExecute.deleteBatch", param);
//      LOG.info("已删除记录{}->{}条",ids,delete_count);
//      return ResultBuild.build(delete_count);
//    }

//    /**
//     *  定时任务::执行配置表:导出
//     * @param response   响应体
//     * @param page_no    分页
//     * @param page_size  分页大小
//     * @param pid 关联任务(QRTZ_JOB::ID)
//     * @param job_type 任务类型
//     * @param state 任务状态 传入_默认INIT(EXECUTING.执行中 PAUSED.暂停 COMPLETE.完成 ERROR.异常 INIT.初始化/未启动）
//     * @return 導出excel文件
//    */
//    @Override
//    public void doExport(HttpServletResponse response,Integer page_no,Integer page_size
//,Long pid,String job_type,String state) {
//        LOG.info("导出接收到参数 {},{},{},{},{}",page_no,page_size ,pid,job_type,state);
//        Map<String,Object> param = new HashMap<String,Object>(14,1);
//        param.put("pid",null==pid||"".equals(pid)?null:pid );
//        param.put("job_type",null==job_type||"".equals(job_type)?null:job_type );
//        param.put("state",null==state||"".equals(state)?null:state );
//        //Page list = dbSQLDao.list("com.mee.module.sys.mapper.qrtz_execute.findList",param,page_no,page_size);
//        Page list = dbSQLDao.list("com.mee.xml.sys.QrtzExecute.findList",param,page_no,page_size);
//        List<QrtzExecute> data_list = list.getData();
//        String[] header_field= { "关联任务(QRTZ_JOB::ID)\npid","任务类型\njob_type","任务状态 传入_默认INIT(EXECUTING.执行中 PAUSED.暂停 COMPLETE.完成 ERROR.异常 INIT.初始化/未启动）\nstate","CRON:cron表达式\ncron","CRON:时区\nzone_id","SIMPLE:重复/执行次数\nrepeat_count","SIMPLE:执行时间间隔\nrepeat_interval","SIMPLE:执行时间\ntime_triggered","上一次执行时间\nprev_fire_time","下一次执行时间(当前次)\nnext_fire_time","最后操作:执行机器地址\nhost_ip","最后操作:执行机器名称\nhost_name","任务开始时间\nstart_time","任务结束时间,<1时没有结束时间\nend_time" };
//        String[] data_field = { "pid","job_type","state","cron","zone_id","repeat_count","repeat_interval","time_triggered","prev_fire_time","next_fire_time","host_ip","host_name","start_time","end_time" };
//        File file = ExcelWriteUtil.toXlsxByObj( data_list,header_field,data_field);
//        String seq = SeqGenUtil.genSeq();
//        ResultBuild.toResponse(response,file,"定时任务::执行配置表_导出_"+seq+".xlsx");
//    }

}
