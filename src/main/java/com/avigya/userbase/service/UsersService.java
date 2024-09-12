package com.avigya.userbase.service;

import com.avigya.userbase.model.Users;
import com.avigya.userbase.records.RegisteredUser;
import com.avigya.userbase.records.UserVerify;
import com.avigya.userbase.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersService {

    private final RestTemplate template;
    private final UsersRepository repository;
    public UUID createUser(RegisteredUser userRecord) {

        Users user=Users.builder()
                .userId(userRecord.userID())
                .email(userRecord.email())
                .phone(userRecord.phone()).build();
        repository.save(user);
        log.info("User created");
        return user.getUserId();
    }

    public Users getRegisteredUser(RegisteredUser userRecord) {
        Optional<Users> user=repository.findById(userRecord.userID());
        if(!user.isEmpty())
        {
            return user.get();
        }
        else
        {
            throw new RuntimeException("User not found");
        }

    }

    public String saveUser(Users userRecord) {
        Optional<Users> user=repository.findById(userRecord.getUserId());
        if(!user.isEmpty())
        {
            user.get().setEmail(userRecord.getEmail());
            user.get().setPhone(userRecord.getPhone());
            user.get().setFirstName(userRecord.getFirstName());
            user.get().setLastName(userRecord.getLastName());
            user.get().setFlatName(userRecord.getFlatName());
            user.get().setFlatNo(userRecord.getFlatNo());
            repository.save(user.get());
            verifyUser(new UserVerify(user.get().getUserId(), "User +"+user.get().getFirstName()+" saved"));
            return "Data Saved Succesfully";
        }
        else
        {
            throw new RuntimeException("User not found");
        }


    }

    private void verifyUser(UserVerify userVerify) {

        String response=template.postForObject("https://registrationservice-production-0d3e.up.railway.app/verifyUser",new HttpEntity<>(userVerify),String.class);
        log.info(response);
    }
}