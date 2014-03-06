package dp.ua.pavelmorozov.archertest.services;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dp.ua.pavelmorozov.archertest.dao.UserDAO;
import dp.ua.pavelmorozov.archertest.domain.User;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public List<User> listUser() {
		return userDAO.listUser();	
	}
	
	/**
	 * Find user by Id, fill account and returns user balance
	 */
	@Override
	@Transactional	
	public String fillUserAccount(String id, String amount){
		User user = userDAO.getUser(Integer.parseInt(id));
		BigDecimal balance = user.getBalance();
		MathContext mc = new MathContext(16);
		System.out.println(amount);
		amount = amount.replace(',', '.');
		System.out.println(amount);
		BigDecimal amountBD= new BigDecimal (amount);
		balance = balance.add(amountBD,mc);
		user.setBalance(balance);
		return user.getBalance().toString();
	}	
}
