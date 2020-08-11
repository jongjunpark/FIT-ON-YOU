package com.web.curation.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.web.curation.dao.BoardTwoDao;
import com.web.curation.model.BoardDTO;
@Service
public class BoardService {
	
	@Autowired
	private BoardTwoDao boardTwoDao;
	
	public List<BoardDTO> getMainFeedList(int page, String nickname){
		PageRequest pageRequest = PageRequest.of(page, 10);
		
		return boardTwoDao.getMainFeedList(pageRequest, nickname).getContent();
		
	}
}
