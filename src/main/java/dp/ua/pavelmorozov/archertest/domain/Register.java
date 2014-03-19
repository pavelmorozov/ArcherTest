package dp.ua.pavelmorozov.archertest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
//import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "register", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class Register {
	
    @Id
    @Column(name = "id")
	@GeneratedValue
	private Integer id;
	@ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user")	
	private User user;
	@ManyToOne()
    @JoinColumn(name="admin")	
	private Account admin;
	@Column(name = "amount")	
	private BigDecimal amount;
	@Column(name = "regdate")	
	private Timestamp regDate;
	
	public Register(){}
	
	public Register(User user, Account admin, BigDecimal amount){
		this.user = user;
		this.admin = admin;
		this.amount = amount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Account getAdmin() {
		return admin;
	}
	public void setAdmin(Account admin) {
		this.admin = admin;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
}
