package com.web.curation.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.web.curation.model.user.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserDTO {
   private String nickname;
   private String password;
   private String email;
   private LocalDateTime create_Date;
   private LocalDate birth;
   private String gender;
    private String selfintroduce;

   
   public UserDTO() {}

}
