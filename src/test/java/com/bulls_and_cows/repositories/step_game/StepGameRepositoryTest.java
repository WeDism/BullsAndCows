package com.bulls_and_cows.repositories.step_game;

import com.bulls_and_cows.repositories.DbInit;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class StepGameRepositoryTest extends DbInit {
    @Test
    public void createStepGame() {
        Assert.assertThat(firstStepGame, is(this.stepGameRepository.findOne(firstStepGame.getId())));
        Assert.assertThat(secondStepGame, is(this.stepGameRepository.findOne(secondStepGame.getId())));
        Assert.assertThat(thirdStepGame, is(this.stepGameRepository.findOne(thirdStepGame.getId())));
        Assert.assertThat(fourthStepGame, is(this.stepGameRepository.findOne(fourthStepGame.getId())));
        Assert.assertThat(fifthStepGame, is(this.stepGameRepository.findOne(fifthStepGame.getId())));

    }
}
