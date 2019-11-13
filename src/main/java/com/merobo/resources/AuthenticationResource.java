package com.merobo.resources;

import com.merobo.dtos.LoginTo;
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
    public ResponseEntity<LoginTo> login(@RequestBody LoginTo loginTo) {
        ResponseEntity<LoginTo> response;
        try {
            userService.login(loginTo);
            loginTo.sucessfulLogin();
            response = ResponseEntity.ok(loginTo);
        } catch (UserServiceException e) {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return response;
    }

}