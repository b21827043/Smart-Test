package com.brsanlr.brsanlr.mappers;

import java.util.List;

import com.brsanlr.brsanlr.dto.ChoiceDto;
import com.brsanlr.brsanlr.entities.Choice;
import com.brsanlr.brsanlr.requests.AddChoiceRequest;
import com.brsanlr.brsanlr.requests.UpdateChoiceRequest;

public interface ChoiceMapper {
	
	Choice toChoice(AddChoiceRequest addChoiceRequest);
	ChoiceDto toChoiceDto(Choice choice);
	List<ChoiceDto> toChoiceDtoList(List<Choice> choiceList);
	
	Choice toChoiceUpdate(Choice choice,UpdateChoiceRequest updateChoiceRequest);
}
