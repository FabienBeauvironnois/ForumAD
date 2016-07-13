package fr.adaming.forum.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import fr.adaming.forum.entity.Comment;

@Component
public class CommentDaoImpl implements ICommentDao {

	@PersistenceContext
	private EntityManager em;

	Logger log = Logger.getLogger("CommentDaoImpl");

	@Override
	public Comment addComment(Comment comment) {
		em.persist(comment);
		log.info("Le commentaire " + comment.getTitle() + " a bien été ajouté!");
		return comment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentByKeyWord(String keyWord) {
		Query query = em.createQuery("From Comment c like :x");
		query.setParameter("x", "%" + keyWord + "%");
		
		log.info(query.getResultList().size() + " commentaire(s) ont été trouvé !");
		return query.getResultList();
	}

	@Override
	public Comment updateComment(Comment comment) {
		em.merge(comment);
		log.info("Le commentaire " + comment.getTitle() + " a bien été mis à jour!");
		return comment;
	}

	@Override
	public Comment deleteComment(int idComment) {
		Comment comment = em.find(Comment.class,idComment);
		em.remove(idComment);
		log.info("Le commentaire " + comment.getTitle() + " a bien été supprimé!" );
		return comment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getAllComments() {
		Query query = em.createNamedQuery("FROM Comment");
		return query.getResultList();
	}

}
