package com.bulls_and_cows.repositories;

import com.bulls_and_cows.models.Game;
import com.bulls_and_cows.models.StepGame;
import com.bulls_and_cows.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface StepGameRepository extends CrudRepository<StepGame, UUID> {

    List<StepGame> findByGame(Game game);

    @Query("select count(sg) from StepGame sg join Game g on sg.game=g.id where g.user = :user")
    int countStepGameByUser(@Param("user") User user);
}
