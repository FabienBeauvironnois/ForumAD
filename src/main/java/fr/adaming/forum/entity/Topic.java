package fr.adaming.forum.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@NotNull
	private LocalDateTime creationDate;

	public Topic() {
		super();
	}

	public Topic(String title, User user) {
		super();
		this.title = title;
		this.user = user;
		this.creationDate = LocalDateTime.now();
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

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Long getIdTopic() {
		return idTopic;
	}
	
	
	

}
