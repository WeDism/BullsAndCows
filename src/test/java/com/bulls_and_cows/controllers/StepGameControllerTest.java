package com.bulls_and_cows.controllers;

import com.bulls_and_cows.business_logic.helpers.GameHelper;
import com.bulls_and_cows.models.Game;
import com.bulls_and_cows.models.StepGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;
import java.util.Optional;

import static com.bulls_and_cows.business_logic.BullsAndCowsMatcherTestData.*;
import static com.bulls_and_cows.business_logic.consts.GameConstants.GAME_STATE;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StepGameControllerTest extends ControllerInit {

    private final String PARAM_ANSWER = "answer";
    private final String PART_REQUEST_STEP_NEW = "/step/new";

    @Before
    public void setUp() {
        super.setUp();

        this.initMockSession(this.userIvan);
    }

    @Test
    public void postNewStep() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = post(PART_REQUEST_STEP_NEW)
                .session(this.mockHttpSession)
                .param(PARAM_ANSWER, FIRST_ANSWER_3456);

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().is2xxSuccessful())
                .andExpect(content().string(FIRST_RESPONSE_1B1C));

        Optional<Game> notCompletedGame = GameHelper.findNotCompletedGame(this.userIvan);
        Assert.assertTrue(notCompletedGame.isPresent());

        List<StepGame> byGame = this.stepGameRepository.findByGame(notCompletedGame.get());
        Assert.assertThat(1, is(byGame.size()));

        requestBuilder = post(PART_REQUEST_STEP_NEW)
                .session(this.mockHttpSession)
                .param(PARAM_ANSWER, SECOND_ANSWER_1438);

        perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().is2xxSuccessful())
                .andExpect(content().string(SECOND_RESPONSE_2B2C));

        byGame = this.stepGameRepository.findByGame(notCompletedGame.get());
        Assert.assertThat(2, is(byGame.size()));

    }

    @Test
    public void postNewStepBadRequest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = post(PART_REQUEST_STEP_NEW)
                .session(this.mockHttpSession)
                .param(PARAM_ANSWER, SIXTH_BAD_ANSWER_3333);

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().isBadRequest());

        requestBuilder = post(PART_REQUEST_STEP_NEW)
                .session(this.mockHttpSession)
                .param(PARAM_ANSWER, SEVENTH_BAD_ANSWER_4435);

        perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().isBadRequest());

    }

    @Test
    public void postNewStepWithTrueQuestion() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = post(PART_REQUEST_STEP_NEW)
                .session(this.mockHttpSession)
                .param(PARAM_ANSWER, "8431");

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().is2xxSuccessful())
                .andExpect(content().string(GAME_STATE));

        this.userIvan = this.userRepository.findOne(this.userIvan.getNickname());
        Optional<Game> notCompletedGame = GameHelper.findNotCompletedGame(this.userIvan);
        Assert.assertFalse(notCompletedGame.isPresent());
    }

    @Test

    public void postNewStepWithoutNewGame() throws Exception {
        this.userIvan.getGames().remove(this.secondIvanGame);
        this.userRepository.save(this.userIvan);
        MockHttpServletRequestBuilder requestBuilder = post(PART_REQUEST_STEP_NEW)
                .session(this.mockHttpSession)
                .param(PARAM_ANSWER, "4567");

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().isBadRequest());
    }
}