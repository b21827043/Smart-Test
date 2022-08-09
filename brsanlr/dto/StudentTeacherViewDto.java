package com.brsanlr.brsanlr.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentTeacherViewDto {
	
	private long id;
	private String email;
	private String fullName;
	private String imgUrl = "../img/pp.png";
	
	private List<StudentExamStatusDto> studentExamStatusDtoList;
	
	
	
	
}
