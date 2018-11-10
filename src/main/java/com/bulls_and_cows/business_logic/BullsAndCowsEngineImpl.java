package com.bulls_and_cows.business_logic;

import com.bulls_and_cows.business_logic.consts.GameConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
public final class BullsAndCowsEngineImpl implements BullsAndCowsEngine {

    private BullsAndCowsEngineImpl() {
    }

    private String generateOneNumber(String number) {
        String oneNumber;
        do {
            oneNumber = String.valueOf(new Random().nextInt(10));
        } while (number.contains(oneNumber));
        return oneNumber;
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

    @Override
    public String generate() {
        String number;
        do {
            number = String.valueOf(new Random().nextInt(10_000));
        }
        while (!this.validate(number));
        return number.length() == 3 ? this.generateOneNumber(number) + number : number;
    }

    @Override
    public String matchBullsAndCows(String riddle, String answer) {
        Map<Integer, Character> countBulls = this.countBulls(riddle, answer);
        return this.countBulls(riddle, answer).size() + "B" +
                this.countCows(riddle, answer, countBulls) + "C";
    }

    @Override
    public boolean validate(String string) {
        if (string.length() < GameConstants.SIZE_QUESTION) return false;
        return BullsAndCowsEngine.super.validate(string);
    }

}
