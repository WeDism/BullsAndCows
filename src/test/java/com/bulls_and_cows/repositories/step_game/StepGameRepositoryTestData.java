package com.bulls_and_cows.repositories.step_game;

import com.bulls_and_cows.models.StepGame;

import java.time.LocalDateTime;

import static com.bulls_and_cows.repositories.game.GameRepositoryTestData.*;

public interface StepGameRepositoryTestData {
    LocalDateTime DATE_TIME = LocalDateTime.now();
    LocalDateTime DATE_TIME_FOR_FIRST_STEP = DATE_TIME.plusSeconds(3);
    LocalDateTime DATE_TIME_FOR_SECOND_STEP = DATE_TIME.plusSeconds(4);
    LocalDateTime DATE_TIME_FOR_THIRD_STEP = DATE_TIME.plusSeconds(7);
    LocalDateTime DATE_TIME_FOR_FOURTH_STEP = DATE_TIME.plusSeconds(13);
    LocalDateTime DATE_TIME_FOR_FIFTH_STEP = DATE_TIME.plusSeconds(21);

    StepGame FIRST_STEP_GAME_TEMPLATE =
            new StepGame()
                    .setGame(FIRST_IVAN_GAME_TEMPLATE)
                    .setAnswer("1234")
                    .setDateTime(DATE_TIME_FOR_FIRST_STEP);
    StepGame SECOND_STEP_GAME_TEMPLATE =
            new StepGame()
                    .setGame(FIRST_IVAN_GAME_TEMPLATE)
                    .setAnswer("1235")
                    .setDateTime(DATE_TIME_FOR_SECOND_STEP);
    StepGame THIRD_STEP_GAME_TEMPLATE =
            new StepGame()
                    .setGame(FIRST_IVAN_GAME_TEMPLATE)
                    .setAnswer("4587")
                    .setDateTime(DATE_TIME_FOR_THIRD_STEP);
    StepGame FOURTH_STEP_GAME_TEMPLATE =
            new StepGame()
                    .setGame(FIRST_IVAN_GAME_TEMPLATE)
                    .setAnswer("4821")
                    .setDateTime(DATE_TIME_FOR_FOURTH_STEP);
    StepGame FIFTH_STEP_GAME_TEMPLATE =
            new StepGame()
                    .setGame(FIRST_IVAN_GAME_TEMPLATE)
                    .setAnswer("4675")
                    .setDateTime(DATE_TIME_FOR_FIFTH_STEP);
}
