package dp.ua.pavelmorozov.archertest.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import dp.ua.pavelmorozov.archertest.domain.Register;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterDAOImpl implements RegisterDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Register getRegister(Integer id)	throws DataAccessException{
		Register register = (Register) sessionFactory.getCurrentSession().get(Register.class, id);
		return register;
	}	
	
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
	@SuppressWarnings("unchecked")
	public List<Register> listRegister() throws DataAccessException {
		List <Register> list = (List <Register>) sessionFactory.getCurrentSession().
				createQuery("from Register").list();  
		return list;
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Register> searchRecords(Date fromDate, Date toDate)
			throws DataAccessException{
		List<Register> registerList = (List<Register>) sessionFactory.getCurrentSession().
				createCriteria(Register.class)
				.add(Restrictions.ge("regDate", fromDate)) 
				.add(Restrictions.lt("regDate", toDate))
				.list();
		return registerList;
	}	

}
