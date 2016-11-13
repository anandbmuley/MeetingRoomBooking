package com.merobo.repositories;

import com.merobo.beans.UserBean;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<UserBean, String> {

    Optional<UserBean> findByUsernameAndPassword(String username, String password);

    Optional<UserBean> findByUsername(String username);

    List<UserBean> findByTeamName(String teamName);

}
