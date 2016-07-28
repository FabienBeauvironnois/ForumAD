package fr.adaming.forum.entity;

import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

/*
 * @author Alonzo.M, Beauvironnois.F, Bonnecaze.K, Roblin.M
 * Création d'un nouveau topic qui doit obligatoirement contenir un sujet qui est créé via la classe commentaire. 
 * Un topic doit forcément contenir 1 sujet, si on delete le topic, on delete tous les commentaires qui lui sont liés.
 */
@SuppressWarnings("deprecation")
@Entity
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTopic;

	@NotNull
	private String title;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "idUser")
	private User user;

	// REMARQUE le sujet est également contenu dans la liste des commentaires
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idComment")
	private Comment subject;

	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Sort(type=SortType.NATURAL)
	private SortedSet<Comment> comments = new TreeSet<Comment>();

	@NotNull
	private java.sql.Timestamp timestamp;

	public Topic() {
		super();
	}

	public Topic(String title, Comment subject) {
		super();
		this.title = title;
		this.user = subject.getUser();
		this.timestamp = new java.sql.Timestamp( (new Date()).getTime() );
		// this.comments.add(comment);
		this.subject = subject;
		subject.setTopic(this);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(java.sql.Timestamp date) {
		this.timestamp = date;
	}

	public Long getIdTopic() {
		return idTopic;
	}

	public SortedSet<Comment> getComments() {
		return this.comments;
	}

	public boolean addComment(Comment comment) {
		
		boolean add = comments.add(comment);
	//	boolean contain = comments.contains(comment);
	//	int commentsSize = comments.size();
		if( add ){
			comment.setTopic(this);
			return true;
		}
		return false;

	}

	public Comment getSubject() {
		return subject;
	}

}
