package com.bulls_and_cows.controllers;

import com.bulls_and_cows.models.User;
import com.bulls_and_cows.repositories.DbInit;
import org.junit.Ignore;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.bulls_and_cows.controllers.ControllerInitTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@Ignore
public class ControllerInit extends DbInit {
    @Autowired
    protected MockHttpSession mockHttpSession;
    @Mock
    protected Authentication authentication;
    @Autowired
    protected WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;

    @Override
    public void setUp() {
        super.setUp();
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .dispatchOptions(true).build();
    }

    @SuppressWarnings("NullableProblems")
    protected void initMockSession(User user) {
        ResultActions auth = null;
        try {
            auth = this.mockMvc.perform(post(LOGIN_PATH)
                    .param(NICKNAME, user.getNickname())
                    .param(PASSWORD, user.getPassword()));
            auth.andExpect(redirectedUrl(USER_PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }

        @SuppressWarnings("ConstantConditions")
        MvcResult result = auth.andReturn();
        this.mockHttpSession = (MockHttpSession) result.getRequest().getSession();
    }
}
