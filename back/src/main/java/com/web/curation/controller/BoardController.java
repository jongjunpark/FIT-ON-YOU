package com.web.curation.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.dao.AlarmDao;
import com.web.curation.dao.ArticletagDao;
import com.web.curation.dao.BoardDao;
import com.web.curation.dao.BookmarkDao;
import com.web.curation.dao.FollowDao;
import com.web.curation.dao.ImageDao;
import com.web.curation.dao.InfluencerDao;
import com.web.curation.dao.LikesDao;
import com.web.curation.model.Alarm;
import com.web.curation.model.Articletag;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.Board;
import com.web.curation.model.Bookmark;
import com.web.curation.model.Follow;
import com.web.curation.model.ImageStore;
import com.web.curation.model.Influencer;
import com.web.curation.model.Likes;
import com.web.curation.dao.CurationDao;
import com.web.curation.dao.FollowDao;
import com.web.curation.dao.ImageDao;
import com.web.curation.dao.SearchDao;
import com.web.curation.dao.TagDao;
import com.web.curation.dao.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.Board;
import com.web.curation.model.Curation;
import com.web.curation.model.Follow;
import com.web.curation.model.ImageStore;
import com.web.curation.model.Search;
import com.web.curation.model.Tag;
import com.web.curation.model.User;
import com.web.curation.model.UserDTO;

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
	@Autowired
	BookmarkDao bookmarkDao;
	@Autowired
	LikesDao likesDao;
	@Autowired
	AlarmDao alarmDao;
	@Autowired
	ArticletagDao articletagDao;
	@Autowired
	UserDao userDao;
	@Autowired
	InfluencerDao influencerDao;
	@Autowired
	TagDao tagDao;
	
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

	@PostMapping("/bookmark")
	public Object addBookMark(@Valid @RequestBody Bookmark bookmark) {
		final BasicResponse result = new BasicResponse();

		if (bookmarkDao.save(bookmark) == null) {
			result.status = true;
			result.data = "fail";

		} else {
			result.status = true;
			result.data = "success";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/bookmark")
	public Object delBookMark(@Valid @RequestBody Bookmark bookmark) {
		final BasicResponse result = new BasicResponse();
		try {
			bookmarkDao.deleteByBookedArticleAndBookUser(bookmark.getBookedArticle(), bookmark.getBookUser());
			result.status = true;
			result.data = "success";
		} catch (Exception e) {
			result.status = true;
			result.data = "fail";
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/likes")
	public Object addLikes(@Valid @RequestBody Likes likes) {
		final BasicResponse result = new BasicResponse();

		if (likesDao.countByArticleNoAndNickname(likes.getArticleNo(), likes.getNickname()) == 0) {
			if (likesDao.save(likes) == null) {

				result.status = true;
				result.data = "fail";

			} else {
				Alarm alarm = new Alarm();
				Board board = boardDao.findBoardByArticleNo(likes.getArticleNo());
				alarm.setType("3");
				alarm.setRecevier(board.getArticleUser());
				alarm.setFollower(likes.getNickname());
				alarm.setArticleNo(likes.getArticleNo());
				alarm.setIsRead(0);
				if (alarmDao.save(alarm) == null) {
					result.status = true;
					result.data = "fail";
				} else {
					result.status = true;
					result.data = "success";
				}
			}
		} else {
			result.status = true;
			result.data = "fail";
			result.object = "이미 좋아요 누른 게시글입니다.";
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/likes")
	public Object delLikes(@Valid @RequestBody Likes likes) {
		final BasicResponse result = new BasicResponse();
		if (likesDao.deleteByArticleNoAndNickname(likes.getArticleNo(), likes.getNickname()) == 1) {
			// 좋아요 취소시 알람에서 삭제
			System.out.println(likes.getArticleNo() + " " + likes.getNickname());
			try {
				alarmDao.deleteLikesAlarm("2", likes.getArticleNo(), likes.getNickname());
				result.status = true;
				result.data = "success";

			} catch (Exception e) {
				result.status = true;
				result.data = "fail";
			}
		} else {
			result.status = true;
			result.data = "fail";
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/tags")
	public List<Articletag> gettagArticle(@RequestParam int articleNo) {
		List<Articletag> taglist = new ArrayList<Articletag>();
		taglist = articletagDao.findArticletagByArticleNoOrderByArticleNo(articleNo);
		return taglist;
	}

	@PostMapping("/profileimg")
	public Object getUserProfile(@RequestParam String follow) {
		String nickname = follow;
		System.out.println(nickname);
		User user = userDao.findUserByNickname(nickname).get();
		return user;
	}

	@PostMapping("/influencer")
	public List<Influencer> getInfluencer() {
		Random random = new Random();
		List<Influencer> temp = influencerDao.AllInfluencers();
		List<Influencer> result = new ArrayList<Influencer>();
		int size = temp.size();
		boolean[] check = new boolean[size];
		int idx = 0;
		for (int i = 0; i < 5; i++) {
			idx = random.nextInt(size);
			while (check[idx])
				idx = random.nextInt(size);
			check[idx] = true;
			result.add(temp.get(idx));
		}

		return result;

	}
	@PostMapping(value="/upload")
	public void addArticle(@RequestParam("imgdata") MultipartFile[] imgs, @RequestParam("nickname") String nickname, @RequestParam("content") String content, @RequestParam("tags") String[] tags) {
		String path ="i3b304.p.ssafy.io/img/";
		UUID uuid = UUID.randomUUID();
		
		String[] names = new String[3];
		Board board = new Board();
		board.setArticleUser(nickname);
		board.setContent(content);
		boardDao.save(board);
		
		int articleNo= boardDao.getCountBoard().get(0);
		for (String string : tags) {
			Tag tag = new Tag();
			tag.setTagName(string);
			tagDao.save(tag);
			
			Articletag articletag = new Articletag();
			articletag.setArticleNo(articleNo);
			articletag.setTagName(string);
			articletagDao.save(articletag);
		}
		int idx =0;
		for (MultipartFile multipartFile : imgs) {
			ImageStore img = new ImageStore();
			names[idx] = uuid.toString()+"_"+multipartFile.getOriginalFilename();
			System.out.println(names[idx]);
			img.setArticleNo(articleNo);
			File file = new File(path + names[idx]);
			try {
				multipartFile.transferTo(file);
				String storePath="i3b304.p.ssafy.io/img/"+names[0];
				img.setImageUrl(storePath);
				System.out.println(storePath);
				imageDao.save(img);
			} catch (Exception e) {
				e.printStackTrace();
			}
			idx++;
		}
	}
}
