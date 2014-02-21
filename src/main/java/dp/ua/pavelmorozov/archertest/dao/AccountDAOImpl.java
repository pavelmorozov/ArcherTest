package dp.ua.pavelmorozov.archertest.dao;

import dp.ua.pavelmorozov.archertest.domain.Account;

import org.springframework.dao.DataAccessException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccountDAOImpl implements AccountDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveAccount(Account account) throws DataAccessException {
		sessionFactory.getCurrentSession().save(account);
	}

	@Override
	public void removeAccount(Account account) throws DataAccessException {
		if (null != account) {
        	sessionFactory.getCurrentSession().delete(account);
        }
	}
	
	@Override
	public Account getAccount(String email) throws DataAccessException {
		//return Account by ID
		//Account account = (Account) sessionFactory.getCurrentSession().load(Account.class, 1);
		
		//return account by criteria
		Account account = (Account) sessionFactory.getCurrentSession().
				createCriteria(Account.class).
				add(Restrictions.eq("email",email)).list().get(0);
		
		return account;
	}
}
