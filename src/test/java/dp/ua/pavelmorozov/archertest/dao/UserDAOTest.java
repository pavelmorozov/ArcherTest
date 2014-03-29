package dp.ua.pavelmorozov.archertest.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dp.ua.pavelmorozov.archertest.domain.Account;
import dp.ua.pavelmorozov.archertest.domain.Register;
import dp.ua.pavelmorozov.archertest.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/spring/root-context.xml")
public class UserDAOTest {
	private static final String USER_EMAIL = "junit_test_user@email.com";
	private static final String USER_PASSWORD = "vfs$gh7@s3eaef";
	private static final BigDecimal USER_BALANCE = new BigDecimal(10);@Autowired
	private UserDAO userDAO;
	
	@Test
	@Transactional
	public void userDAOSaveGetRemoveListSearchTest() {
		User user = new User(USER_EMAIL,USER_PASSWORD,USER_BALANCE);
		assertNull(userDAO.getUser(USER_EMAIL));
		userDAO.saveUser(user);
		assertNotNull(userDAO.getUser(USER_EMAIL));
		assertNotNull(userDAO.getUser(user.getId()));
		
		java.util.List<User> userList = userDAO.listUser();
		
		boolean found = false;
		for (User userPersisted : userList) {
			if  (userPersisted.equals(user)) {
				found = true;
				break;
			}
		}
		assertTrue(found);
		
		userList = userDAO.searchUser(USER_EMAIL);

		found = false;
		for (User userPersisted : userList) {
			if  (userPersisted.equals(user)) {
				found = true;
				break;
			}
		}
		assertTrue(found);

		userDAO.removeUser(user);
		assertNull(userDAO.getUser(USER_EMAIL));
	}
}
