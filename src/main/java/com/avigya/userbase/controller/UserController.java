package com.avigya.userbase.controller;

import com.avigya.userbase.model.Users;
import com.avigya.userbase.records.RegisteredUser;
import com.avigya.userbase.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UsersService usersService;

    @PostMapping("/createUser")
    private String createUser(@RequestBody RegisteredUser user)
    {
        log.info("User create request received");
        return usersService.createUser(user).toString();
    }
    @GetMapping("/getUser")
    private ResponseEntity<?> getUser(@RequestBody RegisteredUser user)
    {
        try
        {
            Users userFound=usersService.getRegisteredUser(user);
            return new ResponseEntity<>(userFound,HttpStatus.OK);
        } catch (RuntimeException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/saveUser")
    private ResponseEntity<String> saveUserDetails(@RequestBody Users user) {
        try {
            return new ResponseEntity<>(usersService.saveUser(user), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}