package com.brsanlr.brsanlr.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student extends User {

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "students")
	@JsonIgnoreProperties(value="users")
	private List<Classroom> classrooms = new ArrayList<Classroom>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "student", targetEntity = StudentExamStatus.class, cascade = CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	private List<StudentExamStatus> examStatusList;
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "student", targetEntity = StudentChapterAnalysis.class, cascade = CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	private List<StudentChapterAnalysis> studentChapterAnalysisList;
	
	
	public Student(String email, String fullName, String password) {
		super(email, fullName, password);
	}
	
	
	public Student() {
		
	}
	
	public void deleteClassroom(Classroom classroom) {
		classrooms.remove(classroom);
	}
	
}
