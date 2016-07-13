package fr.adaming.forum.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import fr.adaming.forum.entity.Topic;

@Component
public class TopicDaoImpl implements ITopicDao {
	
	@PersistenceContext
	private EntityManager em;
	
	Logger log = Logger.getLogger("TopicDaoImpl");

	@Override
	public Topic addTopic(Topic topic) {
		em.persist(topic);
		
		log.info("Le topic " + topic.getIdTopic() + " a bien été ajouté !");
		return topic;
	}

	@Override
	public Topic updateTopic(Topic topic) {
		em.merge(topic);
		
		log.info("Le topic " + topic.getIdTopic() + " a bien été modifié !");
		return topic;
	}

	@Override
	public Topic deleteTopic(Long idTopic) {
		Topic topic = em.find(Topic.class, idTopic);
		em.remove(topic);
		
		log.info("Le topic " + topic.getIdTopic() + " à bien été supprimé !");
		return topic;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getAllTopic() {
		Query query = em.createQuery("From Topic");
		
		log.info(query.getResultList().size() + " topic(s) ont bien été trouvé !");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getTopicByKeyWord(String keyWord) {
		Query query = em.createQuery("From Topic like :x");
		query.setParameter("x", "%" + keyWord + "%");
		
		log.info(query.getResultList().size() + " topic(s) ont bien été trouvé !");
		return query.getResultList();
	}

}
