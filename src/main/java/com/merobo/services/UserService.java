package com.merobo.services;

import com.merobo.beans.UserBean;
import com.merobo.dtos.UserTo;
import com.merobo.exceptions.DuplicateUsernameException;
import com.merobo.exceptions.UserNotFoundException;
import com.merobo.exceptions.UserServiceException;
import com.merobo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String resetPassword(String username) throws UserNotFoundException {
        UserBean userBean = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        String randomPwd = UUID.randomUUID().toString();
        userBean.setPassword(encode(randomPwd));
        userRepository.save(userBean);
        return randomPwd;
    }

    public void login(UserTo userTo) throws UserServiceException {
        UserBean userBean = userRepository.findByUsernameAndPassword(userTo.getUsername(), encode(userTo.getPassword())).orElseThrow(UserNotFoundException::new);
        userTo.setName(userBean.getName());
        userTo.setTeamName(userBean.getTeamName());
        userTo.setId(userBean.getId());
    }

    public void create(UserTo userTo) throws UserServiceException {
        Optional<UserBean> userBean = userRepository.findByUsername(userTo.getUsername());
        if (userBean.isPresent()) {
            throw new DuplicateUsernameException();
        }
        UserBean newUserBean = new UserBean();
        newUserBean.setName(userTo.getName());
        newUserBean.setPassword(encode(userTo.getPassword()));
        newUserBean.setTeamName(userTo.getTeamName());
        newUserBean.setUsername(userTo.getUsername());
        userRepository.save(newUserBean);
        userTo.setId(newUserBean.getId());
    }

    private String encode(String password) {
        return Base64.getEncoder().withoutPadding().encodeToString(password.getBytes());
    }

    public void update(UserTo userTo) throws UserServiceException {
        UserBean userBean = userRepository.findByUsername(userTo.getUsername()).orElseThrow(UserNotFoundException::new);
        userBean.setPassword(encode(userTo.getPassword()));
        userRepository.save(userBean);
    }

    public void encodeAll() {
        List<UserBean> userBeans = userRepository.findAll();
        userBeans.stream().forEach(userBean -> {
            userBean.setPassword(encode(userBean.getPassword()));
            userRepository.save(userBean);
        });
    }
}
