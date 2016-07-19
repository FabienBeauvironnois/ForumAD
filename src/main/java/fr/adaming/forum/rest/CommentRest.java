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

import fr.adaming.forum.entity.Comment;
import fr.adaming.forum.service.ICommentService;

@RestController
public class CommentRest {

	@Autowired
	private ICommentService service;
	
	@RequestMapping(value="/addComment", method=RequestMethod.POST)
	public Object addComment (@RequestBody @Valid Comment t, BindingResult r){
		if(r.hasErrors()){
			Map<String, Object> errors = new HashMap<String, Object>();
			errors.put("errors",true);
			for(FieldError f: r.getFieldErrors()){
				errors.put(f.getField(), f.getDefaultMessage());
			}
			return errors;
		}
		return service.addComment(t);
	}
	
	@RequestMapping(value="/getCommentById/{idComment}", method=RequestMethod.GET)
	public Comment getCommentById(@PathVariable Long idComment){
		return service.getCommentById(idComment);
	}
	
	@RequestMapping(value="/getCommentByKeyWord/{KW}", method=RequestMethod.GET)
	public List<Comment> getCommentByKeyWord(@PathVariable String KW){
		return service.getCommentByKeyWord(KW);
	}
	
	@RequestMapping(value="/getAllComments", method=RequestMethod.GET)
	public List<Comment> getAllComments(){
		return service.getAllComments();
	}
	
	@RequestMapping(value="/deleteComment/{idComment}", method=RequestMethod.DELETE)
	public Comment deleteComment(@PathVariable Long idComment){
		return service.deleteComment(idComment);
	}
	
	@RequestMapping(value="/updateComment", method=RequestMethod.PUT)
	public Comment updateComment(@RequestBody Comment comment){
		return service.updateComment(comment);
	}
	
}
