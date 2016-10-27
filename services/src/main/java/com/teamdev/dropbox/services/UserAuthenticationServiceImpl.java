package com.teamdev.dropbox.services;

import com.teamdev.dropbox.loginobjects.AuthenticationToken;
import com.teamdev.dropbox.loginobjects.UserLoginInfo;
import org.springframework.stereotype.Service;

/**
 * @author Bogdan Kovalev.
 */
@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    @Override
    public AuthenticationToken login(UserLoginInfo loginInfo) {
        return null;
    }

    @Override
    public boolean isTokenValid(AuthenticationToken token) {
        return false;
    }
}
