package dp.ua.pavelmorozov.archertest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import java.math.BigDecimal;

/**
 * Subclass of Account class. Stores user balance.
 * @author P
 *
 */

@Entity
@Table(name = "user")
@PrimaryKeyJoinColumn(name="account")
public class User extends Account {
	
	private static final String USER_ROLE = "USER";

	@Column(name = "balance")
	private BigDecimal balance;

	public User() {
	}
	
	public User(String email, String pass, BigDecimal balance) {
		super.setEmail(email);
		super.setPass(pass);
		super.setRole(USER_ROLE);
		this.balance=balance;
	}	

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}
