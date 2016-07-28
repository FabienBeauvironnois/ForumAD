package fr.adaming.forum.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
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
import fr.adaming.forum.service.ICommentService;
import fr.adaming.forum.service.ICompanyService;
import fr.adaming.forum.service.IFormationService;
import fr.adaming.forum.service.IRoleService;
import fr.adaming.forum.service.ITopicService;
import fr.adaming.forum.service.IUserService;

public class CommentServiceTest {
	
	private static ClassPathXmlApplicationContext context;
	private static ICommentService service;
	private static ITopicService serviceTopic;
	private static IUserService serviceUser;
	private static IFormationService serviceFormation;
	private static IRoleService serviceRole;
	private static ICompanyService serviceCompany;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		service = (ICommentService) context.getBean("serviceComment");
		serviceTopic =(ITopicService) context.getBean("serviceTopic");
		serviceUser = (IUserService) context.getBean("serviceUser");
		serviceFormation = (IFormationService) context.getBean("serviceFormation");
		serviceRole = (IRoleService) context.getBean("serviceRole");
		serviceCompany = (ICompanyService) context.getBean("serviceCompany");
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
	
	
	@Test
	public void testAddComment() {
		
		Topic topic = createDefaultTopic();
		
		User user = createDefaultUser();
		user.setEmail("comment@email.com");
		user.setName("Comment");
		user = serviceUser.addUser(user);
		
		String c0 = ( (Comment) serviceTopic.getTopicById(topic.getIdTopic()).getComments().toArray()[0]).getCorpus();
		
		Comment comment1 = service.addComment(new Comment("1er commentaire", user, topic));	

		System.out.println(serviceTopic.getTopicById(topic.getIdTopic()).getComments().size());
		
		String c1 = ( (Comment) serviceTopic.getTopicById(topic.getIdTopic()).getComments().toArray()[1]).getCorpus();
		
		Comment comment2 = service.addComment(new Comment("2nd commentaire", user, topic));	
		
		String c2 = ( (Comment) serviceTopic.getTopicById(topic.getIdTopic()).getComments().toArray()[2]).getCorpus();
				
		System.out.println(  c0 + " - " + c1 + " - " + c2 + " : " + serviceTopic.getTopicById(topic.getIdTopic()).getComments().size());
		
		
		assertNotNull( serviceTopic.getTopicById(topic.getIdTopic()).getComments().size() == 3 );
		
		// Test de la deletion d'un ou des deux commentaires du topic récemment créé
		
		service.deleteComment(comment1.getIdComment());
		service.deleteComment(comment2.getIdComment());
		
		serviceTopic.deleteTopic(topic.getIdTopic());
		serviceUser.deleteUser(topic.getUser().getIdUser());
		serviceUser.deleteUser(user.getIdUser());
	}
	
	
	@Test
	public void testUpdateComment(){
		
		Topic topic = createDefaultTopic();
		User user = createDefaultUser();
		user.setEmail("comment@email.com");
		user.setName("Comment");
		user = serviceUser.addUser(user);
		Comment com = new Comment("Ceci est un commentaire", user, topic);
		
		com = service.addComment(com);
		com.setCorpus("Commentaire mis à jour");
		com = service.updateComment(com);
		
		assertNotNull(com);
		
		service.deleteComment(com.getIdComment());
		
		serviceTopic.deleteTopic(topic.getIdTopic());
		serviceUser.deleteUser(topic.getUser().getIdUser());
		serviceUser.deleteUser(user.getIdUser());
		
	}
	

	@Test
	public void testDeleteComment(){
		Topic topic = createDefaultTopic();
		User user = createDefaultUser();
		user.setEmail("comment@email.com");
		user.setName("Comment");
		user = serviceUser.addUser(user);
		
		Comment com = new Comment("Commentaire à supprimer", user, topic);
		com = service.addComment(com);
		
		List<Comment> listBefore = service.getAllComments();
		int sizeBefore = listBefore.size();
				
		com = service.deleteComment(com.getIdComment());
		List<Comment> listAfter = service.getAllComments();
		int sizeAfter = listAfter.size();
				
		assertTrue(sizeAfter == sizeBefore -1);
		
		service.deleteComment(com.getIdComment());
		
		serviceTopic.deleteTopic(topic.getIdTopic());
		serviceUser.deleteUser(topic.getUser().getIdUser());
		serviceUser.deleteUser(user.getIdUser());
	}

	
	
	public static Topic createDefaultTopic() {
		User user;
		List<User> users = serviceUser.getAllUser();
		if (users.isEmpty()) {
			user = createDefaultUser();
			user = serviceUser.addUser(user);
		} else {
			user = users.get(0);
		}
		Topic topic = new Topic("test", new Comment("Ceci est le sujet du topic", user));
		return serviceTopic.addTopic(topic);
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
