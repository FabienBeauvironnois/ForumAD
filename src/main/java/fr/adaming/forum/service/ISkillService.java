package fr.adaming.forum.service;

import java.util.List;

import fr.adaming.forum.entity.Skill;

public interface ISkillService {
	public Skill addSkill(Skill skill);
	public List<Skill> getSkillByKeyWord(String keyWord);
	public Skill updateSkill(Skill skill);
	public Skill deleteSkill(int idSkill);
	public List<Skill> getAllSkills ();
}
