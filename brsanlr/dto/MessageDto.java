package com.brsanlr.brsanlr.dto;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
	
	private long messageId;
	
	private long senderId;
	
	private String messageText;
	
	private LocalDateTime messageDate;
	
	
	
	
	
}
