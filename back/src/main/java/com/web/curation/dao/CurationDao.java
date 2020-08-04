package com.web.curation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.curation.model.Curation;
import com.web.curation.model.Search;

public interface CurationDao extends JpaRepository<Curation, String> {

	@Query(value = "select * from curation where username = :username order by curationno DESC", nativeQuery = true)
	List<Curation> getCurationByUsername(String username);


}
