package fr.adaming.forum.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.adaming.forum.entity.Address;
import fr.adaming.forum.entity.Company;
import fr.adaming.forum.service.ICompanyService;

public class CompanyServiceTest {
	
	private static ClassPathXmlApplicationContext context;
	private static ICompanyService companyService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		companyService = (ICompanyService) context.getBean("serviceCompany");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Test
	public void testAddCompany() {
		Address address = new Address(5, "rue Bidon", 31600, "Bidonville", "Bidonland");
		Company company = new Company("Adaming", "", address);
		company = companyService.addCompany(company);
		
		assertNotNull(company);
		
	}
	
	/*
	@Test
	public void testGetCompanyById() {
		Address address = new Address(5, "rue Bidon", 31600, "Bidonville", "Bidonland");
		Company company = new Company("Altran", "", address);
		
		company = companyService.addCompany(company);
		
		Company getCompany = companyService.getCompanyById(company.getIdCompany());
		
		assertTrue(company.getIdCompany() == getCompany.getIdCompany());
	}
	
	@Test
	public void testUpdateCompany() {
		Address address = new Address(5, "rue Bidon", 31600, "Bidonville", "Bidonland");
		Company company = new Company("Cap Gemini", "", address);
		
		company = companyService.addCompany(company);
		
		Company companyUpdate = companyService.getCompanyById(company.getIdCompany());
		companyUpdate.setCompanyName("Updated");
		
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
		companyService.addCompany(new Company("SII", "", address));
		
		List<Company> companyList = companyService.getCompanyByMc("SII");
		List<Company> companyList2 = companyService.getCompanyByMc("Bidonville");
		
		
		assertTrue(companyList.size()>0 && companyList2.size()>0);
	}
	
	@Test
	public void testDeleteCompany() {
		Address address = new Address(5, "rue Bidon", 31600, "Bidonville", "Bidonland");
		Company company = companyService.addCompany(new Company("Sopra", "", address));
		
		List<Company> listBefore = companyService.getAllCompany();
				
		company = companyService.deleteCompany(company.getIdCompany());
		
		List<Company> listAfter = companyService.getAllCompany();
		
		assertTrue(listAfter.size() == (listBefore.size()-1));
	}

	@Test
	public void testGetCompanyByAddress() {
		Address address = new Address(5, "rue bidon", 31000, "Bidonville", "BidonLand");
		Company company = companyService.addCompany(new Company("Abylsen", "", address));
		
		address = new Address(5, "rue bidon", 31200, "Toulouse", "BidonLand");
		companyService.addCompany(new Company("CGI", "", address));
		
		
		address = new Address(null, "", 31000, "", "");
		List<Company> list = companyService.getCompanyByAddress(address);
		
		System.out.println(list.size());
		assertNotNull(list);
	}
	*/	
	
}
