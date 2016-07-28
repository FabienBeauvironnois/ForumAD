package fr.adaming.forum.test;

import org.junit.AfterClass;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.adaming.forum.entity.Address;
import fr.adaming.forum.entity.Comment;
import fr.adaming.forum.entity.Company;
import fr.adaming.forum.entity.Formation;
import fr.adaming.forum.entity.Role;
import fr.adaming.forum.entity.Topic;
import fr.adaming.forum.entity.User;
import fr.adaming.forum.service.ICompanyService;
import fr.adaming.forum.service.IFormationService;
import fr.adaming.forum.service.IRoleService;
import fr.adaming.forum.service.ITopicService;
import fr.adaming.forum.service.IUserService;

public class TopicServiceTest {

	private static ClassPathXmlApplicationContext context;
	private static ITopicService topicService;
	private static IUserService userService;
	private static IFormationService serviceFormation;
	private static IRoleService serviceRole;
	private static ICompanyService serviceCompany;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		topicService = (ITopicService) context.getBean("serviceTopic");
		userService = (IUserService) context.getBean("serviceUser");
		serviceFormation = (IFormationService) context.getBean("serviceFormation");
		serviceRole = (IRoleService) context.getBean("serviceRole");
		serviceCompany = (ICompanyService) context.getBean("serviceCompany");

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Test
	public void testAddTopic() {

		Topic topic = createDefaultTopic();

		assertNotNull(topic);

	}

	@Test
	public void testDeleteTopic() {
		
		Topic topic = createDefaultTopic();
		
		List<Topic> listBefore = topicService.getAllTopic();
		topic = topicService.deleteTopic(topic.getIdTopic());
		List<Topic> listAfter = topicService.getAllTopic();

		assertTrue(listAfter.size() == (listBefore.size() - 1));
	}

	@Test
	public void testGetAllTopics() {
		Topic topic = createDefaultTopic();
		List<Topic> topicList = topicService.getAllTopic();
		
		assertTrue(topicList.size() > 0);
		
		topicService.deleteTopic(topic.getIdTopic());
	}

	@Test
	public void testGetTopicById() {
		Topic topic = createDefaultTopic();
		topic = topicService.getTopicById(topic.getIdTopic());

		assertNotNull(topic);
		
		topicService.deleteTopic(topic.getIdTopic());
	}

	@Test
	public void testUpdateTopic() {

		Topic topic = createDefaultTopic();
		Topic topicUpdate = topicService.getTopicById(topic.getIdTopic());
		topicUpdate.setTitle("Update");
		topicService.updateTopic(topicUpdate);

		assertFalse(topic.equals(topicUpdate));
		
		topicService.deleteTopic(topic.getIdTopic());
		topicService.deleteTopic(topicUpdate.getIdTopic());
	}

	@Test
	public void testGetTopicByKeyword() {
		Topic topic = createDefaultTopic();
		topic.setTitle("GetByKW");
		topic = topicService.updateTopic(topic);
		
		List<Topic> topics = topicService.getTopicByKeyWord(topic.getTitle());
		assertFalse(topics.isEmpty());
		System.out.println(topics);
		
		topicService.deleteTopic(topic.getIdTopic());
	}

	public static Topic createDefaultTopic() {
		User user;
		List<User> users = userService.getAllUser();
		if (users.isEmpty()) {
			user = createDefaultUser();
			user = userService.addUser(user);
		} else {
			user = users.get(0);
		}
		Topic topic = new Topic("test", new Comment("Ceci est le sujet du topic", user));
		return topicService.addTopic(topic);
	}

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
