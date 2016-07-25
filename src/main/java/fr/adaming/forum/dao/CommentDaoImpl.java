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
		log.info("Le commentaire " + comment.getIdComment() + " a bien été ajouté!");
		return comment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentByKeyWord(String keyWord) {
		Query query = em.createQuery("From Comment c like :x");
		query.setParameter("x", "%" + keyWord + "%");

		List<Comment> result = query.getResultList();
		log.info(result.size() + " commentaire(s) ont été trouvé !");
		return result;
	}

	@Override
	public Comment updateComment(Comment comment) {
		em.merge(comment);
		log.info("Le commentaire " + comment.getIdComment() + " a bien été mis à jour!");
		return comment;
	}

	@Override
	public Comment deleteComment(Long idComment) {
		Comment comment = getCommentById(idComment);
		if (comment != null) {
			em.remove(comment);
			log.info("Le commentaire " + comment.getIdComment() + " a bien été supprimé!");
		}
		return comment;
	}

	@Override
	public Comment getCommentById(Long idComment) {
		Comment comment = em.find(Comment.class, idComment);
		return comment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getAllComments() {
		Query query = em.createQuery("FROM Comment c");
		return query.getResultList();
	}

}
