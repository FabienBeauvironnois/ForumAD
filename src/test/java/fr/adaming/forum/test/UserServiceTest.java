package fr.adaming.forum.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.adaming.forum.entity.Address;
import fr.adaming.forum.entity.Company;
import fr.adaming.forum.entity.Formation;
import fr.adaming.forum.entity.Role;
import fr.adaming.forum.entity.User;
import fr.adaming.forum.service.IAddressService;
import fr.adaming.forum.service.ICompanyService;
import fr.adaming.forum.service.IFormationService;
import fr.adaming.forum.service.IRoleService;
import fr.adaming.forum.service.IUserService;

public class UserServiceTest {

	private static ClassPathXmlApplicationContext context;
	private static IUserService service;
	private static IAddressService serviceAddress;
	private static IFormationService serviceFormation;
	private static IRoleService serviceRole;
	private static ICompanyService serviceCompany;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		service = (IUserService) context.getBean("serviceUser");
		serviceAddress = (IAddressService) context.getBean("serviceAddress");
		serviceFormation = (IFormationService) context.getBean("serviceFormation");
		serviceRole = (IRoleService) context.getBean("serviceRole");
		serviceCompany = (ICompanyService) context.getBean("serviceCompany");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Test
	public void testAddUser() {
		Role role = new Role("Admin");
		serviceRole.addRole(role);
		Formation formation = new Formation("JBOSS", "Toulouse", new Date(), new Date(), true);
		serviceFormation.addFormation(formation);
		Address address = new Address(15, "toto", 31000, "Toulouse", "France");
		serviceAddress.addAdresse(address);
		Company company = new Company("adaming", "Toulouse", address);
		serviceCompany.addCompany(company);
		User user = new User("FirstName", "Name", address, company, role, "email@email.fr", "unPassw0rd", formation);
		service.addUser(user);
	
		assertNotNull(user.getName());
	}
	
//	@Test
//	public void testDeleteUser() {
//		
//		List<User> listUser = service.getAllUser();
//		int sizeBefore = listUser.size();
//		User user = service.deleteUser(1L); // Attention le changer à chaque fois pour éviter que le test ne passe pas quand on est en update.
//		List<User> listUsersAfter = service.getAllUser();
//		int sizeAfter = listUsersAfter.size();
//		
//		assertNotNull(user);
//		assert(sizeBefore > sizeAfter);
//
//	}
	
	 @Test
	 public void testUpdateUser(){
		 User user = service.addUser(this.createDefaultUser());
		 User updatedUser = service.getUserById(user.getIdUser());
		 updatedUser.setFirstName("HULK");
		 updatedUser = service.updateUser(updatedUser);
		 
		 assertFalse(user.equals(updatedUser));
		 
	 }
	 
	 @Test
	 public void testGetUserByKeyWord(){
		 List<User> user = service.getUserByKeyWord("adam");
		 System.out.println(user);
	 }
	 
	 private User createDefaultUser(){
		Role role = new Role("Admin");
		serviceRole.addRole(role);
		Formation formation = new Formation("JBOSS", "Toulouse", new Date(), new Date(), true);
		serviceFormation.addFormation(formation);
		Address address = new Address(15, "toto", 31000, "Toulouse", "France");
		serviceAddress.addAdresse(address);
		Company company = new Company("adaming", "Toulouse", address);
		serviceCompany.addCompany(company);
		return new User("FirstName", "Name", address, company, role, "email@email.fr", "unPassw0rd", formation);
	 }
		
}
