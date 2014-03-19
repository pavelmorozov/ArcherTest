package dp.ua.pavelmorozov.archertest.dao;

import java.util.Date;
import java.util.List;

import dp.ua.pavelmorozov.archertest.domain.Register;

import org.springframework.dao.DataAccessException;

public interface RegisterDAO {
	public void saveRegister(Register register) 
		throws DataAccessException;
	public void removeRegister(Register register)
		throws DataAccessException;
	public List <Register> listRegister() 
		throws DataAccessException;
	public List <Register> searchRecords(Date fromDate, Date toDate)
			throws DataAccessException;
}
