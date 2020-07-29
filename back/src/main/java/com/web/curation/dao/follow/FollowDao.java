package com.web.curation.dao.follow;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.curation.model.follow.Follow;

public interface FollowDao extends JpaRepository<Follow, String>{
	
	List<Follow> getFollowByFollowinguser(String followingUser);
	
	@Modifying
	@Transactional
	int deleteAllByFollowno(@Param("followNo") Integer followNo);
	
}
