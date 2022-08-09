package com.brsanlr.brsanlr.entities;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
@Table(name = "classroom")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Classroom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long classroomId;
	
	
	private String classroomName;
	private String classroomIntroText;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "classroom_students",
		joinColumns = { @JoinColumn(name = "classroom_id")},
		inverseJoinColumns = { @JoinColumn (name = "user_id")})
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnoreProperties(value="classrooms")
	private List<Student> students = new ArrayList<Student>();
	
	
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "classroom_exams",
		joinColumns = { @JoinColumn(name = "classroom_id")},
		inverseJoinColumns = { @JoinColumn (name = "exam_id")})
	
	@JsonIgnoreProperties(value="classrooms")
	private List<Exam> exams = new ArrayList<Exam>();
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "classroom", targetEntity = Post.class, cascade = CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	private List<Post> posts;
	
	
	
	
	@JsonBackReference
	@ManyToOne
	private Teacher teacher;
	
	public Classroom(String classroomName, String classroomIntroText) {
		this.classroomName = classroomName;
		this.classroomIntroText = classroomIntroText;
	}
	
	public void deleteUser(User user) {
		this.students.remove(user);
	}
	
	public void deleteExam(Exam exam) {
		this.exams.remove(exam);
	}
	
	public void deletePost(Post post) {
		this.posts.remove(post);
	}
	
	public void deleteTeacher() {
		this.teacher = null;
	}
	
}
