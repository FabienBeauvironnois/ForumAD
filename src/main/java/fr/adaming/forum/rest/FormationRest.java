package fr.adaming.forum.rest;

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

import fr.adaming.forum.entity.Formation;
import fr.adaming.forum.entity.Formation;
import fr.adaming.forum.service.IFormationService;

@RestController
public class FormationRest {

	@Autowired
	private IFormationService service;

	@RequestMapping(value = "/getAllFormations", method = RequestMethod.GET)
	public List<Formation> getEtudiants() {
		return service.getAllFormations();
	}

	@RequestMapping(value = "/getFormationById/{idFormation}", method = RequestMethod.GET)
	public Formation getFormationById(@PathVariable Long idFormation) {
		return service.getFormationById(idFormation);
	}

	@RequestMapping(value = "/addFormation", method = RequestMethod.POST)
	public Object addFormation(@RequestBody @Valid Formation u, BindingResult r) {
		if (r.hasErrors()) {
			Map<String, Object> errors = new HashMap<String, Object>();
			errors.put("errors", true);
			for (FieldError f : r.getFieldErrors()) {
				errors.put(f.getField(), f.getDefaultMessage());
			}
			return errors;
		}
		return service.addFormation(u);
	}

	@RequestMapping(value = "/getFormationByKeyWord/{KW}", method = RequestMethod.GET)
	public List<Formation> getFormationById(@PathVariable String KW) {
		return service.getFormationByKeyWord(KW);
	}

	@RequestMapping(value = "/deleteFormation/{idFormation}", method = RequestMethod.DELETE)
	public Formation deleteUSer(@PathVariable Long idFormation) {
		return service.deleteFormation(idFormation);
	}

	@RequestMapping(value = "/updateFormation", method = RequestMethod.PUT)
	public Formation updateFormation(@RequestBody Formation user) {
		return service.updateFormation(user);
	}

}
