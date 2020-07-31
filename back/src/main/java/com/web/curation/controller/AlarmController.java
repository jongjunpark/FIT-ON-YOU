package com.web.curation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.AlarmDao;
import com.web.curation.model.Alarm;
import com.web.curation.model.BasicResponse;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/alarm")
public class AlarmController {
	/*
	 * 댓글 추가  type 1
	팔로우 했을때 type 2
	팔로우 해지 했을때 type 3
	좋아요 눌렀을때 type 4
	좋아요 해지 했을때 type 5(?)
	 */
	
	@Autowired
	private AlarmDao alarmDao;
	
	@GetMapping("/")
	public Map<String,Object> alarmList(@Valid @RequestParam String nickname){
		Map<String,Object> resultMap= new HashMap<>();
		
		List<Alarm> list=alarmDao.findByRecevierAndIsReadOrderByAlarmNoDesc(nickname, 0);
		int cnt=list.size();
		if(cnt!=0) {
			resultMap.put("result",1);
			resultMap.put("alist",list);
			resultMap.put("acnt",cnt);
		}
		else {
			resultMap.put("result",0);
		}
		return resultMap;
	}

	
	
	
	
	
}
