package com.bulls_and_cows.business_logic.helpers;

import com.bulls_and_cows.business_logic.BullsAndCowsEngine;
import com.bulls_and_cows.models.Game;
import com.bulls_and_cows.models.StepGame;
import com.bulls_and_cows.models.User;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface GameHelper {

    static Optional<Game> findNotCompletedGame(List<Game> games) {
        return games.stream()
                .filter(game -> game.getGameEndTime() == null)
                .findFirst();
    }

    static Optional<Game> findNotCompletedGame(User user) {
        return GameHelper.findNotCompletedGame(user.getGames());
    }

    static List<ImmutablePair<StepGame, String>> getImmutablePairStepGameAndResult(List<StepGame> gameSteps, BullsAndCowsEngine bullsAndCowsEngine) {
        return gameSteps.stream().map
                (stepGame -> ImmutablePair.of(stepGame, bullsAndCowsEngine.getResult(stepGame.getGame(), stepGame)))
                .collect(Collectors.toList());
    }
}
