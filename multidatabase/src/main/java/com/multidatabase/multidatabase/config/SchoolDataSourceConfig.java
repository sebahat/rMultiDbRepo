package com.multidatabase.multidatabase.config;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "schoolEntityManagerFactory", transactionManagerRef = "schoolTransactionManager", basePackages = {
		"com.multidatabase.multidatabase.schoolRepo" })
public class SchoolDataSourceConfig {

	@Bean(name = "schoolDataSource")
	@ConfigurationProperties(prefix = "spring.school-datasource")
	public DataSource schoolDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public DataSourceInitializer initSchoolDataSource() {
		ResourceDatabasePopulator resourceDatabasePopulator2 = new ResourceDatabasePopulator();
		resourceDatabasePopulator2.addScript(new ClassPathResource("schemaSchool.sql"));
		resourceDatabasePopulator2.addScript(new ClassPathResource("data.sql"));
		DataSourceInitializer dbInitizer = new DataSourceInitializer();
		dbInitizer.setDatabasePopulator(resourceDatabasePopulator2);
		dbInitizer.setDataSource(schoolDataSource());
		return dbInitizer;

	}

	@Primary
	@Bean(name = "schoolEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(schoolDataSource());
		em.setPackagesToScan("com.multidatabase.multidatabase.school");
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setPersistenceUnitName("School");
		return em;
	}

	@Primary
	@Bean(name = "schoolTransactionManager")
	public PlatformTransactionManager customerTransactionManager(
			@Qualifier("schoolEntityManagerFactory") EntityManagerFactory schoolEntityManagerFactory) {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(schoolEntityManagerFactory);
		return manager;
	}
}
