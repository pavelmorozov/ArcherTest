package dp.ua.pavelmorozov.archertest.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface RegisterGuestService {
	public void saveGuest(String email, String password, String password_repeat, 
			Map <String, Object> map, HttpServletRequest request) throws RuntimeException;
}
