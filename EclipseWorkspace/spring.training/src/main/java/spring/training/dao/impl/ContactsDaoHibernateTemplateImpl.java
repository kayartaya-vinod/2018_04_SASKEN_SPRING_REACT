package spring.training.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import spring.training.dao.ContactsDao;
import spring.training.dao.DaoException;
import spring.training.entity.Contact;

@Repository
public class ContactsDaoHibernateTemplateImpl extends HibernateDaoSupport implements ContactsDao {

	// we are instructing spring to invoke this parameterized constructor
	// and ask for a dependency injection of type HibernateTemplate
	@Autowired
	ContactsDaoHibernateTemplateImpl(HibernateTemplate template) {
		super.setHibernateTemplate(template);
	}

	@Override
	public Contact save(Contact contact) throws DaoException {
		if(contact.getCity()==null) {
			throw new DaoException("Null city not accepted!");
		}
		return getHibernateTemplate().merge(contact);
	}

	@Override
	public Contact findById(Integer id) throws DaoException {
		return getHibernateTemplate().get(Contact.class, id);
	}

	@Override
	public Contact deleteById(Integer id) throws DaoException {
		Contact contact = findById(id);
		if (contact == null) {
			throw new DaoException("Invalid id for deletion: " + id);
		}
		getHibernateTemplate().delete(contact);
		return contact;
	}

	@Override
	public int count() throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Contact> findAll() throws DaoException {
		return findAll(null, null);
	}

	@Override
	public List<Contact> findAll(Integer pageNo, Integer pageSize) throws DaoException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 50 : pageSize;
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Query<Contact> qry = session.createQuery("from Contact", Contact.class);
		qry.setMaxResults(pageSize);
		qry.setFirstResult((pageNo - 1) * pageSize);
		List<Contact> list = qry.getResultList();
		session.close();
		return list;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Contact> findByCity(String city) throws DaoException {
		return (List<Contact>) getHibernateTemplate().find("from Contact where city=?", city);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> findByGender(String gender) throws DaoException {
		DetachedCriteria crit = DetachedCriteria.forClass(Contact.class);
		crit.add(Restrictions.eq("gender", gender));
		return (List<Contact>) getHibernateTemplate().findByCriteria(crit);
	}
}
