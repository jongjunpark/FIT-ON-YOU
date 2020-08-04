package com.web.curation.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.web.curation.model.Comment;

public interface CommentDao extends JpaRepository<Comment, String>{
	
	List<Comment> findAllByArticleNoOrderByCommentNoDesc(int articleNo);
	@Modifying
	@Transactional
	void deleteByCommentNo(int commentNo);
}
