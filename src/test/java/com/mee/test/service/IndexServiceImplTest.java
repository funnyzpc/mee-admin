package com.mee.test.service;

import com.mee.common.service.impl.IndexServiceImpl;
import com.mee.common.util.JacksonUtil;
import com.mee.common.util.MeeResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

/**
 * IndexServiceImpl测试
 *
 * @author shaoow
 * @version 1.0
 * @className IndexServiceImplTest
 * @date 2023/5/6 15:13
 */
@SpringBootTest
@ActiveProfiles("dev")
public class IndexServiceImplTest {

    @Autowired
    public IndexServiceImpl indexService;

    @Test
    public void test01(){
        MeeResult result = indexService.buildMenu();
        System.out.println(JacksonUtil.toJsonString( result ));
    }

}
