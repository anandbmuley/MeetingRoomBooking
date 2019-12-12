package com.merobo.services;

import com.merobo.beans.User;
import com.merobo.dtos.LoginDto;
import com.merobo.dtos.LoginResponseDto;
import com.merobo.dtos.RegisterUserDto;
import com.merobo.dtos.UserDto;
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
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        String randomPwd = UUID.randomUUID().toString();
        user.setPassword(encode(randomPwd));
        userRepository.save(user);
        return randomPwd;
    }

    public LoginResponseDto login(LoginDto loginDto) throws UserServiceException {
        User founderUser = userRepository.findByUsernameAndPassword(loginDto.getUsername(), encode(loginDto.getPassword())).orElseThrow(UserNotFoundException::new);
        return new LoginResponseDto(founderUser.getId(), founderUser.getRole());
    }

    public void create(RegisterUserDto registerUserDto) throws UserServiceException {
        Optional<User> userBean = userRepository.findByUsername(registerUserDto.getUsername());
        if (userBean.isPresent()) {
            throw new DuplicateUsernameException();
        }
        User newUser = new User(registerUserDto.getName(),
                registerUserDto.getEmailId(),
                registerUserDto.getContactNo(),
                registerUserDto.getUsername(),
                encode(registerUserDto.getPassword()),
                registerUserDto.getRole());
        userRepository.save(newUser);
    }

    private String encode(String password) {
        return Base64.getEncoder().withoutPadding().encodeToString(password.getBytes());
    }

    public void update(UserDto userDto) throws UserServiceException {
        User user = userRepository.findByUsername(userDto.getUsername()).orElseThrow(UserNotFoundException::new);
        if (!StringUtils.isEmpty(userDto.getPassword())) {
            user.setPassword(encode(userDto.getPassword()));
        }
        user.setName(userDto.getName());
        userRepository.save(user);
    }

    public void encodeAll() {
        List<User> users = userRepository.findAll();
        users.stream().forEach(userBean -> {
            userBean.setPassword(
                    encode(userBean.getPassword())
            );
            userRepository.save(userBean);
        });
    }

    public Optional<UserDto> findBy(String userId) {
        return userRepository.findById(userId).map($ -> new UserDto($.getName(), $.getEmailId(), $.getContactNo(), $.getRole()));
    }
}
