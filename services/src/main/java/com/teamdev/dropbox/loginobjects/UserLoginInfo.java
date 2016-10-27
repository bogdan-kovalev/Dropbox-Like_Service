package com.teamdev.dropbox.loginobjects;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Bogdan Kovalev.
 */
@ToString
@RequiredArgsConstructor
public class UserLoginInfo {
    public final String email;
    public final String password;
}
