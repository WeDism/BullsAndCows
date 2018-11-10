package com.bulls_and_cows.business_logic;

import com.bulls_and_cows.business_logic.consts.GameConstants;
import com.bulls_and_cows.repositories.DbInit;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static com.bulls_and_cows.business_logic.BullsAndCowsMatcherTestData.*;
import static com.bulls_and_cows.repositories.game.GameRepositoryTestData.FIRST_IVAN_GAME_TEMPLATE;
import static com.bulls_and_cows.repositories.step_game.StepGameRepositoryTestData.FIFTH_STEP_GAME_TEMPLATE;
import static org.hamcrest.CoreMatchers.is;

public class BullsAndCowsEngineTest extends DbInit {
    @Autowired
    private BullsAndCowsEngine bullsAndCowsEngine;

    @Test
    public void matchBullsAndCows() {
        Assert.assertThat(FIRST_RESPONSE_1B1C, is(this.bullsAndCowsEngine.matchBullsAndCows(GAME_RIDDLE, FIRST_ANSWER_3456)));

        Assert.assertThat(SECOND_RESPONSE_2B2C, is(this.bullsAndCowsEngine.matchBullsAndCows(GAME_RIDDLE, SECOND_ANSWER_1438)));

        Assert.assertThat(THIRD_RESPONSE_1B3C, is(this.bullsAndCowsEngine.matchBullsAndCows(GAME_RIDDLE, THIRD_ANSWER_1834)));

        Assert.assertThat(FOURTH_RESPONSE_0B4C, is(this.bullsAndCowsEngine.matchBullsAndCows(GAME_RIDDLE, FOURTH_ANSWER_1348)));

        Assert.assertThat(this.bullsAndCowsEngine.matchBullsAndCows(GAME_RIDDLE, FIFTH_ANSWER_8431), is(FIFTH_RESPONSE_1B0C));
    }

    @Test
    public void getResult() {
        Assert.assertThat(GameConstants.GAME_STATE, is(this.bullsAndCowsEngine.getResult(FIRST_IVAN_GAME_TEMPLATE, FIFTH_STEP_GAME_TEMPLATE)));
    }

    @Test(expected = RuntimeException.class)
    public void checkLengthStringsWithRE() {
        this.bullsAndCowsEngine.matchBullsAndCows("1234", "456");
    }

    @Test()
    public void checkLengthStringsWithoutRE() {
        this.bullsAndCowsEngine.matchBullsAndCows("1234", "4561");
    }

}