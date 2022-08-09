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
@Table(name = "studentExamStatus")
public class StudentExamStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long studentExamStatusId;
	
	private long examId;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	private LocalDateTime examEndDate;
	
	private boolean solved = false;
	private boolean start = false;
	

	
	@JsonBackReference
	@ManyToOne
	private Student student;
	
	
	@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy = "studentExamStatus", targetEntity = StudentAnswers.class, cascade = CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<StudentAnswers> studentAnswers =null;
	
	
	
	
	public StudentExamStatus(long examId) {
		this.examId = examId;

	}
	
	
	
	
	
}
