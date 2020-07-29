package com.web.curation.controller.follow;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.follow.FollowDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.follow.AddFollowRequest;
import com.web.curation.model.follow.Follow;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
public class FollowController {

	@Autowired
	FollowDao followDao;

	@GetMapping("/follow/add")
	@ApiOperation(value = "팔로우 추가")
	public void addFollow(@Valid @RequestParam String followingUser, @Valid @RequestParam String followedUser) {
		Follow follow = new Follow();
		follow.setFolloweduser(followedUser);
		follow.setFollowinguser(followingUser);
		if (followDao.save(follow) == null) {
			System.out.println("안됨");
		} else {
			System.out.println("됨");
			
		}
	}
	
	@GetMapping("/follow/forFollowing")
	@ApiOperation(value = "내가 팔로우한 사람들 리스트")
	public List<Follow> myFollowList(@Valid @RequestParam String userName){
		List<Follow> result = new ArrayList();
		result = followDao.getFollowByFollowinguser(userName);
		
		if(result.isEmpty()) {
			System.out.println("값 없음");
		}else {
			System.out.println("값 있음");
		}
		
		return result;
	}
	
	
	@GetMapping("/follow/delete")
	@ApiOperation(value = "팔로우 삭제")
	public Object deleteFollow(@Valid @RequestParam int followNo) {
		
		final BasicResponse result = new BasicResponse();
		
		 if(followDao.deleteAllByFollowno(followNo)==1) {//성공
			 result.status = true;
			 result.data = "삭제 성공";
		 }else {//실패
			 result.status = true;
			 result.data = "삭제 실패";
		 }
		 return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
