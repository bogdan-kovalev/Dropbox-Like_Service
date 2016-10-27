package com.teamdev.dropbox.serviceobjects;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Bogdan Kovalev.
 */
@ToString
@RequiredArgsConstructor
public class LoginCredentials {
    public final String email;
    public final String password;
}
