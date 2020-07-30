package com.web.curation.dao.alarm;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.curation.model.Alarm;

public interface AlarmDao extends JpaRepository<Alarm, String>{
	List<Alarm> findByRecevierAndIsReadOrderByAlarmNoDesc(String recevier,int isRead);
	
//	@Modifying
//	@Transactional
//	@Query(value="update alarm set isRead=1 where alarmNo in :nos")
//	void readAlarm(@Param("recevier") String recevier, @Param("nos") List<Long> nos);
	
	@Modifying
	@Transactional
	void deleteByRecevierAndFollower(String recevier, String follower);
	
}
