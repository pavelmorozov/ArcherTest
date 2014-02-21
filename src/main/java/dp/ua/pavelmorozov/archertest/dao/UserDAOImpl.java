package dp.ua.pavelmorozov.archertest.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import dp.ua.pavelmorozov.archertest.domain.Account;
import dp.ua.pavelmorozov.archertest.domain.User;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveUser(User user) throws DataAccessException {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public User getUser(String email) throws DataAccessException {
		
		//return user by criteria
		User user = (User) sessionFactory.getCurrentSession().
				createCriteria(User.class).
				add(Restrictions.eq("email",email)).list().get(0);		

		return user;
	}

	@Override
	public void removeUser(User user) throws DataAccessException {
		if (null != user) {
        	sessionFactory.getCurrentSession().delete(user);
        }
	}

	@Override
	public List<User> listUser() throws DataAccessException {
		return sessionFactory.getCurrentSession().createQuery("from user")
	            .list();
	}
}
