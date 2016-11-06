package com.teamdev.dropbox;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Bogdan Kovalev.
 */
public class JwtTokenMalformedException extends AuthenticationException {

    public JwtTokenMalformedException(String msg) {
        super(msg);
    }
}
