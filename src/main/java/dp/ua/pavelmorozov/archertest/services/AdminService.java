package dp.ua.pavelmorozov.archertest.services;

import java.math.BigDecimal;
import java.util.List;

import dp.ua.pavelmorozov.archertest.domain.User;

public interface AdminService {
	public List<User> listUser();
	public String fillUserAccount(String id,  String amount);
}
