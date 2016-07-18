package fr.adaming.forum.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.adaming.forum.entity.Formation;
import fr.adaming.forum.service.IFormationService;

public class FormationServiceTest {
	
	private static ClassPathXmlApplicationContext context;
	private static IFormationService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		service = (IFormationService) context.getBean("serviceFormation");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testAddFormation() {
		Formation formation = new Formation("JavaEE", "Toulouse", new Date(2016, 04, 18), new Date(2016, 07, 8) , false);
		service.addFormation(formation);
		
		assertNotNull(formation.getIdFormation());
	}
	
	@Test
	public void testGetFormationById() {
		Formation formation = service.getFormationById(1L);
		
		assertNotNull(formation);
	}
	
	@Test
	public void testDeleteFormation() {
		Formation formation = service.deleteFormation(1L);
		assertNotNull(formation);
	}

}
