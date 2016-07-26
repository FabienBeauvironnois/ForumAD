package fr.adaming.forum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(
		uniqueConstraints={
		@UniqueConstraint(name="skill", columnNames={"skillName", "skillLevel"})
		})

public class Skill {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSkill;
	
	@NotNull
	private String skillName;
	
	@NotNull
	@Min(1)
	@Max(5)
	private Integer skillLevel;
	

	public Skill() {
		super();
	}

	public Skill(String skillName, Integer skillLevel) {
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

	public Integer getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(Integer skillLevel) {
		this.skillLevel = skillLevel;
	}

	public Long getIdSkill() {
		return idSkill;
	}
	
	

}
