package com.web.curation.controller.account;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.user.BoardDao;
import com.web.curation.dao.user.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.Board;
import com.web.curation.model.user.SignupRequest;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardDao boardDao;
	
	@PostMapping
	public Object writeBoard(@Valid @RequestBody Board board) {
		
		final BasicResponse result = new BasicResponse();
   		if (boardDao.save(board)==null) {
   			result.status = true;
			result.data = "fail";

		} else {
			result.status = true;
			result.data = "success";
		}
   		return  new ResponseEntity<>(result, HttpStatus.OK);
   	}

}
