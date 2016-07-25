package fr.adaming.forum.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;

@Embeddable
public class Address implements Serializable {
		
	
	private Integer streetNumber;
	
	@NotNull //Ne fonctionne pas avec Embedded : définir nullable=false dans les entités l'incluant
	private String streetName;
	
	@NotNull
	private Integer zipCode;
	
	@NotNull
	private String city;
	
	@NotNull
	private String country;
	
	public Address() {
		super();
	}

	public Address(Integer streetNumber, String streetName, Integer zipCode, String city, String country) {
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
	
	

}
