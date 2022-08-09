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
@Table(name = "studentChapterAnalysis")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentChapterAnalysis {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long studentChapterAnalysisId;
	
	private long subChapterId;
	
	private long trueNumber;
	private long falseNumber;
	private long totalNumber;
	
	private long trueRate;
	
	@JsonBackReference
	@ManyToOne
	private Student student;

	public StudentChapterAnalysis(long subChapterId, long trueNumber, long falseNumber, long totalNumber, long trueRate,
			Student student) {
		this.subChapterId = subChapterId;
		this.trueNumber = trueNumber;
		this.falseNumber = falseNumber;
		this.totalNumber = totalNumber;
		this.trueRate = trueRate;
		this.student = student;
	}
	
	
	
	
	
}
