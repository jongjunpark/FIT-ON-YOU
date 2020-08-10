package com.web.curation.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.curation.model.Chat;
import com.web.curation.model.ChatDTO;

public interface ChatDao extends JpaRepository<ChatDTO, String> {

	@Query(value = "select * from chatlist where roomname in (select roomname from chatlist where (firstuser =:firstuser and seconduser=:seconduser)or(firstuser =:seconduser and seconduser=:firstuser)) group by roomname order by lasttime", nativeQuery = true)
	Optional<ChatDTO> getRoomnameByUserInfos(String firstuser, String seconduser);

	@Query(value = "insert into chatlist (firstuser, seconduser, roomname) values(:firstuser, :seconduser,:roomname)", nativeQuery = true)
	void InsertRoomname(String firstuser, String seconduser, String roomname);

	@Query(value = "update chatlist set lasttime = null where roomname = :roomname", nativeQuery = true)
	void updateLasttime(String roomname);

	@Query(value = "select * from chatlist where (firstuser = :username)or(seconduser = :username)", nativeQuery = true)
	List<ChatDTO> getRoomListByUsername(String username);
}
