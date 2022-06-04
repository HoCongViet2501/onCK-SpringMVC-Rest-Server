package com.se.spring.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.se.spring")
@PropertySource({"classpath:persistence-mssql.properties"})
public class AppConfig implements WebMvcConfigurer {
	@Autowired
	private Environment evn;
	
	@Bean
	public DataSource myDataSource() {
		ComboPooledDataSource myDataSource  =new ComboPooledDataSource();
		try {
			myDataSource.setDriverClass(evn.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myDataSource.setJdbcUrl(evn.getProperty("jdbc.url"));
		myDataSource.setUser(evn.getProperty("jdbc.user"));
		myDataSource.setPassword(evn.getProperty("jdbc.password"));
//		set connection pool props

		myDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		myDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		myDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		myDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		return myDataSource;
	}

	private int getIntProperty(String propName) {
		// TODO Auto-generated method stub
		String prop=evn.getProperty(propName);
		int intProp=Integer.parseInt(prop);
		return intProp;
	}
	@Bean
	public  LocalSessionFactoryBean sessionFactoryBean() {
		LocalSessionFactoryBean factoryBean=new  LocalSessionFactoryBean();
		factoryBean.setDataSource(myDataSource());
		factoryBean.setPackagesToScan(evn.getProperty("hibernate.packagesToScan"));
		Properties props=new Properties();
		props.setProperty("hibernate.dialect", evn.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", evn.getProperty("hibernate.show_sql"));
		
		factoryBean.setHibernateProperties(props);
		return factoryBean;
	}
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager=new  HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}
}
