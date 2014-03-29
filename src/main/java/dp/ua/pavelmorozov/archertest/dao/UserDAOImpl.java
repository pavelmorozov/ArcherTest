package dp.ua.pavelmorozov.archertest.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;





//import dp.ua.pavelmorozov.archertest.domain.Account;
import dp.ua.pavelmorozov.archertest.domain.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> searchUser(String searchString) throws DataAccessException {
		List<User> userList = sessionFactory.getCurrentSession().
				createCriteria(User.class).
				add(Restrictions.ilike("email","%"+searchString+"%")).list();
		
		//System.out.println("%"+searchString+"%");
		return userList;
	}
	
	
	@Override
	public void saveUser(User user) throws DataAccessException {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public User getUser(String email) throws DataAccessException {
		//return user by criteria
		List userList = (List) sessionFactory.getCurrentSession().
				createCriteria(User.class).
				add(Restrictions.eq("email",email)).list();

		if (userList.isEmpty()){
			return null;
		}else{
			return (User) userList.get(0);
		}
	}
	
	@Override
	public User getUser(Integer id)
			throws DataAccessException {
		User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
		return user;				
	}

	@Override
	public void removeUser(User user) throws DataAccessException {
		if (null != user) {
        	sessionFactory.getCurrentSession().delete(user);
        }
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> listUser() throws DataAccessException {
		return sessionFactory.getCurrentSession().createQuery("from User")
	            .list();
	}
}
