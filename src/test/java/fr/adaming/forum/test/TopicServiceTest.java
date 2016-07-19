package fr.adaming.forum.test;

import org.junit.AfterClass;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import fr.adaming.forum.entity.Topic;
import fr.adaming.forum.entity.User;
import fr.adaming.forum.service.ITopicService;
import fr.adaming.forum.service.IUserService;

public class TopicServiceTest {

	private static ClassPathXmlApplicationContext context;
	private static ITopicService topicService;
	private static IUserService userService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		topicService = (ITopicService)context.getBean("serviceTopic");
		userService = (IUserService)context.getBean("serviceUser");
	
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
	
	@Test
	public void testAddTopic(){
		User user = userService.getUserById(1L);
		//user = userService.addUser(user);
		Topic topic = new Topic("test2", user);
		topic = topicService.addTopic(topic);
		
		assertNotNull(topic);
		
	}
	/*
	@Test
	public void testDeleteTopic(){
		List<Topic> listBefore = topicService.getAllTopic();
		Topic topic = topicService.getTopicById(2L);
		topic = topicService.deleteTopic(topic.getIdTopic());
		List<Topic> listAfter = topicService.getAllTopic();
		
		assertTrue(listAfter.size() == (listBefore.size()-1));
	}
	
	@Test
	public void testGetAllTopics(){
		List<Topic> topicList = topicService.getAllTopic();
		
		assertNotNull(topicList);
	}
	
	*/
	/*
	@Test
	public void testGetTopicById(){
		Topic topic = topicService.getTopicById(1L);
		
		assertNotNull(topic);
	}
/*
	@Test
	public void testUpdateTopic(){
		
		Topic topic = topicService.getTopicById(1L);
		Topic topicUpdate = topicService.getTopicById(1L);
		topicUpdate.setTitle("Update");
		topicService.updateTopic(topicUpdate);
		
		assertFalse(topic.equals(topicUpdate));
	}
	*/
/*	@Test
	public void testGetTopicByKeyword(){
		List<Topic> topic = topicService.getTopicByKeyWord("Update");
		assertFalse(topic.isEmpty());
		System.out.println(topic);
		}*/
}