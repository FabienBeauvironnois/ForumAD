package fr.adaming.forum.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.adaming.forum.entity.Address;
import fr.adaming.forum.entity.Company;
import fr.adaming.forum.entity.Formation;
import fr.adaming.forum.entity.Role;
import fr.adaming.forum.entity.Skill;
import fr.adaming.forum.entity.User;
import fr.adaming.forum.service.IAddressService;
import fr.adaming.forum.service.ICompanyService;
import fr.adaming.forum.service.IFormationService;
import fr.adaming.forum.service.IRoleService;
import fr.adaming.forum.service.ISkillService;
import fr.adaming.forum.service.IUserService;

public class UserServiceTest {

	private static ClassPathXmlApplicationContext context;
	private static IUserService service;
	private static IFormationService serviceFormation;
	private static IRoleService serviceRole;
	private static ICompanyService serviceCompany;
	private static ISkillService serviceSkill;
	
	private static User defaultUser;
	private static User defaultUserDB;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		service = (IUserService) context.getBean("serviceUser");
		serviceFormation = (IFormationService) context.getBean("serviceFormation");
		serviceRole = (IRoleService) context.getBean("serviceRole");
		serviceCompany = (ICompanyService) context.getBean("serviceCompany");
		serviceSkill = (ISkillService) context.getBean("serviceSkill");
		
		defaultUser = createDefaultUser();
		defaultUserDB = service.addUser(defaultUser);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	
	@Test
	public void testAddUser() {
		
		Role role = new Role("Admin");
		role = serviceRole.addRole(role);
		Formation formation = new Formation("JEE", "Toulouse", new Date(), new Date(), true);
		formation = serviceFormation.addFormation(formation);
		//Formation formation = serviceFormation.getFormationById(1L);
		Address address = new Address(15, "toto", 31000, "Toulouse", "France");
		Company company = new Company("Flander's", "Toulouse", address);
		company = serviceCompany.addCompany(company);
		User user = new User("Prenom", "Nom", address, company, role, "monemail@email.fr", "unPassw0rd", formation, new java.sql.Date(0, 0, 0));
		user.setSkills( new HashSet<Skill>());
		user = service.addUser(user);
	
		//User user2 = new User("Test multiUser in formation", "Name", address, company, role, "email@email.fr", "unPassw0rd", formation);
		
		
		System.out.println( "USERS DANS LA FORMATION : " + serviceFormation.getFormationById(formation.getIdFormation()).getUsers().size() );
		
		
		assertNotNull(user.getName());
		service.deleteUser(user.getIdUser());
	}
	
	@Test
	public void testDeleteUser() {
		
		Role role = new Role("Admin");
		serviceRole.addRole(role);
		Formation formation = new Formation("C++", "Toulouse", new Date(), new Date(), true);
		serviceFormation.addFormation(formation);
		Address address = new Address(15, "toto", 31000, "Toulouse", "France");
		Company company = new Company("adaming2", "Toulouse", address);
		serviceCompany.addCompany(company);
		User user = new User("MUST", "Die", address, company, role, "nothing@email.fr", "unPassw0rd", formation, new java.sql.Date(0, 0, 0));
		user = service.addUser(user);
		
		List<User> listUser = service.getAllUser();
		int sizeBefore = listUser.size();
		user = service.deleteUser(user.getIdUser()); 
		List<User> listUsersAfter = service.getAllUser();
		int sizeAfter = listUsersAfter.size();
		
		assertNotNull(user);
		assert(sizeBefore == sizeAfter +1);
		
	}
	
	
	 @Test
	 public void testUpdateUser(){
		 User user = defaultUserDB;
		 User updatedUser = service.getUserById(user.getIdUser());
		 updatedUser.setFirstName("HULK");
		 updatedUser = service.updateUser(updatedUser);
		 
		 assertFalse(user.equals(updatedUser));
		 
	 }
	 
	 @Test
	 public void testGetUserByKeyWord(){
		 User user = defaultUserDB;
		 user.setName("TEST_BY_KW");
		 user = service.updateUser(user);
		 List<User> users = service.getUserByKeyWord(user.getName());
		 System.out.println(users);
		 assertTrue(users.size()>0);
	 }
	 
	 
	 
		@Test
		public void testGetUserByAddress() {
			Address address = new Address(5, "rue bidon", 31000, "Bidonville", "BidonLand");
			User user = defaultUserDB;
			user.setPersonalAddress(address);
			user = service.updateUser(user);
			
			address = new Address(null, "", 31000, "", "");
			List<User> list = service.getUserByAddress(address);
			
			System.out.println(list.size());
			assertNotNull(list);
		}
	 
	
	
	 public static User createDefaultUser(){
		Role role = new Role("Admin");
		serviceRole.addRole(role);
		Formation formation = new Formation("JBOSS", "Toulouse", new Date(), new Date(), true);
		serviceFormation.addFormation(formation);
		Address address = new Address(15, "toto", 31000, "Toulouse", "France");
		Company company = new Company("Groupe Adaming", "Toulouse", address);
		serviceCompany.addCompany(company);
		return new User("FirstName", "Name", address, company, role, "email@email.fr", "unPassw0rd", formation, new java.sql.Date(0, 0, 0));
	 }
		
}
