package com.web.curation.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.ArticletagDao;
import com.web.curation.dao.BoardDao;
import com.web.curation.dao.CurationDao;
import com.web.curation.dao.ImageDao;
import com.web.curation.dao.SearchDao;
import com.web.curation.dao.UserDao;
import com.web.curation.model.Articletag;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.Board;
import com.web.curation.model.Curation;
import com.web.curation.model.ImageStore;
import com.web.curation.model.ResponseData;
import com.web.curation.model.Search;
import com.web.curation.model.User;
import com.web.curation.model.UserDTO;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/search")

public class SearchController {

	@Autowired
	SearchDao searchDao;
	@Autowired
	CurationDao curationDao;
	@Autowired
	UserDao userDao;
	@Autowired
	BoardDao boardDao;
	@Autowired
	ArticletagDao articleTagDao;
	@Autowired
	ImageDao imageDao;
	
	@PostMapping("/")
	@ApiOperation(value="페이지 업로드")
	public Object uploadBoard (){
		List<Board> temp = boardDao.getList();
		List<ResponseData> result = new ArrayList<ResponseData>();
		for (Board board : temp) {
			ResponseData data = new ResponseData();
			data.setArticles(new Board());
			data.setImgs(new ArrayList<>());
			data.setTags(new ArrayList<>());
			
			try {
			List<ImageStore> imgs = imageDao.findImagestoreByArticleNoOrderByArticleNoDesc(board.getArticleNo());
			data.setImgs(imgs);
			}catch(Exception e){
			e.printStackTrace();
			}
			try{
				List<Articletag> tags = articleTagDao.findArticletagByArticleNoOrderByArticleNo(board.getArticleNo());
				data.setTags(tags);
			}catch(Exception e) {
				e.printStackTrace();
			}
			data.setArticles(board);
			
			result.add(data);
		}
		
		return new ResponseEntity<List<ResponseData>>(result, HttpStatus.OK);
		
	}

	@GetMapping("/user")
	@ApiOperation(value = "유저 검색")
	public Object searchUser(@Valid @RequestParam String username) {
		final BasicResponse result = new BasicResponse();
		List<User> optUser = userDao.findUsersByWord(username);
		if (optUser.isEmpty()) {
			result.object = null;
			result.data = "fail";
			result.status = true;
		} else {
			result.object = optUser;
			result.data = "success";
			result.status = true;
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// ariticleNo만 가져다쓰면됨 왜 모든 컬럼을 가져와야 작동하는 지 이해를 할수 없음 걍외우셈
	@GetMapping("/hash")
	@ApiOperation(value = "검색")
	public Object searchHash(@Valid @RequestParam String username, @Valid @RequestParam String findContent) {

		final BasicResponse result = new BasicResponse();

		StringTokenizer st = new StringTokenizer(findContent);
		List<String> list = new ArrayList<>();

		while (st.hasMoreTokens()) {
			String input = st.nextToken("#");
			System.out.println("input================>" + input);
			list.add(input);
		}

		int size = list.size();
		System.out.println(size);

		List<Curation> curationList = curationDao.getCurationByUsername(username);
		if (!curationList.isEmpty() && curationList.size() + list.size() >= 5) {
			int forSize = curationList.size() + list.size() - 5;
			for (int i = 0; i < (forSize > 5 ? 5 : forSize); i++) {
				curationDao.delete(curationList.get(i));
			}
		}

		List<Search> searchList = searchDao.getSearchByTagname(list, list.size());

		if (searchList.isEmpty()) {
			result.data = "검색된 태그가 존재하지 않습니다";
			result.object = searchList;
			result.status = true;
		} else {
			result.data = "success";
			result.object = searchList;
			result.status = true;
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}

