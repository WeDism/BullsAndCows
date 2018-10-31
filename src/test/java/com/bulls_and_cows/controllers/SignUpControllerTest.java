package com.bulls_and_cows.controllers;

import com.bulls_and_cows.models.User;
import org.apache.commons.lang3.SerializationUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.bulls_and_cows.repositories.user.UserRepositoryTestData.USER_IVAN_TEMPLATE;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SignUpControllerTest extends ControllerInit {

    @Test
    public void postUser() throws Exception {
        User userClone = SerializationUtils.clone(USER_IVAN_TEMPLATE)
                .setNickname(USER_IVAN_TEMPLATE.getNickname() + "_CLONE")
                .setEmail("ivan-clone@ivan-clone.com");
        MultiValueMap<String, String> valueMap = this.getValueMap(userClone);

        MockHttpServletRequestBuilder requestBuilder = post("/sign_up")
                .params(valueMap);
        ResultActions resultNew = this.mockMvc.perform(requestBuilder);
        resultNew.andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("stateRegistration", Boolean.TRUE));

        User user = this.userRepository.findOne(userClone.getNickname());
        Assert.assertThat(user.getNickname(), is(userClone.getNickname()));
    }

    @NotNull
    private LinkedMultiValueMap<String, String> getValueMap(User user) {
        return new LinkedMultiValueMap<String, String>(3) {{
            add("email", user.getEmail());
            add("nickname", user.getNickname());
            add("password", user.getPassword());
        }};
    }
}