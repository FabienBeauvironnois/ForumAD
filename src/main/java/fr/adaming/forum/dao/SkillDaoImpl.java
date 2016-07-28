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
			log.info("La comp�tence " + skill.getSkillName() + " a bien �t� ajout�e");
//		}
//		else {
//			em.merge(skill);
//			log.info("La comp�tence " + skill.getSkillName() + " a bien �t� merg�e");
//		}
		
		return skill;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Skill> getSkillByKeyWord(String keyWord) {
		Query query = em.createQuery("FROM Skill s where s.skillName like :x OR s.skillLevel like :x");
		query.setParameter("x", "%" + keyWord + "%");
		Collection<Skill> result = query.getResultList();
		log.info("Il y a " + result.size() + " comp�tences qui inclue(nt) le mot cl� " + keyWord);
		return result;
	}

	@Override
	public Skill updateSkill(Skill skill) {
		em.merge(skill);
		log.info("La comp�tence " + skill.getSkillName() + " a �t� mise � jour!");
		return em.find(Skill.class, skill.getIdSkill());
	}

	@Override
	public Skill deleteSkill(Long idSkill) {
		Skill skill = em.find(Skill.class, idSkill);
		if (skill != null) {
			em.remove(skill);
			log.info("La comp�tence " + skill.getSkillName() + " a bien �t� supprim�e!");
		}
		return skill;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Skill> getAllSkills() {
		Query query = em.createQuery("FROM Skill");
		Collection<Skill> result = query.getResultList();
		log.info("Il y a " + result.size() + "comp�tences");
		return result;
	}

	@Override
	public Skill getSkillById(Long idSkill) {
		Skill skill = em.find(Skill.class, idSkill);
		if (skill != null) {
			log.info("La comp�tence " + skill.getSkillName() + " est dans la base de donn�e");
		} else {
			log.warning("La comp�tence " + idSkill + " n'est pas dans la base de donn�e");
		}
		return skill;
	}

}
