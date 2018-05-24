package spring.training.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

import spring.training.cfg.AppConfig3;
import spring.training.entity.Contact;

public class P02_TestingHibernateTemplate {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig3.class);
		HibernateTemplate ht = ctx.getBean(HibernateTemplate.class);
		
		int id = 12;
		Contact c = ht.get(Contact.class, id);
		System.out.println(c);
		
		ctx.close();
		
	}
}
