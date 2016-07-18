package fr.adaming.forum.test;

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
		
		List<Role> listRoles = service.getAllRole();
		int sizeBefore = listRoles.size();
		Role role = service.deleteRole(3L);
		List<Role> listRolesAfter = service.getAllRole();
		int sizeAfter = listRolesAfter.size();
		
		assertNotNull(role);
		assert(sizeBefore > sizeAfter);
		
	}
	
	@Test 
	public void testGetAllRole(){
		List<Role> role = service.getAllRole();
		assertNotNull(role);
	}
	

}
