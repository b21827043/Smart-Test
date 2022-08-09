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
public class StudentAnalysisSubChapterDto {
	
	private long subChapterId;
	
	private String subChapterName;
	private String subChapterIntroText;

	private AnalysisDto analysisDto;

	public StudentAnalysisSubChapterDto(long subChapterId, String subChapterName, String subChapterIntroText) {
		this.subChapterId = subChapterId;
		this.subChapterName = subChapterName;
		this.subChapterIntroText = subChapterIntroText;
	}
	
	
	
}
