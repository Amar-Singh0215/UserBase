package com.avigya.userbase.controller;

import com.avigya.userbase.records.RegisteredUser;
import com.avigya.userbase.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UsersService usersService;

    @PostMapping("/createUser")
    private String createUser(@RequestBody RegisteredUser user)
    {
        log.info("User create request received");
        return usersService.createUser(user);
    }

    private String createUser( )
    {
        return "";
    }
}