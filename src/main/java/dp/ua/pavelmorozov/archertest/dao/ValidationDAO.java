package dp.ua.pavelmorozov.archertest.dao;

import org.springframework.dao.DataAccessException;
import dp.ua.pavelmorozov.archertest.domain.Validation;

public interface ValidationDAO {
	public void saveValidation(Validation validation)
		throws DataAccessException;
	public Validation getValidation(String uuid)
		throws DataAccessException;
	public void removeValidation(Validation validation)
		throws DataAccessException;	
}
