package com.web.curation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.curation.model.BoardDTO;

public interface BoardTwoDao extends JpaRepository<BoardDTO,String>{

	@Query(value="select distinct b.*," + 
			" (select count(*) from likes l where b.articleNo=l.articleNo and l.nickname=:nickname) as likechk," + 
			" (select count(*) from bookmark m where b.articleNo=m.bookedArticle and m.bookUser=:nickname) as markchk" + 
			" from board b, likes l where b.articleUser=:articleUser", nativeQuery=true)
	List<BoardDTO> getMainFeedList(String articleUser, String nickname);
}
