package spring.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.training.dao.ContactsDao;
import spring.training.dao.DaoException;
import spring.training.entity.Contact;

@Service
public class ContactsManager {
	
	@Autowired
	@Qualifier("contactsDaoHibernateTemplateImpl")
	ContactsDao dao;
	
	@Transactional(rollbackFor= {DaoException.class})
	public void updateContacts() throws DaoException {
		int[] ids = {12, 34, 56, 78};
		String[] cities = {"a", "aa", "aaa", null};
		
		int i=0;
		for(int id: ids) {
			Contact c = dao.findById(id);
			c.setCity(cities[i++]);
			dao.save(c);
		}
	}

}






