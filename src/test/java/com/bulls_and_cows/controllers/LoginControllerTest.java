package com.bulls_and_cows.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class LoginControllerTest extends ControllerInit {
    @Autowired
    LoginController loginController;

    @Test
    public void setServletConfig() {
        MockServletContext servletContext = new MockServletContext();
        servletContext.setContextPath("");
        MockServletConfig servletConfig = new MockServletConfig(servletContext);
        this.loginController.setServletConfig(servletConfig);
        Assert.assertThat(servletConfig.getServletContext().getAttribute("bootstrap.css"), is("/web_resources/css/bootstrap.min.css"));

        servletContext.setContextPath("/bulls_and_cows");
        servletConfig = new MockServletConfig(servletContext);
        this.loginController.setServletConfig(servletConfig);
        Assert.assertThat(servletConfig.getServletContext().getAttribute("bootstrap.css"), is("/bulls_and_cows" + "/web_resources/css/bootstrap.css"));
    }

    @Test
    public void getLoginView() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/login")
                .session(this.mockHttpSession);


        ResultActions perform = this.mockMvc.perform(requestBuilder);
        perform.andExpect(status().is2xxSuccessful())
                .andExpect(view().name("login"));

    }
}