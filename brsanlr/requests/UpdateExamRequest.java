package com.brsanlr.brsanlr.requests;

import java.util.List;

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
public class UpdateExamRequest {
	
	
	private String examName;
	private String examIntroText;
	
	private long examDuration;
	
	private long expireDay;
	
	private List<Long> questionIdList;
	
	
	
}
