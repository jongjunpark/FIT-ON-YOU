package com.web.curation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.web.curation.model.Board;
import com.web.curation.model.ImageStore;


public interface ImageDao extends JpaRepository<ImageStore, String>{
	
	@Query(value="select i.imageurl, i.articleno, i.imageno " + 
			"from board b, imagestore i " + 
			"where b.articleuser = :nickname " + 
			"group by i.articleno "
			+ "order by i.articleno desc", nativeQuery = true)
	List<ImageStore> myBoardList(String nickname);
	
	@Query(value="select i.imageUrl , i.articleno, i.imageno " + 
			" from board b, bookmark m, imagestore i " + 
			" where b.articleno=m.bookedarticle and m.bookuser=:nickname " + 
			" group by i.articleno "
			+" order by i.articleno desc", nativeQuery=true)
	List<ImageStore> bookMarkImgList(@Param("nickname") String nickname);
	
	List<ImageStore> findImagestoreByArticleNoOrderByArticleNoDesc(int num);
	

}
