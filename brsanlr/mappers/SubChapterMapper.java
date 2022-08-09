package com.brsanlr.brsanlr.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.brsanlr.brsanlr.dto.StudentAnalysisSubChapterDto;
import com.brsanlr.brsanlr.dto.SubChapterDto;
import com.brsanlr.brsanlr.entities.SubChapter;
import com.brsanlr.brsanlr.requests.AddSubChapterRequest;
import com.brsanlr.brsanlr.requests.UpdateSubChapterRequest;

@Mapper(componentModel = "spring",uses=QuestionMapper.class)
public interface SubChapterMapper {

	SubChapter toSubChapter(AddSubChapterRequest addSubChapterRequest);
	SubChapterDto toSubChapterDto(SubChapter subChapter);
	List<SubChapterDto> toSubChapterDtoList(List<SubChapter> subChapterList);
	
	SubChapter toSubChapterUpdate(SubChapter subChapter,UpdateSubChapterRequest updateSubChapterRequest);
	
	StudentAnalysisSubChapterDto toStudentAnalysisSubChapterDto(SubChapter subChapter);
	List<StudentAnalysisSubChapterDto> toStudentAnalysisSubChapterDtoList(List<SubChapter> subChapterList);
}
