package com.mee.core.dao;


import com.mee.common.entity.BaseEntity;

import java.util.Map;

/**
 * @author funnyzpc
 * 数据库扩展接口
 */
public interface DBSQLDao extends SQLDao {

    <T> T queryOne(String id, Map params);
    <P extends BaseEntity> int update(String id, P params);
}
