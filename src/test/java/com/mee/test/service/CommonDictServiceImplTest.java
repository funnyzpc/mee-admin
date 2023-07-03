package com.mee.test.service;

import com.mee.common.service.impl.CommonDictServiceImpl;
import com.mee.common.util.JacksonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

/**
 * CommonDictServiceImpl
 *
 * @author shaoow
 * @version 1.0
 * @className CommonDictServiceImplTest
 * @date 2023/5/15 11:19
 */
@SpringBootTest
@ActiveProfiles("dev")
public class CommonDictServiceImplTest {

    @Autowired
    private CommonDictServiceImpl commonDictService;

    @Test
    public void test01(){
        String[] names = {"sys_oper_type","sys_job_status"};
        Map dicts = commonDictService.getDicts(names);
        System.out.println(JacksonUtil.toJsonString(dicts));
    }
}
