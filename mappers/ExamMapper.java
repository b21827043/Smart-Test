package com.brsanlr.brsanlr.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.brsanlr.brsanlr.dto.ExamBeforeDto;
import com.brsanlr.brsanlr.dto.ExamDto;
import com.brsanlr.brsanlr.entities.Exam;
import com.brsanlr.brsanlr.requests.AddExamRequest;
import com.brsanlr.brsanlr.requests.UpdateExamRequest;

@Mapper(componentModel="spring",uses=QuestionMapper.class)
public interface ExamMapper {
	
	Exam toExam(AddExamRequest addExamRequest);
	ExamDto toExamDto(Exam exam);
	ExamBeforeDto toExamBeforeDto(Exam exam);
	List<ExamDto> toExamDtoList(List<Exam> examList);
	List<ExamBeforeDto> toExamBeforeDtoList(List<Exam> examList);
	Exam toExamUpdate(Exam exam,UpdateExamRequest updateExamRequest);
	
}
