package com.brsanlr.brsanlr.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.brsanlr.brsanlr.dto.MessageDto;
import com.brsanlr.brsanlr.entities.Message;
import com.brsanlr.brsanlr.requests.AddMessageRequest;
import com.brsanlr.brsanlr.requests.UpdateMessageRequest;

@Mapper(componentModel = "spring")
public interface MessageMapper {
	
	Message toMessage(AddMessageRequest addMessageRequest);
	MessageDto toMessageDto(Message message);
	List<MessageDto> toMessageDtoList(List<Message> messageList);

	Message toMessageUpdate(Message message,UpdateMessageRequest updateMessageRequest);

	
}
