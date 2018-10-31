package com.bulls_and_cows.controllers;

import com.bulls_and_cows.business_logic.helpers.GameHelper;
import com.bulls_and_cows.models.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest extends ControllerInit {

    @Before
    public void setUp() {
        super.setUp();

        this.initMockSession(this.userIvan);
    }

    @Test
    public void getUserView() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/user")
                .session(this.mockHttpSession);

        Optional<Game> notCompletedGame = GameHelper.findNotCompletedGame(this.userIvan.getGames());
        Assert.assertTrue(notCompletedGame.isPresent());

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user"))
                .andExpect(model().attribute("classForATag", is("btn-primary")));

        notCompletedGame = GameHelper.findNotCompletedGame(this.userIvan.getGames());
        Assert.assertTrue(notCompletedGame.isPresent());
        notCompletedGame.ifPresent(game -> userIvan.getGames().remove(game));
        this.userRepository.save(this.userIvan);

        perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user"))
                .andExpect(model().attribute("classForATag", is("btn-info")))
                .andExpect(model().attribute("style", "style=\"pointer-events: none;\""));

    }

    @Test
    public void getHistoryGames() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/user/history")
                .session(this.mockHttpSession);

        Optional<Game> notCompletedGame = GameHelper.findNotCompletedGame(this.userIvan.getGames());
        Assert.assertTrue(notCompletedGame.isPresent());

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().is2xxSuccessful())
                .andExpect(view().name("history"))
                .andExpect(model().attribute("optional", notCompletedGame))
                .andExpect(model().attribute("gameList", hasItems(this.userIvan.getGames().toArray())))
                .andExpect(model().attribute("user", this.userIvan));

    }
}