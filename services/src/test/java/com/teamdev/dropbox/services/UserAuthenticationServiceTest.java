package com.teamdev.dropbox.services;

import com.teamdev.dropbox.repository.UserRepository;
import com.teamdev.dropbox.serviceobjects.AuthenticationToken;
import com.teamdev.dropbox.serviceobjects.UserLoginInfo;
import com.teamdev.dropbox.serviceobjects.UserRegistrationInfo;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * @author Bogdan Kovalev.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserAuthenticationServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @After
    public void clearRepository() {
        userRepository.deleteAll();
    }

    @Test
    public void checkItGeneratesSameTokenForSameLoginInfo() throws Exception {
        final UserRegistrationInfo registrationData = new UserRegistrationInfo("John", "JohnSnow@winterfell.com", "qwerty");
        userRegistrationService.register(registrationData);

        final AuthenticationToken token1 = userAuthenticationService.login(
                new UserLoginInfo(registrationData.email, registrationData.password));

        final AuthenticationToken token2 = userAuthenticationService.login(
                new UserLoginInfo(registrationData.email, registrationData.password));

        assertThat(token1.token, equalTo(token2.token));
    }

    @Test
    public void checkItGeneratesDifferentTokensForDifferentLoginInfo() throws Exception {
        final UserRegistrationInfo registrationData1 = new UserRegistrationInfo("John", "JohnSnow@winterfell.com", "qwerty");
        final UserRegistrationInfo registrationData2 = new UserRegistrationInfo("John", "John_Snow@winterfell.com", "qwerty");

        userRegistrationService.register(registrationData1);
        userRegistrationService.register(registrationData2);

        final AuthenticationToken token1 = userAuthenticationService.login(
                new UserLoginInfo(registrationData1.email, registrationData1.password));

        final AuthenticationToken token2 = userAuthenticationService.login(
                new UserLoginInfo(registrationData2.email, registrationData2.password));

        assertThat(token1.token, is(not(equalTo(token2.token))));
    }

    @Test
    public void checkSuccessfulLogin() throws Exception {
        final UserRegistrationInfo registrationData = new UserRegistrationInfo("John", "JohnSnow@winterfell.com", "qwerty");
        userRegistrationService.register(registrationData);

        final AuthenticationToken token = userAuthenticationService.login(
                new UserLoginInfo(registrationData.email, registrationData.password));

        assertThat(userAuthenticationService.isTokenValid(token), is(true));
    }

    @Test
    public void checkFailedLogin() throws Exception {
        final UserRegistrationInfo registrationData = new UserRegistrationInfo("John", "JohnSnow@winterfell.com", "qwerty");
        userRegistrationService.register(registrationData);

        final AuthenticationToken token = userAuthenticationService.login(
                new UserLoginInfo(registrationData.email, "anotherpassword"));

        assertThat(token, is(nullValue()));
    }
}