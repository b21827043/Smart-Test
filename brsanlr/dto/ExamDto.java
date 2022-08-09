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
public class ExamDto {
	
	private long examId;
	
	private String examName;
	private String examIntroText;
	
	private long examDuration;
	
	private LocalDateTime expiredDate;
	
	private boolean expired;
	
	private List<QuestionDto> questionDtoList;
	
}
