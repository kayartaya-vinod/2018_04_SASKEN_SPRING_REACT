package spring.training.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.training.cfg.AppConfig3;
import spring.training.dao.DaoException;
import spring.training.service.ContactsManager;

public class P04_TestingTransactions {

	public static void main(String[] args) throws DaoException {
		
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig3.class);
		
		ContactsManager mgr = ctx.getBean(ContactsManager.class);
		
		mgr.updateContacts();
		
		ctx.close();
		
	}
}
