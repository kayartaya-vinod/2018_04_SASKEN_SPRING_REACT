package spring.training.cfg;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import spring.training.entity.Contact;

@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = { "spring.training.dao.impl", "spring.training.aop", "spring.training.service",
		"spring.training.web" })
// @ComponentScan scans for all classes with any of these annotations:
// @Component, @Configuration, @Repository, @Service, @Controller, and
// @RestController
public class AppConfig3 {

	// HibernateTransactionManager depends on a SessionFactory, which represents a
	// database. HTM collects all writable transaction statements going via the
	// DataSource of the session-factory, and executes all of them as an atomic (all
	// or none) operation.
	@Bean
	public PlatformTransactionManager transactionManager(SessionFactory factory) {
		return new HibernateTransactionManager(factory);
	}

	@Bean
	public HibernateTemplate ht(SessionFactory factory) {
		return new HibernateTemplate(factory);
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource ds) {
		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(ds); // Database to manage
		sf.setAnnotatedClasses(Contact.class); // Entity classes to manage

		Properties props = new Properties();
		props.setProperty("hibernate.show_sql", "false");
		props.setProperty("hibernate.format_sql", "true");
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		sf.setHibernateProperties(props);

		return sf;
	}

	//@Bean
	public DataSource ds() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("org.h2.Driver");
		bds.setUrl("jdbc:h2:tcp://localhost/~/sasken_spring_training");
		bds.setUsername("sa");
		bds.setPassword("");
		return bds;
	}

}
