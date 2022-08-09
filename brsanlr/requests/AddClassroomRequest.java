package com.brsanlr.brsanlr.requests;



import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddClassroomRequest {
	
	private long teacherId;
	
	private String classroomName;
	private String classroomIntroText;
	
	private List<Long> studentIdList;
	
	
}
