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

import fr.adaming.forum.entity.User;
import fr.adaming.forum.service.IUserService;

@RestController
public class UserRest {

	@Autowired
	private IUserService service;
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public Object addUser (@RequestBody @Valid User u, BindingResult r){
		if(r.hasErrors()){
			Map<String, Object> errors = new HashMap<String, Object>();
			errors.put("errors",true);
			for(FieldError f: r.getFieldErrors()){
				errors.put(f.getField(), f.getDefaultMessage());
			}
			return errors;
		}
		return service.addUser(u);
	}
	
	@RequestMapping(value="/getUserById/{idUser}", method=RequestMethod.GET)
	public User getUserById(@PathVariable Long idUser){
		return service.getUserById(idUser);
	}
	
	@RequestMapping(value="/getAllUsers", method=RequestMethod.GET)
	public List<User> getAllUsers(){
		return service.getAllUser();
	}
	
	@RequestMapping(value="/getUserByKeyWord/{KW}", method=RequestMethod.GET)
	public List<User> getUserById(@PathVariable String KW){
		return service.getUserByKeyWord(KW);
	}
	
	@RequestMapping(value="/deleteUser/{idUser}", method=RequestMethod.DELETE)
	public User deleteUSer(@PathVariable Long idUser){
		return service.deleteUser(idUser);
	}
	
	@RequestMapping(value="/updateUser", method=RequestMethod.POST)
	public User updateUser(@RequestBody User user){
		return service.updateUser(user);
	}
	
}
