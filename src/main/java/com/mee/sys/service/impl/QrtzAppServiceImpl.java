package com.mee.sys.service.impl;

import com.mee.common.service.SeqGenServiceImpl;
import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import com.mee.core.dao.DBSQLDao;
import com.mee.core.model.Page;
import com.mee.sys.service.QrtzAppService;
import org.quartz.Scheduler;
import org.quartz.impl.QrtzApp;
import org.quartz.impl.StdScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * 定时任务::应用表(QrtzApp) 业务接口
 *
 * @author  ash
 * @version v1.1
 * @date    2024-09-14 10:23:41
*/
@Service
public class QrtzAppServiceImpl implements QrtzAppService {

    /**
    *   日志
    */
    private static final Logger LOG = LoggerFactory.getLogger(QrtzAppServiceImpl.class);

    /**
    *   DAO
    */
//    @Autowired
    private final DBSQLDao dbSQLDao;
    
    /**
    * 主键生成器
    */
//    @Autowired
    private final SeqGenServiceImpl seqGenService;
    private final Scheduler scheduler;

    public QrtzAppServiceImpl(DBSQLDao dbSQLDao, SeqGenServiceImpl seqGenService, DataSource dataSource) {
        this.dbSQLDao = dbSQLDao;
        this.seqGenService = seqGenService;
        this.scheduler=new StdScheduler(dataSource);
    }

    /**
     *  定时任务::应用表:列表
     * @param page_no    分页
     * @param page_size  分页大小
     * @param application 调度名称/应用名称
     * @param state 状态 N.停止/不可用  Y.开启/可用
     * @return 分頁數據
     */
    @Override
    public MeeResult<Page<QrtzApp>> list(Integer page_no, Integer page_size , String application, String state){
        LOG.info("导出接收到参数 {},{},{},{}",page_no,page_size ,application,state);
        Map<String,Object> param = new HashMap<String,Object>(4,1);
        param.put("application",null==application||"".equals(application)?null:application+"%" );
        param.put("state",null==state||"".equals(state)?null:state );
        Page<QrtzApp> list = dbSQLDao.list("com.mee.xml.sys.QrtzApp.findList",param,page_no,page_size);
        return ResultBuild.build(list);
    }

//    /**
//     * 定时任务::应用表::按主键查询
//     *
//     * @param application 主键
//     * @return 定时任务::应用表
//    */
//    @Override
//    public MeeResult<QrtzApp> findByApplication(String application){
//      LOG.info("开始查询:{}",application);
//      //if( "".equals(application)){
//      //  LOG.error("必要参数为空:{}",application);
//      //  return ResultBuild.fail("必要参数为空[application]");
//      //}
//      Map<String,Object> param = new HashMap<String,Object>(2,1);
//      param.put("application",application);
//      QrtzApp qrtzApp = dbSQLDao.findOne("com.mee.xml.sys.QrtzApp.findByApplication", param);
//      return ResultBuild.build(qrtzApp);
//    }

    /**
     * 定时任务::应用表::新增
     *
     * @param qrtzApp 定时任务::应用表
     * @return 插入条数
    */
    @Override
    public MeeResult<Integer> add(QrtzApp qrtzApp){
      LOG.info("接收到参数 {}", qrtzApp);
      if(null==qrtzApp.getState() || null==qrtzApp.getApplication() ){
          return ResultBuild.fail("参数缺失请检查~");
      }
//      long n = System.currentTimeMillis()/1000*1000;
//      qrtzApp.setTime_interval(15000L);
//      qrtzApp.setTime_pre(n);
//      qrtzApp.setTime_next(n);
//      // 通用字段
//      int insert_count = dbSQLDao.insert("com.mee.xml.sys.QrtzApp.insert",qrtzApp);
      Object[] result = scheduler.addApp(qrtzApp);
      int insert_count =  (int)result[0];
      if(insert_count>0){
          return ResultBuild.build(insert_count);
      }else{
          return ResultBuild.fail((String) result[1]);
      }
    }


//    /**
//     * 定时任务::应用表::修改
//     *
//     * @param qrtzApp 定时任务::应用表
//     * @return 更新条数
//    */
//    @Override
//    public MeeResult<Integer> update(QrtzApp qrtzApp){
//      LOG.info("接收到参数 {}",qrtzApp);
//      if( null==qrtzApp.getApplication()||null==qrtzApp.getState()||null==qrtzApp.getTimePre()||null==qrtzApp.getTimeNext() ){
//          return ResultBuild.fail("必要参数缺失，请检查~");
//      }
//      // 通用字段
//      int update_count = dbSQLDao.update("com.mee.xml.sys.QrtzApp.update",qrtzApp);
//      LOG.info("已更新定时任务::应用表明细：{}->{}条",qrtzApp,update_count);
//      return ResultBuild.build(update_count);
//    }

    /**
     * 定时任务::应用表::删除
     *
     * @id 定时任务::应用表 主键
     * @return 删除条数
    */
    @Override
    public MeeResult<Integer> deleteByApplication(String application){
      LOG.info("开始查询:{}",application);
      if( null==application || "".equals(application.trim())){
          LOG.error("必要参数为空:{}",application);
          return ResultBuild.fail("必要参数为空[application]");
      }
//      Map<String,Object> param = new HashMap<String,Object>(2,1);
//      param.put("application",application);
//      // 是否有子节点
//      List<QrtzNode> node_list = dbSQLDao.find("com.mee.xml.sys.QrtzNode.findList", param);
//      if( null!=node_list && !node_list.isEmpty()){
//          return ResultBuild.fail("请删除节点后再行删除应用！");
//      }
//      int delete_count = dbSQLDao.delete("com.mee.xml.sys.QrtzApp.deleteByApplication", param);
        Object[] result = scheduler.deleteApp(application);
      int delete_count = (int)result[0];
      if(delete_count>0){
          return ResultBuild.build( delete_count );
      }else{
          return ResultBuild.fail( (String)result[1] );
      }
    }

    @Override
    public MeeResult<Integer> appState(QrtzApp _qrtzApp) {
        // 先查询是否已经是目标状态
        // 应用状态调整
        // 节点状态同步调整
//        String application = _qrtzApp.getApplication();
//        String state = _qrtzApp.getState();
//        // 先查询是否已经是目标状态
//        Map<String,String> param = new HashMap<>(2,1);
//        param.put("application",application);
//        QrtzApp qrtzApp = dbSQLDao.findOne("com.mee.xml.sys.QrtzApp.findList", param);
//        if( !"N".equals(state) && !"Y".equals(state) ){
//            return ResultBuild.fail("状态仅可为: N or Y！");
//        }
//        if( null==qrtzApp || state.equals(qrtzApp.getState() ) ){
//            return ResultBuild.fail("记录为空或应用已经是目标状态了！");
//        }
//        // 应用状态调整
//        qrtzApp.setState(state);
//        int updateAppCount = dbSQLDao.update("com.mee.xml.sys.QrtzApp.updateState", qrtzApp);
//        if(updateAppCount<1){
//            return ResultBuild.fail("状态调整失败，请刷新后重试~");
//        }
//        // 节点状态同步调整
//        QrtzNode qrtzNode = new QrtzNode();
//        qrtzNode.setApplication(application);
//        qrtzNode.setState(state);
//        qrtzNode.setTimeCheck(System.currentTimeMillis()/1000*1000);
//        int updateNodeCount = dbSQLDao.update("com.mee.xml.sys.QrtzNode.updateState",qrtzNode);

        int updateCount = scheduler.updateAppState(_qrtzApp.getApplication(),_qrtzApp.getState());
        LOG.info("update app state {}条,{}",updateCount,_qrtzApp);
        if(updateCount>0){
            return ResultBuild.SUCCESS();
        }else{
            return ResultBuild.fail("调整失败,请重试!");
        }
    }


}
