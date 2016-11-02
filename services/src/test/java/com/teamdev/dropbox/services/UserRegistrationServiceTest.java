package com.teamdev.dropbox.services;

import com.teamdev.dropbox.dto.UserDTO;
import com.teamdev.dropbox.repository.UserRepository;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Bogdan Kovalev.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserRegistrationServiceTest {

    @Autowired
    UserRegistrationService registrationService;

    @Autowired
    UserRepository userRepository;

    private final UserRegistrationInfo validUserRegistrationInfo =
            new UserRegistrationInfo(new UserName("John"), new Email("john@mail.com"), new Password("abc123"));

    @After
    public void clearRepository() {
        userRepository.deleteAll();
    }

    @Test
    public void registerNewUser() throws Exception {
        final UserDTO userDTO =
                registrationService.register(validUserRegistrationInfo);

        assertThat(userDTO, is(notNullValue()));
        assertThat(userDTO.id, is(notNullValue()));
        assertThat(userDTO.name, is(validUserRegistrationInfo.name.getValue()));
        assertThat(userDTO.email, is(validUserRegistrationInfo.email.getValue()));
    }
}