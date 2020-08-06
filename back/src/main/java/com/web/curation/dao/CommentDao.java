package com.web.curation.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.web.curation.model.Comment;

public interface CommentDao extends JpaRepository<Comment, String>{
	
	List<Comment> findAllByArticleNoOrderByCommentNoAsc(int articleNo);
	@Modifying
	@Transactional
	void deleteByCommentNo(int commentNo);
	
	@Query(value="select * from comment c, user u where articleNo=:articleNo and c.writer=u.nickname", nativeQuery=true)
	List<Comment> allCommentListByArtcieNo(int articleNo);
}
