
package com.web.curation.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.user.UserDao;
import com.web.curation.model.UserDTO;
import com.web.curation.model.user.User;

import javassist.NotFoundException;

/**
 * 
 * UserService
 *
 */

@Service
public class UserLoginService {
	
	@Autowired
	private UserDao userDao;
	
	public UserDTO login(String email, String password) throws NotFoundException {
		Optional<User> user = userDao.getUserByEmail(email);
		if(user.isPresent()) {
			
			return new UserDTO(user.get());
		}else {
			return new UserDTO();
		}
//		if(email.equals("abc@def.net") && password.equals("1234")) {
//			return new User(email,password);
//
//		}else {
//			throw new RuntimeException("그런 사람 없어요");
//		}
	}
}
