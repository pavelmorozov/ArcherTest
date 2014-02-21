package dp.ua.pavelmorozov.archertest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dp.ua.pavelmorozov.archertest.dao.AccountDAO;
import dp.ua.pavelmorozov.archertest.dao.UserDAO;
import dp.ua.pavelmorozov.archertest.domain.Account;
import dp.ua.pavelmorozov.archertest.domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserDAO userDAO;	

	@Override
	@Transactional
	public User getUser(String email) {
		User user = userDAO.getUser(email);
		return user ;
	}

}
