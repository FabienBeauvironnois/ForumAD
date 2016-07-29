package fr.adaming.forum.rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.adaming.forum.entity.Skill;
import fr.adaming.forum.service.ISkillService;

@RestController
public class SkillRest {
	
	@Autowired
	private ISkillService skillservice;
	
	@RequestMapping(value="/getAllSkills",method=RequestMethod.GET)
	public Collection<Skill> getAllSkills(){
		return skillservice.getAllSkills();
	}
	
	@RequestMapping(value="/addSkill", method=RequestMethod.POST)
	public Object addSkill (@RequestBody @Valid Skill skill, BindingResult r){
		if(r.hasErrors()){
			Map<String, Object> errors = new HashMap<String, Object>();
			errors.put("errors",true);
			for(FieldError f: r.getFieldErrors()){
				errors.put(f.getField(), f.getDefaultMessage());
			}
			return errors;
		}
		return skillservice.addSkill(skill);
	}
	
	@RequestMapping(value="/getSkillById/{idSkill}", method=RequestMethod.GET)
	public Skill getSkillById(@PathVariable Long idSkill){
		return skillservice.getSkillById(idSkill);
	}

	@RequestMapping(value="/getSkill/name={name}&level={level}", method=RequestMethod.GET)
	public Collection<Skill> getSkillById(@PathVariable String name, @PathVariable Integer level){
		return skillservice.getSkill(new Skill(name, level));
	}
	
	@RequestMapping(value="/getSkillByKeyWord/{keyWord}", method=RequestMethod.GET)
	public Collection<Skill> getSkillByKeyWord(@PathVariable String keyWord){
		return skillservice.getSkillByKeyWord(keyWord);
	}
	
	@RequestMapping(value="/deleteSkill/{idSkill}", method=RequestMethod.DELETE)
	public Skill deleteSkill(@PathVariable Long idSkill){
		return skillservice.deleteSkill(idSkill);
	}
	
	@RequestMapping(value="/updateSkill", method={RequestMethod.PUT, RequestMethod.PATCH})
	public Skill updateSkill(@RequestBody Skill skill){
		return skillservice.updateSkill(skill);
	}

}
