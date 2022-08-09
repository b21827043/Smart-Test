package com.brsanlr.brsanlr.mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses= {StudentExamStatusMapper.class})
public abstract class StudentAndClassroomMapper implements ClassroomMapper,StudentMapper{
	
	
	
}
