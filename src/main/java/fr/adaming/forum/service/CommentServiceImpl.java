package fr.adaming.forum.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.forum.dao.ICommentDao;
import fr.adaming.forum.entity.Comment;

@Transactional
public class CommentServiceImpl implements ICommentService {
	
	Logger log = Logger.getLogger("CommentServiceImpl");

	@Autowired
	private ICommentDao commentDao;
	
	public void setCommentDao(ICommentDao commentDao) {
		this.commentDao = commentDao;
		log.info("<---dao Comment injected------>");
	}
	
	@Override
	public Comment addComment(Comment comment) {
		return commentDao.addComment(comment);
	}

	@Override
	public List<Comment> getCommentByKeyWord(String keyWord) {
		return commentDao.getCommentByKeyWord(keyWord);
	}

	@Override
	public Comment updateComment(Comment comment) {
		return commentDao.updateComment(comment);
	}

	@Override
	public Comment deleteComment(Long idComment) {
		return commentDao.deleteComment(idComment);
	}

	@Override
	public List<Comment> getAllComments() {
		return commentDao.getAllComments();
	}

}
