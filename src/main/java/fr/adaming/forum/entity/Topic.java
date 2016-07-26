package fr.adaming.forum.entity;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class Topic {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idTopic;
	
	@NotNull
	private String title;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;
		
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idComment")
	private Comment subject;
	
	@OneToMany(mappedBy="topic", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private Collection<Comment> comments = new HashSet<Comment>();
		
	@NotNull	
	private java.sql.Date sqldate;

	public Topic() {
		super();
	}

	public Topic(String title, Comment comment) {
		super();
		this.title = title;
		this.user = comment.getUser();
		Date utilDate = new Date();
		this.sqldate = new java.sql.Date(utilDate.getTime());
		//this.comments.add(comment);
		this.subject = comment;
		comment.setTopic(this);
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

	public Date getDate() {
		return sqldate;
	}

	public void setDate(java.sql.Date date) {
		this.sqldate = date;
	}

	public Long getIdTopic() {
		return idTopic;
	}

	
	public Collection<Comment> getComments() {
		return comments;
	}

	public void addComment(Comment comment) {
		if( !this.comments.contains(comment) ){
			this.comments.add(comment);
		}
		if(comment.getTopic() != this){
			comment.setTopic(this);
		}
	}
	

	public Comment getSubject() {
		return subject;
	}
	
	

}
