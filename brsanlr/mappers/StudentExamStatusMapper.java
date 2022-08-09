package com.brsanlr.brsanlr.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.brsanlr.brsanlr.dto.StudentExamStatusDto;
import com.brsanlr.brsanlr.entities.StudentExamStatus;

@Mapper(componentModel = "spring",uses=StudentAnswersMapper.class)
public interface StudentExamStatusMapper {
	
	StudentExamStatusDto toStudentExamStatusDto(StudentExamStatus studentExamStatus);
	List<StudentExamStatusDto> toStudentExamStatusDtoList(List<StudentExamStatus> studentExamStatusList);
	
}
