package com.multidatabase.multidatabase.config;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableJpaRepositories(entityManagerFactoryRef = "libraryEntityManagerFactory", transactionManagerRef = "libraryTransactionManager", basePackages = {
		"com.multidatabase.multidatabase.libraryRepo" })
public class LibraryDataSourceConfig {

	@Bean(name = "libraryDataSource")
	@ConfigurationProperties(prefix = "spring.library-datasource")
	public DataSource libraryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public DataSourceInitializer initLibrarydataSource() {
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		resourceDatabasePopulator.addScript(new ClassPathResource("schemaLibrary.sql"));
		DataSourceInitializer dbInitizer = new DataSourceInitializer();
		dbInitizer.setDatabasePopulator(resourceDatabasePopulator);
		dbInitizer.setDataSource(libraryDataSource());
		return dbInitizer;
	}

	@Bean(name = "libraryEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em
	      = new LocalContainerEntityManagerFactoryBean();
	    em.setDataSource(libraryDataSource());
	    em.setPackagesToScan("com.multidatabase.multidatabase.library");
	    em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	    em.setPersistenceUnitName("Library");

		return em;
				
	}

	@Bean(name = "libraryTransactionManager")
	public PlatformTransactionManager customerTransactionManager(
		@Qualifier("libraryEntityManagerFactory") EntityManagerFactory libraryEntityManagerFactory) {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(libraryEntityManagerFactory);
		return manager;
	}

}
