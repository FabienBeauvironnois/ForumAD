package fr.adaming.forum.test;

import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.adaming.forum.dao.SkillLevel;
import fr.adaming.forum.entity.Skill;
import fr.adaming.forum.service.ISkillService;

public class SkillServiceTest {

	private static ClassPathXmlApplicationContext context;
	private static ISkillService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		service = (ISkillService) context.getBean("serviceSkill");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Test
	public void testAddSkill() {
		Skill skill = new Skill("Java", SkillLevel.one);
		service.addSkill(skill);
	
		assertNotNull(skill.getIdSkill());
	}
	

}
