package com.avigya.userbase.records;

import java.util.UUID;

public record RegisteredUser(UUID userID,String email,String phone) {
}
