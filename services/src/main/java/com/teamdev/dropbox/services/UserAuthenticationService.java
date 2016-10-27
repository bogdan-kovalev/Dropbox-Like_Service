package com.teamdev.dropbox.services;

import com.teamdev.dropbox.serviceobjects.AuthenticationToken;
import com.teamdev.dropbox.serviceobjects.UserLoginInfo;

/**
 * @author Bogdan Kovalev.
 */

public interface UserAuthenticationService {

    AuthenticationToken login(UserLoginInfo loginInfo) throws Exception;

    boolean isTokenValid(AuthenticationToken token) throws Exception;
}
