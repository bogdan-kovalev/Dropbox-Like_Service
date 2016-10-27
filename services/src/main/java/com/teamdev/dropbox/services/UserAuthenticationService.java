package com.teamdev.dropbox.services;

import com.teamdev.dropbox.serviceobjects.AuthenticationToken;
import com.teamdev.dropbox.serviceobjects.LoginCredentials;

/**
 * @author Bogdan Kovalev.
 */

public interface UserAuthenticationService {

    AuthenticationToken login(LoginCredentials loginCredentials) throws Exception;

    boolean isTokenValid(AuthenticationToken token) throws Exception;
}
