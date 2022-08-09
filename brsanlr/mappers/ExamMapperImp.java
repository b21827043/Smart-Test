package com.brsanlr.brsanlr.mappers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brsanlr.brsanlr.dto.ExamBeforeDto;
import com.brsanlr.brsanlr.dto.ExamDto;
import com.brsanlr.brsanlr.entities.Exam;
import com.brsanlr.brsanlr.requests.AddExamRequest;
import com.brsanlr.brsanlr.requests.UpdateExamRequest;

@Component
public class ExamMapperImp implements ExamMapper{
	
	@Autowired
	QuestionMapper questionMapper;
	
	@Override
	public Exam toExam(AddExamRequest addExamRequest) {
		LocalDateTime now = LocalDateTime.now();
		now = now.plusDays(addExamRequest.getExpireDay());
		return new Exam(addExamRequest.getExamName(),addExamRequest.getExamIntroText(),addExamRequest.getExamDuration(),now,false);
	}
	
	@Override
	public ExamDto toExamDto(Exam exam) {
		return new ExamDto(exam.getExamId(),exam.getExamName(),exam.getExamIntroText(),exam.getExamDuration(),exam.getExpiredDate(),exam.isExpired(),questionMapper.toQuestionDtoList(exam.getQuestions()));
	}
	
	@Override
	public ExamBeforeDto toExamBeforeDto(Exam exam) {
		return new ExamBeforeDto(exam.getExamId(),exam.getExamName(),exam.getExamIntroText(),exam.getExamDuration(),exam.getExpiredDate(),exam.isExpired());
	}
	

	@Override
	public List<ExamDto> toExamDtoList(List<Exam> examList) {
		List<ExamDto> examDtoList = new ArrayList<ExamDto>();
		if (examList == null) {
			return examDtoList;
		}
		for (int i = 0 ; i < examList.size() ; i++) {
			ExamDto examDto = toExamDto(examList.get(i));
			examDtoList.add(examDto);
		}
		return examDtoList;
	}
	
	@Override
	public List<ExamBeforeDto> toExamBeforeDtoList(List<Exam> examList) {
		List<ExamBeforeDto> examDtoList = new ArrayList<ExamBeforeDto>();
		if (examList == null) {
			return examDtoList;
		}
		for (int i = 0 ; i < examList.size() ; i++) {
			ExamBeforeDto examBeforeDto = toExamBeforeDto(examList.get(i));
			examDtoList.add(examBeforeDto);
		}
		return examDtoList;
	}
	

	@Override
	public Exam toExamUpdate(Exam exam, UpdateExamRequest updateExamRequest) {
		LocalDateTime time = exam.getExpiredDate();
		time.plusDays(updateExamRequest.getExpireDay());
		return new Exam(exam.getExamId(),updateExamRequest.getExamName(),updateExamRequest.getExamIntroText(),updateExamRequest.getExamDuration(),time,exam.isExpired(),exam.getQuestions(),exam.getClassrooms(),exam.getTeacher());
	}





}
