package com.web.curation.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.web.curation.dao.SearchDao;
import com.web.curation.model.Search;

@Service
public class SearchService {

	@Autowired
	private SearchDao searchDao;
	
	public List<Search> getArticles(int page, List<String> tagname, int size){
		PageRequest pageRequest = PageRequest.of(page, 30);
		return searchDao.getSearchByTagname(pageRequest, tagname, size).getContent();
	}
}
