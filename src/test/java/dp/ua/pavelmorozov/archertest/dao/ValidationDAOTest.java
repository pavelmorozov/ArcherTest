package dp.ua.pavelmorozov.archertest.dao;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dp.ua.pavelmorozov.archertest.domain.Account;
import dp.ua.pavelmorozov.archertest.domain.Register;
import dp.ua.pavelmorozov.archertest.domain.User;
import dp.ua.pavelmorozov.archertest.domain.Validation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/spring/root-context.xml")
public class ValidationDAOTest {
	private static final String ACCOUNT_EMAIL = "junit_test_admin@email.com";
	private static final String ACCOUNT_PASSWORD = "vfs$gh7@s3eaef";
	private static final String ACCOUNT_ROLE = "GUEST";
	
	private static final String UUID = "jfnvcheydjslksdjkdjdjk";
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private ValidationDAO validationDAO;
	
	@Test
	@Transactional
	public void validationDAOSaveGetTest() {
		Account guestAccount = new Account(ACCOUNT_EMAIL,
				ACCOUNT_PASSWORD, ACCOUNT_ROLE);
		accountDAO.saveAccount(guestAccount);
		
		assertNull(validationDAO.getValidation(UUID));
		
		Validation validation = new Validation(guestAccount,UUID);
		validationDAO.saveValidation(validation);
		assertNotNull(validationDAO.getValidation(UUID));
		
		validationDAO.removeValidation(validation);
		assertNull(validationDAO.getValidation(UUID));
	}
}
