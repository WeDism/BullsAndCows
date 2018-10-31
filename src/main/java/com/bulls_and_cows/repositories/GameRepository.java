package com.bulls_and_cows.repositories;

import com.bulls_and_cows.models.Game;
import com.bulls_and_cows.models.User;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface GameRepository extends CrudRepository<Game, UUID> {
    int countGamesByUser(User user);

    boolean existsGameByUserAndGameEndTimeIsNull(User user);
}
