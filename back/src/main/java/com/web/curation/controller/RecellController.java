package com.web.curation.controller;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.dao.ImageDao;
import com.web.curation.dao.RecellDao;
import com.web.curation.dao.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.ImageStore;
import com.web.curation.model.Recell;

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

	@PostMapping(value="/upload")
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
