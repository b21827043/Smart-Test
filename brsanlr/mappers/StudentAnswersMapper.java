package com.brsanlr.brsanlr.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.brsanlr.brsanlr.dto.StudentAnswersDto;
import com.brsanlr.brsanlr.entities.StudentAnswers;

@Mapper(componentModel = "spring")
public interface StudentAnswersMapper {
	
	StudentAnswersDto toStudentAnswersDto(StudentAnswers studentAnswers);
	List<StudentAnswersDto> toStudentAnswersDtoList(List<StudentAnswers> studentAnswersList);
	
}
