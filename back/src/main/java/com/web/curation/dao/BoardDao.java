package com.web.curation.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.web.curation.model.Board;
import com.web.curation.model.Follow;

public interface BoardDao extends JpaRepository<Board, String> {

	List<Board> findBoardByArticleUserOrderByArticleNoDesc(String articleUser);

	Board findBoardByArticleNo(int articleno);
	
	@Query(value="select articleNo from board order by articleNo desc",nativeQuery = true)
	List<Integer> getCountBoard();
	
	@Modifying
	@Transactional
	@Query(value="update board set favoriteCnt=favoriteCnt+1 where articleNo=:articleNo",nativeQuery=true)
	int increFvCnt(int articleNo);
	@Modifying
	@Transactional
	@Query(value="update board set favoriteCnt=favoriteCnt-1 where articleNo=:articleNo",nativeQuery=true)
	int decreFvCnt(int articleNo);
	
}
