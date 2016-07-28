package fr.adaming.forum.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/*
 * @author Alonzo.M, Beauvironnois.F, Bonnecaze.K, Roblin.M
 * Différentes formations réalisées par l'utilisateur via adaming.
 * A priori une seule par utilisateur mais ne sait-on jamais.
 * La liste des formations sera à gérer par l'administrateur du forum (ou via un logiciel interne à l'entreprise qui pourrait communiquer avec.
 * Chaque formation a une date de début et une date de fin pour pouvoir différencier les différentes "promotions".
 * 
 */

@Entity
@Table(
		uniqueConstraints={
		@UniqueConstraint(name="formation", columnNames={"formationName", "city", "dateBegin", "dateEnd"})
		})
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
	private boolean processing;
	
	/*
	 * Comme on a un Entity Manager et non pas un Session factory, on met un Fetch Eager pour récupérer tous les élèves de la formation
	 */
	@OneToMany(mappedBy="formation", fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<User> user = new HashSet<User>();

	public Formation() {
		super();
	}

	public Formation(String formationName, String city, Date dateBegin, Date dateEnd, boolean processing) {
		super();
		this.formationName = formationName;
		this.city = city;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
		this.processing = processing;
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

	public boolean isProcessing() {
		return processing;
	}

	public void setProcessing(boolean processing) {
		this.processing = processing;
	}

	public Long getIdFormation() {
		return idFormation;
	}

	public Set<User> getUsers() {
		return user;
	}

	public void setUsers(Set<User> user) {
		this.user = user;
	}

}
