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
@Table(name = "studentAnswers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentAnswers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long studentAnswersId;
	
	private long questionId;
	private long choiceId;
	private long trueChoiceId;
	private boolean trueChoice = false;
	
	@JsonBackReference
	@ManyToOne
	private StudentExamStatus studentExamStatus;
	
	public StudentAnswers(long questionId) {
		this.questionId = questionId;
	}
	
}
