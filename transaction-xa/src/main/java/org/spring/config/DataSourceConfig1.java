package org.spring.config;

import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "org.spring.dao.user", sqlSessionFactoryRef = "SqlSessionFactory1")
public class DataSourceConfig1 {
    @Bean(name = "DataSource1")
    @Primary
    public DataSource testDataSource() {
        MysqlXADataSource mysqlXADataSource=new MysqlXADataSource();
        mysqlXADataSource.setUrl("jdbc:mysql://127.0.0.1:3306/fbs_transaction?serverTimezone=GMT%2B8");
        mysqlXADataSource.setPassword("");
        mysqlXADataSource.setUser("root");
        AtomikosDataSourceBean atomikosDataSourceBean=new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("Datasource1");
        return atomikosDataSourceBean;

    }

    @Bean(name = "SqlSessionFactory1")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("DataSource1") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/user/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "SqlSessionTemplate1")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("SqlSessionFactory1") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
