package fr.adaming.forum.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/*
 * @author Alonzo.M, Beauvironnois.F, Bonnecaze.K, Roblin.M
 * Les différentes compétences qui ont pu être acquises par l'utilisateur au cours et hors formation.
 * Ces compétences sont notées selon 5 niveaux (1 à 5) 1 étant débutant et 5 expert. 
 */

@Entity
@Table(
		uniqueConstraints={
		@UniqueConstraint(name="skill", columnNames={"skillName", "skillLevel"})
		})

public class Skill implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSkill;
	
	@NotNull
	private String skillName;
	
	@NotNull
	@Min(1)
	@Max(5)
	private Integer skillLevel;
	
	@ManyToMany(mappedBy="skills")
	private Set<User> user = new HashSet<User>();
	

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
