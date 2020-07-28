package com.web.curation.model.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id//pk를 지정
//    @GeneratedValue(strategy = GenerationType.IDENTITY)//자동 증분 칼럼
    private String nickname;

    private String password;
    //어노테이션이 없더라도 테이블 칼럼명과 자동 매핑
    private String email;

    @Column(insertable = false, updatable = false)
    private LocalDateTime create_Date;
   
    private LocalDate birth;
    private String gender;
    private String selfintroduce;
    private String profile_img;
    
}
