package com.bulls_and_cows.business_logic;

import com.bulls_and_cows.business_logic.consts.GameConstants;
import com.bulls_and_cows.models.Game;
import com.bulls_and_cows.models.StepGame;
import org.apache.commons.lang3.StringUtils;

import static com.bulls_and_cows.business_logic.consts.GameConstants.GAME_STATE;
import static com.bulls_and_cows.business_logic.consts.GameConstants.GAME_SYMBOLS;

public interface BullsAndCowsEngine {
    String generate();

    String matchBullsAndCows(String riddle, String answer);

    default boolean validate(String string) {
        boolean validate = true;
        char[] chars = string.toCharArray();
        for (int i = 0; i < string.length(); i++) {
            int countMatches = StringUtils.countMatches(string, chars[i]);
            if (countMatches > 1 || !GAME_SYMBOLS.contains(String.valueOf(chars[i]))) validate = false;
        }
        return validate;
    }

    default String getResult(Game game, StepGame stepGame) {
        String result = GAME_STATE;
        if (!game.getRiddle().equals(stepGame.getAnswer())) {
            String answer = stepGame.getAnswer();
            String riddle = game.getRiddle();
            result = this.matchBullsAndCows(riddle, answer);
        }
        return result;
    }
}
