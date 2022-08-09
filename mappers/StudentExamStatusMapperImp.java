package com.brsanlr.brsanlr.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brsanlr.brsanlr.dto.StudentExamStatusDto;
import com.brsanlr.brsanlr.entities.StudentExamStatus;

@Component
public class StudentExamStatusMapperImp implements StudentExamStatusMapper{
	
	@Autowired
	StudentAnswersMapper studentAnswersMapper;
	
	@Override
	public StudentExamStatusDto toStudentExamStatusDto(StudentExamStatus studentExamStatus) {
		return new StudentExamStatusDto(studentExamStatus.getStudentExamStatusId(),studentExamStatus.getExamId(),studentExamStatus.getStartDate(),studentExamStatus.getEndDate(),studentExamStatus.getExamEndDate(),studentExamStatus.isSolved(),studentExamStatus.isStart(),studentAnswersMapper.toStudentAnswersDtoList(studentExamStatus.getStudentAnswers()));
	}

	@Override
	public List<StudentExamStatusDto> toStudentExamStatusDtoList(List<StudentExamStatus> studentExamStatusList) {
		List<StudentExamStatusDto> studentExamStatusDtoList = new ArrayList<StudentExamStatusDto>();
		if (studentExamStatusList == null) {
			return studentExamStatusDtoList;
		}
		for (int i = 0 ; i <studentExamStatusList.size() ; i++) {
			StudentExamStatusDto studentExamStatusDto = toStudentExamStatusDto(studentExamStatusList.get(i));
			studentExamStatusDtoList.add(studentExamStatusDto);
		}
		return studentExamStatusDtoList;
	}
	
	
	
}
