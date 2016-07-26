package fr.adaming.forum.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.adaming.forum.entity.Comment;
import fr.adaming.forum.entity.Topic;
import fr.adaming.forum.entity.User;
import fr.adaming.forum.service.ICommentService;
import fr.adaming.forum.service.ITopicService;
import fr.adaming.forum.service.IUserService;

public class CommentServiceTest {
	
	private static ClassPathXmlApplicationContext context;
	private static ICommentService service;
	private static ITopicService serviceTopic;
	private static IUserService serviceUser;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		service = (ICommentService) context.getBean("serviceComment");
		serviceTopic =(ITopicService) context.getBean("serviceTopic");
		serviceUser = (IUserService) context.getBean("serviceUser");
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
	
	
	@Test
	public void testAddComment() {
		List<User> users = serviceUser.getAllUser();
		Comment comment = new Comment("Ceci est un commentaire", users.get(0));
		
		Topic topic = new Topic("TestAddComment", comment);
		topic = serviceTopic.addTopic(topic);
		
		comment = new Comment("2ème commentaire", users.get(0), topic);
		comment = service.addComment(comment);	
				
		assertNotNull( ( (Comment) serviceTopic.getTopicById(topic.getIdTopic()).getComments().toArray()[1]).getCorpus() );
		
	}
	
	
	@Test
	public void testUpdateComment(){
		Comment com = new Comment("Ceci est un commentaire", serviceUser.getAllUser().get(0), serviceTopic.getAllTopic().get(0));
		
		com = service.addComment(com);
		com.setCorpus("Commentaire mis à jour");
		service.updateComment(com);
		
	}
	
	
	@Test
	public void testDeleteComment(){
		Comment com = new Comment("Commentaire à supprimer", serviceUser.getAllUser().get(0), serviceTopic.getAllTopic().get(0));
		com = service.addComment(com);
		
		List<Comment> listBefore = service.getAllComments();
		int sizeBefore = listBefore.size();
				
		com = service.deleteComment(com.getIdComment());
		List<Comment> listAfter = service.getAllComments();
		int sizeAfter = listAfter.size();
				
		assertTrue(sizeAfter == sizeBefore -1);
	}
	

}
