package com.web.curation.controller;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.ChatDao;
import com.web.curation.dao.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.Chat;
import com.web.curation.model.ChatDTO;
import com.web.curation.model.User;
import com.web.curation.request.ChatReturnDTO;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	ChatDao chatDao;

	@Autowired
	UserDao userDao;

	@GetMapping("/allChatList")
	public Object getAllCahtList(@RequestParam String username) {
		final BasicResponse result = new BasicResponse();
		List<ChatDTO> chatDtoList = new ArrayList<>();
		chatDtoList = chatDao.getRoomListByUsername(username);
		List<ChatReturnDTO> chatReturnDtoList = new ArrayList<>();
		if (!chatDtoList.isEmpty()) {
			for (Iterator<ChatDTO> iter = chatDtoList.iterator(); iter.hasNext();) {
				ChatDTO tempChat = iter.next();
				ChatReturnDTO newChatDto = new ChatReturnDTO();
				newChatDto.setChatno(tempChat.getChatno());
				newChatDto.setFirstuser(tempChat.getFirstuser());
				newChatDto.setLasttime(tempChat.getLasttime());
				newChatDto.setRoomname(tempChat.getRoomname());
				newChatDto.setSeconduser(tempChat.getSeconduser());
				if (!tempChat.getFirstuser().equals(username)) {
					System.out.println(1);
					Optional<User> optUser = userDao.findUserByNickname(tempChat.getFirstuser());
					if (optUser.get().getProfile_img() != null) {
						newChatDto.setImg(optUser.get().getProfile_img());
					}
				} else if (!tempChat.getSeconduser().equals(username)) {
					System.out.println(2);
					Optional<User> optUser = userDao.findUserByNickname(tempChat.getSeconduser());
					if (optUser.get().getProfile_img() != null) {
						newChatDto.setImg(optUser.get().getProfile_img());
					}
				}

				chatReturnDtoList.add(newChatDto);
			}
			result.data = "success";
			result.object = chatReturnDtoList;
			result.status = true;
		} else {
			result.data = "empty";
			result.object = chatReturnDtoList;
			result.status = true;
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/existroom")
	public Object existroom(@RequestParam String firstuser, @RequestParam String seconduser) {
		System.out.println(1);
		final BasicResponse result = new BasicResponse();
		Chat chat;
		Optional<ChatDTO> optChatdto = chatDao.getRoomnameByUserInfos(firstuser, seconduser);
		if (optChatdto.isPresent()) {// 있다면 lasttime을 update후에
			System.out.println(2);
			// 룸네임을 반환
			chat = new Chat(optChatdto.get());
			chatDao.updateLasttime(chat.getRoomname());
			result.data = "success";
			result.object = chat;
			result.status = true;
		} else {
			System.out.println(3);
			// 새로운 룸네임을 저장후 이를 반환
			String roomname = firstuser + seconduser;
			chatDao.InsertRoomname(firstuser, seconduser, roomname);
			optChatdto = chatDao.getRoomnameByUserInfos(firstuser, seconduser);
			chat = new Chat(optChatdto.get());
			result.object = chat;
			result.data = "success";
			result.status = true;
		}
		System.out.println(4);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}