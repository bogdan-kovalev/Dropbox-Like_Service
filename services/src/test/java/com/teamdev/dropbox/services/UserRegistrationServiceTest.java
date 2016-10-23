package com.teamdev.dropbox.services;

import com.teamdev.dropbox.dto.UserDTO;
import com.teamdev.dropbox.dto.UserRegistrationDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Bogdan Kovalev.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class UserRegistrationServiceTest {

    @Autowired
    UserRegistrationService registrationService;

    private final UserRegistrationDTO validUserRegistrationDTO =
            new UserRegistrationDTO("John", "john@mail.com", "abc123");

    @Test
    public void registerNewUser() {
        final UserDTO userDTO =
                registrationService.register(validUserRegistrationDTO);

        assertThat(userDTO, is(instanceOf(UserDTO.class)));
        assertThat(userDTO.id, is(notNullValue()));
        assertThat(userDTO.name, is(validUserRegistrationDTO.name));
        assertThat(userDTO.email, is(validUserRegistrationDTO.email));
    }
}