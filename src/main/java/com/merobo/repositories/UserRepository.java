package com.merobo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.merobo.beans.UserBean;

public interface UserRepository extends MongoRepository<UserBean, String> {

	UserBean findByUsernameAndPassword(String username, String password);

	UserBean findByUsername(String username);

	List<UserBean> findByTeamName(String teamName);

}
