package com.brsanlr.brsanlr.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.brsanlr.brsanlr.dto.TeacherDto;
import com.brsanlr.brsanlr.entities.Teacher;
import com.brsanlr.brsanlr.requests.AddUserRequest;
@Mapper(componentModel = "spring",uses= {ExamMapper.class,ClassroomMapper.class})
public interface TeacherMapper {
	
	Teacher toTeacher(AddUserRequest addUserRequest);
	TeacherDto toTeacherDto(Teacher teacher);
	
	List<TeacherDto> toTeacherDtoList(List<Teacher> teacherList);
	
}
