package fr.adaming.forum.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/*
 * @author Alonzo.M, Beauvironnois.F, Bonnecaze.K, Roblin.M
 * Address est un composant et non pas une entity
 * Elle est un composant de company et de User d'où le Embeddable.
 */
@Embeddable
public class Address implements Serializable {
		
	
	/**
	 * Default Generated.
	 */
	private static final long serialVersionUID = -8841800640695855559L;

	private Integer streetNumber;
	
	@NotNull //Ne fonctionne pas avec Embedded : définir nullable=false dans les entités l'incluant
	private String streetName;
	
	/*
	 * On met des Wrapper et non pas les types simples (int) pour avoir un null = NULL et non pas = 0
	 */
	
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

	public Integer getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
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
