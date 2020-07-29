package com.web.curation.controller.mypage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.board.BoardDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.Board;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/mypage")
public class MyPageController {
	
	@Autowired
	private BoardDao boardDao;
	
	@GetMapping("/myboard")
	public Map<String,Object> getMyBoard(@RequestParam String nickname){
		Map<String,Object> result=new HashMap<>();
		
		List<Board> boards = boardDao.findBoardByArticleUserOrderByArticleNoDesc(nickname);
	
		if(boards.size()==0) {
			result.put("result",0);
		}
		else{
			result.put("boards", boards);
			result.put("result", 1);
		}
		
		return result;
		
	}
	
	@GetMapping("/bookmark")
	public Map<String,Object> getBookmark(@RequestParam String nickname){
		Map<String,Object> result=new HashMap<>();
		
		List<Board> boards= boardDao.bookmarkList(nickname);
		
		if(boards.size()==0) {
			result.put("result",0);
		}
		else {
			result.put("boards",boards);
			result.put("result",1);
		}
		
		
		
		return result;
	}
}
