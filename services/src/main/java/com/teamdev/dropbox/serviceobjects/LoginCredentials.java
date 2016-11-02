package com.teamdev.dropbox.serviceobjects;

import com.teamdev.dropbox.tinytypes.Email;
import com.teamdev.dropbox.tinytypes.Password;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Bogdan Kovalev.
 */
@ToString
@RequiredArgsConstructor
public class LoginCredentials {
    public final Email email;
    public final Password password;
}
