package com.web.curation.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.curation.model.Tag;

public interface TagDao extends JpaRepository<Tag, String>{
	
	String findBytagName(String tagName);

}
