package com.mee.core.annotion;


import com.mee.core.model.Page;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
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
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shadow
 * @description 物理分页插件
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,RowBounds.class, ResultHandler.class})
})
public class PhysicalPageOracleInterceptor implements Interceptor {
    private static final Logger log = LoggerFactory.getLogger(PhysicalPageOracleInterceptor.class);


    /**
     * 执行拦截逻辑的方法
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 参数列表
        Object[] args = invocation.getArgs();
        if (args[1] != null && Page.class.isAssignableFrom(args[1].getClass())) {
            final MappedStatement mappedStatement = (MappedStatement) args[0];
            final Page page = (Page)args[1];
            // 重置下参数invocation的参数,否则参数无法找到
            args[1]=page.getParams();
            final Object parameter = page.getParams();
            // final RowBounds rowBounds = (RowBounds) args[2];
            // 获取offset,即查询的起始位置
            int offset = (page.getIndex() - 1) * page.getSize();
            int limit = page.getSize();
            // 获取BoundSql对象，其中记录了包含"?"占位符的SQL语句
            final BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            // 获取BoundSql中记录的SQL语句
            String sql = boundSql.getSql().replaceAll("\n"," ").replaceAll("  "," ");
            procPage(mappedStatement, page);
            sql = getPagingSql(sql,offset,limit);
            log.info("page sql=>{}", sql);
            // 重置RowBounds对象
            // args[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
            // args[2] = new RowBounds(offset,limit);
            // 根据当前语句创建新的MappedStatement
            args[0] = createMappedStatement(mappedStatement, boundSql, sql);
        }
        return invocation.proceed();
    }

    // 分页缓存策略(分页大于10000的只获取一次 并且去掉后续的order by字段)
    private static ConcurrentHashMap<String,Integer> PAGE_CACHE = new ConcurrentHashMap(80,1);
    public static final String COUNT_TEMPLATE= "SELECT COUNT(1) FROM (%s) TMP_TABLE_";
    protected void procPage(MappedStatement stmt, Page p){
        // 获取缓存
        if(null == p.getParams() || (p.getParams() instanceof Map && ((Map)p.getParams()).isEmpty() ) ){
            if(null != PAGE_CACHE.get(stmt.getId())){
                p.setTotal(PAGE_CACHE.get(stmt.getId()));
                return;
            }
        }
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
            // 设置缓存
            if(null == p.getParams() || (p.getParams() instanceof Map && ((Map)p.getParams()).isEmpty() ) ) {
                if(rs.getInt(1)>10000){
                    PAGE_CACHE.put(stmt.getId(),rs.getInt(1));
                }
            }
        }catch (Exception e){
            log.error("获取分页异常了 page:{},sql:{}",p,countSql,e);
        }
    }

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

    private Object createMappedStatement(MappedStatement mappedStatement, BoundSql boundSql,String sql) {
        // 创建新的BoundSql对象
        BoundSql newBoundSql = createBoundSql(mappedStatement, boundSql, sql);
        Builder builder = new Builder(mappedStatement.getConfiguration(), mappedStatement.getId(),new BoundSqlSqlSource(newBoundSql), mappedStatement.getSqlCommandType());
        builder.useCache(mappedStatement.isUseCache());
        builder.cache(mappedStatement.getCache());
        builder.databaseId(mappedStatement.getDatabaseId());
        builder.fetchSize(mappedStatement.getFetchSize());
        builder.flushCacheRequired(mappedStatement.isFlushCacheRequired());

        builder.keyColumn(delimitedArrayToString(mappedStatement.getKeyColumns()));
        builder.keyGenerator(mappedStatement.getKeyGenerator());
        builder.keyProperty(delimitedArrayToString(mappedStatement.getKeyProperties()));

        builder.lang(mappedStatement.getLang());
        builder.resource(mappedStatement.getResource());

        builder.parameterMap(mappedStatement.getParameterMap());
        builder.resultMaps(mappedStatement.getResultMaps());
        builder.resultOrdered(mappedStatement.isResultOrdered());
        builder.resultSets(delimitedArrayToString(mappedStatement.getResultSets()));
        builder.resultSetType(mappedStatement.getResultSetType());

        builder.timeout(mappedStatement.getTimeout());
        builder.statementType(mappedStatement.getStatementType());

        return builder.build();

    }

    public String delimitedArrayToString(String[] array) {
        String result = "";
        if (array == null || array.length == 0) {
            return result;
        }
        for (int i = 0; i < array.length; i++) {
            result += array[i];
            if (i != array.length - 1) {
                result += ",";
            }
        }
        return result;
    }

    class BoundSqlSqlSource implements SqlSource {

        private BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

    private BoundSql createBoundSql(MappedStatement mappedStatement, BoundSql boundSql, String sql) {
        return new BoundSql(mappedStatement.getConfiguration(), sql,boundSql.getParameterMappings(), boundSql.getParameterObject());
    }

    /**
     * 重写sql
     */
    private String getPagingSql(String sql, int offset, int limit) {
        // SELECT TMP_TABLE_COUNT_.* FROM ( SELECT TMP_TABLE_.*,ROWNUM AS R_ FROM ( {SELECT * FROM SYS_USER} ) TMP_TABLE_) TMP_TABLE_COUNT_ WHERE TMP_TABLE_COUNT_.R_>0
        StringBuilder sqlBuilder = new StringBuilder(sql.length()+180);
        sqlBuilder.append("SELECT TMP_TABLE_COUNT_.* FROM ( SELECT TMP_TABLE_.*,ROWNUM AS R_ FROM (");
        sqlBuilder.append(sql);
        sqlBuilder.append(") TMP_TABLE_) TMP_TABLE_COUNT_ ");
        sqlBuilder.append(" WHERE TMP_TABLE_COUNT_.R_>"+offset);
        sqlBuilder.append(" AND   TMP_TABLE_COUNT_.R_<="+(offset+limit));
        return sqlBuilder.toString();
    }

    /**
     * 决定是否触发intercept()方法
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 根据配置初始化Interceptor对象
     */
    @Override
    public void setProperties(Properties properties) {
        log.info("properties: " + properties.getProperty("testProp"));
    }
}
