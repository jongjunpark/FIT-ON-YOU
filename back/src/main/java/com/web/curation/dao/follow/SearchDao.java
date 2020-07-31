package com.web.curation.dao.follow;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.curation.model.follow.Search;

public interface SearchDao extends JpaRepository<Search, String> {

	@Query(value = "select * from articletag where tagname = :name1", nativeQuery = true)
	List<Search> getSearchByTagnameOne(String name1);

	@Query(value = "select * from articletag where tagname in (:name1,:name2) group by articleno having count(articleno) >= 2", nativeQuery = true)
	List<Search> getSearchByTagnameTwo(@Param("name1")String name1,@Param("name2") String name2);
//
	@Query(value = "select * from articletag where tagname in (?1,?2,?3) group by articleno having count(articleno) >= 3", nativeQuery = true)
	List<Search> getSearchByTagnameThree(String name1, String name2, String name3);
//
	@Query(value = "select * from articletag where tagname in (?1,?2,?3,?4) group by articleno having count(articleno) >= 4", nativeQuery = true)
	List<Search> getSearchByTagnameFour(String name1, String name2, String name3, String name4);
//
	@Query(value = "select * from articletag where tagname in (?1,?2,?3,?4,?5) group by articleno having count(articleno) >= 5", nativeQuery = true)
	List<Search> getSearchByTagnameFive(String name1, String name2, String name3, String name4, String name5);
}
