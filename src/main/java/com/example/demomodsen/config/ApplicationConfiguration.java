package com.example.demomodsen.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.example.demomodsen")
public class ApplicationConfiguration {

    DatabaseProperties databaseProperties;

    @Autowired
    public ApplicationConfiguration(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    @Value("${hibernate.dialect}")
    private String dialect;

    @Bean
    public BasicDataSource dataSource(){
        BasicDataSource bd = new BasicDataSource();
        bd.setDriverClassName(databaseProperties.driverClassName());
        bd.setUrl(databaseProperties.url());
        bd.setUsername(databaseProperties.username());
        bd.setPassword(databaseProperties.password());
        return bd;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(
                "com.example.demomodsen");
        sessionFactory.setHibernateProperties((Properties) new Properties().setProperty("hibernate.dialect",dialect));
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
