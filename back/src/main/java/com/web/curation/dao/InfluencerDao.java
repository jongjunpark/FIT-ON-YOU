package com.web.curation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.web.curation.model.Influencer;

public interface InfluencerDao extends JpaRepository<Influencer, String> {

	@Query(value = "select * from influencer", nativeQuery = true)
	List<Influencer> AllInfluencers();
	

}
