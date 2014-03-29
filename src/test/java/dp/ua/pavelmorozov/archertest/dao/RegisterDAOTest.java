package dp.ua.pavelmorozov.archertest.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import antlr.collections.List;
import dp.ua.pavelmorozov.archertest.domain.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/spring/root-context.xml")
public class RegisterDAOTest {
	private static final String ADMIN_EMAIL = "junit_test_admin@email.com";
	private static final String ADMIN_PASSWORD = "vfs$gh7@s3eaef";
	private static final String ADMIN_ROLE = "ADMIN";

	private static final BigDecimal REGISTER_RECORD_AMOUNT = new BigDecimal(100);
	
	private static final String USER_EMAIL = "junit_test_user@email.com";
	private static final String USER_PASSWORD = "vfs$gh7@s3eaef";
	private static final BigDecimal USER_BALANCE = new BigDecimal(10);
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private RegisterDAO registerDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Test
	@Transactional
	public void registerDAOSaveGetTest() {
		Account adminAccount = new Account(ADMIN_EMAIL,
				ADMIN_PASSWORD, ADMIN_ROLE);
		accountDAO.saveAccount(adminAccount);
		Account accountFromDB = accountDAO.getAccount(ADMIN_EMAIL);
		
		User user = new User(USER_EMAIL,USER_PASSWORD,USER_BALANCE);
		userDAO.saveUser(user);
		User userFromDB = userDAO.getUser(USER_EMAIL);
		
		System.out.println(userFromDB.getEmail() 
				+ " " + accountFromDB.getEmail());
		
		Register register = new Register(userFromDB, accountFromDB, REGISTER_RECORD_AMOUNT);
		registerDAO.saveRegister(register);
		
		Register registerPersisted = registerDAO.getRegister(register.getId());
		
		System.out.println("Get persisted record " + registerPersisted.getAdmin().getEmail()
				+ " " + registerPersisted.getUser().getEmail());
	}

	@Test
	@Transactional
	public void registerDAORemoveTest() {
		Account adminAccount = new Account(ADMIN_EMAIL,
				ADMIN_PASSWORD, ADMIN_ROLE);
		accountDAO.saveAccount(adminAccount);
		Account accountFromDB = accountDAO.getAccount(ADMIN_EMAIL);
		
		User user = new User(USER_EMAIL,USER_PASSWORD,USER_BALANCE);
		userDAO.saveUser(user);
		User userFromDB = userDAO.getUser(USER_EMAIL);
		
		Register register = new Register(userFromDB, accountFromDB, REGISTER_RECORD_AMOUNT);
		registerDAO.saveRegister(register);
		
		Register registerDeleted = registerDAO.getRegister(register.getId());
		assertNotNull(registerDeleted);

		registerDAO.removeRegister(register);
		
		System.out.println("record removed ! " + register.getId() 
				+ " " + register.getUser().getEmail()
				+ " " + register.getAdmin().getEmail());
		
		registerDeleted = registerDAO.getRegister(register.getId());
		assertNull(registerDeleted);
	}

	@Test
	@Transactional
	public void registerDAOListTest() {
		Account adminAccount = new Account(ADMIN_EMAIL,
				ADMIN_PASSWORD, ADMIN_ROLE);
		accountDAO.saveAccount(adminAccount);
		Account accountFromDB = accountDAO.getAccount(ADMIN_EMAIL);
		
		User user = new User(USER_EMAIL,USER_PASSWORD,USER_BALANCE);
		userDAO.saveUser(user);
		User userFromDB = userDAO.getUser(USER_EMAIL);
		
		Register register = new Register(userFromDB, accountFromDB, REGISTER_RECORD_AMOUNT);
		registerDAO.saveRegister(register);

		java.util.List<Register> registerList = registerDAO.listRegister();
		
		for (Register registerRecordFromDB : registerList) {
			System.out.println(registerRecordFromDB.getAdmin().getEmail()
					+ " " + registerRecordFromDB.getUser().getEmail());
			if  (registerRecordFromDB.equals(register)) {
				System.out.println("find record !");
				assertEquals(registerRecordFromDB.getAdmin().getEmail(),ADMIN_EMAIL);
				assertEquals(registerRecordFromDB.getUser().getEmail(),USER_EMAIL);
				assertEquals(registerRecordFromDB.getAmount(),REGISTER_RECORD_AMOUNT);
				break;
			}
		}
	}
	
	@Test
	@Transactional
	public void registerDAOSearchTest() {
		
		Account adminAccount = new Account(ADMIN_EMAIL,
				ADMIN_PASSWORD, ADMIN_ROLE);
		accountDAO.saveAccount(adminAccount);
		Account accountFromDB = accountDAO.getAccount(ADMIN_EMAIL);
		
		User user = new User(USER_EMAIL,USER_PASSWORD,USER_BALANCE);
		userDAO.saveUser(user);
		User userFromDB = userDAO.getUser(USER_EMAIL);

		DateTime fromDT = new DateTime();
		DateTime toDT = new DateTime().plusDays(1);
		
		Register register = new Register(userFromDB, accountFromDB, REGISTER_RECORD_AMOUNT);
		registerDAO.saveRegister(register);
		
		java.util.List<Register> registerList = registerDAO.searchRecords(fromDT.toDate(),toDT.toDate());
		
		for (Register registerRecordFromDB : registerList) {
			System.out.println(registerRecordFromDB.getAdmin().getEmail()
					+ " " + registerRecordFromDB.getUser().getEmail());
			if  (registerRecordFromDB.equals(register)) {
				System.out.println("find record !");
				assertEquals(registerRecordFromDB.getAdmin().getEmail(),ADMIN_EMAIL);
				assertEquals(registerRecordFromDB.getUser().getEmail(),USER_EMAIL);
				assertEquals(registerRecordFromDB.getAmount(),REGISTER_RECORD_AMOUNT);
				break;
			}
		}
		
		registerDAO.removeRegister(register);
		
		registerList = registerDAO.searchRecords(fromDT.toDate(),toDT.toDate());
		for (Register registerRecordFromDB1 : registerList) {
			System.out.println(registerRecordFromDB1.getAdmin().getEmail()
					+ " " + registerRecordFromDB1.getUser().getEmail());
			if  (registerRecordFromDB1.equals(register)) {
				fail("record have to be not found! ");
				break;
			}
		}		
	}
}
