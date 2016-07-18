package fr.adaming.forum.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.adaming.forum.entity.Role;
import fr.adaming.forum.service.IRoleService;

public class RoleServiceTest {

	private static ClassPathXmlApplicationContext context;
	private static IRoleService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		service = (IRoleService) context.getBean("serviceRole");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Test
	public void testAddRole() {
		Role role = new Role("Admin");
		service.addRole(role);

		assertNotNull(role.getIdRole());
	}

	@Test
	public void testDeleteRole() {
		Role role = new Role("Moderator");
		role = service.addRole(role);

		int sizeBefore = service.getAllRole().size();
		service.deleteRole(role.getIdRole()); // Attention le changer � chaque
												// fois pour �viter que le test
												// ne passe pas quand on est en
												// update.
		int sizeAfter = service.getAllRole().size();

		assertNotNull(role);
		assert (sizeBefore > sizeAfter);
	}

	@Test
	public void testGetAllRole() {
		List<Role> role = service.getAllRole();
		assertNotNull(role);
	}

	@Test
	public void testUpdateRole() {
		Role role = new Role("Moderator");
		role = service.addRole(role);
		Role updatedRole = service.getRoleById(role.getIdRole());
		updatedRole.setStatus("Master");
		service.updateRole(updatedRole);

		assertFalse(role.equals(updatedRole));

	}

}
