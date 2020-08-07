package com.web.curation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.curation.model.Board;
import com.web.curation.model.Follow;

public interface BoardDao extends JpaRepository<Board, String> {

	List<Board> findBoardByArticleUserOrderByArticleNoDesc(String articleUser);

	Board findBoardByArticleNo(int articleno);
	
	@Query(value="select articleNo from board order by articleNo desc",nativeQuery = true)
	List<Integer>getCountBoard();
	
}
