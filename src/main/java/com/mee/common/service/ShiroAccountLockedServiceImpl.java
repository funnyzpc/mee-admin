package com.mee.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther shadow
 * @description 账户锁定及解锁
 */
@Service
public class ShiroAccountLockedServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(ShiroAccountLockedServiceImpl.class);

    /**
     * 账户重试计数器
     */
    private static ConcurrentHashMap<String, AtomicInteger> locked_counter = new ConcurrentHashMap<String,AtomicInteger>(8);

    /**
     * 最大重试次数
     */
    private static final int max_limit_retry_count = 5;

    /**
     *
     * @param user_name
     * @return true:锁定
     */
    public boolean isLocked(String user_name){
        if(!StringUtils.hasText(user_name)){
            log.error("用户名为空,请检查！");
            return Boolean.TRUE;
        }
        AtomicInteger counter = locked_counter.get(user_name);
        if(null == counter){
            return Boolean.FALSE;
        }
        if(counter.intValue()>=max_limit_retry_count){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 重试次数+1
     * @param user_name
     * @return
     */
    public void addOne(String user_name){
        if(!StringUtils.hasText(user_name)){
            log.error("用户名为空,请检查！");
            return;
        }
        AtomicInteger counter = locked_counter.get(user_name);
        if(!locked_counter.containsKey(user_name) || null == counter){
            locked_counter.put(user_name,new AtomicInteger(1));
            return;
        }
        counter.getAndIncrement();
        locked_counter.put(user_name,counter);
    }

    /**
     * 清除重试计数器
     * @param user_name
     */
    @Async
    public void clearCounter(String user_name){
        if(!StringUtils.hasText(user_name)){
            log.error("用户名为空,请检查！");
            return;
        }
        if(locked_counter.containsKey(user_name)){
            locked_counter.remove(user_name);
        }
    }
}
