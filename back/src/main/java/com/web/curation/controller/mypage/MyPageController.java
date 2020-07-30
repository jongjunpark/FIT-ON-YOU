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
import com.web.curation.dao.board.ImageDao;
import com.web.curation.dao.follow.FollowDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.Board;
import com.web.curation.model.ImageStore;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
	
	@Autowired
	private ImageDao imageDao;
	
	@Autowired
	private FollowDao followDao;
	

	@ApiOperation(value="마이페이지 누를 시 팔로우 숫자")
	@GetMapping("/")
	public Map<String,Object> followCount(@RequestParam String nickname){
		Map<String,Object> resultMap=new HashMap<>();
		final BasicResponse result = new BasicResponse();
		
		result.data="1";
		result.status=true;
		resultMap.put("result",result);
		//나를 팔로우 한사람
		Long followedCnt = followDao.countByFolloweduser(nickname);
		
		//내가 팔로우 한사람
		Long followingCnt= followDao.countByFollowinguser(nickname);
		
		resultMap.put("followedCnt",followedCnt);
		resultMap.put("followingCnt",followingCnt);
		
		return resultMap;
		
	}
	
	

	@ApiOperation(value="내 글 보기 탭 누를시")
	@GetMapping("/myboard")
	public Map<String,Object> getMyBoard(@RequestParam String nickname){
		Map<String,Object> resultMap=new HashMap<>();
		final BasicResponse result = new BasicResponse();
		List<ImageStore> myBoards = imageDao.myBoardList(nickname);
	
		if(myBoards.size()==0) {
			result.data="0";
			result.status=true;
			resultMap.put("result",result);
		}
		else{
			result.data="1";
			result.status=true;
			resultMap.put("result",result);
			resultMap.put("myBoards",myBoards);
		}
		
		return resultMap;
		
	}
	
	@ApiOperation(value="북마크 탭 누를시")
	@GetMapping("/bookmark")
	public Map<String,Object> getBookmark(@RequestParam String nickname){
		Map<String,Object> resultMap=new HashMap<>();
		final BasicResponse result = new BasicResponse();
		List<ImageStore> bmImgList= imageDao.bookMarkImgList(nickname);
		
		if(bmImgList.size()==0) {
			result.data="0";
			result.status=true;
			resultMap.put("result",result);
		}
		else {
			result.data="1";
			result.status=true;
			resultMap.put("bmImgList",bmImgList);
			resultMap.put("result",result);
		}
		
		
		return resultMap;
	}
}
