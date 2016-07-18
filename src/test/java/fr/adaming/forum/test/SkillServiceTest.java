package fr.adaming.forum.test;

import static org.junit.Assert.*;

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
		Skill skill = new Skill("JavaEE", SkillLevel.three);
		skill = service.addSkill(skill);
		
		int sizeBefore = service.getAllSkills().size();
		skill = service.deleteSkill(skill.getIdSkill());
		int sizeAfter = service.getAllSkills().size();
		
		assertNotNull(skill);
		assertTrue( service.getSkillById(skill.getIdSkill()) == null );
		assert(sizeBefore == sizeAfter+1);
	}
		
	
	@Test
	public void testGetSkillById(){
		Skill skill = new Skill("JavaEE", SkillLevel.three);
		service.addSkill(skill);
		skill = service.getSkillById(skill.getIdSkill());
		
		assertNotNull(skill);
	}
	
	@Test
	public void testGetAllSkills(){
		List<Skill> skill = service.getAllSkills();
		assertNotNull(skill);
	}
	
	@Test
	public void testGetSkillByKeyword(){
		Skill skill = new Skill("Python", SkillLevel.three);
		service.addSkill(skill);
		List<Skill> skillform = service.getSkillByKeyWord("Python");
		
		assertFalse(skillform.isEmpty());
	}
	
	@Test
	public void testUpdateSkill(){
		Skill skill = new Skill("C++",SkillLevel.four);	
		skill = service.addSkill(skill);	
		
		Skill s = service.getSkillById(skill.getIdSkill());
		s.setSkillName("toto");
		s = service.updateSkill(s);
		
		assertFalse( s.equals(skill) );
	}

}
