package com.brsanlr.brsanlr.dto;


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
public class StudentAnswersDto {
	
	private long studentAnswersId;
	
	private long questionId;
	private long choiceId;
	private long trueChoiceId;
	private boolean trueChoice;
	
	
	
	
}
