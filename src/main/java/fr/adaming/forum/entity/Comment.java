package fr.adaming.forum.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
public class Comment implements Comparable<Comment>{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idComment;
	
	@NotNull
	private String corpus;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idTopic")
	private Topic topic;
	
	@NotNull
	private java.sql.Timestamp timestamp;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;

	public Comment(String corpus, User user, Topic topic) {
		super();
		this.corpus = corpus;
		this.user = user;
		this.timestamp = new java.sql.Timestamp( (new Date()).getTime());
		this.setTopic(topic);
	}
	
	public Comment(String corpus, User user) {
		super();
		this.corpus = corpus;
		this.user = user;
		this.timestamp = new java.sql.Timestamp( (new Date()).getTime() );
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

	public java.sql.Timestamp geTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Long getIdComment() {
		return idComment;
	}

	public Topic getTopic() {
		return topic;
	}
	
	public void setTopic(Topic topic) {
		if( topic.getComments().contains(this)){
			this.topic = topic;
		}else{
			topic.addComment(this);
		}
	}
	
	
	@Override
	public int compareTo(Comment o) {
		//return o.getDate().compareTo( this.getDate() );
		if(this.equals(o)){
			return 0;
		}
		if( this.geTimestamp().compareTo(o.geTimestamp())>=0 ){
			return 1;
		}
		return -1;
	}

}