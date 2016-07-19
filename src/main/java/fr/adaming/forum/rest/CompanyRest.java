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

import fr.adaming.forum.entity.Company;
import fr.adaming.forum.service.ICompanyService;

@RestController
public class CompanyRest {
	
	@Autowired
	private ICompanyService companyService;
	
	@RequestMapping(value="/getCompany",method=RequestMethod.GET)
	public List<Company> getAllCompany(){
		return companyService.getAllCompany();
	}
	
	@RequestMapping(value="/addCompany", method=RequestMethod.POST)
	public Object addCompany (@RequestBody @Valid Company company, BindingResult r){
		if(r.hasErrors()){
			Map<String, Object> errors = new HashMap<String, Object>();
			errors.put("errors",true);
			for(FieldError f: r.getFieldErrors()){
				errors.put(f.getField(), f.getDefaultMessage());
			}
			return errors;
		}
		return companyService.addCompany(company);
	}
	
	@RequestMapping(value="/getCompanyById/{idCompany}", method=RequestMethod.GET)
	public Company getCompanyById(@PathVariable Long idCompany){
		return companyService.getCompanyById(idCompany);
	}
	
	@RequestMapping(value="/getCompanyByKeyWord/{keyWord}", method=RequestMethod.GET)
	public List<Company> getCompanyByKeyWord(@PathVariable String keyWord){
		return companyService.getCompanyByMc(keyWord);
	}
	
	@RequestMapping(value="/deleteCompany/{idCompany}", method=RequestMethod.DELETE)
	public Company deleteCompany(@PathVariable Long idCompany){
		return companyService.deleteCompany(idCompany);
	}
	
	@RequestMapping(value="/updateCompany", method=RequestMethod.PUT)
	public Company updateCompany(@RequestBody Company company){
		return companyService.updateCompany(company);
	}

}
