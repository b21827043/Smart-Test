package com.brsanlr.brsanlr.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "teacher")
//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
public class Teacher extends User {
	
	
	//One to many
	@JsonManagedReference
	@OneToMany(mappedBy = "teacher", targetEntity = Classroom.class, cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	private List<Classroom> classrooms = new ArrayList<Classroom>();
	
	//One to many
	@JsonManagedReference
	@OneToMany(mappedBy = "teacher", targetEntity = Exam.class, cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	private List<Exam> exams = new ArrayList<Exam>();

	public Teacher(String email, String fullName, String password) {
		super(email, fullName, password);
	}
	
	public Teacher() {}
	
	public void deleteClassroom(Classroom classroom) {
		this.classrooms.remove(classroom);
	}
	
	public void deleteExam(Exam exam) {
		this.exams.remove(exam);
	}
	
	public void addExam(Exam exam) {
		this.exams.add(exam);
	}
	
}
