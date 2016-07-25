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
		return user;
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
		Query query = em.createQuery("From User");

		log.info("Il y a " + query.getResultList().size() + " utilisateur(s) !");
		return query.getResultList();
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
	public List<User> getUserByKeyWord(String keyWord) {
		Query query = em.createQuery(
				"FROM User u INNER JOIN u.formation f JOIN u.company c WHERE u.firstName LIKE :x OR u.name LIKE :x OR f.formationName LIKE :x OR c.companyName LIKE :x OR c.streetNumber LIKE :x OR c.zipCode LIKE :x OR c.city LIKE :x OR c.country LIKE :x");

		query.setParameter("x", "%" + keyWord + "%");

		log.info(query.getResultList().size() + "utilisateur(s) ont été trouvé !");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByAddress(Address address) {
		Query query = em.createQuery(
				"FROM User u WHERE u.streetNumber LIKE :streetNumber AND u.streetName LIKE :streetName AND u.zipCode LIKE :zipCode AND u.city LIKE :city AND u.country LIKE :country");

		query.setParameter("streetNumber", "%" + address.getStreetNumber() + "%");
		query.setParameter("streetName", "%" + address.getStreetName() + "%");
		query.setParameter("zipCode", "%" + address.getZipCode() + "%");
		query.setParameter("city", "%" + address.getCity() + "%");
		query.setParameter("coutry", "%" + address.getCountry() + "%");

		log.info(query.getResultList().size() + "utilisateur(s) ont été trouvé !");
		return query.getResultList();
	}

}
