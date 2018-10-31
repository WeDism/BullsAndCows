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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class GameControllerTest extends ControllerInit {
    @Before
    public void setUp() {
        super.setUp();

        this.initMockSession(this.userIvan);
    }

    @Test
    public void getNewGame() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/game/new")
                .session(this.mockHttpSession);

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().is2xxSuccessful())
                .andExpect(view().name("game"));
        Assert.assertThat(2, is(this.userRepository.findOne(this.userIvan.getNickname()).getGames().size()));

    }

    @Test
    public void getResumeGame() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/game/resume")
                .session(this.mockHttpSession);

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().is2xxSuccessful())
                .andExpect(view().name("game"))
                .andExpect(model().attribute("game", this.userIvan.getGames().stream()
                        .filter(game -> game.getGameEndTime() == null)
                        .findFirst().get()));

        Optional<Game> notCompletedGame = GameHelper.findNotCompletedGame(userIvan.getGames());
        Assert.assertTrue(notCompletedGame.isPresent());
        notCompletedGame.ifPresent(game -> userIvan.getGames().remove(game));
        this.userRepository.save(this.userIvan);

        perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user"))
                .andExpect(model().attributeDoesNotExist("game"));
    }
}