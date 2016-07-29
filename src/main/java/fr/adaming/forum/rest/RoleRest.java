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

import fr.adaming.forum.entity.Role;
import fr.adaming.forum.service.IRoleService;

@RestController
public class RoleRest {

	@Autowired
	private IRoleService service;
	
	@RequestMapping(value="/addRole", method=RequestMethod.POST)
	public Object addRole (@RequestBody @Valid Role u, BindingResult r){
		if(r.hasErrors()){
			Map<String, Object> errors = new HashMap<String, Object>();
			errors.put("errors",true);
			for(FieldError f: r.getFieldErrors()){
				errors.put(f.getField(), f.getDefaultMessage());
			}
			return errors;
		}
		return service.addRole(u);
	}
	
	@RequestMapping(value="/getRoleById/{idRole}", method=RequestMethod.GET)
	public Role getRoleById(@PathVariable Long idRole){
		return service.getRoleById(idRole);
	}
	
	@RequestMapping(value="/getAllRoles", method=RequestMethod.GET)
	public List<Role> getAllRoles(){
		return service.getAllRole();
	}
		
	@RequestMapping(value="/deleteRole/{idRole}", method=RequestMethod.DELETE)
	public Role deleteUSer(@PathVariable Long idRole){
		return service.deleteRole(idRole);
	}
	
	@RequestMapping(value="/updateRole", method={RequestMethod.PUT, RequestMethod.PATCH})
	public Role updateRole(@RequestBody Role role){
		return service.updateRole(role);
	}
	
}
