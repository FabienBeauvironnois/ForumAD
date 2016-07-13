package fr.adaming.forum.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Formation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idFormation;
	
	@NotNull
	private String formationName;
	
	@NotNull
	private String city;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dateBegin;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dateEnd;
	
	@NotNull
	private boolean status;

	public Formation() {
		super();
	}

	public Formation(String formationName, String city, Date dateBegin, Date dateEnd, boolean status) {
		super();
		this.formationName = formationName;
		this.city = city;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
		this.status = status;
	}

	public String getFormationName() {
		return formationName;
	}

	public void setFormationName(String formationName) {
		this.formationName = formationName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getIdFormation() {
		return idFormation;
	}
	
	
	
	

}
