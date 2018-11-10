package com.bulls_and_cows.controllers;

import com.bulls_and_cows.business_logic.BullsAndCowsEngine;
import com.bulls_and_cows.business_logic.helpers.GameHelper;
import com.bulls_and_cows.models.Game;
import com.bulls_and_cows.models.StepGame;
import com.bulls_and_cows.models.User;
import com.bulls_and_cows.repositories.GameRepository;
import com.bulls_and_cows.repositories.StepGameRepository;
import com.bulls_and_cows.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.bulls_and_cows.business_logic.consts.GameConstants.GAME_STATE;
import static com.bulls_and_cows.business_logic.consts.GameConstants.SIZE_QUESTION;

@RequestMapping(value = "user/step")
@Controller
public class StepGameController {
    private final StepGameRepository stepGameRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final BullsAndCowsEngine bullsAndCowsEngine;

    @Autowired
    public StepGameController(StepGameRepository stepGameRepository, UserRepository userRepository,
                              GameRepository gameRepository, BullsAndCowsEngine bullsAndCowsEngine) {
        this.stepGameRepository = stepGameRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.bullsAndCowsEngine = bullsAndCowsEngine;
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public ResponseEntity postNewStep(Authentication authentication, @ModelAttribute("stepGame") StepGame stepGame) {
        User user = this.userRepository.findOne(authentication.getName());
        Optional<Game> notCompletedGame = GameHelper.findNotCompletedGame(user);
        if (notCompletedGame.isPresent() && stepGame.getAnswer().length() == SIZE_QUESTION && this.bullsAndCowsEngine.validate(stepGame.getAnswer())) {
            Game game = notCompletedGame.get();
            stepGame.setGame(game)
                    .setDateTime(LocalDateTime.now());
            this.stepGameRepository.save(stepGame);

            String result = this.bullsAndCowsEngine.getResult(game, stepGame);
            ResponseEntity<String> responseEntity = new ResponseEntity<>(result, HttpStatus.OK);

            if (GAME_STATE.equals(result)) {
                this.userRepository.save(
                        user.setRating(this.gameRepository.countGamesByUser(user) /
                                (float) this.stepGameRepository.countStepGameByUser(user)));
                game.setGameEndTime(LocalDateTime.now());
                this.gameRepository.save(game);
            }
            return responseEntity;
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
