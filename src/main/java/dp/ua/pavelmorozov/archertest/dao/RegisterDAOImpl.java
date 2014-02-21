package dp.ua.pavelmorozov.archertest.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import dp.ua.pavelmorozov.archertest.domain.Register;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterDAOImpl implements RegisterDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveRegister(Register register) throws DataAccessException {
		sessionFactory.getCurrentSession().save(register);
	}

	@Override
	public void removeRegister(Register register) throws DataAccessException {
		if (null != register) {
        	sessionFactory.getCurrentSession().delete(register);
        }
	}

	@Override
	public List<Register> listRegister() throws DataAccessException {
		return sessionFactory.getCurrentSession().createQuery("from register")
	            .list();
	}

}
