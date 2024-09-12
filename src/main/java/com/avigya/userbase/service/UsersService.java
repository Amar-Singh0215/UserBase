package com.avigya.userbase.service;

import com.avigya.userbase.model.Users;
import com.avigya.userbase.records.RegisteredUser;
import com.avigya.userbase.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersService {

    private final UsersRepository repository;
    public String createUser(RegisteredUser userRecord) {

        Users user=Users.builder()
                .userId(userRecord.userID())
                .email(userRecord.email())
                .phone(userRecord.phone()).build();
        repository.save(user);
        log.info("User created");
        return "User created";
    }
}