package com.brsanlr.brsanlr.requests;

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
public class AddQuestionRequest {
	
	private long questionDif;
	
	private String questionImg;
	private String questionText;
	
	private long subChapterId;
	
}
