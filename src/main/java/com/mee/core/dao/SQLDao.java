package com.mee.core.dao;


import com.mee.common.entity.BaseEntity;
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
	 * @param id
	 * @param params
	 * @return
	 */
	List query(String id);

	/**
	 * execute a query of id
	 * @param id
	 * @param params
	 * @param count
	 * @return
	 */
	List query(String id, int count);

	/**
	 * execute a query of id, use params as the sql parameter
	 * @param id
	 * @param params
	 * @return
	 */
	List query(String id, Map params);

	/**
	 * execute a query of id, use params as the sql parameter
	 * @param id
	 * @param params
	 * @param count
	 * @return
	 */
	List query(String id, Map params, int count);

	 /**
	  * get a page
	  * @param id
	  * @param pageIdx
	  * @return
	  */
	 Page list(String id, int pageIdx, int pageSize);

	 /**
	  * get a page with parameter of o
	  * @param id
	  * @param params
	  * @param pageIdx
	  * @return
	  */
	 Page list(String id, Map params, int pageIdx, int pageSize);

	 /**
	 * execute a insert sql, and return rows count
	 * @param id
	 * @param params
	 * @return
	 */
	int create(String id, Map params);

	/** create item **/
	<P extends BaseEntity> String create(String id, P params);

	/**
	 * execute a update sql
	 * @param id
	 * @param params
	 */
	int update(String id, Map params);

	/**
	 * execute a delete sql
	 * @param id
	 * @param params
	 */
	int delete(String id, Map params);

	List query(String id, List list);
}
