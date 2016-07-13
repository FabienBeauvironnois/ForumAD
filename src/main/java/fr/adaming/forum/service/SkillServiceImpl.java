package fr.adaming.forum.service;

import java.util.List;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.forum.dao.ISkillDao;
import fr.adaming.forum.entity.Skill;

@Transactional
public class SkillServiceImpl implements ISkillService{

	Logger log = Logger.getLogger("SkillServiceImpl");
	
	@Autowired
	private ISkillDao skillDao;
	
	public void setSkillDao(ISkillDao skillDao) {
		this.skillDao = skillDao;
		log.info("<---dao Skill injected------>");
	}
	
	@Override
	public Skill addSkill(Skill skill) {
		return skillDao.addSkill(skill);
	}

	@Override
	public List<Skill> getSkillByKeyWord(String keyWord) {
		return skillDao.getSkillByKeyWord(keyWord);
	}

	@Override
	public Skill updateSkill(Skill skill) {
		
		return skillDao.updateSkill(skill);
	}

	@Override
	public Skill deleteSkill(int idSkill) {
		
		return skillDao.deleteSkill(idSkill);
	}

	@Override
	public List<Skill> getAllSkills() {
		return skillDao.getAllSkills();
	}

}
