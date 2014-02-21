package dp.ua.pavelmorozov.archertest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import java.math.BigDecimal;


@Entity
@Table(name = "user")
//@PrimaryKeyJoinColumn(name="id") as default the same column in join condition
@PrimaryKeyJoinColumn(name="account")
public class User extends Account {

	@Column(name = "balance")
	private BigDecimal balance;

	public User() {
	}
	
	public User(String email, String pass, BigDecimal balance) {
		super.setEmail(email);
		super.setPass(pass);
		this.balance=balance;
	}	

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}
