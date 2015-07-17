package org.lu.config;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Import(PropertyResolveConfig.class)
@EnableJpaRepositories(basePackages = { "org.lu.repository" })
@ComponentScan(basePackages = { "org.lu.repository" })
@EnableTransactionManagement
public class DataBaseConfig {

	private static final String[] ENTITY_PACKAGES = { "org.lu.entity" };
	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
	private static final String HBM2DDL = "update";

	@Value("${db.driver}")
	private String driverClassName;

	@Value("${db.url}")
	private String jdbcUrl;

	@Value("${db.username}")
	private String username;

	@Value("${db.password}")
	private String password;
	
	@PostConstruct
	public void initialize() {
		System.out.println("resume data up");
	}

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName(driverClassName);
		dataSourceConfig.setJdbcUrl(jdbcUrl);
		dataSourceConfig.setUsername(username);
		dataSourceConfig.setPassword(password);

		return new HikariDataSource(dataSourceConfig);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.H2);
		vendorAdapter.setGenerateDdl(true);
		//vendorAdapter.setShowSql(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(ENTITY_PACKAGES);
		factory.setDataSource(dataSource());

		Properties jpaProperties = new Properties();
		jpaProperties.put(PROPERTY_NAME_HIBERNATE_DIALECT,
				"org.hibernate.dialect.H2Dialect");
		jpaProperties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, true);
		jpaProperties.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO, HBM2DDL);
		jpaProperties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY,
				"org.hibernate.cfg.ImprovedNamingStrategy");
		// jpaProperties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, true);
		factory.setJpaProperties(jpaProperties);
		return factory;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return txManager;
	}
}
