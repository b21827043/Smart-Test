package com.brsanlr.brsanlr.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "message")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long messageId;
	
	private long senderId;
	
	private String messageText;
	
	private LocalDateTime messageDate;
	
	
	@JsonBackReference
	@ManyToOne
	private Post post;
	

	public Message(long senderId, String messageText,LocalDateTime now,Post post) {
		this.senderId = senderId;
		this.messageText = messageText;
		this.messageDate = now;
		this.post = post;
	}


	public Message(long senderId, String messageText, LocalDateTime messageDate) {
		this.senderId = senderId;
		this.messageText = messageText;
		this.messageDate = messageDate;
	}


	public Message(long messageId, long senderId,String messageText, LocalDateTime messageDate) {
		this.messageId = messageId;
		this.senderId = senderId;
		this.messageText = messageText;
		this.messageDate = messageDate;
	}
	
	
	
	
}
