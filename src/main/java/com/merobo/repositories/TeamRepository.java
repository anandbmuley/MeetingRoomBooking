package com.merobo.repositories;

import com.merobo.beans.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeamRepository extends MongoRepository<Team, String> {

    List<Team> deleteByName(String name);

    Team findByName(String name);

    List<Team> findByNameIn(List<String> names);

}
