package com.mee.common.web;

import com.mee.core.dao.DBSQLDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/common/dict")
public class CommonDictController {

    @Autowired
    private DBSQLDao dbsqlDao;

    @PostMapping("/list")
    public List<Object> list(String catalogCode){
        return dbsqlDao.query("com.mee.xml.Common.Dict.findList");
    }
}
