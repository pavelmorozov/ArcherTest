package dp.ua.pavelmorozov.archertest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "validation")
public class Validation {
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@OneToOne()
	//@Column(name = "guest")
	@JoinColumn(name="guest")
    private Account guest;
	
	@Column(name = "uuid")	
    private String uuid;
    
    public Validation(){}
    
    public Validation(Account guest, String uuid){
    	this.guest =  guest;
    	this.uuid = uuid;
    }
   
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Account getGuest() {
		return guest;
	}

	public void setGuest(Account guest) {
		this.guest = guest;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
