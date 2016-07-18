package fr.adaming.forum.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import fr.adaming.forum.dao.SkillLevel;
import fr.adaming.forum.entity.Formation;
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
		Skill skill = new Skill("JavaEE", SkillLevel.three);
		service.addSkill(skill);
	
		assertNotNull(skill.getIdSkill());
	}

	@Test
	public void testDeleteSkill(){
		
		List<Skill> listSkills = service.getAllSkills();
		int sizeBefore = listSkills.size();
		Skill skill = service.deleteSkill(3L);
		List<Skill> listSkillsafter = service.getAllSkills();
		int sizeAfter = listSkillsafter.size();
		assertNotNull(skill);
		assert(sizeBefore > sizeAfter);
	}
		
	
	@Test
	public void testGetSkillById(){
		Skill skill = service.getSkillById(2L);
		
		assertNotNull(skill);
	}
	@Test
	public void testGetAllSkills(){
		List<Skill> skill = service.getAllSkills();
		assertNotNull(skill);
	}
	
	@Test
	public void testGetSkillByKeyword(){
		
		List<Skill> skillform = service.getSkillByKeyWord("Java");
		assertFalse(skillform.isEmpty());
	}
	
	@Test
	public void testUpdateSkill(){
		Skill skill = new Skill("C++",SkillLevel.four);	
		
		skill = service.addSkill(skill);		
		Skill s = service.updateSkill(skill);
		s.setSkillName("toto");
		s = service.updateSkill(s);
		
		assertTrue( s.equals(skill));
	}

}
