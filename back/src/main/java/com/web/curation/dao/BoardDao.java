package com.web.curation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.Board;

public interface BoardDao extends JpaRepository<Board, String>{
	
	List<Board> findBoardByArticleUserOrderByArticleNoDesc(String articleUser);
	
}
