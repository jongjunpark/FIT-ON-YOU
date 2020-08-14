package com.web.curation.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.web.curation.model.Recell;


public interface RecellDao extends JpaRepository<Recell, String> {
	@Query(value="select recellNo from recellboard order by recellNo desc",nativeQuery = true)
	List<Integer> getCountRecell();	
	
	
}