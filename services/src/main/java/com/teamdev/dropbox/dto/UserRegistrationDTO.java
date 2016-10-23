package com.teamdev.dropbox.dto;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Bogdan Kovalev.
 */

@RequiredArgsConstructor
@ToString
public class UserRegistrationDTO {

    public final String name;
    public final String email;
    public final String password;
}
