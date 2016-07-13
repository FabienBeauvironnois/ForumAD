package fr.adaming.forum.dao;

import java.util.List;

import fr.adaming.forum.entity.Skill;

public interface ISkillDao {
	
	public Skill addSkill(Skill skill);
	public List<Skill> getSkillByKeyWord(String keyWord);
	public Skill updateSkill(Skill skill);
	public Skill deleteSkill(int idSkill);
	public List<Skill> getAllSkills ();

}
