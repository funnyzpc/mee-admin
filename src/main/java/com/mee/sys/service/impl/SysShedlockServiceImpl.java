package com.mee.sys.service.impl;

import com.mee.common.util.DateUtil;
import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import com.mee.core.dao.DBSQLDao;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysShedlock;
import com.mee.sys.service.SysShedlockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 定时任务业务类
 *
 * @author shadow
 * @version v1.0
 * @className SysShedlockServiceImpl
 * @date 2023/6/4 1:06 AM
 */
@Deprecated
@Service
public class SysShedlockServiceImpl implements SysShedlockService {

    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(SysShedlockServiceImpl.class);

    @Autowired
    private DBSQLDao dbSQLDao;

    @Override
    public MeeResult<Page<SysShedlock>> list(String name, String label,LocalDateTime locked_at_start, LocalDateTime locked_at_end, int pageIdx, int pageSize){
        LOG.info("开始查询数据 pageIdx:{}, pageSize:{}",pageIdx,pageSize);
        Map<String,Object> queryParam = new HashMap<String,Object>(2,1);
        if(!StringUtils.isEmpty(name)){queryParam.put("name","%"+name+"%");}
        if(!StringUtils.isEmpty(label)){queryParam.put("label","%"+label+"%");}
        if(!StringUtils.isEmpty(locked_at_start)){
            queryParam.put("locked_at_start",locked_at_start);
        }
        if(!StringUtils.isEmpty(locked_at_end)){
            queryParam.put("locked_at_end",locked_at_end);
        }
        Page<SysShedlock> list = dbSQLDao.list("com.mee.xml.SysShedlock.findList", queryParam, pageIdx, pageSize);
        return ResultBuild.build(list);
    }

    @Override
    public MeeResult<Integer> update(SysShedlock sysShedlock){
        // 参数校验
        if(null == sysShedlock || null==sysShedlock.getName() || "".equals(sysShedlock.getLabel()) || null==sysShedlock.getStatus() ){
            return ResultBuild.fail("必要参数不可为空！[任务标识]");
        }
        if( "0".equals(sysShedlock.getStatus()) ){
            final LocalDateTime until_time = DateUtil.now().withYear(2099);
            sysShedlock.setLock_until(until_time);
        }
        // 更新数据
        int updateCount = dbSQLDao.update("com.mee.xml.SysShedlock.update",sysShedlock);
        LOG.info("更新sys_shedlock结果:{}",updateCount);
        return ResultBuild.build(updateCount);
    }

    @Override
    public MeeResult<Integer> delete(@RequestParam(required = true) String name){
        if(StringUtils.isEmpty(name)){
            return ResultBuild.FAIL();
        }
        Map<String,Object> params = new HashMap<String,Object>(1,1);
        params.put("name",name);
        int deleteCount = dbSQLDao.delete("com.mee.xml.SysShedlock.delete",params);
        if(deleteCount>0){
            return ResultBuild.build(deleteCount);
        }
        LOG.error("文件删除失败 name:{}",name);
        return ResultBuild.fail("删除失败");
    }

}
