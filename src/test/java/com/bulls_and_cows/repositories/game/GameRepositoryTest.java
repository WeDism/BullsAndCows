package com.bulls_and_cows.repositories.game;

import com.bulls_and_cows.repositories.DbInit;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class GameRepositoryTest extends DbInit {

    @Test
    public void createGame() {
        Assert.assertThat(firstIvanGame, is(this.gameRepository.findOne(firstIvanGame.getId())));
        Assert.assertEquals(firstIvanGame.getId(), this.gameRepository.findOne(firstIvanGame.getId()).getId());
    }
}
