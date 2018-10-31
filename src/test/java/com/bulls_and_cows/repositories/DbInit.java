package com.bulls_and_cows.repositories;

import com.bulls_and_cows.models.Game;
import com.bulls_and_cows.models.StepGame;
import com.bulls_and_cows.models.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.bulls_and_cows.repositories.game.GameRepositoryTestData.FIRST_IVAN_GAME_TEMPLATE;
import static com.bulls_and_cows.repositories.game.GameRepositoryTestData.SECOND_IVAN_GAME_TEMPLATE;
import static com.bulls_and_cows.repositories.step_game.StepGameRepositoryTestData.*;
import static com.bulls_and_cows.repositories.user.UserRepositoryTestData.USER_IVAN_TEMPLATE;

@Ignore
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-context.xml")
public class DbInit {
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected GameRepository gameRepository;
    @Autowired
    protected StepGameRepository stepGameRepository;

    protected User userIvan;
    protected Game firstIvanGame;
    protected Game secondIvanGame;
    protected StepGame firstStepGame;
    protected StepGame secondStepGame;
    protected StepGame thirdStepGame;
    protected StepGame fourthStepGame;
    protected StepGame fifthStepGame;

    @Before
    public void setUp() {
        this.userRepository.save(USER_IVAN_TEMPLATE);
        this.firstIvanGame = this.gameRepository.save(FIRST_IVAN_GAME_TEMPLATE.setId(null));

        this.firstStepGame = this.stepGameRepository.save(FIRST_STEP_GAME_TEMPLATE.setId(null));
        this.secondStepGame = this.stepGameRepository.save(SECOND_STEP_GAME_TEMPLATE.setId(null));
        this.thirdStepGame = this.stepGameRepository.save(THIRD_STEP_GAME_TEMPLATE.setId(null));
        this.fourthStepGame = this.stepGameRepository.save(FOURTH_STEP_GAME_TEMPLATE.setId(null));
        this.fifthStepGame = this.stepGameRepository.save(FIFTH_STEP_GAME_TEMPLATE.setId(null));

        this.firstIvanGame = this.gameRepository.findOne(firstIvanGame.getId());


        this.secondIvanGame = this.gameRepository.save(SECOND_IVAN_GAME_TEMPLATE.setId(null));
        this.userIvan = this.userRepository.findOne(USER_IVAN_TEMPLATE.getNickname());
    }
}
