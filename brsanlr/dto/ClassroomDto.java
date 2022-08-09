package com.brsanlr.brsanlr.dto;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class ClassroomDto {
	
	private long classroomId;
	
	
	private String classroomName;
	private String classroomIntroText;
	
	@JsonIgnoreProperties(value="classrooms")
	private List<StudentTeacherViewDto> users;
	
	@JsonIgnoreProperties(value="classrooms")
	private List<ExamDto> exams;
	
	@JsonIgnoreProperties(value="classroom")
	private List<PostDto> posts;
}
