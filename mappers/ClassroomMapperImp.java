package com.brsanlr.brsanlr.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brsanlr.brsanlr.dto.ClassroomDto;
import com.brsanlr.brsanlr.dto.ClassroomStudentViewDto;
import com.brsanlr.brsanlr.entities.Classroom;
import com.brsanlr.brsanlr.requests.AddClassroomRequest;
import com.brsanlr.brsanlr.requests.UpdateClassroomRequest;

@Component
public class ClassroomMapperImp implements ClassroomMapper {
	
	@Autowired
	StudentMapper studentMapper;
	@Autowired
	ExamMapper examMapper;
	@Autowired
	PostMapper postMapper;

	
	@Override
	public Classroom requestToClassroom(AddClassroomRequest addClassroomRequest) {
		return new Classroom(addClassroomRequest.getClassroomName(),addClassroomRequest.getClassroomIntroText());
	}

	@Override
	public ClassroomDto toClassroomDto(Classroom classroom) {
		return new ClassroomDto(classroom.getClassroomId(),classroom.getClassroomName(),classroom.getClassroomIntroText(),studentMapper.toStudentDtoList(classroom.getStudents()),examMapper.toExamDtoList(classroom.getExams()),postMapper.toPostDtoList(classroom.getPosts()));
	}
	
	@Override
	public ClassroomStudentViewDto toClassroomStudentViewDto(Classroom classroom) {
		return new ClassroomStudentViewDto(classroom.getClassroomId(),classroom.getClassroomName(),classroom.getClassroomIntroText(),studentMapper.studentListToUserDtoList(classroom.getStudents()),examMapper.toExamBeforeDtoList(classroom.getExams()),postMapper.toPostDtoList(classroom.getPosts()));
	}

	@Override
	public List<ClassroomDto> toClassroomDtoList(List<Classroom> classroomList) {
		List<ClassroomDto> classroomDtoList = new ArrayList<ClassroomDto>();
		if(classroomList == null) {
			return classroomDtoList;
		}
		for (int i = 0 ; i<classroomList.size() ; i++) {
			ClassroomDto classroomDto = toClassroomDto(classroomList.get(i));
			classroomDtoList.add(classroomDto);
		}
		return classroomDtoList;
	}

	@Override
	public List<ClassroomStudentViewDto> toClassroomStudentViewDtoList(List<Classroom> classroomList) {
		List<ClassroomStudentViewDto> classroomDtoList = new ArrayList<ClassroomStudentViewDto>();
		if(classroomList == null) {
			return classroomDtoList;
		}
		for (int i = 0 ; i<classroomList.size() ; i++) {
			ClassroomStudentViewDto classroomDto = toClassroomStudentViewDto(classroomList.get(i));
			classroomDtoList.add(classroomDto);
		}
		return classroomDtoList;
	}
	
	@Override
	public Classroom toClassroomUpdate(Classroom classroom, UpdateClassroomRequest updateClassroomRequest) {
		return new Classroom(classroom.getClassroomId(),updateClassroomRequest.getClassroomName(),updateClassroomRequest.getClassroomIntroText(),classroom.getStudents(),classroom.getExams(),classroom.getPosts(),classroom.getTeacher());
	}


	
}
