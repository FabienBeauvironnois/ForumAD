package fr.adaming.forum.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import fr.adaming.forum.entity.Formation;
import fr.adaming.forum.entity.Skill;

@Component
public class SkillDaoImpl implements ISkillDao{

	@PersistenceContext
	private EntityManager em;

	Logger log = Logger.getLogger("CommentDaoImpl");

	@Override
	public Skill addSkill(Skill skill) {
		em.persist(skill);
		log.info("La compétence " + skill.getSkillName() + " a bien été ajoutée");
		return skill;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Skill> getSkillByKeyWord(String keyWord) {
		Query query = em.createQuery("FROM Skill s where s.skillName like :x OR s.skillLevel like :x");
		query.setParameter("x", "%" + keyWord + "%");
		log.info("Il y a " + query.getResultList().size() + " compétences qui inclue(nt) le mot clé " +keyWord );
		return query.getResultList();
	}

	@Override
	public Skill updateSkill(Skill skill) {
		em.merge(skill);
		log.info("La compétence " + skill.getSkillName() + " a été mise à jour!");
		return skill;
	}

	@Override
	public Skill deleteSkill(Long idSkill) {
		Skill skill = em.find(Skill.class, idSkill);
		em.remove(skill);
		log.info("La compétence " + skill.getSkillName() + " a bien été supprimée!");
		return skill;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Skill> getAllSkills() {
		Query query = em.createQuery("FROM Skill");
		log.info("Il y a "+ query.getResultList().size()+"compétences");
		return query.getResultList();
	}
	
	@Override
	public Skill getSkillById(Long idSkill) {
		Skill skill = em.find(Skill.class, idSkill);
		if(skill != null){
			log.info("La compétence "+ skill.getSkillName() +" est dans la base de donnée" );
		}else{
			log.info("La compétence "+ idSkill +" n'est pas dans la base de donnée" );
		}
		return skill;
	}

}
