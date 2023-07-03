package com.mee.test.service;

import com.mee.common.util.JacksonUtil;
import com.mee.common.util.MeeResult;
import com.mee.sys.service.impl.SysMenuServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

/**
 * SysMenu2ServiceImpl测试
 *
 * @author shadow
 * @version v1.0
 * @className SysMenu2ServiceImplTest
 * @date 2023/5/7 10:29 PM
 */
@SpringBootTest
@ActiveProfiles("dev")
public class SysMenu2ServiceImplTest {

    @Autowired
    public SysMenuServiceImpl sysMenu2Service;

    @Test
    public void test01(){
        MeeResult result = sysMenu2Service.menuAll(null);
        System.out.println(JacksonUtil.toJsonString( result ));
    }

}
