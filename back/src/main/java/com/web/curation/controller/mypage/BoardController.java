package com.web.curation.controller.mypage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.board.BoardDao;
import com.web.curation.dao.follow.FollowDao;
import com.web.curation.dao.user.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.Board;
import com.web.curation.model.follow.Follow;

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
			System.out.println(follow.getFolloweduser());
			for (Board board : temp) {
				result.add(board);
				System.out.println(board);
			}
			
		}
		result.sort(boardComp);
		
		return result;
	}
//	@PostMapping("/influencer")
//	public List<>

}
