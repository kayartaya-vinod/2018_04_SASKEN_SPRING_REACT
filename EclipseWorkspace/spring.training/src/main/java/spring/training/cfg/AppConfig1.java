package spring.training.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.training.dao.ContactsDao;
import spring.training.dao.impl.ContactsDaoJdbcImpl;

@Configuration
public class AppConfig1 {

	@Bean
	public ContactsDao dao2() {

		ContactsDaoJdbcImpl cdao = new ContactsDaoJdbcImpl();
		cdao.setDriver("org.h2.Driver");
		cdao.setUrl("jdbc:h2:tcp://localhost/~/sasken_spring_training");
		cdao.setUsername("sa");
		cdao.setPassword("");
		return cdao;
	}
}
