package com.teamdev.dropbox.requestbody;

import lombok.RequiredArgsConstructor;

/**
 * @author Bogdan Kovalev.
 */
@RequiredArgsConstructor
public class RegistrationRequestBody {
    public final String name;
    public final String email;
    public final String password;
}
