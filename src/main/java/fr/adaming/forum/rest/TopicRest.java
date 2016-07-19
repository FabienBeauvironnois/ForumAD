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

import fr.adaming.forum.entity.Topic;
import fr.adaming.forum.service.ITopicService;

@RestController
public class TopicRest {

	@Autowired
	private ITopicService service;
	
	@RequestMapping(value="/addTopic", method=RequestMethod.POST)
	public Object addTopic (@RequestBody @Valid Topic t, BindingResult r){
		if(r.hasErrors()){
			Map<String, Object> errors = new HashMap<String, Object>();
			errors.put("errors",true);
			for(FieldError f: r.getFieldErrors()){
				errors.put(f.getField(), f.getDefaultMessage());
			}
			return errors;
		}
		return service.addTopic(t);
	}
	
	@RequestMapping(value="/getTopicById/{idTopic}", method=RequestMethod.GET)
	public Topic getTopicById(@PathVariable Long idTopic){
		return service.getTopicById(idTopic);
	}
	
	@RequestMapping(value="/getTopicByKeyWord/{KW}", method=RequestMethod.GET)
	public List<Topic> getTopicByKeyWord(@PathVariable String KW){
		return service.getTopicByKeyWord(KW);
	}
	
	@RequestMapping(value="/getAllTopics", method=RequestMethod.GET)
	public List<Topic> getAllTopics(){
		return service.getAllTopic();
	}
	
	@RequestMapping(value="/deleteTopic/{idTopic}", method=RequestMethod.DELETE)
	public Topic deleteTopic(@PathVariable Long idTopic){
		return service.deleteTopic(idTopic);
	}
	
	@RequestMapping(value="/updateTopic", method=RequestMethod.PUT)
	public Topic updateTopic(@RequestBody Topic topic){
		return service.updateTopic(topic);
	}
	
}
