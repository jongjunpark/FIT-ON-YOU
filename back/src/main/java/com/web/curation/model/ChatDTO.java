package com.web.curation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "chatlist")
@Data
public class ChatDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int chatno;

	String firstuser;
	String seconduser;
	String roomname;
	String lasttime;

}
