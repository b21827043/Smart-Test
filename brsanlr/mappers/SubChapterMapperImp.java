package com.brsanlr.brsanlr.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brsanlr.brsanlr.dto.StudentAnalysisSubChapterDto;
import com.brsanlr.brsanlr.dto.SubChapterDto;
import com.brsanlr.brsanlr.entities.SubChapter;
import com.brsanlr.brsanlr.requests.AddSubChapterRequest;
import com.brsanlr.brsanlr.requests.UpdateSubChapterRequest;

@Component
public class SubChapterMapperImp implements SubChapterMapper{

	@Autowired
	QuestionMapper questionMapper;
	
	
	@Override
	public SubChapter toSubChapter(AddSubChapterRequest addSubChapterRequest) {
		return new SubChapter(addSubChapterRequest.getSubChapterName(),addSubChapterRequest.getSubChapterIntroText());
	}

	@Override
	public SubChapterDto toSubChapterDto(SubChapter subChapter) {
		return new SubChapterDto(subChapter.getSubChapterId(),subChapter.getSubChapterName(),subChapter.getSubChapterIntroText(),questionMapper.toQuestionDtoList(subChapter.getQuestions()));
	}

	@Override
	public List<SubChapterDto> toSubChapterDtoList(List<SubChapter> subChapterList) {
		List<SubChapterDto> subChapterDtoList = new ArrayList<SubChapterDto>();
		if(subChapterList == null) {
			return subChapterDtoList;
		}
		for(int i = 0 ; i < subChapterList.size(); i++) {
			SubChapterDto subChapterDto = toSubChapterDto(subChapterList.get(i));
			subChapterDtoList.add(subChapterDto);
		}
		return subChapterDtoList;
	}

	@Override
	public SubChapter toSubChapterUpdate(SubChapter subChapter, UpdateSubChapterRequest updateSubChapterRequest) {
		return new SubChapter(subChapter.getSubChapterId(),updateSubChapterRequest.getSubChapterName(),updateSubChapterRequest.getSubChapterIntroText(),subChapter.getChapter(),subChapter.getQuestions());
	}

	@Override
	public StudentAnalysisSubChapterDto toStudentAnalysisSubChapterDto(SubChapter subChapter) {
		return new StudentAnalysisSubChapterDto(subChapter.getSubChapterId(),subChapter.getSubChapterName(),subChapter.getSubChapterIntroText());
	}

	@Override
	public List<StudentAnalysisSubChapterDto> toStudentAnalysisSubChapterDtoList(List<SubChapter> subChapterList) {
		List<StudentAnalysisSubChapterDto> studentAnalysisSubChapterDtoList = new ArrayList<StudentAnalysisSubChapterDto>();
		if (subChapterList == null) {
			return studentAnalysisSubChapterDtoList;
		}
		for ( int i = 0 ; i < subChapterList.size() ; i++) {
			StudentAnalysisSubChapterDto studentAnalysisSubChapterDto = toStudentAnalysisSubChapterDto(subChapterList.get(i));
			studentAnalysisSubChapterDtoList.add(studentAnalysisSubChapterDto);
		}
		return studentAnalysisSubChapterDtoList;
	}

}
