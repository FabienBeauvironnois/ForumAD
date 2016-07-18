package fr.adaming.forum.test;

import static org.junit.Assert.*;



import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.adaming.forum.entity.Address;
import fr.adaming.forum.service.IAddressService;

public class AddressServiceTest {
	
	private static ClassPathXmlApplicationContext context;
	private static IAddressService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		service = (IAddressService) context.getBean("serviceAddress");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Test
	public void testAddAddress() {
		Address address = new Address(5, "rue Bidon", 31600, "Bidonville", "Bidonland");
		address = service.addAdresse(address);
		
		assertNotNull(address.getIdAddress());
		
	}
	
	@Test
	public void testGetAddressById() {
		Address address = service.getAdresseById(1L);
		
		assertNotNull(address);
		
	}
	
	@Test
	public void testUpdateAddress() {
		Address address = service.getAdresseById(1L);
		Address addressUpdate = service.getAdresseById(1L);
		addressUpdate.setCity("Update2");
		addressUpdate = service.updateAdresse(addressUpdate);
		
		
		assertFalse(addressUpdate.equals(address));
	}
	
	/*@Test
	public void testDeleteAddress() {
		
		Address address = new Address(5, "rue Bidon", 31600, "Bidonville", "Bidonland");
		address = service.addAdresse(address);
		
		Long idAddress = service.deleteAdresse(address.getIdAddress()).getIdAddress();
		
				
		assertTrue( service.getAdresseById(idAddress) == null );
		
		
	}*/

}
