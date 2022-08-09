package com.brsanlr.brsanlr.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long postId;
	
	private long senderId;

	
	private String postTitle;
	private String postText;
	
	private LocalDateTime postDate;
	
	
	@JsonBackReference
	@ManyToOne
	@LazyCollection(LazyCollectionOption.FALSE)
	private Classroom classroom;
	
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "post", targetEntity = Message.class, cascade = CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	private List<Message> messages;
	
	public void deleteClassroom() {
		this.classroom = null;
	}
	

	public Post(long senderId,String postTitle, String postText,
			LocalDateTime postDate) {
		this.senderId = senderId;
		this.postTitle = postTitle;
		this.postText = postText;
		this.postDate = postDate;
	}

	public Post(long postId, long senderId, String postTitle, String postText,
			LocalDateTime postDate) {
		this.postId = postId;
		this.senderId = senderId;
		this.postTitle = postTitle;
		this.postText = postText;
		this.postDate = postDate;
	}
	
	
	

}
