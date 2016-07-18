package fr.adaming.forum.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import fr.adaming.forum.dao.SkillLevel;

@Entity
public class Skill {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSkill;
	
	@NotNull
	private String skillName;
	
	@NotNull
	private SkillLevel skillLevel;
	

	public Skill() {
		super();
	}

	public Skill(String skillName, SkillLevel skillLevel) {
		super();
		this.skillName = skillName;
		this.skillLevel = skillLevel;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public SkillLevel getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(SkillLevel skillLevel) {
		this.skillLevel = skillLevel;
	}

	public Long getIdSkill() {
		return idSkill;
	}
	
	

}
