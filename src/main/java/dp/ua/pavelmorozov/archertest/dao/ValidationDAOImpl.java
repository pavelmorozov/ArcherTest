package dp.ua.pavelmorozov.archertest.dao;

import org.springframework.dao.DataAccessException;

import dp.ua.pavelmorozov.archertest.domain.Account;
import dp.ua.pavelmorozov.archertest.domain.Validation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ValidationDAOImpl implements ValidationDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveValidation(Validation validation)
			throws DataAccessException {
		sessionFactory.getCurrentSession().save(validation);
	}

	@Override
	public Validation getValidation(String uuid) throws DataAccessException {
		Validation validation = (Validation) sessionFactory.
				getCurrentSession().load(Validation.class, uuid);
		if (null != validation) {
			return validation;
		}else{
			return null;
        }
	}

	@Override
	public void removeValidation(Validation validation)
			throws DataAccessException {
		if (null != validation) {
        	sessionFactory.getCurrentSession().delete(validation);
        }
	}
}
