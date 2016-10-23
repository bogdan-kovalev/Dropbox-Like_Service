package com.teamdev.dropbox.services;

import com.teamdev.dropbox.dto.UserDTO;
import com.teamdev.dropbox.dto.UserRegistrationDTO;

/**
 * @author Bogdan Kovalev.
 */
public interface UserRegistrationService {

    UserDTO register(UserRegistrationDTO registrationData);

}
