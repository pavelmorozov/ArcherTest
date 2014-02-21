package dp.ua.pavelmorozov.archertest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "validation")
public class Validation {

	/**
	 *  "guest" column of "validations" table
	 *  stores "id" of "Account" class ("accounnts" table "id") 
	 */
	//@OneToOne	
	//@OneToOne(targetEntity = Account.class)
	//@JoinColumn(name="id")
	//@Column(name = "guest")
	
	
	@Id
	@Column(name = "guest")
    private Integer guest;
	@Column(name = "uuid")	
    private String uuid;
    
    public Validation(){}
    
    public Validation(Account guest, String uuid){
    	this.guest =  guest.getId();
    	this.uuid = uuid;
    }
   
	public Integer getGuest() {
		return guest;
	}

	public void setGuest(Integer guest) {
		this.guest = guest;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
