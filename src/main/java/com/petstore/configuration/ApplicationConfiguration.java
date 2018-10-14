package com.petstore.configuration;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

/**
 * @author Nishant
 * 
 * @see {@link Configuration} 
 * 
 * */
@Configuration
@ComponentScan(basePackages= {"com.petstore.pet","com.petstore.security"})
public class ApplicationConfiguration
{
	
	@Value("${spring.datasource.driverClassName}")
	String driverClassName;
	@Value("${spring.datasource.url}")
	String url;
	@Value("${spring.datasource.username}")
	String username;
	@Value("${spring.datasource.password}")
	String password;
	
	@Value("${imageURLrootLocation}")
	String rootlocation;
	
	/**
	 * @return 
	 */
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DataSource dataSource = DataSourceBuilder.create().username(username).password(password).url(url)
				.driverClassName(driverClassName).build();
		return dataSource;
	}

	
	/**
	 * @param dataSource
	 * @return
	 */
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.scanPackages("com.petstore.pet.entities","com.petstore.security.entities");
		return sessionBuilder.buildSessionFactory();
	}
}
