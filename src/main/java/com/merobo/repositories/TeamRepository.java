package com.merobo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.merobo.beans.TeamBean;

public interface TeamRepository extends MongoRepository<TeamBean, String> {

	List<TeamBean> deleteByName(String name);
	TeamBean findByName(String name);

}
