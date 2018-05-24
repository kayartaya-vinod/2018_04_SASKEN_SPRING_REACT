package spring.training.programs;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.training.cfg.AppConfig3;
import spring.training.dao.ContactsDao;
import spring.training.dao.DaoException;
import spring.training.entity.Contact;

public class P03_ContactsDaoTest {

	public static void main(String[] args) throws DaoException {

		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig3.class);

		ContactsDao dao = ctx.getBean("contactsDaoHibernateTemplateImpl", ContactsDao.class);
		System.out.println("dao is an instanceof " + dao.getClass());

		Contact c = dao.findById(12);
		System.out.println(c);
		c.setCity("Houston");
		c = dao.save(c);
		c = dao.findById(12);
		System.out.println(c);
		
		List<Contact> list = dao.findByGender("Male");
		System.out.printf("There are %d male contacts\n", list.size());

		list = dao.findByCity("Houston");
		System.out.printf("There are %d contacts from '%s' city\n", list.size(), "Houston");
		ctx.close();

	}

}
