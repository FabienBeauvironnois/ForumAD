package fr.adaming.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.forum.dao.ICommentDao;
import fr.adaming.forum.entity.Comment;

@Transactional
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private ICommentDao commentDao;
	
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
	public Comment deleteComment(int idComment) {
		return commentDao.deleteComment(idComment);
	}

	@Override
	public List<Comment> getAllComments() {
		return commentDao.getAllComments();
	}

}
