package com.web.curation.request;

import com.web.curation.model.ChatDTO;

import lombok.Data;


public class ChatReturnDTO {
   int chatno;
   String firstuser;
   String seconduser;
   String roomname;
   String lasttime;
   String img;
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
   public String getImg() {
      return img;
   }
   public void setImg(String img) {
      this.img = img;
   }
   
}