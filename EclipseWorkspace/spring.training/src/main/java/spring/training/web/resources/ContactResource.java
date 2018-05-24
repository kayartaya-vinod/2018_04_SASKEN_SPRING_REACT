package spring.training.web.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.training.dao.ContactsDao;
import spring.training.dao.DaoException;
import spring.training.entity.Contact;
import spring.training.entity.ContactList;

@CrossOrigin(origins = "*")
@RestController // (all methods of this class are considered as @ResponseBody)
@RequestMapping("/api/contacts")
public class ContactResource {

	@Autowired
	@Qualifier("contactsDaoHibernateTemplateImpl")
	ContactsDao dao;

	@RequestMapping("/version")
	public String versionInfo() {
		return "contact-resource version 1.0";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	public Contact getContactById(@PathVariable("id") Integer id) throws DaoException {
		return dao.findById(id);
	}

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	public List<Contact> getAllContactsAsJson(@RequestParam(name = "page", required = false) Integer pageNo)
			throws DaoException {
		return dao.findAll(pageNo, null);
	}

	// handles these requests:
	// http://localhost:8080/springmvc/api/contacts
	// http://localhost:8080/springmvc/api/contacts?page=3
	@RequestMapping(method = RequestMethod.GET, produces = { "application/xml" })
	public ContactList getAllContactsAsXml(@RequestParam(name = "page", required = false) Integer pageNo)
			throws DaoException {
		return new ContactList(dao.findAll(pageNo, null));
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = "application/json")
	public Map<String, Object> addNewContact(@RequestBody Contact contact) {
		Map<String, Object> map = new HashMap<>();

		try {
			contact = dao.save(contact);
			map.put("success", true);
			map.put("id", contact.getId());
		} catch (Exception e) {
			map.put("success", false);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
