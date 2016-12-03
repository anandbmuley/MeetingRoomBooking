package com.merobo.repositories;

import com.merobo.beans.TeamBean;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeamRepository extends MongoRepository<TeamBean, String> {

    List<TeamBean> deleteByName(String name);

    TeamBean findByName(String name);

    List<TeamBean> findByNameIn(List<String> names);

}
