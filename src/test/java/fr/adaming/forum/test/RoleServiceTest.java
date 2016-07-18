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
		service.deleteRole(1L);
		
	}
	
	@Test 
	public void testGetAllRole(){
		List<Role> role = service.getAllRole();
		assertNotNull(role);
	}
	

}
