package com.brsanlr.brsanlr.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



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
@Table(name = "chapter")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties("subChapters")
public class Chapter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long chapterId;
	
	private String chapterName;
	private String chapterIntroText;

	@JsonBackReference
	@ManyToOne
	private Course course;
	
	@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy = "chapter", targetEntity = SubChapter.class, cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<SubChapter> subChapters;



	public Chapter(String chapterName, String chapterIntroText,Course course) {
		this.chapterName = chapterName;
		this.chapterIntroText = chapterIntroText;
		this.course = course;
	}



	public Chapter(String chapterName, String chapterIntroText) {
		this.chapterName = chapterName;
		this.chapterIntroText = chapterIntroText;
	}
	

	
	
}
