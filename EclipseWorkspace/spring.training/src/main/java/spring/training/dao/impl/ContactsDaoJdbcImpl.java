package spring.training.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.training.dao.ContactsDao;
import spring.training.dao.DaoException;
import spring.training.entity.Contact;

@Repository
public class ContactsDaoJdbcImpl implements ContactsDao {

	// dependencies
	private String driver;
	private String url;
	private String username;
	private String password;

	@Autowired(required = false)
	private DataSource dataSource;

	// good practice
	public ContactsDaoJdbcImpl() {
	}

	public ContactsDaoJdbcImpl(String driver, String url, String username, String password) {
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	// spring uses this for dependency injection
	// writable property "driver" (mutator)
	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// exposes a writable property called "dataSource"
	// of type javax.sql.DataSource
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private Connection createConnection() throws ClassNotFoundException, SQLException {
		if (dataSource != null) {
			return dataSource.getConnection();
		}

		Class.forName(driver);
		return DriverManager.getConnection(url, username, password);
	}

	@Override
	public int count() throws DaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = createConnection();
			stmt = conn.prepareStatement("select count(*) from phonebook");
			rs = stmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			throw new DaoException(e); // exception funnelling
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}

	@Override
	public Contact save(Contact contact) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact findById(Integer id) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Override
	public Contact deleteById(Integer id) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Override
	public List<Contact> findAll() throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Override
	public List<Contact> findAll(Integer pageNo, Integer pageSize) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Override
	public List<Contact> findByCity(String city) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Override
	public List<Contact> findByGender(String gender) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

}
