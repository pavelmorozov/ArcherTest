package dp.ua.pavelmorozov.archertest.dao;

import org.springframework.dao.DataAccessException;
import dp.ua.pavelmorozov.archertest.domain.Account;

public interface AccountDAO {
	public void saveAccount(Account account) 
			throws DataAccessException;
	public void removeAccount(Account account)
			throws DataAccessException;
	public Account getAccount(String email)
			throws DataAccessException;

}