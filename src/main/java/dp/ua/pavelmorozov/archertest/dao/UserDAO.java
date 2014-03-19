package dp.ua.pavelmorozov.archertest.dao;

import org.springframework.dao.DataAccessException;

import java.util.List;

import dp.ua.pavelmorozov.archertest.domain.User;

public interface UserDAO {
	public List <User> searchUser(String searchString)
		throws DataAccessException;
	public void saveUser(User user)
		throws DataAccessException;
	public User getUser(String email)
		throws DataAccessException; 
	public User getUser(Integer id)
			throws DataAccessException;
	public void removeUser(User user)
		throws DataAccessException;
	public List <User> listUser()
		throws DataAccessException;
}
