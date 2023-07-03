package com.mee.core.dao;


import com.mee.core.model.Page;

import java.util.List;
import java.util.Map;

/**
 * @author funnyzpc
 * @description common sql interface
 *
 */
public interface SQLDao {
	/**
	 * execute a query of id
	 * @param id	mapper ID
	 * @return
	 */
	<T> List<T> find(String id);

	/**
	 * execute a query of id
	 * @param id	mapper ID
	 * @param count
	 * @return
	 */
	<T> List<T> find(String id, int count);

	/**
	 * execute a query of id, use params as the sql parameter
	 * @param id
	 * @param params
	 * @return
	 */
	<T> List<T> find(String id, Map params);

	/**
	 * execute a query of id, use params as the sql parameter
	 * @param id	mapper ID
	 * @param params
	 * @param count
	 * @return
	 */
	<T> List<T> find(String id, Map params, int count);

	/**
	 * 按列表数据查询
	 * @param id	mapper ID
	 * @param list 列表参数
	 * @return 列表数据
	 */
	<T> List<T> find(String id, List list);

	 /**
	  * get a page
	  * @param id	mapper ID
	  * @param pageIdx  页
	  * @param pageSize 记录数
	  * @return 分页数据
	  */
	 <T> Page<T> list(String id, int pageIdx, int pageSize);

	 /**
	  * get a page with parameter of o
	  * @param id	mapper ID
	  * @param params 参数
	  * @param pageIdx  页
	  * @param pageSize 记录数
	  * @return 分页数据
	  */
	 <T> Page<T> list(String id, Map params, int pageIdx, int pageSize);

	 /**
	 * execute a insert sql, and return rows count
	  * @param id	mapper ID
	 * @param params entity or map
	 * @return 写入行数
	 */
	int insert(String id, Object params);

	/**
	 * execute a update sql
	 * @param id	mapper ID
	 * @param obj entity or map
	 * @return 影响行数
	 */
	int update(String id, Object obj);

	/**
	 * execute a delete sql
	 * @param id	mapper ID
	 * @param params 参数
	 * @return  影响行数
	 */
	int delete(String id, Map params);


}
