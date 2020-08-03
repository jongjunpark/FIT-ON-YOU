package com.web.curation.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.AlarmDao;
import com.web.curation.dao.BoardDao;
import com.web.curation.dao.BookmarkDao;
import com.web.curation.dao.FollowDao;
import com.web.curation.dao.ImageDao;
import com.web.curation.dao.LikesDao;
import com.web.curation.model.Alarm;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.Board;
import com.web.curation.model.Bookmark;
import com.web.curation.model.Follow;
import com.web.curation.model.ImageStore;
import com.web.curation.model.Likes;

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
	@Autowired
	FollowDao followDao;
	@Autowired
	ImageDao imageDao;
	
	@Autowired
	BookmarkDao bookmarkDao;
	
	@Autowired
	LikesDao likesDao;
	
	@Autowired
	AlarmDao alarmDao;
	
	
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
	Comparator<Board> boardComp = new Comparator<Board>() {
		@Override
		public int compare(Board o1, Board o2) {
			if(o1.getArticleNo()<o2.getArticleNo())
				return 1;
			else return -1;
		}
	};
	@PostMapping("/newsfeed")
	public List<Board> getFollowArticle(@RequestParam String nickname){
		List<Follow>searchFollow = new ArrayList<Follow>();
		searchFollow =  followDao.getFollowByFollowinguser(nickname);
		List<Board> result = new ArrayList<>();
		
		for (Follow follow : searchFollow) {
			List<Board>temp = boardDao.findBoardByArticleUserOrderByArticleNoDesc(follow.getFolloweduser());
			for (Board board : temp) {
				result.add(board);
				//System.out.println(board);
			}
			
		}
		result.sort(boardComp);
		
		return result;
	}
	@PostMapping("/images")
	public List<ImageStore> getImageArticle(@RequestParam int articleNo){
		List<ImageStore> urllist = new ArrayList<ImageStore>();
		urllist= imageDao.findImagestoreByArticleNoOrderByArticleNoDesc(articleNo);
		return urllist;
	}
	
	@PostMapping("/bookmark")
	public Object addBookMark(@Valid @RequestBody Bookmark bookmark) {
		final BasicResponse result = new BasicResponse();
		
		if(bookmarkDao.save(bookmark)==null) {
			result.status = true;
			result.data = "fail";

		} else {
			result.status = true;
			result.data = "success";
		}
   		return  new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/bookmark")
	public Object delBookMark(@Valid @RequestBody Bookmark bookmark) {
		final BasicResponse result = new BasicResponse();	
		try{
			bookmarkDao.deleteByBookedArticleAndBookUser(bookmark.getBookedArticle(), bookmark.getBookUser());
			result.status = true;
			result.data = "success";
		}catch(Exception e) {
			result.status=true;
			result.data = "fail";
		}

   		return  new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@PostMapping("/likes")
	public Object addLikes(@Valid @RequestBody Likes likes) {
		final BasicResponse result = new BasicResponse();
		

		if(likesDao.countByArticleNoAndNickname(likes.getArticleNo(), likes.getNickname())==0) {
			if(likesDao.save(likes)==null) {
				
				result.status = true;
				result.data = "fail";		
				
				
			} else {
				Alarm alarm = new Alarm();
				Board board = boardDao.findByArticleNo(likes.getArticleNo());
				alarm.setType("3");
				alarm.setRecevier(board.getArticleUser());
				alarm.setFollower(likes.getNickname());
				alarm.setArticleNo(likes.getArticleNo());
				alarm.setIsRead(0);
				if(alarmDao.save(alarm)==null) {
					result.status = true;
					result.data = "fail";
				}
				else {
					result.status = true;
					result.data = "success";
				}
			}
		}else {	
			result.status=true;
			result.data="fail";
			result.object="이미 좋아요 누른 게시글입니다.";
		}
	
		
   		return  new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/likes")
	public Object delLikes(@Valid @RequestBody Likes likes) {
		final BasicResponse result = new BasicResponse();	
		if(likesDao.deleteByArticleNoAndNickname(likes.getArticleNo(), likes.getNickname())==1) {
			// 좋아요 취소시 알람에서 삭제 
			System.out.println(likes.getArticleNo()+" "+likes.getNickname());
			try{
				alarmDao.deleteLikesAlarm("2", likes.getArticleNo(), likes.getNickname());
				result.status = true;
				result.data = "success";
			
			}catch(Exception e){
				result.status=true;
				result.data = "fail";				
			}
		}else {
			result.status=true;	
			result.data = "fail";
		}

   		return  new ResponseEntity<>(result, HttpStatus.OK);
	}

}
