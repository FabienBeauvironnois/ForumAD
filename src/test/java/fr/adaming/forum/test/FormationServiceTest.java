package fr.adaming.forum.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

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
		
		/*
		int beforeSize = service.getAllFormations().size();
		Formation createdFormation = service.addFormation(formation);
		int afterSize = service.getAllFormations().size();
		System.out.println("formation créée : " + createdFormation);
		assertTrue( afterSize > beforeSize );
		*/
		
		assertNotNull(service.addFormation(formation));
		
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetFormationById() {
		Formation formation = new Formation("C++", "Toulouse", new Date(2016, 04, 18), new Date(2016, 07, 8) , false);
		Long id = service.addFormation(formation).getIdFormation();
		formation = service.getFormationById(id);
		
		assertNotNull(formation);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDeleteFormation() {
		Formation formation = new Formation("JavaEE", "Paris", new Date(2016, 04, 18), new Date(2016, 07, 8) , false);
		Long id = service.addFormation(formation).getIdFormation();
		
		formation = service.deleteFormation(id);
		assertNotNull(formation);
	}


	@SuppressWarnings("deprecation")
	@Test
	public void testUpdateFormation(){
		Formation formation = new Formation(".NET", "Toulouse", new Date(2016, 04, 18), new Date(2016, 07, 8) , false);
		formation = service.addFormation(formation);
		
		Formation oldForm  = service.getFormationById(formation.getIdFormation());
		formation.setCity("Paris");
		Formation newForm = service.updateFormation(formation);
		
		System.out.println(oldForm.getCity());
		System.out.println(newForm.getCity());
		
		assertFalse( newForm.equals(oldForm) );
		
	}
	
	@Test
	public void  testGetAllFormations(){
		List<Formation> formList = service.getAllFormations();
		
		assertFalse( "Nombre de formations trouvées : " + formList.size(), formList.isEmpty() );
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetFormationByKeyWord(){
		Formation formation = new Formation("C++", "Bordeaux", new Date(2016, 04, 18), new Date(2016, 07, 8) , false);
		service.addFormation(formation);
		
		List<Formation> formList1 = service.getFormationByKeyWord("C++");
		List<Formation> formList2 = service.getFormationByKeyWord("Bordeaux");
		
		assertFalse( formList1.isEmpty() || formList2.isEmpty() );
		
	}
	
}
