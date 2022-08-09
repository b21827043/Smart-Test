package com.brsanlr.brsanlr.requests;

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
public class AddPostRequest {
	
	private long senderId;
	
	private String postTitle;
	private String postText;
	
	private long classroomId;
	
	
}
