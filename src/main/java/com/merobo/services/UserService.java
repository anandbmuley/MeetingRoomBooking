package com.merobo.services;

import com.merobo.beans.UserBean;
import com.merobo.dtos.LoginResponseTo;
import com.merobo.dtos.LoginTo;
import com.merobo.dtos.RegisterUserTo;
import com.merobo.dtos.UserTo;
import com.merobo.exceptions.DuplicateUsernameException;
import com.merobo.exceptions.UserNotFoundException;
import com.merobo.exceptions.UserServiceException;
import com.merobo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public LoginResponseTo login(LoginTo loginTo) throws UserServiceException {
        userRepository.findByUsernameAndPassword(loginTo.getUsername(), encode(loginTo.getPassword())).orElseThrow(UserNotFoundException::new);
        return new LoginResponseTo();
    }

    public void create(RegisterUserTo registerUserTo) throws UserServiceException {
        Optional<UserBean> userBean = userRepository.findByUsername(registerUserTo.getUsername());
        if (userBean.isPresent()) {
            throw new DuplicateUsernameException();
        }
        UserBean newUserBean = new UserBean(registerUserTo.getName(),
                registerUserTo.getEmailId(),
                registerUserTo.getContactNo(),
                registerUserTo.getUsername(),
                encode(registerUserTo.getPassword()));
        userRepository.save(newUserBean);
    }

    private String encode(String password) {
        return Base64.getEncoder().withoutPadding().encodeToString(password.getBytes());
    }

    public void update(UserTo userTo) throws UserServiceException {
        UserBean userBean = userRepository.findByUsername(userTo.getUsername()).orElseThrow(UserNotFoundException::new);
        if (!StringUtils.isEmpty(userTo.getPassword())) {
            userBean.setPassword(encode(userTo.getPassword()));
        }
        userBean.setName(userTo.getName());
        userRepository.save(userBean);
    }

    public void encodeAll() {
        List<UserBean> userBeans = userRepository.findAll();
        userBeans.stream().forEach(userBean -> {
            userBean.setPassword(
                    encode(userBean.getPassword())
            );
            userRepository.save(userBean);
        });
    }
}
