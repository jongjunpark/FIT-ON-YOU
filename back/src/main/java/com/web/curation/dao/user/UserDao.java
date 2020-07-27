
package com.web.curation.dao.user;

import java.util.Optional;

import com.web.curation.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * JpaRepository에는 기본적인 CRUD(findAll, findOne, save, deleteAll)메소드가 
 * 정의되어 있으며 이를 상속한 인터페이스를 만듦으로써 구현 클래스 없이 레포지터리 구성이 가능하다.
 * 제네릭으로 도메인 클래스와 키타입만 적용하면 된다
 * */
public interface UserDao extends JpaRepository<User, String> {
	
	Optional<User> getUserByEmail(String email);
    Optional<User> findUserByEmailAndNickname(String email, String nickname);
    Optional<User> findUserByNickname(String nickname);
    User findUserByEmail(String email);
 
}
