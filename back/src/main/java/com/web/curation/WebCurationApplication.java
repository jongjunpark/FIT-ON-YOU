package com.web.curation;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.web.curation.service.user.JwtInterceptor;

@SpringBootApplication
public class WebCurationApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(WebCurationApplication.class, args);
	}
	
	@Autowired
	private JwtInterceptor jwtInterceptor;
	/**
	 * JWTInterceptor를 설치한다.
	 */
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")
		.excludePathPatterns(Arrays.asList("/**"));
	}
	
	/**	
	 * Interceptor를 이용해서 처리하므로 전역의 CorssOrigin 처리를 해준다.
	 */
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("jwt-auth-token");
	}
}
