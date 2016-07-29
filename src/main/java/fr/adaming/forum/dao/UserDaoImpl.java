package fr.adaming.forum.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import fr.adaming.forum.entity.Address;
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
		
		return em.find(User.class, user.getIdUser());
//		return user;
	}


	@Override
	public User deleteUser(Long idUser) {
		User user = em.find(User.class, idUser);
		if (user != null) {

			em.remove(user);
			log.info("L'utilisateur " + user.getIdUser() + " à bien été supprimé !");
			
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() {
		Query query = em.createQuery("From User u JOIN u.formation JOIN u.company");
		List<User> result = query.getResultList();
		log.info("Il y a " + result.size() + " utilisateur(s) !");
		return result;
	}

	@Override
	public User getUserById(Long idUser) {
		User user = em.find(User.class, idUser);
		if (user != null) {
			log.info("L'utilisateur " + user.getIdUser() + " à bien été trouvé !");
		} else {
			log.warning("L'utilisateur " + idUser + " n'a pas été trouvé !");
		}

		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersBySkill(String skillName , Integer level) {
		Query query = em.createQuery(
				"FROM User u JOIN u.skills us "
				+ "WHERE us.skillName LIKE :name "
				+ "AND us.skillLevel BETWEEN :level AND 5");

		query.setParameter("name", skillName);
		query.setParameter("level", level);
		
		List<User> result = query.getResultList();
		log.info(result.size() + "utilisateur(s) ont été trouvé !");
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByKeyWord(String keyWord) {
		Query query = em.createQuery(
				"FROM User WHERE formation.formationName LIKE :x "
						+ "OR company.companyAddress.city LIKE :x "
						+ "OR company.companyAddress.country LIKE :x "
						+ "OR company.companyName LIKE :x "
						+ "OR firstName LIKE :x "
						+ "OR personalAddress.city LIKE :x "
						+ "OR personalAddress.country LIKE :x");

		query.setParameter("x", "%" + keyWord + "%");
		List<User> result = query.getResultList();
		log.info(result.size() + "utilisateur(s) ont été trouvé !");
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByAddress(Address address) {
		String queryStr = "FROM User u WHERE u.personalAddress.streetName LIKE :streetName AND u.personalAddress.streetName LIKE :streetName AND u.personalAddress.city LIKE :city AND u.personalAddress.country LIKE :country";
		
		if( address.getStreetNumber()!=null ){
			queryStr += " AND u.personalAddress.streetNumber LIKE :streetNumber";
		}
		if( address.getZipCode()!=null){
			queryStr += " AND u.personalAddress.zipCode LIKE :zipCode";
		}

		Query query = em.createQuery(queryStr);
		
		if( address.getStreetNumber()!=null ){
		query.setParameter("streetNumber", address.getStreetNumber());
		}
		if( address.getZipCode()!=null){
			query.setParameter("zipCode", address.getZipCode());
		}
		query.setParameter("streetName", "%" + address.getStreetName() + "%");
		query.setParameter("city", "%" + address.getCity() + "%");
		query.setParameter("country", "%" + address.getCountry() + "%");

		List<User> result = query.getResultList();
		log.info(result.size() + " utilisateur(s) ont été trouvé !");
		return result;
	}

}
