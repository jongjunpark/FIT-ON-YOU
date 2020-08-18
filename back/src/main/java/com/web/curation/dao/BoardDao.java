package com.web.curation.dao;

import java.util.List;
import java.util.Optional;

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
	
	@Query(value="select * from board order by articleNo desc limit 60",nativeQuery = true)
	List<Board> getList();
	
	@Query(value = "select * from board where articleNo = :articleno",nativeQuery = true)
	Optional<Board> selectArticleno(int articleno);
	
	@Query(value="delete from board where articleNo=:articleNo",nativeQuery = true)
	void delBoardArticle(int articleNo);
	
	
}
