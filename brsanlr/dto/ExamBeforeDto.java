package com.brsanlr.brsanlr.dto;

import java.time.LocalDateTime;

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
public class ExamBeforeDto {
	
private long examId;
	
	private String examName;
	private String examIntroText;
	
	private long examDuration;
	
	private LocalDateTime expiredDate;
	
	private boolean expired;
	
	
}
