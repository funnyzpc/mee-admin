package com.mee.core.configuration;

import com.mee.core.annotion.PhysicalPageInterceptor;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;


/**
* 为mybatis设置sqlSession及mapper.xml配置
* @className    MybatisConfiguration
* @author       shadow
* @date         2023/6/21 10:45
* @version      1.0
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
        // 多廠商數據源支持,這裏只是申明，具體樣例見：com.mee.xml.SysDictDetail.findByNames
        // 所有數據庫操作，對於mybatis而言，他默認走不帶databaseId參數的mapper項，如果當前匹配到這個databaseId則走這個mapper項
        // 申明VendorDatabaseId后一般無需在配置文件中申明此項:mybatis.configuration.database-id
        // 如果有其他不知名數據庫廠商則建議調試VendorDatabaseIdProvider::getDatabaseId以獲取正確的key
        VendorDatabaseIdProvider vendorDatabaseIdProvider = new VendorDatabaseIdProvider();
        Properties properties = new Properties();
        properties.put("MySQL","mysql");
        properties.put("PostgreSQL","postgresql");
        // 如果當前為oracle數據庫則需要重寫分頁插件，參見: PhysicalPageInterceptor
        properties.put("Oracle","oracle");
        vendorDatabaseIdProvider.setProperties(properties);
        bean.setDatabaseIdProvider(vendorDatabaseIdProvider);
        return bean.getObject();
    }

}
