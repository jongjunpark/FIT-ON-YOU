package com.web.curation.controller.account;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.validation.Valid;
import lombok.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.user.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.UserDTO;
import com.web.curation.model.user.SignupRequest;
import com.web.curation.model.user.User;
import com.web.curation.service.user.JwtService;
import com.web.curation.service.user.UserLoginService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class AccountController {

	@Autowired
	UserDao userDao;
	
	@Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserLoginService userService;

	@GetMapping("/account/login")
	@ApiOperation(value = "로그인")
	public Object login(@RequestParam(required = true) final String email,
            @RequestParam(required = true) final String password) {

//      Optional<User> userOpt = userDao.findUserByEmailAndPassword(email, password);
    	
    	
		ResponseEntity response = null;
//
//      if (userOpt.isPresent()) {
//          final BasicResponse result = new BasicResponse();
//          result.status = true;
//          result.data = "success";
//          response = new ResponseEntity<>(result, HttpStatus.OK);
//      } else {
//          response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//      }
  	
	
//	  	System.out.println(email+"123");
//	  	System.out.println(password+"123123");
	
	  	Map<String,Object> resultMap = new HashMap<>();
	  	HttpStatus status=HttpStatus.ACCEPTED;
	  	try {
		    	UserDTO loginUser = userService.login(email, password);
		    	System.out.println(loginUser.getEmail());
		    	System.out.println(loginUser.getPassword());
		    	
		    	//아이디가 존재하지 않으면 result -1
		    	if(loginUser.getEmail()==null || !loginUser.getEmail().equals(email)) {
		    		resultMap.put("result",-1);
		    	}
		    	
		    	//비밀번호가 틀리다면 res 2
		    	else if(!loginUser.getPassword().equals(password)) {
		    		resultMap.put("result", 2);
		    	}
		    	//로그인 성공 했다면 토큰 생성 후 result 1
		    	//토큰 정보는 request의 헤더로 보내고 나머지는 Map에 답는다.
		    	else  {
		    		String token = jwtService.create(loginUser);
		    		System.out.println(token);
		    		resultMap.put("auth_token",token);
		    		resultMap.put("result", 1);
		    		resultMap.put("data", loginUser);
		    	}
		    	
		    	//res.setHeader("jwt-auth-token",token);
	  	} catch (NotFoundException e) {
	  		resultMap.put("message", e.getMessage());
	  		status=HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
	    return new ResponseEntity<Map<String,Object>>(resultMap,status);
	}

	@PostMapping("/account/signup")
	@ApiOperation(value = "가입하기")

	public Object signup(@Valid @RequestBody SignupRequest request) {
		// 이메일, 닉네임 중복처리 필수
		// 회원가입단을 생성해 보세요.
		System.out.println("1");
		final BasicResponse result = new BasicResponse();
		// 이메일, 닉네임 중복처리 필수
		String nickname = request.getNickname();
		String email = request.getEmail();
//		UserDTO checkUid = new UserDTO(userDao.findUserByNickname(nickname).get());
//		UserDTO checkEmail = new UserDTO(userDao.findUserByEmail(email).get());
//		System.out.println(checkEmail.getEmail());
//		if (checkEmail.getEmail() != null) {
//			System.out.println("2");
//			result.status = true;
//			result.data = "중복된 이메일이 존재 합니다";
//			return new ResponseEntity<>(result, HttpStatus.OK);
//		}
//		if (checkUid != null) {
//			System.out.println("3");
//			result.status = true;
//			result.data = "중복된 닉네임이 존재 합니다";
//			return new ResponseEntity<>(result, HttpStatus.OK);
//		}
		// 저장
		System.out.println("4");
		User user = new User();
		LocalDateTime currentDateTime = LocalDateTime.now();
		StringTokenizer st = new StringTokenizer(request.getBirth());
		LocalDateTime ldt = currentDateTime.withYear(Integer.parseInt(st.nextToken()))
				.withMonth(Integer.parseInt(st.nextToken())).withDayOfMonth(Integer.parseInt(st.nextToken()));

		System.out.println(request.getNickname());
		user.setNickname(request.getNickname());
		user.setEmail(request.getEmail());
		user.setBirth(ldt);
		user.setGender(request.getGender());
		user.setPassword(request.getPassword());
		user.setSelfintroduce(null);
		System.out.println("toString------------------->"+user.toString());
		userDao.save(user);
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}