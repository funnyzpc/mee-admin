package com.mee.core.configuration;

import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* 全局异常代理
* @className    CommonExceptionHandler
* @author       shadow
* @date         2023/7/3 13:12
* @version      1.0
*/
@ControllerAdvice
public class CommonExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(CommonExceptionHandler.class);

    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public MeeResult<Void> exceptionHandler(Exception e){
        // 先记录
        LOG.error("异常了:",e);
        // 再返回
        String msg = e.getMessage();
        return ResultBuild.fail(null==msg?"异常:未知原因~":"异常:"+msg);
    }

}