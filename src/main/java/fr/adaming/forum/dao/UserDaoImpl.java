package fr.adaming.forum.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import fr.adaming.forum.entity.User;

@Component
public class UserDaoImpl implements IUserDao {
	
	@PersistenceContext
	private EntityManager em;
	
	Logger log = Logger.getLogger("UserDaoImpl");
	
	@Override
	public User addUser(User user) {
		em.persist(user);
		
		log.info("L'utilisateur " + user.getIdUser() + " à bien été ajouté !");
		return user;
	}

	@Override
	public User updateUser(User user) {
		em.merge(user);
		
		log.info("L'utilisateur " + user.getIdUser() + " à bien été modifié !");
		return user;
	}

	@Override
	public User deleteUser(Long idUser) {
		User user = em.find(User.class, idUser);
		em.remove(user);
		
		log.info("L'utilisateur " + user.getIdUser() + " à bien été supprimé !");
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() {
		Query query = em.createQuery("From User");
		
		log.info("Il y a " + query.getResultList().size() + " utilisateur(s) !");
		return query.getResultList();
	}

	@Override
	public User getUserById(Long idUser) {
		User user = em.find(User.class, idUser);
		
		log.info("L'utilisateur " + user.getIdUser() + " à bien été trouvé !");
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByKeyWord(String keyWord) {
		Query query = em.createQuery("From User u like :x");
		query.setParameter("x", "%" + keyWord + "%");
		
		log.info(query.getResultList().size() + "utilisateur(s) ont été trouvé !");
		return query.getResultList();
	}

}
