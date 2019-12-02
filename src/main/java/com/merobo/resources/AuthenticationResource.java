package com.merobo.resources;

import com.merobo.dtos.LoginDto;
import com.merobo.exceptions.UserServiceException;
import com.merobo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("auth")
public class AuthenticationResource {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) {
        ResponseEntity response;
        try {
            response = ResponseEntity.ok(userService.login(loginDto));
        } catch (UserServiceException e) {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        return response;
    }

}