package com.brsanlr.brsanlr.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.brsanlr.brsanlr.dto.QuestionDto;
import com.brsanlr.brsanlr.entities.Question;
import com.brsanlr.brsanlr.requests.AddQuestionRequest;
import com.brsanlr.brsanlr.requests.UpdateQuestionRequest;

@Mapper(componentModel = "spring",uses=ChoiceMapper.class)
public interface QuestionMapper {
	
	Question toQuestion(AddQuestionRequest addQuestionRequest);
	QuestionDto toQuestionDto(Question question);
	List<QuestionDto> toQuestionDtoList(List<Question> questionList);
	
	Question toQuestionUpdate(Question question,UpdateQuestionRequest updateQuestionRequest);
}
