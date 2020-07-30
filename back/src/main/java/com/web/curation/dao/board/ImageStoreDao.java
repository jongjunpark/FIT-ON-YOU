package com.web.curation.dao.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.ImageStore;

public interface ImageStoreDao extends JpaRepository<ImageStore, String>{
	
	List<ImageStore> findImagestoreByArticleNoOrderByArticleNoDesc(int num);
		
}
