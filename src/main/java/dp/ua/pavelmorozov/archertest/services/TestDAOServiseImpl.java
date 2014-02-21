package dp.ua.pavelmorozov.archertest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dp.ua.pavelmorozov.archertest.dao.AccountDAO;
import dp.ua.pavelmorozov.archertest.domain.Account;

@Service
public class TestDAOServiseImpl implements TestDAOServise {

	@Autowired
    private AccountDAO accountDAO;		
	
	@Override
	@Transactional
	public Account getAccount(String email) {

		
		
		
		
		
				
	
		System.out.println(email);
		//Account account = (Account) sessionFactory.getCurrentSession().load(Account.class, email);
		Account account = accountDAO.getAccount(email);
		System.out.println(account.getEmail()+" "+account.getPass()+" "+account.getRole());
		return account;
		
		
		
		
		
		
		
	}

}
