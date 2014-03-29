package dp.ua.pavelmorozov.archertest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "account", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id"),
		@UniqueConstraint(columnNames = "email") })
@Inheritance(strategy=InheritanceType.JOINED)

public class Account {

    @Id
    @Column(name = "id", unique = true, nullable = false)
	@GeneratedValue
	private Integer id;
    @Column(name = "email", unique = true, nullable = false)    
    private String email;
    @Column(name = "created")    
    private Timestamp created;
    @Column(name = "pass")    
    private String pass;
    @Column(name = "role")    
    private String role;
    @Column(name = "valid")    
    private Boolean valid;
    //@OneToOne(cascade = CascadeType.REMOVE)
    //@PrimaryKeyJoinColumn
    //@JoinColumn(name="id")
    //private Validation validation;
	@OneToMany()
	@JoinColumn(name="id")    
    private List<Register> regRecords;
   
	public Account() {
	}

	public Account(String email, String pass, String role) {
		this.email = email;
		this.pass = pass;
		this.role = role;
		this.valid = false; //untill validation process
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

//	public Validation getValidation() {
//		return validation;
//	}
//
//	public void setValidation(Validation validation) {
//		this.validation = validation;
//	}

	public List<Register> getRegRecords() {
		return regRecords;
	}

	public void setRegRecords(List<Register> regRecords) {
		this.regRecords = regRecords;
	}    
}