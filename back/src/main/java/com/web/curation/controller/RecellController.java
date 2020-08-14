package com.web.curation.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

import io.swagger.annotations.ApiOperation;
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

	@PostMapping(value = "/upload")
	public void addArticle(@RequestParam("imgdata") MultipartFile imgs, @RequestParam("nickname") String nickname,
			@RequestParam("content") String content, @RequestParam("price") String price, @RequestParam("size") String size) {
		String path = "/var/www/html/dist/images/board/";
		// String path ="https://i3b304.p.ssafy.io/dist/images/board/";

		UUID uuid = UUID.randomUUID();

		String[] names = new String[3];
		Recell recell = new Recell();
		recell.setRecellUser(nickname);
		recell.setRecellContent(content);
		recell.setRecellPrice(price);
		recell.setRecellSize(size);

		int recellNo = recellDao.getCountRecell().get(0);
		ImageStore img = new ImageStore();
		String name = nickname + "_recell_" + imgs.getOriginalFilename();
		img.setArticleNo(recellNo);
		File file = new File(path + name);
		try {
			imgs.transferTo(file);
			String storePath = "../images/board/" + name;
			img.setImageUrl(storePath);
			recell.setRecellImage(storePath);
			recellDao.save(recell);
			imageDao.save(img);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
