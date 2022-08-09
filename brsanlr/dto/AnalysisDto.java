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
public class AnalysisDto {
	private long trueNumber;
	private long falseNumber;
	private long totalNumber;
	
	private long trueRate;
}
