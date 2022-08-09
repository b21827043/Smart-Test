package com.brsanlr.brsanlr.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "exam")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Exam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long examId;
	
	private String examName;
	private String examIntroText;
	
	private long examDuration;
	
	private LocalDateTime expiredDate;
	
	private boolean expired;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "exam_questions",
		joinColumns = { @JoinColumn(name = "exam_id")},
		inverseJoinColumns = { @JoinColumn (name = "question_id")})
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnoreProperties(value="exams")
	private List<Question> questions = new ArrayList<Question>();
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "exams")
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnoreProperties(value="exams")
	private List<Classroom> classrooms = new ArrayList<Classroom>();
	
	
	@JsonBackReference
	@ManyToOne
	private Teacher teacher;
	
	
	public void deleteQuestion(Question question) {
		this.questions.remove(question);
	}
	
	public void deleteClassroom(Classroom classroom) {
		this.classrooms.remove(classroom);
		
	}

	public Exam(String examName, String examIntroText, long examDuration, LocalDateTime expiredDate,
			boolean expired) {
		this.examName = examName;
		this.examIntroText = examIntroText;
		this.examDuration = examDuration;
		this.expiredDate = expiredDate;
		this.expired = expired;
	}
	
	
	
	
	
}
