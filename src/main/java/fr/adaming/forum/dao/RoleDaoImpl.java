package fr.adaming.forum.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import fr.adaming.forum.entity.Address;
import fr.adaming.forum.entity.Formation;
import fr.adaming.forum.entity.Role;

@Component
public class RoleDaoImpl implements IRoleDao {

	@PersistenceContext
	private EntityManager em;

	Logger log = Logger.getLogger("RoleDaoImpl");

	@Override
	public Role addRole(Role role) {
		em.persist(role);

		log.info("Le role " + role.getIdRole() + " a bien �t� ajout� !");
		return role;
	}

	@Override
	public Role updateRole(Role role) {
		em.merge(role);

		log.info("Le role " + role.getIdRole() + " a bien �t� modifi� !");
		return role;
	}

	@Override
	public Role deleteRole(Long idRole) {
		Role role = em.find(Role.class, idRole);
		if (role != null) {
			em.remove(role);
			log.info("Le role " + role.getIdRole() + " a bien �t� supprim� !");
		}
		return role;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAllRole() {
		Query query = em.createQuery("From Role");

		List<Role> result = query.getResultList();
		log.info(result.size() + "role(s) ont �t� trouv� !");
		return result;
	}

	@Override
	public Role getRoleById(Long idRole) {
		Role role = em.find(Role.class, idRole);
		if (role != null) {
			log.info("Le role " + role + " est dans la base de donn�e");
		} else {
			log.warning("Le role " + idRole + " n'est pas dans la base de donn�e");
		}

		return role;
	}

}
