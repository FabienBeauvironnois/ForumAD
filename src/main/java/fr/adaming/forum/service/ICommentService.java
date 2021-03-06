package fr.adaming.forum.service;

import java.util.List;

import fr.adaming.forum.entity.Comment;

public interface ICommentService {
	
	public Comment addComment(Comment comment);
	public List<Comment> getCommentByKeyWord(String keyWord);
	public Comment updateComment(Comment comment);
	public Comment deleteComment(Long idComment);
	public List<Comment> getAllComments ();
	public Comment getCommentById(Long idComment);


}
