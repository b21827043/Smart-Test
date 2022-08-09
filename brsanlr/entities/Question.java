package com.brsanlr.brsanlr.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "question")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties("choices")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long questionId;
	
	// Difficulty level of question 
	private long questionDif;
	
	// Question img and text
	private String questionImg;
	private String questionText;
	

	@JsonBackReference
	@ManyToOne
	private SubChapter subChapter;

	
	@JsonManagedReference
	@OneToMany(mappedBy = "question", targetEntity = Choice.class, cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Choice> choices;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "questions")
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnoreProperties(value="questions")
	private List<Exam> exams = new ArrayList<Exam>();
	
	
	public Question(long questionDif, String questionImg, String questionText,SubChapter subChapter) {
		this.questionDif = questionDif;
		this.questionImg = questionImg;
		this.questionText = questionText;
		this.subChapter = subChapter;
	}



	public Question(long questionDif, String questionImg, String questionText) {
		this.questionDif = questionDif;
		this.questionImg = questionImg;
		this.questionText = questionText;
	}
	
	public void deleteExam(Exam exam) {
		this.exams.remove(exam);
	}
	
	
	
	
}