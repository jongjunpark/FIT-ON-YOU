package com.web.curation.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
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
   
    private LocalDateTime birth;
    private String gender;
    private String selfintroduce;
    


}
