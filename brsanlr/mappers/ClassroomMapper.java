package com.brsanlr.brsanlr.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.brsanlr.brsanlr.dto.ClassroomDto;
import com.brsanlr.brsanlr.dto.ClassroomStudentViewDto;
import com.brsanlr.brsanlr.entities.Classroom;
import com.brsanlr.brsanlr.requests.AddClassroomRequest;
import com.brsanlr.brsanlr.requests.UpdateClassroomRequest;

@Mapper(componentModel = "spring",uses= {StudentMapper.class ,ExamMapper.class,PostMapper.class})
public interface ClassroomMapper {
	
	Classroom requestToClassroom(AddClassroomRequest addClassroomRequest);
	ClassroomDto toClassroomDto(Classroom classroom);
	ClassroomStudentViewDto toClassroomStudentViewDto(Classroom classroom);
	List<ClassroomDto> toClassroomDtoList(List<Classroom> classroomList);
	List<ClassroomStudentViewDto> toClassroomStudentViewDtoList(List<Classroom> classroomList);
	Classroom toClassroomUpdate(Classroom classroom,UpdateClassroomRequest updateClassroomRequest);
	
}
