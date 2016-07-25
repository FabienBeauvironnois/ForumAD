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

		log.info("L'utilisateur " + user.getIdUser() + " � bien �t� ajout� !");
		return user;
	}

	@Override
	public User updateUser(User user) {
		em.merge(user);

		log.info("L'utilisateur " + user.getIdUser() + " � bien �t� modifi� !");
		return user;
	}

	@Override
	public User deleteUser(Long idUser) {
		User user = em.find(User.class, idUser);
		if (user != null) {
			em.remove(user);
			log.info("L'utilisateur " + user.getIdUser() + " � bien �t� supprim� !");
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() {
		Query query = em.createQuery("From User");
		List<User> result = query.getResultList();
		log.info("Il y a " + result.size() + " utilisateur(s) !");
		return result;
	}

	@Override
	public User getUserById(Long idUser) {
		User user = em.find(User.class, idUser);
		if (user != null) {
			log.info("L'utilisateur " + user.getIdUser() + " � bien �t� trouv� !");
		} else {
			log.warning("L'utilisateur " + idUser + " n'a pas �t� trouv� !");
		}

		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByKeyWord(String keyWord) {
		Query query = em.createQuery(
				"FROM User u INNER JOIN u.formation f JOIN u.company c WHERE u.firstName LIKE :x OR u.name LIKE :x OR f.formationName LIKE :x OR c.companyName LIKE :x OR c.companyName LIKE :x OR c.companyAddress.city LIKE :x OR c.companyAddress.country LIKE :x OR u.personalAddress.city LIKE :x OR u.personalAddress.country LIKE :x");

		query.setParameter("x", "%" + keyWord + "%");
		List<User> result = query.getResultList();
		log.info(result.size() + "utilisateur(s) ont �t� trouv� !");
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
		log.info(result.size() + " utilisateur(s) ont �t� trouv� !");
		return result;
	}

}
