package fr.adaming.forum.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import fr.adaming.forum.entity.Role;
import fr.adaming.forum.entity.Skill;

@Component
public class SkillDaoImpl implements ISkillDao {

	@PersistenceContext
	private EntityManager em;

	Logger log = Logger.getLogger("CommentDaoImpl");

	@Override
	public Skill addSkill(Skill skill) {
//		if (skill.getIdSkill() == null){
			em.persist(skill);
			log.info("La compétence " + skill.getSkillName() + " a bien été ajoutée");
//		}
//		else {
//			em.merge(skill);
//			log.info("La compétence " + skill.getSkillName() + " a bien été mergée");
//		}
		
		return skill;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Skill> getSkillByKeyWord(String keyWord) {
		Query query = em.createQuery("FROM Skill s where s.skillName like :x OR s.skillLevel like :x");
		query.setParameter("x", "%" + keyWord + "%");
		Collection<Skill> result = query.getResultList();
		log.info("Il y a " + result.size() + " compétences qui inclue(nt) le mot clé " + keyWord);
		return result;
	}

	@Override
	public Skill updateSkill(Skill skill) {
		em.merge(skill);
		log.info("La compétence " + skill.getSkillName() + " a été mise à jour!");
		return em.find(Skill.class, skill.getIdSkill());
	}

	@Override
	public Skill deleteSkill(Long idSkill) {
		Skill skill = em.find(Skill.class, idSkill);
		if (skill != null) {
			em.remove(skill);
			log.info("La compétence " + skill.getSkillName() + " a bien été supprimée!");
		}
		return skill;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Skill> getAllSkills() {
		Query query = em.createQuery("FROM Skill");
		Collection<Skill> result = query.getResultList();
		log.info("Il y a " + result.size() + "compétences");
		return result;
	}

	@Override
	public Skill getSkillById(Long idSkill) {
		Skill skill = em.find(Skill.class, idSkill);
		if (skill != null) {
			log.info("La compétence " + skill.getSkillName() + " est dans la base de donnée");
		} else {
			log.warning("La compétence " + idSkill + " n'est pas dans la base de donnée");
		}
		return skill;
	}

}
