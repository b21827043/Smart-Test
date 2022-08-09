package com.brsanlr.brsanlr.entities;

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

@Entity
@Table(name = "choice")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Choice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long choiceId;
	
	private String choiceImg;
	private String choiceText;
	
	@JsonBackReference
	@ManyToOne
	private Question question;
	
	
	private boolean correctAnswer;


	public Choice(String choiceImg, String choiceText,long questionId,Question question) {
		this.choiceImg = choiceImg;
		this.choiceText = choiceText;
		this.question = question;
	}



	public Choice(String choiceImg, String choiceText,boolean correctAnswer) {
		this.choiceImg = choiceImg;
		this.choiceText = choiceText;
		this.correctAnswer = correctAnswer;
	}
	

	
	
}
