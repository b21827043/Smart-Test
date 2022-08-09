package com.brsanlr.brsanlr.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brsanlr.brsanlr.dto.StudentTeacherViewDto;
import com.brsanlr.brsanlr.dto.UserDto;
import com.brsanlr.brsanlr.entities.Student;
import com.brsanlr.brsanlr.requests.AddUserRequest;

@Component
public class StudentMapperImp implements StudentMapper{
	
	@Autowired
	StudentExamStatusMapper studentExamStatusMapper;
	
	
	@Override
	public Student toStudent(AddUserRequest addUserRequest) {
		return new Student(addUserRequest.getEmail(),addUserRequest.getFullName(),addUserRequest.getPassword());
	}

	@Override
	public StudentTeacherViewDto toStudentDto(Student student) {
		return new StudentTeacherViewDto(student.getId(),student.getEmail(),student.getFullName(),student.getImgUrl(),studentExamStatusMapper.toStudentExamStatusDtoList(student.getExamStatusList()));
	}

	@Override
	public List<StudentTeacherViewDto> toStudentDtoList(List<Student> studentList) {
		List<StudentTeacherViewDto> studentDtoList = new ArrayList<StudentTeacherViewDto>();
		if (studentList == null) {
			return studentDtoList;
		}
		for (int i = 0 ; i < studentList.size() ; i++) {
			StudentTeacherViewDto studentDto = toStudentDto(studentList.get(i));
			studentDtoList.add(studentDto);
		}
		return studentDtoList;
	}
	
	@Override
	public UserDto studentToUserDto(Student student) {
		return new UserDto(student.getId(),student.getEmail(),student.getFullName(),student.getImgUrl());
	}
	
	@Override
	public List<UserDto> studentListToUserDtoList(List<Student> userList) {
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		if (userList == null) {
			return userDtoList;
		}
		for (int i = 0 ; i < userList.size() ; i++) {
			UserDto userDto = studentToUserDto(userList.get(i));
			userDtoList.add(userDto);
		}
		return userDtoList;
	}

	
}
