package com.web.curation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.ToString;

@Entity
@Table(name = "chatlist")
@ToString
public class ChatDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int chatno;

	String firstuser;
	String seconduser;
	String roomname;
	String lasttime;
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
	

}
