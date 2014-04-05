package dp.ua.pavelmorozov.archertest.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface GuestService {
	public String mailConfirmation(Map<String, Object> map, HttpServletRequest request);
}
