package com.web.curation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.curation.model.Search;

public interface SearchDao extends JpaRepository<Search, String> {

	@Query(value = "select * from articletag where tagname in (:tagname) group by articleno having count(articleno) >= :size", nativeQuery = true)
	List<Search> getSearchByTagname(List<String> tagname, int size);

	@Query(value = "select * from articletag where tagname in (:input) group by articleno", nativeQuery = true)
	List<Search> getContentsNoByTagname(@Param("input") List<String> input);

}
