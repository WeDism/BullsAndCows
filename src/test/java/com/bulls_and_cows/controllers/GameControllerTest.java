package com.bulls_and_cows.controllers;

import com.bulls_and_cows.business_logic.helpers.GameHelper;
import com.bulls_and_cows.models.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GameControllerTest extends ControllerInit {
    @Before
    public void setUp() {
        super.setUp();

        this.initMockSession(this.userIvan);
    }

    @Test
    public void getNewGame() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/user/game/new")
                .session(this.mockHttpSession);

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/game/resume"));

        Assert.assertThat(2, is(this.userRepository.findOne(this.userIvan.getNickname()).getGames().size()));

    }

    @Test
    public void getResumeGame() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/user/game/resume")
                .session(this.mockHttpSession);

        Optional<Game> notCompletedGame = GameHelper.findNotCompletedGame(this.userIvan.getGames());
        Assert.assertTrue(notCompletedGame.isPresent());

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().is2xxSuccessful())
                .andExpect(view().name("game"))
                .andExpect(model().attribute("gameSteps",
                        GameHelper.getImmutablePairStepGameAndResult(
                                this.stepGameRepository.findByGame(notCompletedGame.get()),
                                this.bullsAndCowsEngine)));


        notCompletedGame = GameHelper.findNotCompletedGame(this.userIvan.getGames());
        Assert.assertTrue(notCompletedGame.isPresent());
        notCompletedGame.ifPresent(game -> userIvan.getGames().remove(game));
        this.userRepository.save(this.userIvan);

        perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user"))
                .andExpect(model().attributeDoesNotExist("gameSteps"));
    }
}