package fr.adaming.forum.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idAddress;
	
	private int streetNumber;
	
	@NotNull
	private String streetName;
	
	@NotNull
	private int zipCode;
	
	@NotNull
	private String city;
	
	@NotNull
	private String country;
	
	@OneToMany(mappedBy="personalAddress", orphanRemoval=true)
	private Set<User> users = new HashSet<User>();
	
	@OneToMany(mappedBy="companyAddress", orphanRemoval=true)
	private Set<Company> companies;

	public Address() {
		super();
	}

	public Address(int streetNumber, String streetName, int zipCode, String city, String country) {
		super();
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getIdAddress() {
		return idAddress;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void addUser(User user) {
		this.users.add(user);
	}
	
	public Set<Company> getCompanies() {
		return this.companies;
	}

	public void addCompany(Company company) {
		this.companies.add(company);
	}
	
	

}
