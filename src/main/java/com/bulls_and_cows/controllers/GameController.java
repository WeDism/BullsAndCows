package com.bulls_and_cows.controllers;

import com.bulls_and_cows.business_logic.BullsAndCowsEngine;
import com.bulls_and_cows.business_logic.helpers.GameHelper;
import com.bulls_and_cows.models.Game;
import com.bulls_and_cows.models.StepGame;
import com.bulls_and_cows.models.User;
import com.bulls_and_cows.repositories.StepGameRepository;
import com.bulls_and_cows.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "game")
@Controller
public class GameController {
    private static final Logger LOG = LoggerFactory.getLogger(GameController.class);
    private final UserRepository userRepository;
    private final BullsAndCowsEngine bullsAndCowsEngine;
    private final StepGameRepository stepGameRepository;

    @Autowired
    public GameController(UserRepository userRepository, BullsAndCowsEngine bullsAndCowsEngine, StepGameRepository stepGameRepository) {
        this.userRepository = userRepository;
        this.bullsAndCowsEngine = bullsAndCowsEngine;
        this.stepGameRepository = stepGameRepository;
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String getNewGame(Authentication authentication) {
        User user = this.userRepository.findOne(authentication.getName());
        Optional<Game> notCompletedGame = GameHelper.findNotCompletedGame(user.getGames());
        notCompletedGame.ifPresent(game -> user.getGames().remove(game));

        Game newGame = new Game()
                .setUser(user)
                .setGameStartTime(LocalDateTime.now())
                .setRiddle(this.bullsAndCowsEngine.generate());

        user.getGames().add(newGame);

        this.userRepository.save(user);
        return "redirect:/game/resume";
    }

    @RequestMapping(value = "resume", method = RequestMethod.GET)
    public ModelAndView getResumeGame(Authentication authentication) {
        User user = this.userRepository.findOne(authentication.getName());
        Optional<Game> first = GameHelper.findNotCompletedGame(user.getGames());

        if (first.isPresent()) {
            Game game = first.get();
            List<StepGame> gameSteps = this.stepGameRepository.findByGame(game);
            gameSteps.sort((o1, o2) -> {
                if (o1.getDateTime().isAfter(o2.getDateTime())) return 1;
                else if (o1.getDateTime().isEqual(o2.getDateTime())) return 0;
                return -1;
            });

            return new ModelAndView("game")
                    .addObject("gameSteps", GameHelper.getImmutablePairStepGameAndResult(gameSteps, this.bullsAndCowsEngine));
        }
        return new ModelAndView("redirect:/user");
    }

}
