package com.teamdev.dropbox.services;

import com.teamdev.dropbox.loginobjects.AuthenticationToken;
import com.teamdev.dropbox.loginobjects.UserLoginInfo;

/**
 * @author Bogdan Kovalev.
 */

public interface UserAuthenticationService {

    AuthenticationToken login(UserLoginInfo loginInfo);

    boolean isTokenValid(AuthenticationToken token);
}
