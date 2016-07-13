package fr.adaming.forum.dao;

import java.util.List;

import fr.adaming.forum.entity.Comment;

public interface ICommentDao {
	
	public Comment addComment(Comment comment);
	public List<Comment> getCommentByKeyWord(String keyWord);
	public Comment updateComment(Comment comment);
	public Comment deleteComment(int idComment);
	public List<Comment> getAllComments ();

}
