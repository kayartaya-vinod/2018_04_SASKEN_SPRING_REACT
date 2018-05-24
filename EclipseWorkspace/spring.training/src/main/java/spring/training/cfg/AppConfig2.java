package spring.training.cfg;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import spring.training.dao.ContactsDao;
import spring.training.dao.impl.ContactsDaoJdbcImpl;

@Configuration
public class AppConfig2 {

	@Bean // (name= {"dataSource"})
	public DataSource ds() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("org.h2.Driver");
		bds.setUrl("jdbc:h2:tcp://localhost/~/sasken_spring_training");
		bds.setUsername("sa");
		bds.setPassword("");
		
		bds.setInitialSize(50);
		bds.setMaxIdle(50);
		bds.setMaxTotal(100);
		bds.setMinIdle(5);
		
		return bds;
	}
	
	@Primary
	@Bean // (name= {"dataSource"})
	public DataSource mysqlDs() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		bds.setUrl("jdbc:mysql://localhost/sasken_spring_training");
		bds.setUsername("root");
		bds.setPassword("root");
		
		bds.setInitialSize(50);
		bds.setMaxIdle(50);
		bds.setMaxTotal(100);
		bds.setMinIdle(5);
		
		return bds;
	}

	@Bean(autowire=Autowire.BY_TYPE)
	public ContactsDao dao1() { 
		return  new ContactsDaoJdbcImpl();
	}
	
	@Bean
	public ContactsDao dao(DataSource ds) { // dependency injection
		ContactsDaoJdbcImpl cdao = new ContactsDaoJdbcImpl();
		// cdao.setDataSource(dataSource()); // manual wiring
		cdao.setDataSource(ds); // manual wiring
		return cdao;
	}
}
