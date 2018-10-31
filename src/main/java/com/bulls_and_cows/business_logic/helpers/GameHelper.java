package com.bulls_and_cows.business_logic.helpers;

import com.bulls_and_cows.models.Game;
import com.bulls_and_cows.models.User;

import java.util.List;
import java.util.Optional;

public interface GameHelper {

    static Optional<Game> findNotCompletedGame(List<Game> games) {
        return games.stream()
                .filter(game -> game.getGameEndTime() == null)
                .findFirst();
    }
    static Optional<Game> findNotCompletedGame(User user) {
        return GameHelper.findNotCompletedGame(user.getGames());
    }

}
