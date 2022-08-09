package com.brsanlr.brsanlr.dto;

import java.time.LocalDateTime;
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
public class StudentExamStatusDto {
	
	private long studentExamStatusId;
	
	private long examId;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	private LocalDateTime examEndDate;
	private boolean solved;
	private boolean start;
	
	private List<StudentAnswersDto> studentAnswersDtoList;
	
	
}
