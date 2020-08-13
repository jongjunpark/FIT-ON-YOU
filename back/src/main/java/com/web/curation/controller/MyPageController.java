package com.web.curation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.ArticletagDao;
import com.web.curation.dao.BoardDao;
import com.web.curation.dao.BoardTwoDao;
import com.web.curation.dao.FollowDao;
import com.web.curation.dao.ImageDao;
import com.web.curation.dao.UserDao;
import com.web.curation.model.Articletag;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.BoardDTO;
import com.web.curation.model.ImageStore;
import com.web.curation.model.User;

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
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BoardTwoDao boardTwoDao;
	
	@Autowired
	private ArticletagDao articletagDao;
	

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
		Optional<User> user= userDao.findById(nickname);
		resultMap.put("userinfo",user.get());
		resultMap.put("followedCnt",followedCnt);
		resultMap.put("followingCnt",followingCnt);
		
		return resultMap;
		
	}
	
	@ApiOperation(value="다른 유저 프로필 페이지")
	@GetMapping("/otheruser")
	public Map<String,Object> getUserinfo(@RequestParam String nickname){
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
		
		User user=userDao.findUserByNickname(nickname).get();
		user.setPassword("");
		resultMap.put("userinfo",user);
		
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
	
	
	@GetMapping("/boardone")
	public Object getBoard(@RequestParam int articleNo , @RequestParam String nickname) {
		Map<String, Object> resultMap = new HashMap<>();
		
		final BasicResponse result = new BasicResponse();
		
		BoardDTO board = boardTwoDao.getBoard(articleNo, nickname);
		List<Articletag> tags = articletagDao.findArticletagByArticleNoOrderByArticleNo(articleNo);
		List<ImageStore> images= imageDao.findImagestoreByArticleNoOrderByArticleNoDesc(articleNo);
		String profImg = userDao.findProfileImgByNickname(board.getArticleUser());
		
		result.status=true;
		result.data="success";
		
		resultMap.put("result",result);
		resultMap.put("board",board);
		resultMap.put("tags",tags);
		resultMap.put("images",images);
		resultMap.put("profileImg",profImg);
		
		return resultMap;
	}
}
