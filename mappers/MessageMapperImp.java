package com.brsanlr.brsanlr.mappers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.brsanlr.brsanlr.dto.MessageDto;
import com.brsanlr.brsanlr.entities.Message;
import com.brsanlr.brsanlr.requests.AddMessageRequest;
import com.brsanlr.brsanlr.requests.UpdateMessageRequest;

@Component
public class MessageMapperImp implements MessageMapper{

	@Override
	public Message toMessage(AddMessageRequest addMessageRequest) {
		LocalDateTime now = LocalDateTime.now();
		return new Message(addMessageRequest.getSenderId(),addMessageRequest.getMessageText(),now);
	}

	@Override
	public MessageDto toMessageDto(Message message) {
		return new MessageDto(message.getMessageId(),message.getSenderId(),message.getMessageText(),message.getMessageDate());
	}

	@Override
	public List<MessageDto> toMessageDtoList(List<Message> messageList) {
		if (messageList == null) {
			return new ArrayList<MessageDto>();
		}
		List<MessageDto> messageDtoList = new ArrayList<MessageDto>();
		for (int i = 0 ; i < messageList.size() ; i++) {
			MessageDto messageDto = toMessageDto(messageList.get(i));
			messageDtoList.add(messageDto);
		}
		return messageDtoList;
	}


	
	@Override
	public Message toMessageUpdate(Message message,UpdateMessageRequest updateMessageRequest) {
		return new Message(message.getMessageId(),message.getSenderId(),updateMessageRequest.getMessageText(),message.getMessageDate());
	}

}
