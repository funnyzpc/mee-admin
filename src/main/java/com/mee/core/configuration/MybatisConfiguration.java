package com.mee.core.configuration;

import com.mee.core.annotion.PhysicalPageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author funnyzpc
 * @description 为mybatis设置sqlSession及mapper.xml配置
 */
@Configuration
public class MybatisConfiguration {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfiguration(new org.apache.ibatis.session.Configuration());
        // 加入sql语句拦截器
        bean.setPlugins(new Interceptor[] {new PhysicalPageInterceptor()});
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:dao/*/*.xml"));
        return bean.getObject();
    }

}
