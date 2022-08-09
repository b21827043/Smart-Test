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
public class QuestionDto {
	
	private long questionId;
	
	private long questionDif;
	
	private String questionImg;
	private String questionText;
	
	
	private List<ChoiceDto> choiceDtoList;
	
	
}
