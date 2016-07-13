package fr.adaming.forum.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class User {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long idUser;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String name;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idAddress")
	private Address personalAddress;
	
	@ManyToOne
	@JoinColumn(name="idCompany")
	private Company company;
	
	/**
	 * On tente rôle ici mais on verra à la longue s'il ne faut pas le mettre en List.
	 */
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idRole")
	private Role role;
	
	@NotNull
	@Pattern(regexp=".+@.+\\.[a-z]+", message="Invalid email address!")
	private String email;
	
	@NotNull
	@Pattern.List({
	    @Pattern(regexp = "(?=.*[0-9])", message = "Password must contain one digit."),
	    @Pattern(regexp = "(?=.*[a-z])", message = "Password must contain one lowercase letter."),
	    @Pattern(regexp = "(?=.*[A-Z])", message = "Password must contain one uppercase letter."),
	    @Pattern(regexp = "(?=\\S+$)", message = "Password must contain no whitespace.")
	})
	private String password;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idFormation")
	private Formation formation;

	public User(String firstName, String name, Address personalAddress, Company company, Role role, String email,
			String password, Formation formation) {
		super();
		this.firstName = firstName;
		this.name = name;
		this.personalAddress = personalAddress;
		this.company = company;
		this.role = role;
		this.email = email;
		this.password = password;
		this.formation = formation;
	}

	public User() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getPersonalAddress() {
		return personalAddress;
	}

	public void setPersonalAddress(Address personalAddress) {
		this.personalAddress = personalAddress;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getIdUser() {
		return idUser;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	
	
	
	

}
