
package com.web.curation.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.web.curation.model.User;

/*
 * JpaRepository에는 기본적인 CRUD(findAll, findOne, save, deleteAll)메소드가 
 * 정의되어 있으며 이를 상속한 인터페이스를 만듦으로써 구현 클래스 없이 레포지터리 구성이 가능하다.
 * 제네릭으로 도메인 클래스와 키타입만 적용하면 된다
 * */
public interface UserDao extends JpaRepository<User, String> {
	
	Optional<User> getUserByEmail(String email);
    Optional<User> findUserByEmailAndBirth(String email, LocalDate birth);
    Optional<User> findUserByNickname(String nickname);
    Optional<User> findUserByEmail(String email);
    
    @Modifying
    @Transactional
    @Query(value="UPDATE user set password=:password where email=:email", nativeQuery=true)
    void updatePassword(@Param("password")String password,@Param("email") String email);
    
    @Modifying
    @Transactional
    @Query(value="UPDATE user set nickname=:cur where nickname=:prev", nativeQuery=true)
    void updateNickname(@Param("prev")String prev,@Param("cur") String cur);
    
    @Modifying
    @Transactional
    @Query(value="select * from user where nickname like %:word%", nativeQuery=true)
    List<User> findUsersByWord(String word);
}
