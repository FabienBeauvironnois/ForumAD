package fr.adaming.forum.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.adaming.forum.entity.Address;
import fr.adaming.forum.entity.Company;
import fr.adaming.forum.service.IAddressService;
import fr.adaming.forum.service.ICompanyService;

public class CompanyServiceTest {
	
	private static ClassPathXmlApplicationContext context;
	private static ICompanyService companyService;
	private static IAddressService addressService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		companyService = (ICompanyService) context.getBean("serviceCompany");
		addressService = (IAddressService) context.getBean("serviceAddress");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Test
	public void testAddCompany() {
		Address address = new Address(5, "rue Bidon", 31600, "Bidonville", "Bidonland");
		address = addressService.addAddress(address);
		Company company = new Company("Adaming", "", address);
		
		company = companyService.addCompany(company);
		
		assertNotNull(company);
		
	}
	
	/*
	@Test
	public void testGetCompanyById() {
		Address address = new Address(5, "rue Bidon", 31600, "Bidonville", "Bidonland");
		address = addressService.addAddress(address);
		Company company = new Company("Adaming", "", address);
		
		company = companyService.addCompany(company);
		
		Company getCompany = companyService.getCompanyById(company.getIdCompany());
		
		assertTrue(company.getIdCompany() == getCompany.getIdCompany());
	}
	
	@Test
	public void testUpdateCompany() {
		Address address = new Address(5, "rue Bidon", 31600, "Bidonville", "Bidonland");
		address = addressService.addAddress(address);
		Company company = new Company("Adaming", "", address);
		
		company = companyService.addCompany(company);
		
		Company companyUpdate = companyService.getCompanyById(company.getIdCompany());
		companyUpdate.setCompanyName("Update");
		
		companyUpdate = companyService.updateCompany(companyUpdate);
		
		assertFalse(company.equals(companyUpdate));
		
	}
	
	@Test
	public void testGetAllCompany() {
		List<Company> companyList = companyService.getAllCompany();
		
		assertNotNull(companyList);
	}
	
	@Test
	public void testGetCompanyByMc() {
		Address address = new Address(5, "rue Bidon", 31600, "Bidonville", "Bidonland");
		address = addressService.addAddress(address);
		companyService.addCompany(new Company("SII", "", address));
		
		List<Company> companyList = companyService.getCompanyByMc("SII");
		
		assertNotNull(companyList);
	}
	
	@Test
	public void testDeleteCompany() {
		Address address = new Address(5, "rue Bidon", 31600, "Bidonville", "Bidonland");
		address = addressService.addAddress(address);
		Company company = companyService.addCompany(new Company("SII", "", address));
		
		List<Company> listBefore = companyService.getAllCompany();
				
		company = companyService.deleteCompany(company.getIdCompany());
		
		List<Company> listAfter = companyService.getAllCompany();
		
		assertTrue(listAfter.size() == (listBefore.size()-1));
	}
*/
}
