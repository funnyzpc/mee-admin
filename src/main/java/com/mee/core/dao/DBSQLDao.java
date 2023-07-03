package com.mee.core.dao;

import java.util.Map;


/**
* 数据库扩展接口
* @className    DBSQLDao
* @author       shadow
* @date         2023/7/3 10:24
* @version      1.0
*/
public interface DBSQLDao extends SQLDao {

    /**
     * 查询单条数据
     * @param id	mapper ID
     * @param params 参数
     * @return 单条数据
     * @param <T>
     */
    <T> T findOne(String id, Map params);

    /**
     * 删除数据
     * @param id	mapper ID
     * @param params 参数
     * @return 删除行数(影响行数)
     * @param <P>
     */
    <P> int delete(String id, P params);

}
