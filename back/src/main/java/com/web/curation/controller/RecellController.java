package com.web.curation.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.dao.BoardDao;
import com.web.curation.dao.ImageDao;
import com.web.curation.dao.RecellDao;
import com.web.curation.dao.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.Chat;
import com.web.curation.model.ChatDTO;
import com.web.curation.model.ImageStore;
import com.web.curation.model.Recell;
import com.web.curation.service.user.BoardService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/recell")
public class RecellController {

	@Autowired
	ImageDao imageDao;
	@Autowired
	UserDao userDao;
	@Autowired
	RecellDao recellDao;
	@Autowired
	BoardService boardService;

	
	@GetMapping("/existroom")
	public Object existroom(@RequestParam String roomname) {
		final BasicResponse result = new BasicResponse();
		Recell recell;
		Optional<Recell> optRecell = recellDao.existRoomname(roomname);
		if (optRecell.isPresent()) {// 있다면 lasttime을 update후에
			System.out.println(2);
			// 룸네임을 반환
			recell = optRecell.get();
			result.data = "success";
			result.object = recell;
			result.status = true;
		} else {
			// 새로운 룸네임을 저장후 이를 반환
			
			result.object = null;
			result.data = "not exist roomname";
			result.status = true;
		}
		System.out.println(4);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// 내게시글만 가져오기
	@GetMapping("/myContents")
	public Object getMyContents(@RequestParam String username) {
		final BasicResponse result = new BasicResponse();
		List<Recell> myRecellList = recellDao.getMyContents(username);
		if (!myRecellList.isEmpty()) {
			result.object = myRecellList;
			result.data = "success";
			result.status = true;
		} else {
			result.object = null;
			result.data = "Nodata";
			result.status = true;
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/newsfeed/{page}")
	public Object getFollowArticle(@PathVariable int page) {
		List<Recell> result = boardService.getRecellList(page);
		return result;
	}

	@PostMapping("/soldout")
	public void changeSaleCheck(@RequestParam int num) {
		recellDao.changeSalechek(num);
	}

	@PostMapping(value = "/upload")
	public void addRecell(@RequestParam("recellimg") MultipartFile recellimg, @RequestParam("nickname") String nickname,
			@RequestParam("content") String content, @RequestParam("price") String price,
			@RequestParam("size") String size) {
		String path = "/var/www/html/dist/images/board/";
		// String path ="https://i3b304.p.ssafy.io/dist/images/board/";
		UUID uuid = UUID.randomUUID();
		Recell recell = new Recell();
		recell.setRecellUser(nickname);
		recell.setRecellContent(content);
		recell.setRecellPrice(price);
		recell.setRecellSize(size);
		recell.setRoomname(uuid.toString());
		String name = uuid.toString() + "_recell_" + recellimg.getOriginalFilename();
		String storePath = "../images/board/" + name;
		recell.setRecellImage(storePath);
		System.out.println(recell.toString());
		recellDao.save(recell);

		int recellNo = recellDao.getCountRecell().get(0);
		ImageStore img = new ImageStore();
		img.setArticleNo(recellNo);
		File file = new File(path + name);
		try {
			recellimg.transferTo(file);
			img.setImageUrl(storePath);
			imageDao.save(img);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
