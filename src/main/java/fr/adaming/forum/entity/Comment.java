package fr.adaming.forum.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idComment;
	
	@NotNull
	private String corpus;
	
	@NotNull
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="idTopic")
	private Topic topic;
	
	@NotNull
	private java.sql.Date sqlDate;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;

	public Comment(String corpus, User user, Topic topic) {
		super();
		this.corpus = corpus;
		this.user = user;
		Date utilDate = new Date();
		this.sqlDate = new java.sql.Date(utilDate.getTime());
		this.setTopic(topic);
	}
	
	public Comment(String corpus, User user) {
		super();
		this.corpus = corpus;
		this.user = user;
		Date utilDate = new Date();
		this.sqlDate = new java.sql.Date(utilDate.getTime());
	}	

	public Comment() {
		super();
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCorpus() {
		return corpus;
	}

	public void setCorpus(String corpus) {
		this.corpus = corpus;
	}

	public java.sql.Date getDate() {
		return sqlDate;
	}

	public void setDate(java.sql.Date date) {
		this.sqlDate = date;
	}

	public Long getIdComment() {
		return idComment;
	}

	public Topic getTopic() {
		return topic;
	}
	
	public void setTopic(Topic topic) {
		if( !topic.getComments().contains(this) ){
			topic.addComment(this);
		}
		this.topic = topic;
	}
		
}
