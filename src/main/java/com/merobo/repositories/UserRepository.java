package com.merobo.repositories;

import com.merobo.beans.UserBean;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<UserBean, String> {

    UserBean findByUsernameAndPassword(String username, String password);

    UserBean findByUsername(String username);

    List<UserBean> findByTeamName(String teamName);

}
