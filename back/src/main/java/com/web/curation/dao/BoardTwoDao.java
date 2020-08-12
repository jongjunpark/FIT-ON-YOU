package com.web.curation.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.curation.model.BoardDTO;

@Repository
public interface BoardTwoDao extends JpaRepository<BoardDTO,String>{

	@Query(value="select b.*," + 
			" (select count(*) from likes l where b.articleNo=l.articleNo and l.nickname=:nickname) as likechk," + 
			" (select count(*) from bookmark m where b.articleNo=m.bookedArticle and m.bookUser=:nickname) as markchk" + 
			" from board b where b.articleUser in (select followingUser from follow f where f.followedUser=:nickname) order by articleNo desc", 
			countQuery="select count(*), "+ 
					" (select count(*) from likes l where b.articleNo=l.articleNo and l.nickname=:nickname) as likechk," + 
					" (select count(*) from bookmark m where b.articleNo=m.bookedArticle and m.bookUser=:nickname) as markchk" + 
					" from board b where b.articleUser in (select followingUser from follow f where f.followedUser=:nickname) order by articleNo desc",
			nativeQuery=true)
	Page<BoardDTO> getMainFeedList(Pageable pageable, @Param("nickname") String nickname);
}
