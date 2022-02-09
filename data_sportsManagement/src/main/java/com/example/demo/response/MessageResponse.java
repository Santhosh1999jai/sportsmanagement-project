package com.example.demo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageResponse {
	private int status;
	private String message;
	private Object object;
	
	public MessageResponse(int status, String message, Object object) {
		super();
		this.status = status;
		this.message = message;
		this.object = object;
	}
	
	public MessageResponse(int status,String message) {
		super();
		this.status = status;
		this.message = message;
	}

}
