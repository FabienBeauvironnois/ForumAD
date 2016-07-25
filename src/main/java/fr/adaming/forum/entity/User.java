package fr.adaming.forum.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(name = "streetName", column = @Column(nullable = false)),
		@AttributeOverride(name = "zipCode", column = @Column(nullable = false)),
		@AttributeOverride(name = "city", column = @Column(nullable = false)),
		@AttributeOverride(name = "country", column = @Column(nullable = false))
		})
	@NotNull
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
	
	@OneToMany
	private Set<Skill> skills = new HashSet<Skill>();
	
	@NotNull
//	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
	@Pattern.List({
	    @Pattern(regexp = "^(?=.*[0-9]).{0,}$", message = "Password must contain at least one digit."),
	    @Pattern(regexp = "^(?=.*[a-z]).{0,}$", message = "Password must contain at least one lowercase letter."),
	    @Pattern(regexp = "^(?=.*[A-Z]).{0,}$", message = "Password must contain at least one uppercase letter."),
	    @Pattern(regexp = "^(?=\\S+$).{0,}$", message = "Password must contain no whitespace."), 
	    @Pattern(regexp= "^.{3,14}$", message = "Password must contain from 4 to 15 characters ")
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
		
		//personalAddress.addUser(this);
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

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}
	
	
	
	

}
