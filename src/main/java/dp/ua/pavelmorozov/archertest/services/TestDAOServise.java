package dp.ua.pavelmorozov.archertest.services;

import dp.ua.pavelmorozov.archertest.domain.Account;

public interface TestDAOServise {
	public Account getAccount(String email);
}
