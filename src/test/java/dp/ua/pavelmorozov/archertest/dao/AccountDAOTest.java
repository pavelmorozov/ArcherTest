package dp.ua.pavelmorozov.archertest.dao;

import dp.ua.pavelmorozov.archertest.domain.*;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/spring/root-context.xml")
public class AccountDAOTest {
	
	private static final String ACCOUNT_EMAIL = "junit_test_account@email.com";
	private static final String ACCOUNT_PASSWORD = "vfs$gh7@s3eaef";
	private static final String ACCOUNT_ROLE = "ROLE_USER";
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Test
	@Transactional
	public void accountDAOGetAccountSaveAccount() {
		
		assertNull(accountDAO.getAccount(ACCOUNT_EMAIL));
		
		Account account = new Account(ACCOUNT_EMAIL,
				ACCOUNT_PASSWORD, ACCOUNT_ROLE);
		accountDAO.saveAccount(account);
		Account accountFromDB = accountDAO.getAccount(ACCOUNT_EMAIL);
		assertEquals(account.getEmail(),accountFromDB.getEmail());
		assertEquals(account.getPass(),accountFromDB.getPass());
		assertEquals(account.getRole(),accountFromDB.getRole());
	}
	
	@Test
	@Ignore
	@Transactional
	public void accountDAORemoveAccount() {
		Account account = new Account(ACCOUNT_EMAIL,
				ACCOUNT_PASSWORD, ACCOUNT_ROLE);
		accountDAO.saveAccount(account);
		accountDAO.removeAccount(account);
		Account accountFromDB = accountDAO.getAccount(ACCOUNT_EMAIL);
		assertNull(accountFromDB);
	}	
}
