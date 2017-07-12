package com.hfjy.service.order.config;

import com.hfjy.service.order.wr.DBConfig;
import com.hfjy.service.order.wr.DataSourceUtil;
import com.hfjy.service.order.wr.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.hfjy.service.order.dao", sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {
    @Resource
    private DBConfig dbConfig;

    @Bean(name = "dataSource")
    public DynamicDataSource dataSource() {
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setWriteDataSource(DataSourceUtil.getDataSource(dbConfig.getWrite()));
        dataSource.setReadDataSource(DataSourceUtil.getDataSource(dbConfig.getRead()));
        return dataSource;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/*.xml"));
        sessionFactoryBean.setDataSource(dataSource);
        return sessionFactoryBean.getObject();
    }
}
