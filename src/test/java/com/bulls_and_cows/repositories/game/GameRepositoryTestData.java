package com.bulls_and_cows.repositories.game;

import com.bulls_and_cows.models.Game;
import com.bulls_and_cows.repositories.user.UserRepositoryTestData;

import java.time.LocalDateTime;

public interface GameRepositoryTestData {
    String FIRST_IVAN_GAME_RIDDLE = "4675";
    String SECOND_IVAN_GAME_RIDDLE = "8431";

    Game FIRST_IVAN_GAME_TEMPLATE = new Game()
            .setGameStartTime(LocalDateTime.now())
            .setGameEndTime(LocalDateTime.now().plusMinutes(5L))
            .setRiddle(FIRST_IVAN_GAME_RIDDLE)
            .setUser(UserRepositoryTestData.USER_IVAN_TEMPLATE);
    Game SECOND_IVAN_GAME_TEMPLATE = new Game()
            .setGameStartTime(LocalDateTime.now().plusMinutes(10L))
            .setRiddle(SECOND_IVAN_GAME_RIDDLE)
            .setUser(UserRepositoryTestData.USER_IVAN_TEMPLATE);
}
