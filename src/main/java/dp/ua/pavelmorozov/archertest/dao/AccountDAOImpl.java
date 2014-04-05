package dp.ua.pavelmorozov.archertest.dao;

import java.util.List;

import dp.ua.pavelmorozov.archertest.domain.Account;
import org.springframework.dao.DataAccessException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	@SuppressWarnings("unchecked")
	public Account getAccount(String email) throws DataAccessException {
		List <Account> accountList = (List <Account>) sessionFactory.getCurrentSession().
				createCriteria(Account.class).
				add(Restrictions.eq("email",email)).list();
		if (accountList.isEmpty()){
			return null;
		}else{
			return accountList.get(0);
		}		
	}
}
