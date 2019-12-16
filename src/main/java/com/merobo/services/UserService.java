package com.merobo.services;

import abm.authenticator.repositories.UserRepository;
import com.merobo.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<UserDto> findBy(String userId) {
        return userRepository.findById(userId).map($ -> new UserDto($.getName(), $.getEmailId(), $.getContactNo(), $.getRole()));
    }
}
