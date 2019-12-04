package com.merobo.resources;

import com.merobo.dtos.PasswordDto;
import com.merobo.dtos.RegisterUserDto;
import com.merobo.dtos.UserDto;
import com.merobo.exceptions.UserNotFoundException;
import com.merobo.exceptions.UserServiceException;
import com.merobo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("users")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity register(@RequestBody RegisterUserDto registerUserDto) {
        ResponseEntity response;
        try {
            userService.create(registerUserDto);
            response = ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UserServiceException e) {
            response = ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return response;
    }

    @PostMapping("{username}/resetpassword")
    public ResponseEntity resetPassword(@PathVariable("username") String username) {
        ResponseEntity response = null;
        try {
            String newPwd = userService.resetPassword(username);
            response = ResponseEntity.ok(new PasswordDto(newPwd));
        } catch (UserNotFoundException e) {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return response;
    }

    @GetMapping("{userId}")
    public ResponseEntity getUser(@PathVariable String userId) {
        return userService.findBy(userId).map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
    }

    @PutMapping("{username}")
    public ResponseEntity changePassword(@PathVariable("username") String username, @RequestBody UserDto userDto) {
        ResponseEntity response = null;
        try {
            userDto.setUsername(username);
            userService.update(userDto);
            response = ResponseEntity.noContent().build();
        } catch (UserServiceException e) {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return response;
    }
}
