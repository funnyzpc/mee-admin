package com.mee.core.dao.impl;

import com.mee.common.service.SeqGenServiceImpl;
import com.mee.core.dao.DBSQLDao;
import com.mee.core.model.Page;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

/**
 * @author funnyzpc
 *
 */
@Repository
public class SQLDaoImpl implements DBSQLDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(SQLDaoImpl.class);

	private static final ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");

	@Resource
	private SeqGenServiceImpl seqGenService;
	@Resource
	private SqlSessionTemplate sqlSession;
	
	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public List find(String id) {
		return sqlSession.selectList(id);
	}
	@Override
	public List find(String id, int count) {
		return sqlSession.selectList(id, null, new RowBounds(0, count));
	}

	@Override
	public List find(String id, Map params) {
		return sqlSession.selectList(id, params);
	}
	@Override
	public List find(String id, Map params, int count) {
		return sqlSession.selectList(id, params, new RowBounds(0, count));
	}

	@Override
	public <T> T findOne(String id, Map params){
		return sqlSession.selectOne(id,params);
	}

	@Override
	public int insert(String id, Object obj) {
		return sqlSession.insert(id, obj);
	}

/*	@Override
	public <P extends BaseEntity> String create(String id, P params) {
		if(null != params.getId() && !"".equals(params.getId())){
			LOGGER.error("记录已存在：{}", JacksonUtil.toJsonString(params));
			return null;
		}
		// 设置ID、创建人、创建日期
		params.setId(seqGenService.genPrimaryKey());
		params.setCreate_date(LocalDateTime.now(ZONE_ID));
		if(null == params.getCreate_by()){
			params.setCreate_by(ShiroUtils.getUserId());
		}
		if(sqlSession.insert(id, params)>0){
			return  params.getId();
		}
		return null;
	}*/

	@Override
	public int update(String id, Object obj) {
		return sqlSession.update(id, obj);
	}

/*	@Override
	public <P extends BaseEntity> int update(String id, P params) {
		// 空ID不更新
		if(null == params.getId() || "".equals(params.getId().trim())){
			LOGGER.error("更新记录ID为空：{}", JacksonUtil.toJsonString(params));
			return 0;
		}
		return sqlSession.update(id, params);
	}*/

	@Override
	public <P> int delete(String id, P params) {
		return sqlSession.delete(id, params);
	}

	@Override
	public int delete(String id, Map params) {
		return sqlSession.delete(id, params);
	}

	@Override
	public List find(String id, List list) {
		return sqlSession.selectList(id, list);
	}
	
	@Override
	public Page list(String id, int pageIdx, int pageSize) {
		return list(id, null, pageIdx, pageSize);
	}

	@Override
	public <T> Page<T> list(String id, Map params, int pageIdx, int pageSize) {
  		Page p = new Page<>(params, pageIdx, pageSize);
		return p.setData(sqlSession.selectList(id, p));
	}
}
