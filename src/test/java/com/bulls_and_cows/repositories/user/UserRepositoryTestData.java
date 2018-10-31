package com.bulls_and_cows.repositories.user;

import com.bulls_and_cows.models.User;

import static com.bulls_and_cows.models.User.Role.USER;

public interface UserRepositoryTestData {
    String NICKNAME_IVAN = "Ivan";

    User USER_IVAN_TEMPLATE = new User()
            .setNickname(NICKNAME_IVAN)
            .setPassword("qwerty")
            .setRole(USER)
            .setEmail("USER_IVAN_TEMPLATE@mail.com");

}
