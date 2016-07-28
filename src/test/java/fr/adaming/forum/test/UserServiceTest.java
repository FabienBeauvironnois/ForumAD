package fr.adaming.forum.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.AssertFalse;

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
		try {
			defaultUserDB = service.addUser(defaultUser);
		} catch (Exception e) {
			defaultUser.setFirstName("new" + defaultUser.getFirstName());
			defaultUserDB = service.addUser(defaultUser);
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	/*
	 * Test l'ajout d'un 2nd User de même formation que le default
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testAddUser() {

		Role role = new Role("Admin");
		role = serviceRole.addRole(role);
		Address address = new Address(15, "toto", 31000, "Toulouse", "France");
		Company company = new Company("Flander's", "Toulouse", address);
		company = serviceCompany.addCompany(company);
		User user = new User("Prenom", "Nom", address, company, role, "monemail@email.fr", "unPassw0rd",
				defaultUserDB.getFormation(), new java.sql.Date(0, 0, 0));
		Skill skill = new Skill("OPM", 5);
		//skill = serviceSkill.addSkill(skill);
		user.addSkill(skill);
		user = service.addUser(user);

		// User user2 = new User("Test multiUser in formation", "Name", address,
		// company, role, "email@email.fr", "unPassw0rd", formation,
		// new java.sql.Date(0, 0, 0));

		System.out.println("USERS DANS LA FORMATION : "
				+ serviceFormation.getFormationById(user.getFormation().getIdFormation()).getUsers().size());

		assertNotNull(user.getName());
		service.deleteUser(user.getIdUser());

	}

	@Test
	public void testAddSkillToUser() {

		// Préparation des données nécessaires au test
		User user = createDefaultUser();
		user.setFirstName("User1");
		user.setEmail("User1@email.com");
		User user2 = createDefaultUser();
		user2.setFirstName("User2");
		user2.setEmail("User2@email.com");
		user = service.addUser(user);
		user2 = service.addUser(user2);

		Skill skill = new Skill("TestAddSkillToUser", 3);
		user.removeSkill(skill);
		user2.removeSkill(skill);
		user = service.updateUser(user);
		user2 = service.updateUser(user2);
		
//		Collection<Skill> skills = serviceSkill.getSkillByKeyWord(skill.getSkillName());
//		for (Skill s : skills) {
//			serviceSkill.deleteSkill(s.getIdSkill());
//		}
		
		//Début du test
		
		user.addSkill(skill);
		System.out.println("Le skill " + user.getSkills().toArray()[0] + " a bien été rajouté à l'utilisateur n° "
				+ user.getIdUser());
		user = service.updateUser(user);

		System.out.println("L'utilisateur" + user.getFirstName() + " a bien été mis à jour !");

		// On lie un second utilisateur au seul skill créé

		user2.addSkill(skill);
		user2 = service.updateUser(user2);

		// On teste la persistence du skill malgré la délétion d'un des deux
		// utilisateurs liés.

		assertTrue("Le premier user n'a pas de skill ajouté", !user.getSkills().isEmpty());
		assertTrue("Le second user n'a pas de skill ajouté", !user2.getSkills().isEmpty());

		user = service.deleteUser(user.getIdUser());
		assertTrue("Le skill n'est plus lié à User2", !user2.getSkills().isEmpty());

		// Test du orphanRemoval
		user2 = service.deleteUser(user2.getIdUser());
		assertTrue("Le skill orphelin n'a pas été supprimé", serviceSkill.getSkillById(skill.getIdSkill()) == null);

	}

	@SuppressWarnings("deprecation")

	@Test
	public void testDeleteUser() {

		// Role role = new Role("Admin");
		// serviceRole.addRole(role);
		// Formation formation = new Formation("C++", "Toulouse", new Date(),
		// new Date(), true);
		// serviceFormation.addFormation(formation);
		// Address address = new Address(15, "toto", 31000, "Toulouse",
		// "France");
		// Company company = new Company("adaming2", "Toulouse", address);
		// serviceCompany.addCompany(company);
		// User user = new User("MUST", "Die", address, company, role,
		// "nothing@email.fr", "unPassw0rd", formation,
		// new java.sql.Date(0, 0, 0));

		User user = createDefaultUser();
		user.setFirstName("MustDie");
		user.setEmail("delete@email.com");
		user = service.addUser(user);

		List<User> listUser = service.getAllUser();
		int sizeBefore = listUser.size();
		user = service.deleteUser(user.getIdUser());
		List<User> listUsersAfter = service.getAllUser();
		int sizeAfter = listUsersAfter.size();

		assertNotNull(user);
		assert (sizeBefore == sizeAfter + 1);

	}

	@Test
	public void testUpdateUser() {
		User user = defaultUserDB;
		User updatedUser = service.getUserById(user.getIdUser());
		updatedUser.setFirstName("HULK");
		updatedUser = service.updateUser(updatedUser);

		assertTrue("User non mis à jour !", !user.equals(updatedUser));

	}

	@Test
	public void testGetUserByKeyWord() {
		User user = defaultUserDB;
		user.setName("TEST_BY_KW");
		user = service.updateUser(user);
		List<User> users = service.getUserByKeyWord(user.getName());
		System.out.println(users);
		assertTrue(users.size() > 0);
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

	
	@Test
	public void testGetUserBySkill() {
		User user = defaultUserDB;
		for (Skill s : user.getSkills()) {
			user.removeSkill(s);
		}
		service.updateUser(user);

		Skill skill = new Skill("JPA", 3);
		user.addSkill(skill);
		user = service.updateUser(user);

		List<User> users1 = service.getUsersBySkill(new Skill("JPA", 2));
		List<User> users2 = service.getUsersBySkill(new Skill("JPA", 5));

		assertTrue(users1.size() == 1 && users2.size() == 0);

		for (Skill s : user.getSkills()) {
			user.removeSkill(s);
		}
		defaultUserDB = service.updateUser(user);
	}

	
	@SuppressWarnings("deprecation")
	public static User createDefaultUser() {
		Role role;
		Formation formation;
		Company company;

		List<Role> roles = serviceRole.getAllRole();
		if (roles.isEmpty()) {
			role = serviceRole.addRole(new Role("Admin"));
		} else {
			role = roles.get(0);
		}

		List<Formation> formations = serviceFormation.getAllFormations();
		if (formations.isEmpty()) {
			formation = serviceFormation.addFormation(new Formation("JS", "Toulouse", new Date(), new Date(), true));
		} else {
			formation = formations.get(0);
		}

		Address address = new Address(15, "toto", 31000, "Toulouse", "France");

		List<Company> companies = serviceCompany.getAllCompany();
		if (companies.isEmpty()) {
			company = serviceCompany.addCompany(new Company("Groupe Adaming", "Toulouse", address));
		} else {
			company = companies.get(0);
		}
		return new User("FirstName", "Name", address, company, role, "email@email.fr", "unPassw0rd", formation,
				new java.sql.Date(0, 0, 0));
	}

}
