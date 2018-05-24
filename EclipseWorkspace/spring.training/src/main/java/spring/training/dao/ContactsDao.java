package spring.training.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import spring.training.entity.Contact;

@Transactional(readOnly = true)
public interface ContactsDao {
	// CRUD OPERATIONS

	// CREATE / UPDATE
	@Transactional(readOnly = false)
	public Contact save(Contact contact) throws DaoException;

	public Contact findById(Integer id) throws DaoException;

	@Transactional(readOnly = false)
	public Contact deleteById(Integer id) throws DaoException;

	// QUERIES
	public int count() throws DaoException;

	public List<Contact> findAll() throws DaoException;

	public List<Contact> findAll(Integer pageNo, Integer pageSize) throws DaoException;

	public List<Contact> findByCity(String city) throws DaoException;

	public List<Contact> findByGender(String gender) throws DaoException;
}
