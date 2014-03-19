package dp.ua.pavelmorozov.archertest.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dp.ua.pavelmorozov.archertest.domain.User;

public interface AdminService {
	public void listUser(Map map, HttpServletRequest request);
	public String fillUserAccount(String id,  String amount);
	public void listRegister(Map map, HttpServletRequest request);
	public void searchUser( Map map, HttpServletRequest request);
	public void searchRegistryRecords( Map map, HttpServletRequest request, Date fromDate, Date  toDate);
}
