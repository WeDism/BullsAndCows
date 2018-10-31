package com.bulls_and_cows.controllers;

import com.bulls_and_cows.business_logic.helpers.GameHelper;
import com.bulls_and_cows.models.Game;
import com.bulls_and_cows.models.User;
import com.bulls_and_cows.repositories.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

@RequestMapping(value = "user")
@Controller
public class UserController {
    private static final Logger LOG = getLogger(UserController.class);
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getUserView() {
        return "user";
    }

    @RequestMapping(value = "history", method = RequestMethod.GET)
    public String getHistoryGames(Authentication authentication, Model model) {
        User user = this.userRepository.findOne(authentication.getName());
        Optional<Game> notCompletedGame = GameHelper.findNotCompletedGame(user);
        model.addAttribute(user);
        model.addAttribute(user.getGames());
        model.addAttribute(notCompletedGame);
        return "history";
    }

}
