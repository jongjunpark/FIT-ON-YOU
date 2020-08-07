package com.web.curation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.AlarmDao;
import com.web.curation.dao.CommentDao;
import com.web.curation.dao.UserDao;
import com.web.curation.model.Alarm;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.Comment;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private AlarmDao alarmDao;
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping
	public Object getAllComment(@RequestParam int articleNo) {
		Map<String,Object> resultMap=new HashMap<>();
		final BasicResponse result = new BasicResponse();
		List<Comment> commentli = new ArrayList<>();
		List<String> profile=new ArrayList<>();
		commentli=commentDao.findAllByArticleNoOrderByCommentNoAsc(articleNo);
		for(Comment comment : commentli) {
			profile.add(userDao.findProfileImgByNickname(comment.getWriter()));
		}
		
		result.status=true;
		result.data="success";
		
		
		resultMap.put("result",result);		
		resultMap.put("commentli",commentli);		
		resultMap.put("result",result);		
		resultMap.put("profileli",profile);
		
		return resultMap;
		
	}
	
	// 글 작성자도 같이 전달 받기
	@PostMapping
	public Object addComment(@RequestParam String writer, @RequestParam int articleNo, @RequestParam String content, @RequestParam String articleUser ) {
		System.out.println(articleNo + " "+ articleUser+" "+writer+ " "+content);
		
		final BasicResponse result = new BasicResponse();
		Map<String ,Object> resultMap=new HashMap<>();
		Comment comment = new Comment();
		comment.setArticleNo(articleNo);
		comment.setWriter(writer);
		comment.setContent(content);
		
		Comment rescmt = new Comment();
		rescmt=commentDao.save(comment);
		if(rescmt==null) {
			result.data="fail";
			
		}else {		
			result.data="success";
			if(!articleUser.equals(writer)) {
				if(alarmDao.findByTypeAndArticleNoAndFollower("1", articleNo, writer)==null) {
					Alarm alarm=new Alarm();
					alarm.setType("1");
					alarm.setIsRead(0);
					alarm.setArticleNo(articleNo);
					alarm.setRecevier(articleUser);
					alarm.setFollower(writer);
					alarmDao.save(alarm);
				}
				else {
					result.object="하나의 글에 같은사람이 댓글 두번 단 경우";
				}
			}
			resultMap.put("rescmt",rescmt);
		}
		result.status=true;
		resultMap.put("result",result);
		
		return resultMap;
	}
	
	@DeleteMapping
	public Object delComment(@RequestParam int commentNo) {
		final BasicResponse result = new BasicResponse();
		
		try{
			commentDao.deleteByCommentNo(commentNo);
			result.data="success";
		}catch (Exception e) {
			result.data="fail";
		}
		result.status=true;
		
		return result;
		
	}

}
