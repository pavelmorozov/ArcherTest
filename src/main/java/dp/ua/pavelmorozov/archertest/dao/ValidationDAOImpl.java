package dp.ua.pavelmorozov.archertest.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import dp.ua.pavelmorozov.archertest.domain.Validation;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
	@SuppressWarnings("unchecked")
	public Validation getValidation(String uuid) throws DataAccessException {
		List<Validation> validationList = (List<Validation>) sessionFactory.getCurrentSession().
				createCriteria(Validation.class).
				add(Restrictions.eq("uuid",uuid)).list();

		if (validationList.isEmpty()){
			return null;
		}else{
			return (Validation) validationList.get(0);
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
