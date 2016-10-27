package com.teamdev.dropbox.controller;

import com.teamdev.dropbox.dto.UserDTO;
import com.teamdev.dropbox.serviceobjects.UserRegistrationInfo;
import com.teamdev.dropbox.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bogdan Kovalev.
 */

@RestController
@RequestMapping("/users")
public class UserServiceController {

    @Autowired
    UserRegistrationService userRegistrationService;

    @PostMapping
    public UserDTO register(@RequestBody UserRegistrationInfo registrationData) throws Exception {
        return this.userRegistrationService.register(registrationData);
    }
}
