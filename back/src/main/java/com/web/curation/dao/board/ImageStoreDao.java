package com.web.curation.dao.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.Imagestore;

public interface ImageStoreDao extends JpaRepository<Imagestore, String>{
	
	List<Imagestore> findImagestoreByArticleNoOrderByArticleNoDesc(int num);
		
}
