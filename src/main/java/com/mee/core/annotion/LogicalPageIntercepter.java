package com.mee.core.annotion;

import com.mee.core.model.Page;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * @author funnyzpc
 * @description 逻辑分页插件
 *
 */
@Intercepts(@Signature(type = Executor.class, method = "query", args = {
		MappedStatement.class, Object.class, RowBounds.class,
		ResultHandler.class }))
public class LogicalPageIntercepter implements Interceptor {
	private static final Logger log = LoggerFactory.getLogger(LogicalPageIntercepter.class);
	/**
	 * @intercept
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		if (args[1] != null && Page.class.isAssignableFrom(args[1].getClass())) {
			Page p = (Page) args[1];
			procPage((MappedStatement) args[0], p);
			args[1] = p.getParams();
			args[2] = new RowBounds((p.getIndex() - 1) * p.getSize(), p.getSize());
		}
		return invocation.proceed();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see Interceptor#plugin(Object)
	 */
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see
	 * Interceptor#setProperties(Properties)
	 */
	@Override
	public void setProperties(Properties properties) {

	}

	public static final String COUNT_TEMPLATE= "SELECT COUNT(1) FROM (%s) as c";

	protected void procPage(MappedStatement stmt, Page p) throws SQLException {
		BoundSql bsql = stmt.getBoundSql(p.getParams());
		String countSql = String.format(COUNT_TEMPLATE,bsql.getSql());
		Object params = bsql.getParameterObject();

		try(Connection conn = stmt.getConfiguration().getEnvironment().getDataSource().getConnection()){
			PreparedStatement pstmt = conn.prepareStatement(countSql);
			BoundSql countBS = new BoundSql(stmt.getConfiguration(), countSql,
					bsql.getParameterMappings(), params);
			setParameters(pstmt, stmt, countBS, params);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			p.setTotal(rs.getInt(1));
		}catch (Exception e){
			log.error("出现异常了 sql:{}",bsql.getSql(),e);
		}
	}

	/**
	 * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.
	 * DefaultParameterHandler
	 * 
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	private void setParameters(PreparedStatement ps,
			MappedStatement mappedStatement, BoundSql boundSql,
			Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters")
				.object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration
					.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null
					: configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException(
								"There was no TypeHandler found for parameter "
										+ propertyName + " of statement "
										+ mappedStatement.getId());
					}
					typeHandler.setParameter(ps,i+1, value,parameterMapping.getJdbcType());
				}
			}
		}
	}

	/*
	private MappedStatement copyFromMappedStatement(MappedStatement ms,
			SqlSource newSqlSource) {
		Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
				ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.cache(ms.getCache());
		MappedStatement newMs = builder.build();
		return newMs;
	}
	*/
}
