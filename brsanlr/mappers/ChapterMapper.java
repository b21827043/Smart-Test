package com.brsanlr.brsanlr.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.brsanlr.brsanlr.dto.ChapterDto;
import com.brsanlr.brsanlr.dto.StudentAnalysisChapterDto;
import com.brsanlr.brsanlr.entities.Chapter;
import com.brsanlr.brsanlr.requests.AddChapterRequest;
import com.brsanlr.brsanlr.requests.UpdateChapterRequest;

@Mapper(componentModel = "spring",uses= SubChapterMapper.class)
public interface ChapterMapper {
	
	Chapter toChapter(AddChapterRequest addChapterRequest);
	ChapterDto toChapterDto(Chapter chapter);
	List<ChapterDto> toChapterDtoList(List<Chapter> chapterList);
	
	Chapter toChapterUpdate(Chapter chapter,UpdateChapterRequest updateChapterRequest);
	
	StudentAnalysisChapterDto toStudentAnalysisChapterDto(Chapter chapter);
	List<StudentAnalysisChapterDto> toStudentAnalysisChapterDtoList(List<Chapter> chapter);
	
}
