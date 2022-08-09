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
public class ChapterDto {
	
	private long chapterId;
	
	private String chapterName;
	private String chapterIntroText;
	
	private List<SubChapterDto> subChapterDtoList;
	
}
