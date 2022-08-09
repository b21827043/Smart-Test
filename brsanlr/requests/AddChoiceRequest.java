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
public class AddChoiceRequest {
	
	private String choiceImg;
	private String choiceText;
	private boolean correctAnswer;
	private long questionId;
}
