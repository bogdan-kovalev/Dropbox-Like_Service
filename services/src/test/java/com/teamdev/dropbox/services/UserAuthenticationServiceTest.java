package com.teamdev.dropbox.services;

import com.teamdev.dropbox.repository.UserRepository;
import com.teamdev.dropbox.serviceobjects.AuthenticationToken;
import com.teamdev.dropbox.serviceobjects.LoginCredentials;
import com.teamdev.dropbox.serviceobjects.UserRegistrationInfo;
import com.teamdev.dropbox.tinytypes.Email;
import com.teamdev.dropbox.tinytypes.Password;
import com.teamdev.dropbox.tinytypes.UserName;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
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
    public void checkItGeneratesSameTokenForSameUserCredentials() throws Exception {
        final UserRegistrationInfo registrationData = new UserRegistrationInfo(new UserName("John"), new Email("JohnSnow@winterfell.com"), new Password("qwerty"));
        userRegistrationService.register(registrationData);

        final AuthenticationToken token1 = userAuthenticationService.login(
                new LoginCredentials(registrationData.email, registrationData.password));

        final AuthenticationToken token2 = userAuthenticationService.login(
                new LoginCredentials(registrationData.email, registrationData.password));

        assertThat(token1.token, equalTo(token2.token));
    }

    @Test
    public void checkItGeneratesDifferentTokensForDifferentUserCredentials() throws Exception {
        final UserRegistrationInfo registrationData1 = new UserRegistrationInfo(new UserName("John"), new Email("JohnSnow@winterfell.com"), new Password("qwerty"));
        final UserRegistrationInfo registrationData2 = new UserRegistrationInfo(new UserName("John"), new Email("John_Snow@winterfell.com"), new Password("qwerty"));

        userRegistrationService.register(registrationData1);
        userRegistrationService.register(registrationData2);

        final AuthenticationToken token1 = userAuthenticationService.login(
                new LoginCredentials(registrationData1.email, registrationData1.password));

        final AuthenticationToken token2 = userAuthenticationService.login(
                new LoginCredentials(registrationData2.email, registrationData2.password));

        assertThat(token1.token, is(not(equalTo(token2.token))));
    }

    @Test
    public void checkSuccessfulLogin() throws Exception {
        final UserRegistrationInfo registrationData = new UserRegistrationInfo(new UserName("John"), new Email("JohnSnow@winterfell.com"), new Password("qwerty"));
        userRegistrationService.register(registrationData);

        final AuthenticationToken token = userAuthenticationService.login(
                new LoginCredentials(registrationData.email, registrationData.password));

        assertThat(userAuthenticationService.retrieveUser(token), is(notNullValue()));
    }

    @Test(expected = Exception.class)
    public void checkFailedLogin() throws Exception {
        final UserRegistrationInfo registrationData = new UserRegistrationInfo(new UserName("John"), new Email("JohnSnow@winterfell.com"), new Password("qwerty"));
        userRegistrationService.register(registrationData);

        userAuthenticationService.login(new LoginCredentials(registrationData.email, new Password("anotherpassword")));
    }
}