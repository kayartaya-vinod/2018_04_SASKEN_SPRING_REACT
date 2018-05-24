package spring.training.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.training.cfg.AppConfig3;
import spring.training.dao.ContactsDao;
import spring.training.dao.DaoException;

public class P01_SpringAsFactory {

	public static void main(String[] args) throws DaoException {
		// variable representing the spring container
		AnnotationConfigApplicationContext ctx;

		// object of spring container
		ctx = new AnnotationConfigApplicationContext(AppConfig3.class);

		ContactsDao dao = ctx.getBean(ContactsDao.class);

		int cc = dao.count();
		System.out.println("Total contacts = " + cc);

		ctx.close();
	}
}
