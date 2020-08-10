package com.web.curation.model;

import lombok.Data;

@Data
public class Chat {
	int chatno;
	String firstuser;
	String seconduser;
	String roomname;
	String lasttime;
	
	public Chat() {
		
	}

	public Chat(ChatDTO chatDto) {
		this.chatno = chatDto.chatno;
		this.firstuser = chatDto.firstuser;
		this.seconduser = chatDto.seconduser;
		this.roomname = chatDto.roomname;
		this.lasttime = chatDto.lasttime;
	}
}
