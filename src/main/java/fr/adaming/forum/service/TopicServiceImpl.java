package fr.adaming.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.forum.dao.ITopicDao;
import fr.adaming.forum.entity.Topic;

@Transactional
public class TopicServiceImpl implements ITopicService{

	@Autowired
	private ITopicDao topicDao;
	
	@Override
	public Topic addTopic(Topic topic) {
		return topicDao.addTopic(topic);
	}

	@Override
	public Topic updateTopic(Topic topic) {
		return topicDao.updateTopic(topic);
	}

	@Override
	public Topic deleteTopic(Long idTopic) {
		return topicDao.deleteTopic(idTopic);
	}

	@Override
	public List<Topic> getAllTopic() {
		return topicDao.getAllTopic();
	}

	@Override
	public List<Topic> getTopicByKeyWord(String keyWord) {
		return topicDao.getTopicByKeyWord(keyWord);
	}

}
