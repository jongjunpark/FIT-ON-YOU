package com.web.curation.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.curation.model.Follow;

public interface FollowDao extends JpaRepository<Follow, String>{
	
	List<Follow> getFollowByFollowinguser(String followingUser);
	
	@Modifying
	@Transactional
	int deleteAllByFollowno(@Param("followNo") Integer followNo);
	
	List<Follow> getFollowByFolloweduser(String userName);
	
	@Query(value="SELECT a.followno, a.followeduser, a.followinguser FROM (select * from follow where followeduser =:userName)as a inner join (select * from follow where followinguser =:userName ) as b on a.followeduser = b.followinguser and a.followinguser = b.followeduser",nativeQuery = true)
	List<Follow> getFollowByFolloweinguserAndFollweduser(String userName);
	
	Follow findByFollowno(Integer followNo);
	
	Long countByFolloweduser(String followeduser);
	Long countByFollowinguser(String followinguser);
	
	long countByFolloweduserAndFollowinguser(String followeduser, String followinguser);
	
	Optional<Follow> getFollowByfolloweduserAndFollowinguser(String followeduser, String followinguser);
	
}
