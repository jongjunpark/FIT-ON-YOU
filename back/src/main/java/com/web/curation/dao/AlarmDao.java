package com.web.curation.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.curation.model.Alarm;

public interface AlarmDao extends JpaRepository<Alarm, String>{
	
	
	@Query(value="select a.alramNo, a.type, a.recevier, a.follower, ifnull(a.articleNo, -1) as articleNo , a.isread, a.createAt, u.* from alarm a, user u " + 
			"where recevier=:recevier and a.follower=u.nickname order by a.alramNo desc;",nativeQuery=true)
	List<Alarm> findByRecevierAndIsReadOrderByAlramNoDesc(String recevier);
	
//	@Modifying
//	@Transactional
//	@Query(value="update alarm set isRead=1 where alarmNo in :nos")
//	void readAlarm(@Param("recevier") String recevier, @Param("nos") List<Long> nos);
	// 팔로우 등록시
	@Modifying
	@Transactional
	@Query(value="insert into alarm (type, recevier, follower, isRead) values"
			+ " (:type, :recevier, :follower, :isRead)", nativeQuery=true)
	void addFollowAlarm(String type, String recevier, String follower, int isRead);
	
	// 팔로우 해제시
	
	@Modifying
	@Transactional
	@Query(value="delete from alarm where type=:type and recevier=:recevier and follower=:follower",nativeQuery=true)
	int deleteByTypeAndRecevierAndFollower(String type, String recevier, String follower);
	
	//좋아요 취소시
	@Modifying
	@Transactional
	@Query(value="delete from alarm where type=:type and articleno=:articleno and follower=:follower",nativeQuery=true)
	int deleteLikesAlarm(String type, int articleno, String follower);
	
	//알람 읽음 처리
	@Modifying
	@Transactional
	@Query(value="update alarm set isRead=1 where alramNo in :alramNo",nativeQuery=true)
	int isRead(List<Integer> alramNo);
	
	//알림 모두 삭제
	@Modifying
	@Transactional
	@Query(value="delete from alarm where recevier=:recevier",nativeQuery=true)
	int allDel(String recevier);
	
	Alarm findByTypeAndArticleNoAndFollower(String type, int articleNo, String Follower);
	
	@Query(value="select count(*) from alarm where recevier=:recevier and isRead=0 ",nativeQuery=true)
	int checkAlarm(String recevier);
	

}
