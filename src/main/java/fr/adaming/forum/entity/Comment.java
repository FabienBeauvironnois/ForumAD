package fr.adaming.forum.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/*
 * @author Alonzo.M, Beauvironnois.F, Bonnecaze.K, Roblin.M@author Alonzo.M, Beauvironnois.F, Bonnecaze.K, Roblin.M
 * Classe permettant de générer les commentaires ainsi que le sujet principal du topic.
 */

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

	public java.sql.Timestamp getTimestamp() {
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
	
	/*
	 * TODO A quoi sert la fonction ici ...
	 */
	
	@Override
	public int compareTo(Comment o) {
		//return o.getDate().compareTo( this.getDate() );

		if( this.equals(o) || this.timestamp == null){
			return this.equals(o) ? 0 : 1 ;
		}
		if( this.timestamp.compareTo(o.timestamp)>=0 ){
			return 1;
		}
		return -1;
	}

}