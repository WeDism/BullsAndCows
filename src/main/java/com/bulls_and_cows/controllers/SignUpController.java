package com.bulls_and_cows.controllers;

import com.bulls_and_cows.models.User;
import com.bulls_and_cows.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.bulls_and_cows.models.User.Role.*;
import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class SignUpController {
    private static final Logger LOG = getLogger(SignUpController.class);
    private final UserRepository userRepository;

    @Autowired
    public SignUpController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "sign_up", method = RequestMethod.POST)
    public String postUser(@ModelAttribute("user") @Valid User user, BindingResult result, RedirectAttributes redirectAttributes) {
        boolean existsByNickname = this.userRepository.existsByNickname(user.getNickname());
        boolean existsByEmail = this.userRepository.existsByEmail(user.getEmail());
        if (!result.hasErrors() && !existsByNickname && !existsByEmail) {
            user.setRole(USER);
            this.userRepository.save(user);
            redirectAttributes.addFlashAttribute("stateRegistration", Boolean.TRUE);
        } else {
            List<ImmutablePair> errors = result.getFieldErrors().stream()
                    .map(e -> ImmutablePair.of(StringUtils.capitalize(e.getField()), e.getDefaultMessage()))
                    .collect(Collectors.toList());

            if (existsByNickname) errors.add(ImmutablePair.of("Nickname", "This nickname already used"));
            if (existsByEmail) errors.add(ImmutablePair.of("Email", "This email already used"));

            redirectAttributes.addFlashAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("stateRegistration", Boolean.FALSE);
        }
        return "redirect:/";
    }

}
