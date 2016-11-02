package com.teamdev.dropbox.controller;

import com.teamdev.dropbox.dto.UserDTO;
import com.teamdev.dropbox.requestbody.LoginRequestBody;
import com.teamdev.dropbox.requestbody.RegistrationRequestBody;
import com.teamdev.dropbox.serviceobjects.AuthenticationToken;
import com.teamdev.dropbox.serviceobjects.LoginCredentials;
import com.teamdev.dropbox.serviceobjects.UserRegistrationInfo;
import com.teamdev.dropbox.services.UserAuthenticationService;
import com.teamdev.dropbox.services.UserRegistrationService;
import com.teamdev.dropbox.tinytypes.Email;
import com.teamdev.dropbox.tinytypes.Password;
import com.teamdev.dropbox.tinytypes.UserName;
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

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @PostMapping
    public UserDTO register(@RequestBody RegistrationRequestBody body) throws Exception {
        return this.userRegistrationService.register(
                new UserRegistrationInfo(
                        new UserName(body.name), new Email(body.email), new Password(body.password)
                )
        );
    }

    @PostMapping
    @RequestMapping("/login")
    public AuthenticationToken login(@RequestBody LoginRequestBody body) throws Exception {
        return this.userAuthenticationService.login(
                new LoginCredentials(
                        new Email(body.email), new Password(body.password)
                )
        );
    }
}
