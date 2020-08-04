package com.web.curation.controller;

import java.util.ArrayList;
import java.util.List;

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
	
	@GetMapping
	public Object getAllComment(@RequestParam int articleNo) {
		final BasicResponse result = new BasicResponse();
		List<Comment> li = new ArrayList<>();
		
		li=commentDao.findAllByArticleNoOrderByCommentNoDesc(articleNo);
		result.status=true;
		result.data="success";
		result.object=li;
		
		return result;
		
	}
	
	// 글 작성자도 같이 전달 받기
	@PostMapping
	public Object addComment(@RequestParam String writer, @RequestParam int articleNo, @RequestParam String content, @RequestParam String articleUser ) {
		final BasicResponse result = new BasicResponse();
		Comment comment = new Comment();
		comment.setArticleNo(articleNo);
		comment.setWriter(writer);
		comment.setContent(content);
		
		if(commentDao.save(comment)==null) {
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
		}
		result.status=true;
		
		
		return result;
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
