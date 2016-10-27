package com.teamdev.dropbox.services;

import com.teamdev.dropbox.dto.UserDTO;
import com.teamdev.dropbox.serviceobjects.UserRegistrationInfo;

/**
 * @author Bogdan Kovalev.
 */
public interface UserRegistrationService {

    UserDTO register(UserRegistrationInfo registrationInfo) throws Exception;

}
