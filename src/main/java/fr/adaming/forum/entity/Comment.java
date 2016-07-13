package fr.adaming.forum.entity;

import java.sql.Date;
import java.time.LocalDateTime;

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
	private String title;
	
	@NotNull
	private String corpus;
	
	@ManyToOne
	@JoinColumn(name="idTopic")
	private Topic topic;
	
	@NotNull
	private LocalDateTime date;

	
	
	public Comment(String title, String corpus, Topic topic) {
		super();
		this.title = title;
		this.corpus = corpus;
		this.topic = topic;
		this.date = LocalDateTime.now();
	}
	
	

	public Comment() {
		super();
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCorpus() {
		return corpus;
	}

	public void setCorpus(String corpus) {
		this.corpus = corpus;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Long getIdComment() {
		return idComment;
	}
	
	
	
}
