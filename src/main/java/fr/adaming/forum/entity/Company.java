package fr.adaming.forum.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCompany;

	@NotNull
	private String companyName;

	@NotNull
	private String companyBranch;

	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(name = "streetName", column = @Column(nullable = false)),
		@AttributeOverride(name = "zipCode", column = @Column(nullable = false)),
		@AttributeOverride(name = "city", column = @Column(nullable = false)),
		@AttributeOverride(name = "country", column = @Column(nullable = false))
		})
	@NotNull
	private Address companyAddress;

	public Company() {
		super();
	}

	public Company(String companyName, String companyBranch, Address companyAddress) {
		super();
		this.companyName = companyName;
		this.companyBranch = companyBranch;
		this.companyAddress = companyAddress;

	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyBranch() {
		return companyBranch;
	}

	public void setCompanyBranch(String companyBranch) {
		this.companyBranch = companyBranch;
	}

	public Address getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(Address companyAddress) {
		this.companyAddress = companyAddress;
	}

	public Long getIdCompany() {
		return idCompany;
	}

}
