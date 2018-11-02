package com.example.websocket;

import lombok.Data;

@Data
public class HelloMessage {
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
