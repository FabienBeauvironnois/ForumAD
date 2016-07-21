package fr.adaming.forum.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import fr.adaming.forum.entity.Skill;

public interface ISkillDao {
	
	public Skill addSkill(Skill skill);
	public Collection<Skill> getSkillByKeyWord(String keyWord);
	public Skill updateSkill(Skill skill);
	public Skill deleteSkill(Long idSkill);
	public Collection<Skill> getAllSkills ();
	public Skill getSkillById(Long idSkill);

}
