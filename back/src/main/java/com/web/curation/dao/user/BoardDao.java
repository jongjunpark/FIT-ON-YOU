package com.web.curation.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.Board;

public interface BoardDao extends JpaRepository<Board, String>{

}
