package com.bulls_and_cows.business_logic;

import com.bulls_and_cows.models.Game;
import com.bulls_and_cows.models.StepGame;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.bulls_and_cows.business_logic.consts.GameConstants.GAME_STATE;
import static com.bulls_and_cows.business_logic.consts.GameConstants.GAME_SYMBOLS;

@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
public final class BullsAndCowsEngine {

    private BullsAndCowsEngine() {
    }

    public String generate() {
        String value;
        do {
            value = String.valueOf(new Random().nextInt(9999));
        }
        while (!this.validate(value));
        return value.length() == 3 ? "0" + value : value;
    }

    public boolean validate(String string) {
        if (string.length() < 3) return false;

        boolean validate = true;
        for (int i = 0; i < string.length(); i++) {
            char[] chars = string.toCharArray();
            int countMatches = StringUtils.countMatches(string, chars[i]);
            if (countMatches > 1 || !GAME_SYMBOLS.contains(String.valueOf(chars[i]))) validate = false;
        }
        return validate;
    }

    private Map<Integer, Character> countBulls(String riddle, String answer) {
        this.checkLengthStrings(riddle, answer);
        Map<Integer, Character> countBulls = new HashMap<>(4);
        for (int i = 0; i < riddle.length(); i++) {
            if (riddle.toCharArray()[i] == answer.toCharArray()[i]) countBulls.put(i, answer.toCharArray()[i]);
        }
        return countBulls;
    }

    private void checkLengthStrings(String riddle, String answer) {
        if (riddle.length() != answer.length())
            throw new RuntimeException(String.format("Length riddle is {%s} and length answer is {%s} strings should be equals",
                    riddle.length(), answer.length()));
    }

    private int countCows(String riddle, String answer, Map<Integer, Character> countBulls) {
        this.checkLengthStrings(riddle, answer);
        int countCows = 0;
        for (int i = 0; i < riddle.length(); i++) {
            if (!countBulls.containsKey(i)) countCows += StringUtils.countMatches(riddle, answer.toCharArray()[i]);
        }
        return countCows;
    }

    public String matchBullsAndCows(String riddle, String answer) {
        Map<Integer, Character> countBulls = this.countBulls(riddle, answer);
        return this.countBulls(riddle, answer).size() + "B" +
                this.countCows(riddle, answer, countBulls) + "C";
    }

    public String getResult(Game game, StepGame stepGame) {
        String result = GAME_STATE;
        if (!game.getRiddle().equals(stepGame.getAnswer())) {
            String answer = stepGame.getAnswer();
            String riddle = game.getRiddle();
            result = this.matchBullsAndCows(riddle, answer);
        }
        return result;
    }
}
