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

import fr.adaming.forum.entity.Address;
import fr.adaming.forum.service.IAddressService;

@RestController
public class AddressRest {

	@Autowired
	private IAddressService service;
	
	@RequestMapping(value="/addAddress", method=RequestMethod.POST)
	public Object addAddress (@RequestBody @Valid Address u, BindingResult r){
		if(r.hasErrors()){
			Map<String, Object> errors = new HashMap<String, Object>();
			errors.put("errors",true);
			for(FieldError f: r.getFieldErrors()){
				errors.put(f.getField(), f.getDefaultMessage());
			}
			return errors;
		}
		return service.addAddress(u);
	}
	
	@RequestMapping(value="/getAddressById/{idAddress}", method=RequestMethod.GET)
	public Address getAddressById(@PathVariable Long idAddress){
		return service.getAddressById(idAddress);
	}
		
	
	@RequestMapping(value="/deleteAddress/{idAddress}", method=RequestMethod.DELETE)
	public Address deleteUSer(@PathVariable Long idAddress){
		return service.deleteAddress(idAddress);
	}
	
	@RequestMapping(value="/updateAddress", method=RequestMethod.PUT)
	public Address updateAddress(@RequestBody Address user){
		return service.updateAddress(user);
	}
	
}
