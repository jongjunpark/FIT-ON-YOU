package com.web.curation.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.BoardDao;
import com.web.curation.dao.CurationDao;
import com.web.curation.dao.FollowDao;
import com.web.curation.dao.ImageDao;
import com.web.curation.dao.SearchDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.Board;
import com.web.curation.model.Curation;
import com.web.curation.model.Follow;
import com.web.curation.model.ImageStore;
import com.web.curation.model.Search;

import io.swagger.annotations.ApiOperation;
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
	CurationDao curationDao;
	@Autowired
	SearchDao searchDao;

	@GetMapping("/api/board/curation")
	@ApiOperation(value = "큐레이션 기능")
	public Object curatedContents(String username) {
		System.out.println(1);
		final BasicResponse result = new BasicResponse();
		List<Curation> curationList = curationDao.getCurationByUsername(username);
		List<Board> boardList = new ArrayList<>();

		if (!curationList.isEmpty()) {// 안비어 있을경우
			int size = curationList.size();

			List<String> input = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				System.out.println(curationList.get(i).getTagname());
				input.add(curationList.get(i).getTagname());
			}

			List<Search> contents = searchDao.getContentsNoByTagname(input);

			int contentsSize = contents.size();
			for (int c = 0; c < contentsSize; c++) {
				Board newBoard = boardDao.findBoardByArticleNo(contents.get(c).getArticleno());

				if (newBoard != null) {
					boardList.add(newBoard);
				}
			}

			result.data = "success";
			result.status = true;
			result.object = boardList;
		} else {// 큐레이션이 비어있을 경우
				// 팔로우 의 새 게시글을 보여준다
		}

		return result;
	}

	@PostMapping
	public Object writeBoard(@Valid @RequestBody Board board) {

		final BasicResponse result = new BasicResponse();
		if (boardDao.save(board) == null) {
			result.status = true;
			result.data = "fail";

		} else {
			result.status = true;
			result.data = "success";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	Comparator<Board> boardComp = new Comparator<Board>() {
		@Override
		public int compare(Board o1, Board o2) {
			if (o1.getArticleNo() < o2.getArticleNo())
				return 1;
			else
				return -1;
		}
	};

	@PostMapping("/newsfeed")
	public List<Board> getFollowArticle(@RequestParam String nickname) {
		List<Follow> searchFollow = new ArrayList<Follow>();
		searchFollow = followDao.getFollowByFollowinguser(nickname);
		List<Board> result = new ArrayList<>();

		for (Follow follow : searchFollow) {
			List<Board> temp = boardDao.findBoardByArticleUserOrderByArticleNoDesc(follow.getFolloweduser());
			for (Board board : temp) {
				result.add(board);
				// System.out.println(board);
			}

		}
		result.sort(boardComp);

		return result;
	}

	@PostMapping("/images")
	public List<ImageStore> getImageArticle(@RequestParam int articleNo) {
		List<ImageStore> urllist = new ArrayList<ImageStore>();
		urllist = imageDao.findImagestoreByArticleNoOrderByArticleNoDesc(articleNo);
		return urllist;
	}

}
