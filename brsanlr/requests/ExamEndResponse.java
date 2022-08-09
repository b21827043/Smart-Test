package com.brsanlr.brsanlr.requests;

import java.util.List;

import com.brsanlr.brsanlr.dto.StudentAnswersDto;

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
public class ExamEndResponse {
	
	List<StudentAnswersDto> studentAnswerDtoList;
	
}
