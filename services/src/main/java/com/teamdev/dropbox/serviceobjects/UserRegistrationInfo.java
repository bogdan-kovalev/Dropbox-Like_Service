package com.teamdev.dropbox.serviceobjects;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Bogdan Kovalev.
 */

@RequiredArgsConstructor
@ToString
public class UserRegistrationInfo {

    public final String name;
    public final String email;
    public final String password;
}
