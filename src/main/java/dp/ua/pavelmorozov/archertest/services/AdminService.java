package dp.ua.pavelmorozov.archertest.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
	public void listUser(Map <String, Object> map, HttpServletRequest request);
	public String fillUserAccount(String id,  String amount);
	public void listRegister(Map <String, Object> map, HttpServletRequest request);
	public String searchUser(Map <String, Object> map, HttpServletRequest request);
	public String searchRegistryRecords(Map <String, Object> map, HttpServletRequest request);
}
