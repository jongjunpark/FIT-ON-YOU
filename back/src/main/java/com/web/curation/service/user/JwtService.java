package com.web.curation.service.user;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.web.curation.model.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtService {
	@Value("${jwt.salt}")
	private String salt;
	
	@Value("${jwt.expmin}")
	private Long expireMin;
	
	/**
	 * 로그인 성공 시 사용자 정보를 기반으로 JWTToken을 생성해서 반환한다.
	 * 
	 * @param loginUser
	 * @return
	 */
	
	public String create(final UserDTO loginUser) {
		//log.trace("time:{}",expireMin);
		final JwtBuilder builder = Jwts.builder();
		// JWT Token = Header + Payload + Signagure
		// Header 설정
		
		builder.setHeaderParam("typ", "JWT"); // 토큰의 타입으로 고정 값
		
		// Payload 설정 - claim 정보 포함
		builder.setSubject("로그인토큰") // 토큰 제목 설정
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*expireMin))//유효기간
				.claim("User",loginUser).claim("second","더 담고 싶은 것"); // 담고 싶은 정보 설정
		
		//signature - secret key를 이용한 암호화
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		
		//마지막 직렬화
		final String jwt = builder.compact();
		//log.debug("토큰 발행:{}",jwt);
		return jwt;
		
		
	}
	/**
	 * 전달받은 토큰이 제대로 생성된 것인 확인하고 문제가 있다면 Runtime 예외를 발생시킨다.
	 * @param jwt
	 */
	
	
	public void checkValid(final String jwt) {
		
		//log.trace("토큰 점검:{}",jwt);
		Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
	
	}
	/**
	 * jwt 토큰을 분석해서 필요한 정보를 반환한다.
	 * 
	 * @param jwt
	 * @return
	 */
	public Map<String,Object> get(final String jwt){
		Jws<Claims> claims =null;
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		}catch(final Exception e) {
			throw new RuntimeException();
		}
		
		//log.trace("claims:{},claims);
		
		return claims.getBody();
	}
}
