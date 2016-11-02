package com.teamdev.dropbox.requestbody;

import lombok.RequiredArgsConstructor;

/**
 * @author Bogdan Kovalev.
 */
@RequiredArgsConstructor
public class LoginRequestBody {
    public final String email;
    public final String password;
}
