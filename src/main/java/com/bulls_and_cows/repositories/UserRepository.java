package com.bulls_and_cows.repositories;

import com.bulls_and_cows.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

}
