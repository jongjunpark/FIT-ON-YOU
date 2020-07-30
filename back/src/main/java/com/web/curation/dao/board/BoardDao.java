package com.web.curation.dao.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.curation.model.Board;
import com.web.curation.model.follow.Follow;

public interface BoardDao extends JpaRepository<Board, String>{
	
	List<Board> findBoardByArticleUserOrderByArticleNoDesc(String articleUser);
	
}
