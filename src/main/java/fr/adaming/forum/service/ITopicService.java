package fr.adaming.forum.service;

import java.util.List;

import fr.adaming.forum.entity.Topic;

public interface ITopicService {
	
	public Topic addTopic(Topic topic);
	public Topic updateTopic(Topic topic);
	public Topic deleteTopic(Long idTopic);
	
	public List<Topic> getAllTopic();
	public List<Topic> getTopicByKeyWord(String keyWord);
	public Topic getTopicById(Long idTopic);

}
