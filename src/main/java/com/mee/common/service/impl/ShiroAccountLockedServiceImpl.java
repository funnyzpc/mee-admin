package com.mee.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
* 账户锁定及解锁
* @className    ShiroAccountLockedServiceImpl
* @author       shadow
* @date         2023/6/2 15:19
* @version      1.0
*/
@Service
public class ShiroAccountLockedServiceImpl {

    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(ShiroAccountLockedServiceImpl.class);

    /**
     * 账户重试计数器
     */
    private static ConcurrentHashMap<String, AtomicInteger> LOCKED_COUNTER = new ConcurrentHashMap<String,AtomicInteger>(8);

    /**
     * 最大重试次数
     */
    private static final int MAX_LIMIT_RETRY_COUNT = 5;

    /**
     *
     * @param user_name 用户名
     * @return true:锁定
     */
    public boolean isLocked(String user_name){
        if(!StringUtils.hasText(user_name)){
            LOG.error("用户名为空,请检查！");
            return Boolean.TRUE;
        }
        AtomicInteger counter = LOCKED_COUNTER.get(user_name);
        if(null == counter){
            return Boolean.FALSE;
        }
        if(counter.intValue()>= MAX_LIMIT_RETRY_COUNT){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 重试次数+1
     * @param user_name 用户名
     * @return
     */
    public void addOne(String user_name){
        if(!StringUtils.hasText(user_name)){
            LOG.error("用户名为空,请检查！");
            return;
        }
        AtomicInteger counter = LOCKED_COUNTER.get(user_name);
        if(!LOCKED_COUNTER.containsKey(user_name) || null == counter){
            LOCKED_COUNTER.put(user_name,new AtomicInteger(1));
            return;
        }
        counter.getAndIncrement();
        LOCKED_COUNTER.put(user_name,counter);
    }

    /**
     * 清除重试计数器
     * @param user_name 用户名
     */
    @Async
    public void clearCounter(String user_name){
        if(!StringUtils.hasText(user_name)){
            LOG.error("用户名为空,请检查！");
            return;
        }
        if(LOCKED_COUNTER.containsKey(user_name)){
            LOCKED_COUNTER.remove(user_name);
        }
    }
}
