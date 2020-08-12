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

	public int getChatno() {
		return chatno;
	}

	public void setChatno(int chatno) {
		this.chatno = chatno;
	}

	public String getFirstuser() {
		return firstuser;
	}

	public void setFirstuser(String firstuser) {
		this.firstuser = firstuser;
	}

	public String getSeconduser() {
		return seconduser;
	}

	public void setSeconduser(String seconduser) {
		this.seconduser = seconduser;
	}

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public String getLasttime() {
		return lasttime;
	}

	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}

	public Chat(ChatDTO chatDto) {
		this.chatno = chatDto.chatno;
		this.firstuser = chatDto.firstuser;
		this.seconduser = chatDto.seconduser;
		this.roomname = chatDto.roomname;
		this.lasttime = chatDto.lasttime;
	}
}
