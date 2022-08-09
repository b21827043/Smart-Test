package com.brsanlr.brsanlr.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brsanlr.brsanlr.dto.TeacherDto;
import com.brsanlr.brsanlr.entities.Teacher;
import com.brsanlr.brsanlr.requests.AddUserRequest;

@Component
public class TeacherMapperImp implements TeacherMapper{
	
	@Autowired
	ExamMapper examMapper;
	@Autowired
	ClassroomMapper classroomMapper;
	
	
	@Override
	public Teacher toTeacher(AddUserRequest addUserRequest) {
		return new Teacher(addUserRequest.getEmail(),addUserRequest.getFullName(),addUserRequest.getPassword());
	}

	@Override
	public TeacherDto toTeacherDto(Teacher teacher) {
		return new TeacherDto(teacher.getId(),teacher.getEmail(),teacher.getFullName(),teacher.getImgUrl(),examMapper.toExamDtoList(teacher.getExams()),classroomMapper.toClassroomDtoList(teacher.getClassrooms()));
	}

	@Override
	public List<TeacherDto> toTeacherDtoList(List<Teacher> teacherList) {
		List<TeacherDto> teacherDtoList = new ArrayList<TeacherDto>();
		if ( teacherList == null) {
			return teacherDtoList;
		}
		for (int i = 0 ; i < teacherList.size() ; i++) {
			TeacherDto teacherDto = toTeacherDto(teacherList.get(i));
			teacherDtoList.add(teacherDto);
		}
		return teacherDtoList;
	}
	
}
