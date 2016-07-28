package fr.adaming.forum.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import fr.adaming.forum.entity.Company;
import fr.adaming.forum.entity.Formation;

@Component
public class FormationDaoImpl implements IFormationDao {

	@PersistenceContext
	private EntityManager em;

	Logger log = Logger.getLogger("FormationDaoImpl");

	@Override
	public Formation addFormation(Formation formation) {
		em.persist(formation);
		log.info("La formation " + formation + " a été ajoutée.");
		return formation;
	}

	@Override
	public Formation getFormationById(Long idFormation) {
		Formation formation = em.find(Formation.class, idFormation);
		if (formation != null) {
			log.info("La formation " + formation.getFormationName() + " est dans la base de donnée");
		} else {
			log.warning("La formation " + idFormation + " n'a pas été trouvé");
		}
		return formation;
	}

	@Override
	public Formation updateFormation(Formation formation) {
		em.merge(formation);
		log.info("La formation " + formation + " a été modifiée");
		return em.find(Formation.class, formation.getIdFormation());

	}

	@Override
	public Formation deleteFormation(Long idFormation) {
		Formation formation = getFormationById(idFormation);
		if (formation != null) {
			em.remove(formation);
			log.info("La formation " + formation + " a été supprimée de la base de donnée");
		}
		return formation;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Formation> getAllFormations() {
		Query query = em.createQuery("FROM Formation");
		List<Formation> result = query.getResultList();
		log.info("Il y a " + result.size() + " Formation(s) !");
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Formation> getFormationByKeyWord(String keyWord) {
		Query query = em.createQuery("FROM Formation f WHERE f.formationName LIKE :x OR f.city LIKE :x");
		query.setParameter("x", "%" + keyWord + "%");

		List<Formation> result = query.getResultList();
		log.info(result.size() + " formations ont été trouvé !");
		return result;
	}

}
