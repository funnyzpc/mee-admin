package com.mee.sys.service.impl;

import com.mee.common.service.SeqGenServiceImpl;
import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import com.mee.core.dao.DBSQLDao;
import com.mee.core.model.Page;
import com.mee.sys.service.QrtzNodeService;
import org.quartz.Scheduler;
import org.quartz.impl.QrtzNode;
import org.quartz.impl.StdScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * 定时任务::节点实例表(QrtzNode) 业务接口
 *
 * @author  ash
 * @version v1.1
 * @date    2024-09-18 10:17:53
*/
@Service
public class QrtzNodeServiceImpl implements QrtzNodeService {

    /**
    *   日志
    */
    private static final Logger LOG = LoggerFactory.getLogger(QrtzNodeServiceImpl.class);

    /**
    *   DAO
    */
    public final DBSQLDao dbSQLDao;
    
    /**
    * 主键生成器
    */
    public final SeqGenServiceImpl seqGenService;

    private final Scheduler scheduler;

    public QrtzNodeServiceImpl(DBSQLDao dbSQLDao, SeqGenServiceImpl seqGenService, DataSource dataSource) {
        this.dbSQLDao = dbSQLDao;
        this.seqGenService = seqGenService;
        this.scheduler = new StdScheduler(dataSource);;
    }

    /**
     *  定时任务::节点实例表:列表
     * @param page_no    分页
     * @param page_size  分页大小
     * @param application 调度名称/应用名称
     * @param host_ip 实例机器IP
     * @param host_name 实例机器名称
     * @param state 状态 N.停止/不可用  Y.开启/可用
     * @return 分頁數據
     */
    @Override
    public MeeResult<Page<QrtzNode>> list(Integer page_no, Integer page_size , String application, String host_ip, String host_name, String state){
      LOG.info("导出接收到参数 {},{},{},{},{},{}",page_no,page_size ,application,host_ip,host_name,state);
      Map<String,Object> param = new HashMap<String,Object>(4,1);
      param.put("application",null==application||"".equals(application)?null:application );
      param.put("host_ip",null==host_ip||"".equals(host_ip)?null:host_ip );
      param.put("host_name",null==host_name||"".equals(host_name)?null:host_name );
      param.put("state",null==state||"".equals(state)?null:state );
      Page list = dbSQLDao.list("com.mee.xml.sys.QrtzNode.findList",param,page_no,page_size);
      return ResultBuild.build(list);
    }

//    /**
//     * 定时任务::节点实例表::按主键查询
//     *
//     * @param application 主键
//     * @return 定时任务::节点实例表
//    */
//    @Override
//    public MeeResult<QrtzNode> findByApplication(String application){
//      LOG.info("开始查询:{}",application);
//      Map<String,Object> param = new HashMap<String,Object>(2,1);
//      param.put("application",application);
//      QrtzNode qrtzNode = dbSQLDao.findOne("com.mee.xml.sys.QrtzNode.findByApplication", param);
//      return ResultBuild.build(qrtzNode);
//    }

    /**
     * 定时任务::节点实例表::新增
     *
     * @param qrtzNode 定时任务::节点实例表
     * @return 插入条数
    */
    @Override
    public MeeResult<Integer> add(QrtzNode qrtzNode){
        LOG.info("接收到参数 {}", qrtzNode);
        if(null==qrtzNode.getApplication() || null==qrtzNode.getHostIp()|| null==qrtzNode.getHostName() ||null==qrtzNode.getState() ){
            return ResultBuild.fail("必要参数缺失请检查~");
        }
        String state = qrtzNode.getState();
        if(!"N".equals(state) && !"Y".equals(state) ){
            return ResultBuild.fail("状态仅可为: N or Y");
        }
        qrtzNode.setTimeCheck(System.currentTimeMillis()/1000*1000);
        // 通用字段
//        int insert_count = dbSQLDao.insert("com.mee.xml.sys.QrtzNode.insert",qrtzNode);
        Object[] result = scheduler.addNode(qrtzNode);
        int insert_count = (int)result[0];
        if(insert_count>0){
            return ResultBuild.build(insert_count);
        }else{
            return ResultBuild.fail((String)result[1]);
        }
    }


    /**
     * 定时任务::节点实例表::修改
     *
     * @param qrtzNode 定时任务::节点实例表
     * @return 更新条数
    */
    @Override
    public MeeResult<Integer> update(QrtzNode qrtzNode){
      LOG.info("接收到参数 {}",qrtzNode);
      if( null==qrtzNode.getApplication()||null==qrtzNode.getHostIp()  ){
          return ResultBuild.fail("必要参数缺失，请检查~");
      }
      qrtzNode.setTimeCheck(System.currentTimeMillis()/1000*1000);
      // 通用字段
//      int update_count = dbSQLDao.update("com.mee.xml.sys.QrtzNode.update",qrtzNode);
      int update_count = scheduler.updateNode(qrtzNode);
      LOG.info("已更新定时任务::节点实例表明细：{}->{}条",qrtzNode,update_count);
      if(update_count>0){
          return ResultBuild.build(update_count);
      }else{
          return ResultBuild.fail("更新节点失败!");
      }
    }

    /**
     * 定时任务::节点实例表::删除
     *
     * @id 定时任务::节点实例表 主键
     * @return 删除条数
    */
    @Override
    public MeeResult<Integer> deleteByApplication(String application,String host_ip){
      LOG.info("开始查询:{}",application);
//      if( "".equals(application) || "".equals(host_ip)){
//          LOG.error("必要参数为空:{}",application);
//          return ResultBuild.fail("必要参数为空[application]");
//      }
//      Map<String,Object> param = new HashMap<String,Object>(2,1);
//      param.put("application",application);
//      param.put("host_ip",host_ip);
//      int delete_count = dbSQLDao.delete("com.mee.xml.sys.QrtzNode.deleteByApplication", param);
      int delete_count = scheduler.deleteNode(application,host_ip);
        if(delete_count>0){
            return ResultBuild.build(delete_count);
        }else{
            return ResultBuild.fail("删除节点失败!");
        }
    }

//    /**
//     * 定时任务::节点实例表::批量删除
//     *
//     * @applications 定时任务::节点实例表 主键集合
//     * @return 删除条数
//    */
//    @Override
//    public MeeResult<Integer> deleteBatch(String[] applications){
//      if( 0==applications.length ){
//        LOG.error("必要参数为空:{}",applications);
//        return ResultBuild.fail("必要参数为空[application]");
//      }
//      Map<String,Object> param = new HashMap<String,Object>(2,1);
//      param.put("list",applications);
//      //int delete_count = dbSQLDao.delete("com.mee.module.sys.mapper.qrtz_node.deleteBatch", param);
//      int delete_count = dbSQLDao.delete("com.mee.xml.sys.QrtzNode.deleteBatch", param);
//      LOG.info("已删除记录{}->{}条",applications,delete_count);
//      return ResultBuild.build(delete_count);
//    }

    @Override
    public MeeResult<Integer> nodeState(QrtzNode qrtzNode) {
//        final String state = qrtzNode.getState();
//        if(!"N".equals(state) && !"Y".equals(state) ){
//            return ResultBuild.fail("状态仅可为: N or Y");
//        }
//        if(null==qrtzNode.getApplication() || null==qrtzNode.getHostIp()){
//            return ResultBuild.fail("必要参数为空");
//        }
//        qrtzNode.setTimeCheck(System.currentTimeMillis()/1000*1000);
//        int update_count = dbSQLDao.update("com.mee.xml.sys.QrtzNode.updateState",qrtzNode);
//        return update_count<1?ResultBuild.FAIL():ResultBuild.build(update_count);
        int update_count = scheduler.updateNodeState(qrtzNode);
        if(update_count>0){
            return ResultBuild.build(update_count);
        }else{
            return ResultBuild.fail("状态调整失败!");
        }
    }

}
