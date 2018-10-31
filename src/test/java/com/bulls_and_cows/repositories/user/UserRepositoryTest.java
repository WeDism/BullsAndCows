package com.bulls_and_cows.repositories.user;

import com.bulls_and_cows.repositories.DbInit;
import org.junit.Assert;
import org.junit.Test;

import static com.bulls_and_cows.repositories.user.UserRepositoryTestData.USER_IVAN_TEMPLATE;
import static com.bulls_and_cows.repositories.user.UserRepositoryTestData.NICKNAME_IVAN;
import static org.hamcrest.CoreMatchers.is;

public class UserRepositoryTest extends DbInit {
    @Test
    public void createUser() {
        Assert.assertThat(USER_IVAN_TEMPLATE, is(this.userRepository.findOne(NICKNAME_IVAN)));
        Assert.assertEquals(USER_IVAN_TEMPLATE.getNickname(), this.userRepository.findOne(NICKNAME_IVAN).getNickname());
        Assert.assertEquals(USER_IVAN_TEMPLATE.getNickname(), this.userRepository.findOne(NICKNAME_IVAN).getNickname());
    }
}
