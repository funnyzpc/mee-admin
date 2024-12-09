package com.mee.test.service;

import com.mee.core.dao.DBSQLDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


/**
 * SysUserTest
 *
 * @author shadow
 * @version 1.0
 * @className SysUserServiceTest
 * @date 2024/3/13 14:19
 */
@SpringBootTest
@ActiveProfiles("oracle")
public class SysUserServiceTest {
    /**
     *   Dao
     */
    @Autowired
    public DBSQLDao dbSQLDao;

    @Test
    public void test01(){
//        com.github.pagehelper.Page<Object> objects = new com.github.pagehelper.Page<>(1, 10);
//        List<SysUser> list = dbSQLDao.find("com.mee.xml.SysUser.findList");
//        System.out.println(list.size());
//        System.out.println(JacksonUtil.toJsonString(list));

    }



}
